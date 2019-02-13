package goshan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

public class ShowDetail extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowDetail() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String wid = request.getParameter("wid");
		String access_token = "2.00eQ319DXkJlxB65dcef0bb6eWA1IC";
		try {
			Users us = new Users();
			us.client.setToken(access_token);
			User user = us.showUserById(wid);
			request.setAttribute("user", user);
			Timeline tm = new Timeline();
			tm.client.setToken(access_token);
			
			String tools_path = this.getServletContext().getRealPath("/")+"WEB-INF\\tools";
			String cache_path = this.getServletContext().getRealPath("/")+"WEB-INF\\cache";
			String exe_pre = "cmd /c \"d: && cd "+tools_path;
			
			StatusWapper status = tm.getUserTimelineByUid(wid, new Paging(1, 100), 0, 0);
			
			File f = new File(cache_path+"\\Weibo_Cache\\"+wid+"_Weibo.txt");
			if (!f.exists()){
				f.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			for(Status s : status.getStatuses()){
				bw.write(s.toString());
				bw.write("\r\n");
				bw.flush();
			}
			bw.close();
			
			String cmd1 = exe_pre+" && ZombieFansFilter 0 "+cache_path+"\\Weibo_Cache "+wid+"\"";
			Process p = Runtime.getRuntime().exec(cmd1);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String res = br.readLine();
			res = res.substring(res.indexOf("\t")+1);
			br.close();
			request.setAttribute("ZombieType", res);
			
			String cmd2 = exe_pre+"\\FansPOI && FansPOI 0 "+cache_path+"\\Weibo_Cache "+wid+"\"";
			p = Runtime.getRuntime().exec(cmd2);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			res = br.readLine();
			
			Vector<String> words = new Vector<String>();
			f = new File(cache_path+"\\Weibo_Cache_res\\"+wid+"_Weight.txt");
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String line;
			int index = 0;
			while ((line = br.readLine()) != null && index < 50){
				words.add(line.substring(0, line.indexOf("/")));
				index ++;
			}
			request.setAttribute("KeyWords", words);
			
			this.getServletContext().getRequestDispatcher("/show_detail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
