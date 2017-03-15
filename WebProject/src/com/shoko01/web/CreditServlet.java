package com.shoko01.web;

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreditServlet
 */
@WebServlet("/credit.jsp")
public class CreditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		Statement statement = null;
		Connection connection = null;
		String username = request.getParameter("username");
		String memo = request.getParameter("memo");
		memo = memo.replaceAll("\r\n", "");
		memo = memo.replaceAll("\n", "");
		final int count1 = username.length();
		final int count2 = memo.length();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String now = format.format(new Date());
		if (username.matches(".*,.*") || memo.matches(".*,.*") || count1 > 20 || count2 > 100) {
			String error1 = "";
			String error2 = "";
			if (username.matches(".*,.*") && memo.matches(".*,.*")) {
				error1 = "<p>名前とメモに,が入力されています</p>";
			} else if (username.matches(".*,.*")) {
				error1 = "<p>名前に,が入力されています</p>";
			} else if (memo.matches(".*,.*")) {
				error1 = "<p>メモに,が入力されています</p>";
			}
			if (count1 > 20 && count2 > 100) {
				error2 = "<p>名前とメモが文字数制限を超えています</p>";
			} else if (count1 > 20) {
				error2 = "<p>名前が文字数制限を超えています</p>";
			} else if (count2 > 100) {
				error2 = "<p>メモが文字数制限を超えています</p>";
			}
			final String HEAD_TAG = "<html>" + "<head>" + "<title>Web画面データ登録失敗</title>" + "</head>";
			final String BODY = "<body>" + "<h1>Web画面データ登録失敗</h1>" + error1 + error2 + "<table border=\"2\">" + "<tr>"
					+ "<td>名前</td>" + "<td>%USERNAME%</td>" + "</tr>" + "<tr>" + "<td>メモ</td>" + "<td>%MEMO%</td>"
					+ "</tr>" + "</table>" + "<br />" + "<a href=\"index.jsp\">戻る</a>" + "</body>" + "</html>";
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(HEAD_TAG);
			String replacedBody = BODY.replaceAll("%USERNAME%", username);
			replacedBody = replacedBody.replaceAll("%MEMO%", memo);
			stringBuffer.append(replacedBody);
			response.getWriter().println(stringBuffer.toString());
		} else {
			File file = new File("//home/admin/Desktop/data.txt");
			FileWriter filewriter = new FileWriter(file, true);
			filewriter.append(username);
			filewriter.append(",");
			filewriter.append(memo);
			filewriter.append(",");
			filewriter.append(now);
			filewriter.append("\r\n");
			filewriter.close();
			final String HEAD_TAG = "<html>" + "<head>" + "<title>Web画面データ登録完了画面</title>" + "</head>";
			final String BODY = "<body>" + "<h1>Web画面データ登録完了画面</h1>" + "<table border=\"2\">" + "<tr>" + "<td>名前</td>"
					+ "<td>%USERNAME%</td>" + "</tr>" + "<tr>" + "<td>メモ</td>" + "<td>%MEMO%</td>" + "</tr>"
					+ "</table>" + "<br />" + "<a href=\"index.jsp\">戻る</a>" + "</body>" + "</html>";
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(HEAD_TAG);
			String replacedBody = BODY.replaceAll("%USERNAME%", username);
			replacedBody = replacedBody.replaceAll("%MEMO%", memo);
			stringBuffer.append(replacedBody);
			response.getWriter().println(stringBuffer.toString());
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:/home/admin/sqlite3.db");
				statement = connection.createStatement();
				statement.execute( "insert into USER_TBL values (username,memo,now)");
			} catch (SQLException e) {
				throw new ServletException(e);
			}
			finally {
				try {
					statement.close();
					statement = null;
					connection.close();
					connection = null;
				} catch (Exception e) {
					throw new ServletException(e);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
