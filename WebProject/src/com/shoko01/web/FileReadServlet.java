package com.shoko01.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileReadServlet
 */
@WebServlet("/filereader.jsp")
public class FileReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=UTF-8");
		
		File file = new File("/home/admin/Desktop/data.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		Connection connection = null;
		PreparedStatement ps = null;
		final String JDBC = "org.sqlite.JDBC";
		final String DATA_PATH = "jdbc:sqlite:/home/admin/sqlite3.db";
		String sql = "insert or ignore into USER_TBL (username, memo, date) values (?, ?, ?)";
		
		try {
			Class.forName(JDBC);
			
			connection = DriverManager.getConnection(DATA_PATH);
			ps = connection.prepareStatement(sql);
			
			String line = new String();
			
			while( (line = br.readLine()) != null ) {
				String[] records = line.split(",", 0);
				
				ps.setString(1, records[0]);
				ps.setString(2, records[1]);
				ps.setString(3, records[2]);
				
				ps.executeUpdate();
			}
			
			final String HEAD = ""
					+ "<html>"
					+ "<head>"
					+ "<title>読み込み</title>"
					+ "</head>";
			
			final String BODY = ""
					+ "<body>"
					+ "<h1>ファイルの読み込みをしました</h1>"
					+ "<br />"
					+ "<a href=\"index.jsp\">TOPに戻る</a><br />"
					+ "<a href=\"input.jsp\">フォームに戻る</a><br />"
					+ "</body>"
					+ "</html>";
			
			StringBuffer sb = new StringBuffer();
			sb.append(HEAD);
			sb.append(BODY);
			
			response.getWriter().println(sb.toString());
			
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				ps.close();
				ps = null;
				connection.close();
				connection = null;
				fr.close();
				br.close();
			} catch(Exception e) {
				throw new ServletException(e);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
