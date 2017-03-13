package com.shoko01.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DbWebSample")
public class DbWebSample extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CLASS_NAME = DbWebSample.class.getSimpleName();

	public DbWebSample() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.getWriter().append("<html>");
			response.getWriter().append("<head>");
			response.getWriter().append("<title>").append(CLASS_NAME).append("</title>");
			response.getWriter().append("</head>");
			response.getWriter().append("<body>");
			Class.forName("org.sqlite.JDBC");

			Connection connection = null;

			connection = DriverManager.getConnection("jdbc:sqlite:../sqlite3.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			// statement.execute("create table DB_TEST(ID text primary key)");
			// statement.execute("insert into DB_TEST values('DB Test!!')");
			
			
			ResultSet resultSet = statement.executeQuery("select * from DB_TEST");
			while (resultSet.next()) {
				response.getWriter().append(resultSet.getString("ID"));
			}
			connection.close();
			connection = null;
		} catch (Exception e) {
			throw new ServletException(e);
		}
		response.getWriter().append("</body>");
		response.getWriter().append("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
