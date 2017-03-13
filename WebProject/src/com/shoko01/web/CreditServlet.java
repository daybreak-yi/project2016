package com.shoko01.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/credit.jsp")
public class CreditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreditServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		String flag = request.getParameter("flag");
		String serch = request.getParameter("serch");
		
		String username = request.getParameter("username");
		String memo = request.getParameter("memo");
		memo = memo.replaceAll("\r\n", "");
		memo = memo.replaceAll("\n", "");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String now = simpleDateFormat.format(new Date());
		
		StringBuffer stringBuffer = new StringBuffer();
		String replacedBody = null;
		
		final int USERNAME_LIMIT = 20;
		final int MEMO_LIMIT = 100;
		
		final int USERNAME_LENGTH = username.length();
		final int MEMO_LENGTH = memo.length();
		
		final int USERNAME_OVER = USERNAME_LENGTH - USERNAME_LIMIT;
		final int MEMO_OVER = MEMO_LENGTH - MEMO_LIMIT;
		
		final String JDBC = "org.sqlite.JDBC";
		final String DATA_PATH = "jdbc:sqlite:/home/admin/sqlite3.db";
		
		if(flag.equals("ファイルから読み込み")) {
			
			String url = "filereader.jsp";
			response.sendRedirect(url);
			
		} else if(flag.equals("メモ検索")) {
			
			String sql = "select * from USER_TBL where memo like ?";
			
			if(serch.equals("perfect")) {
				sql = "select * from USER_TBL where memo = ?";
			} else if(serch.equals("partial")){
				sql = "select * from USER_TBL where memo like ?";
			}
			
			Connection connection = null;
			PreparedStatement ps = null;
			StringBuffer stringBuffer1 = new StringBuffer();
			
			try {
				Class.forName(JDBC);
				
				connection = DriverManager.getConnection(DATA_PATH);
				ps = connection.prepareStatement(sql);
				
				if(serch.equals("perfect")) {
					ps.setString(1, memo);
				} else if(serch.equals("partial")) {
					ps.setString(1, "%" + memo + "%");
				}
				
				ResultSet rs = ps.executeQuery();
				
				int i = 0;
				while(rs.next()) {
					String username_db = rs.getString("username");
					String memo_db = rs.getString("memo");
					String date = rs.getString("date");
					
					i++;
					stringBuffer1.append("<tr>");
					stringBuffer1.append("<td>");
					stringBuffer1.append(i);
					stringBuffer1.append("</td>");
					stringBuffer1.append("<td>");
					stringBuffer1.append(username_db);
					stringBuffer1.append("</td>");
					stringBuffer1.append("<td>");
					stringBuffer1.append(memo_db);
					stringBuffer1.append("</td>");
					stringBuffer1.append("<td>");
					stringBuffer1.append(date);
					stringBuffer1.append("</td>");
					stringBuffer1.append("</tr>");
				}
				
				rs.close();
				
				final String HEAD = ""
						+ "<html>"
						+ "<head>"
						+ "<title>"
						+ memo
						+ "のメモ検索結果</title>"
						+ "</head>";
				
				final String BODY = ""
						+ "<body>"
						+ "<h1>"
						+ Integer.toString(i)
						+ "件の検索結果</h1>"
						+ "<table border=\"2\">"
						+ "<tr>"
						+ "<td>No</td>"
						+ "<td>名前</td>"
						+ "<td>メモ</td>"
						+ "<td>登録日時</td>"
						+ "</tr>"
						+ "%DATA%"
						+ "</table>"
						+ "</ br>"
						+ "<a href=\"index.jsp\">トップへ戻る</a> "
						+ "<a href=\"input.jsp\">フォームへ戻る</a>"
						+ "</body>"
						+ "</html>";
				
				StringBuffer stringBuffer2 = new StringBuffer();
				stringBuffer2.append(HEAD);
				replacedBody = BODY.replaceAll("%DATA%", stringBuffer1.toString());
				stringBuffer2.append(replacedBody);
				response.getWriter().println(stringBuffer2.toString());
				
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
			
		} else if(flag.equals("名前検索")) {
			
			String sql = "select * from USER_TBL where username like ?";
			
			if(serch.equals("perfect")) {
				sql = "select * from USER_TBL where username = ?";
			} else if(serch.equals("partial")){
				sql = "select * from USER_TBL where username like ?";
			}
			
			Connection connection = null;
			PreparedStatement ps = null;
			StringBuffer stringBuffer1 = new StringBuffer();
			
			try {
				Class.forName(JDBC);
				
				connection = DriverManager.getConnection(DATA_PATH);
				ps = connection.prepareStatement(sql);
				
				if(serch.equals("perfect")) {
					ps.setString(1, username);
				} else if(serch.equals("partial")) {
					ps.setString(1, "%" + username + "%");
				}
				
				ResultSet rs = ps.executeQuery();
				
				int i = 0;
				while(rs.next()) {
					String username_db = rs.getString("username");
					String memo_db = rs.getString("memo");
					String date = rs.getString("date");
					
					i++;
					stringBuffer1.append("<tr>");
					stringBuffer1.append("<td>");
					stringBuffer1.append(i);
					stringBuffer1.append("</td>");
					stringBuffer1.append("<td>");
					stringBuffer1.append(username_db);
					stringBuffer1.append("</td>");
					stringBuffer1.append("<td>");
					stringBuffer1.append(memo_db);
					stringBuffer1.append("</td>");
					stringBuffer1.append("<td>");
					stringBuffer1.append(date);
					stringBuffer1.append("</td>");
					stringBuffer1.append("</tr>");
				}
				
				rs.close();
				
				final String HEAD = ""
						+ "<html>"
						+ "<head>"
						+ "<title>"
						+ username
						+ "の名前検索結果</title>"
						+ "</head>";
				
				final String BODY = ""
						+ "<body>"
						+ "<h1>"
						+ Integer.toString(i)
						+ "件の検索結果</h1>"
						+ "<table border=\"2\">"
						+ "<tr>"
						+ "<td>No</td>"
						+ "<td>名前</td>"
						+ "<td>メモ</td>"
						+ "<td>登録日時</td>"
						+ "</tr>"
						+ "%DATA%"
						+ "</table>"
						+ "</ br>"
						+ "<a href=\"index.jsp\">トップへ戻る</a>"
						+ "<a href=\"input.jsp\">フォームへ戻る</a>"
						+ "</body>"
						+ "</html>";
				
				StringBuffer stringBuffer2 = new StringBuffer();
				stringBuffer2.append(HEAD);
				replacedBody = BODY.replaceAll("%DATA%", stringBuffer1.toString());
				stringBuffer2.append(replacedBody);
				response.getWriter().println(stringBuffer2.toString());
				
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
			
		} else if(flag.equals("更新")) {
			boolean isUpdate = false;
			
			String sql = "select username from USER_TBL";
			Connection connection = null;
			PreparedStatement ps = null;
			
			try {
				Class.forName(JDBC);
				
				connection = DriverManager.getConnection(DATA_PATH);
				connection.setAutoCommit(false);
				ps = connection.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					String username_db = rs.getString("username");
					if(username_db.equals(username)) {
						isUpdate = true;
						break;
					}
				}
				
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
			
			if(isUpdate) {
				
				final String HEAD_TAG = ""
						+ "<html>"
						+ "<head>"
						+ "<title>"
						+ username
						+ "のデータ更新</title>"
						+ "</head>";
				
				final String BODY_TAG = ""
						+ "<body>"
						+ "<h1>"
						+ username
						+ "のデータ更新画面</h1>"
						+ "<form action=\"update.jsp\" method=\"get\">"
						+ "<table border=\"2\">"
						+ "<tr>"
						+ "<td>メモ</td>"
						+ "<td><textarea name=\"memo\"rows=\"3\" cols=\"40\"></textarea></td>"
						+ "</tr>"
						+ "</table>"
						+ "<br />"
						+ "<input type=\"submit\" value=\"送信\" name=\"flag\"> "
						+ "<input type=\"reset\" value=\"リセット\"> "
						+ "<input type=\"hidden\" name=\"username\" value=\""
						+ username
						+ "\">"
						+ "<a href=\"index.jsp\">戻る</a>"
						+ "</form>"
						+ "</body>"
						+ "</html>";
				
				StringBuffer sb = new StringBuffer();
				sb.append(HEAD_TAG);
				sb.append(BODY_TAG);
				response.getWriter().println(sb.toString());
			} else {
				final String ERROR_HEAD = ""
						+ "<html>"
						+ "<head>"
						+ "<title>対象データ無し</title>"
						+ "</head>";
				final String ERROR_BODY = ""
						+ "<body>"
						+ "<h3>更新対象データがDBに登録されてません</h3>"
						+ "<a href=\"index.jsp\">戻る</a>"
						+ "</body>"
						+ "</html>";
				
				StringBuffer sb = new StringBuffer();
				sb.append(ERROR_HEAD);
				sb.append(ERROR_BODY);
				response.getWriter().println(sb.toString());
			}
			
		} else if(flag.equals("削除")) {
			boolean isDelete = false;
			
			Connection connection = null;
			PreparedStatement ps = null;
			
			try {
				Class.forName(JDBC);
				
				String sql = "select username from USER_TBL";
				connection = DriverManager.getConnection(DATA_PATH);
				ps = connection.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					String username_db = rs.getString("username");
					if(username_db.equals(username)) {
						isDelete = true;
						break;
					}
				}
				rs.close();
				rs = null;
				
			} catch(Exception e) {
				throw new ServletException(e);
			} finally {
				try {
					ps.close();
					ps = null;
					
					connection.close();
					connection = null;
				} catch(Exception er) {
					throw new ServletException(er);
				}
			}
			
			if(isDelete) {
				try {
					Class.forName(JDBC);
					
					String sql = "delete from USER_TBL where username = ?";
					
					connection = DriverManager.getConnection(DATA_PATH);
					connection.setAutoCommit(false);
					ps = connection.prepareStatement(sql);
					ps.setString(1, username);
					
					Thread.sleep(3000);
					ps.executeUpdate();
					Thread.sleep(5000);
					connection.commit();
					
					String url = "delete.jsp";
					response.sendRedirect(url);
					
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
			} else {
				final String ERROR_HEAD = ""
						+ "<html>"
						+ "<head>"
						+ "<title>対象データ無し</title>"
						+ "</head>";
				final String ERROR_BODY = ""
						+ "<body>"
						+ "<h3>削除対象データがDBに登録されてません</h3>"
						+ "<a href=\"index.jsp\">戻る</a>"
						+ "</body>"
						+ "</html>";
				
				StringBuffer sb = new StringBuffer();
				sb.append(ERROR_HEAD);
				sb.append(ERROR_BODY);
				response.getWriter().println(sb.toString());
				
			}
		} else {
			if (username.indexOf(",") != -1 || memo.indexOf(",") != -1
					|| USERNAME_LENGTH > USERNAME_LIMIT || MEMO_LENGTH > MEMO_LIMIT) {
					
					String error = "";
					
					if(username.indexOf(",") != -1 || memo.indexOf(",") != -1) {
						
						if(username.indexOf(",") != -1 && memo.indexOf(",") == -1) {
							error = "<p>名前の入力内容に \"<b>,</b>\" が含まれています</p>";
						} else if(username.indexOf(",") == -1 && memo.indexOf(",") != -1) {
							error = "<p>メモの入力内容に \"<b>,</b>\" が含まれています</p>";
						} else if(username.indexOf(",") != -1 && memo.indexOf(",") != -1) {
							error = "<p>名前とメモの入力内容に \"<b>,</b>\" が含まれています</p>";
						}
						
					} else if(USERNAME_LENGTH > USERNAME_LIMIT || MEMO_LENGTH > MEMO_LIMIT) {
						
						if(USERNAME_LENGTH > USERNAME_LIMIT && MEMO_LENGTH <= MEMO_LIMIT) {
							error = "<p>名前の入力内容が文字数制限を超えています（<b>"
									+ Integer.toString(USERNAME_OVER)
									+ "</b>文字超過）</p>";
						} else if(USERNAME_LENGTH <= USERNAME_LIMIT && MEMO_LENGTH > MEMO_LIMIT) {
							error = "<p>メモの入力内容が文字数制限を超えています (<b>"
									+ Integer.toString(MEMO_OVER)
									+ "</b>文字超過)</p>";
						} else if(USERNAME_LENGTH > USERNAME_LIMIT && MEMO_LENGTH > MEMO_LIMIT) {
							error = "<p>名前とメモの入力内容が文字数制限を超えています "
									+ "(名前 : <b>"
									+ Integer.toString(USERNAME_OVER)
									+ "</b>文字超過)"
									+ "(メモ : <b>"
									+ Integer.toString(MEMO_OVER)
									+ "</b>文字超過)"
									+ "</p>";
						}
					}
					
					//以下は入力内容を訂正させるとき
					final String ERROR_HEAD = ""
							+ "<html>"
							+ "<head>"
							+ "<title>Web画面データ誤登録確認</title>"
							+ "</head>";

					final String ERROR_BODY = ""
							+ "<body>"
							+ "<h1>Web画面データ誤登録確認</h1>"
							+ error
							+ "<table border=\"2\">"
							+ "<tr>" + "<td>名前</td>"
							+ "<td>%USERNAME%</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td>メモ</td>"
							+ "<td>%MEMO%</td>"
							+ "</tr>"
							+ "</table>"
							+ "<br />"
							+ "<a href=\"index.jsp\">戻る</a>"
							+ "</body>"
							+ "</html>";

					stringBuffer.append(ERROR_HEAD);
					replacedBody = ERROR_BODY.replaceAll("%USERNAME%", username);
					replacedBody = replacedBody.replaceAll("%MEMO%", memo);
					stringBuffer.append(replacedBody);
					
					response.getWriter().println(stringBuffer.toString());

				} else {
					
					//入力内容が正しい時
					Connection connection = null;
					PreparedStatement ps = null;
					String sql = "insert into USER_TBL (username, memo, date) values(?, ?, ?)";
					
					try{
						final String HEAD_TAG = ""
								+ "<html>"
								+ "<head>"
								+ "<title>Web画面データ登録完了画面</title>"
								+ "</head>";
						final String BODY = "" 
								+ "<body>"
								+ "<h1>Web画面データ登録完了画面</h1>"
								+ "<table border=\"2\">"
								+ "<tr>"
								+ "<td>名前</td>"
								+ "<td>%USERNAME%</td>"
								+ "</tr>"
								+ "<tr>"
								+ "<td>メモ</td>"
								+ "<td>%MEMO%</td>"
								+ "</tr>"
								+ "</table>"
								+ "<br />"
								+ "<a href=\"index.jsp\">戻る</a>"
								+ "</body>"
								+ "</html>";
						
						Class.forName(JDBC);
						
						connection = DriverManager.getConnection(DATA_PATH);
						connection.setAutoCommit(false);
						
						ps = connection.prepareStatement(sql);
						ps.setString(1, username);
						ps.setString(2, memo);
						ps.setString(3, now);
						
						ps.executeUpdate();
						
						connection.commit();
						
						stringBuffer.append(HEAD_TAG);
						replacedBody = BODY.replaceAll("%USERNAME%", username);
						replacedBody = replacedBody.replaceAll("%MEMO%", memo);
						stringBuffer.append(replacedBody);

						response.getWriter().println(stringBuffer.toString());
						
					} catch(Exception e) {
						
						boolean isOverlap = false;
						
						try {
							Class.forName(JDBC);
							
							connection = DriverManager.getConnection(DATA_PATH);
							sql = "select username from USER_TBL";
							ps = connection.prepareStatement(sql);
							
							connection.setAutoCommit(false);
							
							ResultSet rs = ps.executeQuery();
							
							while(rs.next()) {
								String username_db = rs.getString("username");
								if(username_db.equals(username)) {
									isOverlap = true;
									break;
								}
							}
							rs.close();
							rs = null;
							connection.commit();
							
						} catch(Exception er) {
							throw new ServletException(er);
						}
						
						if(isOverlap) {
							final String ERROR_HEAD = ""
									+ "<html>"
									+ "<head>"
									+ "<title>重複データの確認</title>"
									+ "</head>";
							final String ERROR_BODY = ""
									+ "<body>"
									+ "<h3>既に登録されている情報です</h3>"
									+ "<a href=\"index.jsp\">戻る</a>"
									+ "</body>"
									+ "</html>";
							
							StringBuffer sb = new StringBuffer();
							sb.append(ERROR_HEAD);
							sb.append(ERROR_BODY);
							response.getWriter().println(sb.toString());
						} else {
							throw new ServletException(e);
						}
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
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
