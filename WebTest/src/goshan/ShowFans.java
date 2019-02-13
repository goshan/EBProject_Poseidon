package goshan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weibo4j.Friendships;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

public class ShowFans extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowFans() {
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
		//String access_token = ((AccessToken)request.getSession().getAttribute("access_token")).getAccessToken();
		String access_token = "2.00eQ319DXkJlxB65dcef0bb6eWA1IC";
		Friendships fm = new Friendships();
		fm.client.setToken(access_token);
		String screen_name ="邱晗_goshan";
		try {
			UserWapper users = fm.getFollowersByName(screen_name, 100, 0);
			for (User user : users.getUsers()){
				File f = new File(this.getServletContext().getRealPath("/")+"\\WEB-INF\\cache\\Weibo_Cache\\"+user.getId()+".txt");
				if (!f.exists()){
					f.createNewFile();
				}
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
				bw.write(user.toString()+"\r\n");
				bw.flush();
				bw.close();
			}
			request.setAttribute("users", users);
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/show_fans.jsp").forward(request, response);
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
