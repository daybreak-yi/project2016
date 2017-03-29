
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このページへのアクセスURLはhttp://127.0.0.1:8080/Kadai3/Sampleです。
 */
@WebServlet("/Sample")
public class Sample extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sample() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("org.sqlite.JDBC");

			final Connection connection = DriverManager.getConnection("jdbc:sqlite:../sqlite3.db");
			final Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);

			statement.execute("DROP TABLE DB_TEST");
			statement.execute("CREATE TABLE DB_TEST(MSG TEXT, PRIMARY KEY(MSG))");
			statement.execute("INSERT INTO DB_TEST(MSG) VALUES('HelloWorld')");

			final ResultSet resultSet = statement.executeQuery("SELECT * FROM DB_TEST");
			while (resultSet.next()) {
				response.getWriter().append(resultSet.getString("MSG"));
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (final Throwable e) {
			final StringWriter stringWriter = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			response.getWriter().append(stringWriter.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
