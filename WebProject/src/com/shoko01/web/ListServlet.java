package com.shoko01.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/list.jsp")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");

			connection = DriverManager.getConnection("jdbc:sqlite:/home/admin/sqlite3.db");
			statement = connection.createStatement();

			} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			try {
				statement.close();
				statement = null;
				connection.close();
				connection = null;
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		File file = new File("//home/admin/Desktop/data.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String wk;
		int i = 1;
		StringBuffer stringBuffer1 = new StringBuffer();
		while ((wk = bufferedReader.readLine()) != null) {
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
			i = i+1;
		}
		bufferedReader.close();
		fileReader.close();
		final String HEAD_TAG = "<html>"
				+ "<head>"
				+ "<title>Web画面データ一覧画面</title>"
				+ "</head>";
		final String BODY = "<body>"
				+ "<h1>Web画面データ一覧画面</h1>"
				+ "<table border=\"2\">"
				+ "<tr>"
				+ "<td>No</td>"
				+ "<td>名前</td>"
				+ "<td>メモ</td>"
				+ "<td>登録日時</td>"
				+ "</tr>"
				+ "\"select\""
				//+ "%DATA%"
				+ "</table>"
				+ "<br />"
				+ "<a href=\"index.jsp\">戻る</a>"
				+ "</body>"
				+ "</html>";
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(HEAD_TAG);
		String replacedBody = BODY.replaceAll("%DATA%", stringBuffer1.toString());
		stringBuffer.append(replacedBody);
		response.getWriter().println(stringBuffer.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
