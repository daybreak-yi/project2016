package com.shoko01.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/list.jsp")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		File file = new File("//home/admin/Desktop/data.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String wk = new String();
		int i = 1;
		StringBuffer stringBuffer1 = new StringBuffer();
		
		while((wk = bufferedReader.readLine()) != null) {
			String[] array = wk.split(",");
			List<String> list = Arrays.asList(array);
			
			stringBuffer1.append("<tr>");
			stringBuffer1.append("<td>");
			stringBuffer1.append(i);
			stringBuffer1.append("</td>");
			stringBuffer1.append("<td>");
			stringBuffer1.append(list.get(0));
			stringBuffer1.append("</td>");
			stringBuffer1.append("<td>");
			stringBuffer1.append(list.get(1));
			stringBuffer1.append("</td>");
			stringBuffer1.append("<td>");
			stringBuffer1.append(list.get(2));
			stringBuffer1.append("</td>");
			stringBuffer1.append("</tr>");
			
			i = i + 1;
		}
		
		bufferedReader.close();
		fileReader.close();
		*/
		
		String sql = "select * from USER_TBL";
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {

			final String HEAD_TAG = ""
					+ "<html>"
					+ "<head>"
					+ "<title>Web画面データ一覧画面</title>"
					+ "</head>";
			final String BODY = ""
					+ "<body>"
					+ "<h1>Web画面データ一覧画面</h1>"
					+ "<table border=\"2\">"
					+ "<tr>"
					+ "<td>No</td>"
					+ "<td>名前</td>"
					+ "<td>メモ</td>"
					+ "<td>登録日時</td>"
					+ "</tr>"
					+ "%DATA%"
					+ "</table>"
					+ "<br />"
					+ "<a href=\"index.jsp\">戻る</a>"
					+ "</body>"
					+ "</html>";
			
			StringBuffer stringBuffer1 = new StringBuffer();
			int i = 1;
			
			Class.forName("org.sqlite.JDBC");

			connection = DriverManager.getConnection("jdbc:sqlite:/home/admin/sqlite3.db");
			connection.setAutoCommit(false);
			
			ps = connection.prepareStatement(sql);
			
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				String username = resultSet.getString("username");
				String memo = resultSet.getString("memo");
				String date = resultSet.getString("date");
				
				stringBuffer1.append("<tr>");
				stringBuffer1.append("<td>");
				stringBuffer1.append(i);
				stringBuffer1.append("</td>");
				stringBuffer1.append("<td>");
				stringBuffer1.append(username);
				stringBuffer1.append("</td>");
				stringBuffer1.append("<td>");
				stringBuffer1.append(memo);
				stringBuffer1.append("</td>");
				stringBuffer1.append("<td>");
				stringBuffer1.append(date);
				stringBuffer1.append("</td>");
				stringBuffer1.append("</tr>");
				
				i = i + 1;
			}
			resultSet.close();
			resultSet = null;
			connection.commit();
			
			StringBuffer stringBuffer2 = new StringBuffer();
			stringBuffer2.append(HEAD_TAG);
			String replacedBody = BODY.replaceAll("%DATA%", stringBuffer1.toString());
			stringBuffer2.append(replacedBody);
			
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println(stringBuffer2.toString());
			
		} catch (Exception e) {
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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
