package com.ibm.p1.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import weibo4j.Account;
import weibo4j.Friendships;
import weibo4j.Timeline;
import weibo4j.http.HttpClient;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

import com.ibm.p1.entity.AcquireWeiboResult;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.*;
import com.ibm.p1.service.ManageService;
import com.opensymphony.xwork2.ActionContext;

public class AcquireWeibo extends HibernateDaoSupport {

	private String access_token = "2.00VyU5TDXkJlxB5db4a713cc2aYcgC";
	private String weibo_account = "IBM软件技术支持";
	private int counter = 3;// 如果请求微博失败，最多请求3次
	private int read_time = 30000;// 初始读取微博超时时间
	private String cach_dir = System.getProperty("user.dir").substring(0,
			System.getProperty("user.dir").lastIndexOf("\\"))
			+ "\\webapps\\EBP1\\";
	private int statuses_per_page=50;
	private int users_per_page=10;
	private Timer timer = new Timer();
	private boolean synFlag = false;

	public AcquireWeibo() {
		this.access_token = "2.00VyU5TDXkJlxB5db4a713cc2aYcgC";
		this.weibo_account = "IBM软件技术支持";
		this.cach_dir = System.getProperty("user.dir").substring(0,
				System.getProperty("user.dir").lastIndexOf("\\"))
				+ "\\webapps\\EBP1\\";
	}

	public AcquireWeibo(Parameter param) {
		this.access_token = param.getAccess_token();
		this.weibo_account = param.getWeibo_account();
		this.cach_dir = param.getRootPath() + param.getCache_dir_sina() + "\\";
	}

	// 有待修正
	public static boolean isZoombie(String uUid) {
		return false;
	}

	public void mkDir(File f) {

		if (f.getParentFile().exists()) {
			f.mkdir();
		} else {
			mkDir(f.getParentFile());
			f.mkdir();
		}
	}

	// 对外提供接口调用，获得微博数据包括个人信息，个人所发微博
	public AcquireWeiboResult getData(int us, int statuses) {

		Friendships fm = new Friendships();
		fm.client.setToken(access_token);

		Timeline tm = new Timeline();
		tm.client.setToken(access_token);

		int spages = (int) Math.ceil((double) statuses / statuses_per_page);
		if (spages > 200) {
			spages = 200;
		}
		Paging page = new Paging(1, statuses_per_page);

		int count = 1;// 控制读取第一页User的失败次数，如果失败，则继续读取3次，每次间隔时间递增，如果3次后失败，则停止读取。
		long sleepTime = 3600;
		int httpReadTime = read_time;

		AcquireWeiboResult res = new AcquireWeiboResult();
		res.setResult("成功获取微博数据!");
		long flowers = 0;
		long flowerStatuses = 0;

		File dirFile = new File(cach_dir);
		if (!dirFile.exists()) {
			this.mkDir(dirFile);
		}
		for (; count <= counter; count++) {
			try {
				UserWapper uw = fm.getFollowersByName(weibo_account, users_per_page, 0);
				long pages;
				if (us <= 0) {
					long totalNum = uw.getTotalNumber();
					pages = totalNum / users_per_page + 1;
				} else {
					pages = (int) Math.ceil((double) us / users_per_page);
				}

				List<User> users = uw.getUsers();

				boolean uf=false;
				for (int i = 0; i <= pages - 1; i++) {

					for (User u : users) {
						List<List<Status>> ss = new ArrayList<List<Status>>();
						StatusWapper status = null;
						uf=false;
						for (int j = 1; j <= spages; j++) {
							page.setPage(j);
							try {
								status = tm.getUserTimelineByName(u
										.getScreenName(), page, 0, 0);
								ss.add(status.getStatuses());
							} catch (WeiboException ex) {

								if (isExpiredToken(ex.getErrorCode())) {
									res.setStatuses(flowerStatuses);
									res.setUsers(flowers);
									res.setResult("token过期，请重新申请！");
									timer.cancel();
									return res;
								} else if (isRateLimit(ex.getErrorCode())) {
									// Date now=new Date();
									// now.getTime();
									Date now = new Date();
									long mod = now.getTime() % (60 * 60 * 1000);
									long min = mod / (60 * 1000);
									timer.schedule(new WakeUp(this),
											(60 - min+30) * 60 * 1000);
									synchronized (this) {
										while (!synFlag) {
											try {
												this.wait();
											} catch (InterruptedException e1) {
												e1.printStackTrace();
												res.setUsers(flowers);
												res.setStatuses(flowerStatuses);
												res.setResult(ex.getError());
												timer.cancel();
												return res;
											}
										}
										synFlag=false;
									}
									j--;
								}

								uf=true;
								break;
							}

						}
						if(uf){
							continue;
						}
						File f = new File(cach_dir + u.getScreenName() + ".txt");
						if (AcquireWeibo.isZoombie(u.getId())) {
							continue;
						}
						try {
							if (!f.exists()) {
								f.createNewFile();
							}
							FileOutputStream fos = new FileOutputStream(f);
							BufferedWriter bw = new BufferedWriter(
									new OutputStreamWriter(fos, "GBK"));
							bw.write(u.toString());
							bw.flush();
							bw.close();
							System.out.println("成功写入粉丝——" + u.getScreenName()
									+ "信息");

							f = new File(cach_dir + u.getScreenName()
									+ "_Weibo.txt");
							if (!f.exists()) {
								f.createNewFile();
							}

							fos = new FileOutputStream(f);
							bw = new BufferedWriter(new OutputStreamWriter(fos,
									"GBK"));

							for (int ii = 0; ii < ss.size(); ii++) {

								for (Status s : ss.get(ii)) {
									bw.write(s.toString());
									bw.write("\r\n");
									bw.flush();
								}
								flowerStatuses += statuses_per_page;
							}
							bw.close();
							System.out.println("成功写入粉丝——" + u.getScreenName()
									+ statuses + "条微博。");

							flowers++;

						} catch (IOException ex) {

							System.out.println("写入粉丝" + u.getScreenName()
									+ "失败！");
						}
					}

					// 如果连续3次读取粉丝失败，则退出程序
					long time = 3600;
					outer: for (int uc = 0; uc < counter; uc++) {
						try {
							uw = fm.getFollowersByName(weibo_account, users_per_page,
									(int) uw.getNextCursor());
							users = uw.getUsers();
							break outer;
						} catch (WeiboException e) {
							e.printStackTrace();
							if (e.getErrorCode() == 10010) {
								httpReadTime += read_time * 0.5;
								if (httpReadTime <= 1000 * 60 * 10) {
									fm.client = new HttpClient(150,
											httpReadTime, httpReadTime,
											1024 * 1024);
								}
							} else if (isExpiredToken(e.getErrorCode())) {
								res.setStatuses(flowerStatuses);
								res.setUsers(flowers);
								res.setResult("token过期，请重新申请！");
								timer.cancel();
								return res;
							}// 超过rate_limits，每用户每应用1000次/小时
							else if (isRateLimit(e.getErrorCode())) {
								// Date now=new Date();
								// now.getTime();
								Date now = new Date();
								long mod = now.getTime() % (60 * 60 * 1000);
								long min = mod / (60 * 1000);
								timer.schedule(new WakeUp(this),
										(60 - min+30) * 60 * 1000);
								synchronized (this) {
									while (!synFlag) {
										try {
											this.wait();
										} catch (InterruptedException e1) {
											e1.printStackTrace();
											res.setUsers(flowers);
											res.setStatuses(flowerStatuses);
											res.setResult(e.getError());
											timer.cancel();
											return res;
										}
									}
									synFlag=false;
								}
							}
							time += count * time;
							try {
								Thread.sleep(time);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
								res.setUsers(flowers);
								res.setStatuses(flowerStatuses);
								res.setResult(e.getError());
								timer.cancel();
								return res;
							}
						}
					}
				}
				break;
			} catch (WeiboException e) {
				e.printStackTrace();
				int error_code = e.getErrorCode();
				if (e.getErrorCode() == 10010) {
					httpReadTime += read_time * 0.5;
					if (httpReadTime <= 1000 * 60 * 10) {
						fm.client = new HttpClient(150, httpReadTime,
								httpReadTime, 1024 * 1024);
					}
				} else if (isExpiredToken(error_code)) {
					res.setStatuses(flowerStatuses);
					res.setUsers(flowers);
					res.setResult("token过期，请重新申请！");
					timer.cancel();
					return res;
				}
				sleepTime += count * sleepTime;
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					res.setUsers(flowers);
					res.setStatuses(flowerStatuses);
					res.setResult(e.getError());
					timer.cancel();
					return res;
				}
			}
		}
		res.setUsers(flowers);
		res.setStatuses(flowerStatuses);
		timer.cancel();
		return res;
	}


	public boolean isExpiredToken(int error_code) {
		boolean flag = false;
		switch (error_code) {
		case 21314:
			flag = true;
			break;
		case 21315:
			flag = true;
			break;
		case 21316:
			flag = true;
			break;
		case 21317:
			flag = true;
			break;
		case 21327:
			flag = true;
			break;
		case 21332:
			flag = true;
			break;
		case 21501:
			flag = true;
			break;
		case 10006:
			flag = true;
			break;
		default:
			flag = false;
		}
		return flag;
	}

	public boolean isRateLimit(int error_code) {
		boolean flag = false;
		switch (error_code) {
		case 10022:
			flag = true;
			break;
		case 10023:
			flag = true;
			break;
		case 10024:
			flag = true;
			break;
		default:
			flag = false;
		}
		return flag;

	}

	public void getRateLimitStatus() {
		Account account = new Account();
		account.client.setToken(access_token);

		try {
			System.out.println(account.getAccountRateLimitStatus());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

	public boolean isSynFlag() {
		return synFlag;
	}

	public void setSynFlag(boolean synFlag) {
		this.synFlag = synFlag;
	}
//	public static void main(String[] args) {
////
//		AcquireWeibo weibo = new AcquireWeibo();
////		
//		AcquireWeiboResult res = weibo.getData(10000, 100);
//	//	weibo.stop();
//		 /*
//		 System.out.println("]");
//		 System.out
//		 .println("Acquire Weibo Result===============================");
//		 System.out.println("succeed to acquire followers =" +
//		 res.getUsers());
//		 System.out.println("succeed to acquire followerStatuses ="
//		 + res.getStatuses());
//				
//		 System.out.println("After:rate_limit=[");
//		 weibo.getRateLimitStatus();
//		 System.out.println("]");*/
//		//		
//		//		
//		// Date now=new Date();
//		//		
//		// long mod=now.getTime()%(60*60*1000);
//		// long min=mod/(60*1000);
//		// long sec=mod%(60*1000)/1000;
//		// long mils=mod%1000;
//		// System.out.println("分:"+min);
//		// System.out.println("秒:"+sec);
//		// System.out.println("毫秒:"+mils);
//
//	}
}