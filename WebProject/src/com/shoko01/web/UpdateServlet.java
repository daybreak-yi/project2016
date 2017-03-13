package com.shoko01.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update.jsp")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
		String username = request.getParameter("username");
		String memo = request.getParameter("memo");
		memo = memo.replaceAll("\r\n", "");
		memo = memo.replaceAll("\n", "");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String now = simpleDateFormat.format(new Date());
		
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "update USER_TBL set memo = ? , date = ? where username = ?";
		final String JDBC = "org.sqlite.JDBC";
		final String DATA_PATH = "jdbc:sqlite:/home/admin/sqlite3.db";
		
		try {
			Class.forName(JDBC);
			
			connection = DriverManager.getConnection(DATA_PATH);
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			
			ps.setString(1, memo);
			ps.setString(2, now);
			ps.setString(3, username);
			
			Thread.sleep(3000);
			ps.executeUpdate();
			Thread.sleep(5000);
			connection.commit();
			
		} catch(Exception e) {
			throw new ServletException(e);
			
		} finally {
			try {
				ps.close();
				ps = null;
				connection.close();
				connection = null;
			} catch(Exception e) {
				throw new ServletException(e);
			}
		}
		
		final String HEAD_TAG = ""
				+ "<html>"
				+ "<head>"
				+ "<title>内容更新</title>"
				+ "</head>";
		
		final String BODY_TAG = ""
				+ "<body>"
				+ "<h3>内容を更新しました</h3>"
				+ "<br />"
				+ "<a href=\"index.jsp\">戻る</a>"
				+ "</body>"
				+ "</html>";
		
		StringBuffer sb = new StringBuffer();
		sb.append(HEAD_TAG);
		sb.append(BODY_TAG);
		response.getWriter().println(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
