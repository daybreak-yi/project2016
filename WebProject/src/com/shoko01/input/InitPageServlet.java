package com.shoko01.input;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.shoko01.input.common.DbProcessor;
import com.shoko01.input.dto.MCustomer;

@WebServlet("/input/init.jsp")
public class InitPageServlet extends AbstractBaseServlet {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * ページタイトルを取得する
	 * 
	 * @return ページタイトル
	 */
	@Override
	protected String getPageTitle() {
		return this.getClass().getSimpleName();
	}

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
	@Override
	protected StringBuffer doExec(final Map<String, String> request, final StringBuffer response, final Statement statement) throws ServletException, IOException, SQLException {

		try {
			statement.execute("DROP TABLE M_CUSTOMER");
			response.append("M_CUSTOMER DELETED");
			response.append(System.lineSeparator());
		} catch (final Throwable e) {
			// 何もしない
		}

		// テーブル作成
		statement.execute("CREATE TABLE M_CUSTOMER(ID INTEGER, NAME TEXT NOT NULL, AGE NUMERIC NOT NULL, SEX NUMERIC NOT NULL, ZIP_CODE TEXT, PRIMARY KEY(ID))");
		response.append("M_CUSTOMER CREATED");
		response.append(System.lineSeparator());

		// データ挿入
		statement.execute("INSERT INTO M_CUSTOMER(ID, NAME, AGE, SEX, ZIP_CODE) VALUES (1, 'YAMADA', 25, 1, null)");
		statement.execute("INSERT INTO M_CUSTOMER(ID, NAME, AGE, SEX, ZIP_CODE) VALUES (2, 'KIKUCHI', 28, 1, null)");
		statement.execute("INSERT INTO M_CUSTOMER(ID, NAME, AGE, SEX, ZIP_CODE) VALUES (3, 'AOKI', 31, 2, null)");
		statement.execute("INSERT INTO M_CUSTOMER(ID, NAME, AGE, SEX, ZIP_CODE) VALUES (4, 'SAKAMOTO', 29, 1, null)");
		statement.execute("INSERT INTO M_CUSTOMER(ID, NAME, AGE, SEX, ZIP_CODE) VALUES (5, 'NAKATA', 27, 1, '110-0003')");

		response.append("<table border=\"2\">");
		final List<MCustomer> list = DbProcessor.select(statement, "SELECT * FROM M_CUSTOMER ORDER BY ID", MCustomer.class);
		for (final MCustomer customer : list) {
			response.append("<tr>");
			response.append(String.format("<td>%d</td>", customer.getRownum()));
			response.append(String.format("<td>%d</td>", customer.getId()));
			response.append(String.format("<td>%s</td>", customer.getName()));
			response.append(String.format("<td>%d</td>", customer.getAge()));
			response.append(String.format("<td>%d</td>", customer.getSex()));
			response.append(String.format("<td>%s</td>", customer.getZipCode()));
			response.append(String.format("<td>%s</td>", customer.getSql()));
			response.append("</tr>");
		}
		response.append("</table>");
		return response;
	}

}
