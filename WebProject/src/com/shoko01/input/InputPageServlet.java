package com.shoko01.input;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/input/input.jsp")
public class InputPageServlet extends AbstractBaseServlet {

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
		response.append("<form action=\"regist.jsp\" method=\"get\">");
		response.append("<table boder=\"2\">");
		response.append("<tr>");
		response.append("<td>名前</td>");
		response.append("<td><input name=\"name\" type=\"text\" value=\"\" maxlength=\"128\"></td>");
		response.append("</tr>");

		response.append("<tr>");
		response.append("<td>年齢</td>");
		response.append("<td><input name=\"age\" type=\"text\" value=\"\" maxlength=\"128\"></td>");
		response.append("</tr>");

		response.append("<tr>");
		response.append("<td>性別</td>");
		response.append("<td><select name=\"sex\"><option value=\"1\">男性</option><option value=\"2\">女性</option></select></td>");
		response.append("</tr>");

		response.append("<tr>");
		response.append("<td>郵便番号</td>");
		response.append("<td><input name=\"zip_code\" type=\"text\" value=\"\" maxlength=\"128\"></td>");
		response.append("</tr>");
		response.append("</table>");

		response.append("<input type=\"submit\" value=\"送信\">");
		response.append("<input type=\"reset\" value=\"リセット\">");

		response.append("</form>");
		return response;
	}

}
