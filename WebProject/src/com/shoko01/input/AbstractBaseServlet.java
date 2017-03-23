package com.shoko01.input;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractBaseServlet extends HttpServlet {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** DB_PATH */
	private static final String DB_PATH = "../sqlite3.db";

	/** DB_CONNECTION */
	private static final String DB_CONNECTION = "jdbc:sqlite:%s";

	/** JDBC */
	private static final String JDBC = "org.sqlite.JDBC";

	/**
	 * デフォルトコンストラクタ
	 */
	public AbstractBaseServlet() {
		try {
			Class.forName(JDBC);
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Post
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @exception ServletException
	 * @exception IOException
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Get
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @exception ServletException
	 * @exception IOException
	 */
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection(String.format(DB_CONNECTION, DB_PATH));
			statement = connection.createStatement();
			statement.setQueryTimeout(30);

			final String pageTitle = getPageTitle();

			final Map<String, String> map = new HashMap<String, String>();
			final Map<String, String[]> parameterMap = request.getParameterMap();
			final Set<String> keys = parameterMap.keySet();
			for (final String key : keys) {
				final String[] values = parameterMap.get(key);
				if (values == null || values.length <= 0) {
					map.put(key, null);
					continue;
				}
				map.put(key, values[0]);
			}
			final StringBuffer responseBuffer = doExec(map, new StringBuffer(), statement);

			response.getWriter().append("<html>");
			response.getWriter().append("<head>");
			response.getWriter().append(String.format("<title>%s</title>", pageTitle));
			response.getWriter().append("</head>");
			response.getWriter().append("<body>");
			response.getWriter().append(String.format("<h1>%s</h1>", pageTitle));
			response.getWriter().append(responseBuffer.toString().replaceAll(System.lineSeparator(), "<br />"));
			response.getWriter().append("</body>");
			response.getWriter().append("</html>");
			afterProc(response);
		} catch (final SQLException e) {
			final StringWriter stringWriter = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			response.getWriter().append(stringWriter.toString());
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (final Throwable e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * ページタイトルを取得する
	 * 
	 * @return ページタイトル
	 */
	protected abstract String getPageTitle();

	/**
	 * Webページ処理
	 * 
	 * @param request {@link Map}
	 * @param response {@link StringBuffer}
	 * @param statement {@link Statement}
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	protected abstract StringBuffer doExec(final Map<String, String> request, final StringBuffer response, final Statement statement) throws ServletException, IOException, SQLException;

	/**
	 * 事後処理
	 * 
	 * @param response {@link HttpServletResponse}
	 * @exception IOException
	 */
	protected void afterProc(final HttpServletResponse response) throws IOException {

	}
}
