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

@WebServlet("/input/refer.jsp")
public class ReferPageServlet extends AbstractBaseServlet {

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
		response.append("<table border=\"2\">");
		response.append("<tr>");
		response.append(String.format("<td>%s</td>", "レコード番号"));
		response.append(String.format("<td>%s</td>", "ID"));
		response.append(String.format("<td>%s</td>", "名前"));
		response.append(String.format("<td>%s</td>", "年齢"));
		response.append(String.format("<td>%s</td>", "性別"));
		response.append(String.format("<td>%s</td>", "郵便番号"));
		response.append("</tr>");

		final List<MCustomer> list = DbProcessor.select(statement, "SELECT * FROM M_CUSTOMER ORDER BY ID", MCustomer.class);
		for (final MCustomer customer : list) {
			response.append("<tr>");
			response.append(String.format("<td>%d</td>", customer.getRownum()));
			response.append(String.format("<td>%d</td>", customer.getId()));
			response.append(String.format("<td>%s</td>", customer.getName()));
			response.append(String.format("<td>%d</td>", customer.getAge()));
			response.append(String.format("<td>%d</td>", customer.getSex()));
			response.append(String.format("<td>%s</td>", customer.getZipCode()));
			response.append("</tr>");
		}
		response.append("</table>");

		response.append("<a href=\"./input.jsp\">登録画面へ</a>");

		return response;
	}
}
