package com.shoko01.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateTest
 */
@WebServlet("/createtest.jsp")
public class CreateTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateTest() {
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
		String HEAD = "<html>" + "<head>" + "</head>" + "<body>" + "</body>" + "</html>";

		Connection connection = null;
		Statement statement = null;
		String sql = "create table USER_TBL(username text primary key , memo text, date text)";

		try {
			Class.forName("org.sqlite.JDBC");

			connection = DriverManager.getConnection("jdbc:sqlite:/home/admin/sqlite3.db");
			statement = connection.createStatement();

			statement.execute(sql);
			

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

		response.getWriter().println(HEAD);
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
