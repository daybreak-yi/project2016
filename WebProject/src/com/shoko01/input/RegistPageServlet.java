package com.shoko01.input;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import com.shoko01.input.common.DbProcessor;
import com.shoko01.input.dto.VNumeric;

@WebServlet("/input/regist.jsp")
public class RegistPageServlet extends AbstractBaseServlet {

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

		final String name = request.get("name");
		final String age = parse(request.get("age"));
		final String sex = parse(request.get("sex"));
		final String zipCode = getZipCode(request.get("zip_code"));

		final List<VNumeric> list = DbProcessor.select(statement, "SELECT MAX(ID) AS NUMERIC FROM M_CUSTOMER", VNumeric.class);
		final BigDecimal id = list.get(0).getNumeric().add(BigDecimal.ONE);

		final String sql = String.format("INSERT INTO M_CUSTOMER(ID, NAME, AGE, SEX,ZIP_CODE) VALUES (%d, '%s', %s, %s, %s)", id.intValue(), name, age, sex, zipCode);
		statement.execute(sql);
		return response;
	}

	/**
	 * 文字列を解析する
	 * 
	 * @param word 文字列
	 * @return 数値
	 */
	protected String parse(final String word) {
		try {
			return new BigDecimal(word).toString();
		} catch (final Throwable e) {
			return "null";
		}
	}

	/**
	 * 郵便番号を取得する
	 * 
	 * @param zipCode 郵便番号
	 * @return 郵便番号
	 */
	protected String getZipCode(final String zipCode) {
		if (zipCode.isEmpty()) {
			return null;
		}

		return String.format("'%s'", zipCode);
	}

	/**
	 * 事後処理
	 * 
	 * @param response {@link HttpServletResponse}
	 * @throws IOException
	 */
	@Override
	protected void afterProc(final HttpServletResponse response) throws IOException {
		response.sendRedirect("./refer.jsp");
	}

}
