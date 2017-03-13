package com.shoko01.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input.jsp")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InputServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String HEAD_TAG = ""
				+ "<html>"
				+ "<head>"
				+ "<title>Web画面データ登録画面</title>"
				+ "</head>";
		final String BODY = ""
				+ "<body>"
				+ "<h1>Web画面データ登録画面</h1>"
				+ "<p>"
				+ "<b>注意1</b> : \"<b>,</b>\" を名前、メモ双方に含まないでください<br />"
				+ "<b>注意2</b> : 文字数制限があります（名前 : 全角半角併せて20文字）(メモ ： 全角半角併せて100文字)<br />"
				+ "<b>注意3</b> : ファイルを読み込ませる場合は重複しているデータは読み込みません<br />"
				+ "</p>"
				+ "<p>"
				+ "削除をするときは、削除したいメモの名前を名前を入力する枠に入力して「削除」ボタンを押してください<br />"
				+ "更新をするときは、更新したいメモの名前を名前を入力する枠に入力して「更新」ボタンを押してください<br />"
				+ "</p>"
				+ "<form action=\"credit.jsp\" method=\"get\">"
				+ "<table border=\"2\">"
				+ "<tr>"
				+ "<td>名前</td>"
				+ "<td><input name=\"username\" type=\"text\" value=\"\"></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>メモ</td>"
				+ "<td><textarea name=\"memo\" rows=\"3\" cols=\"40\"></textarea></td>"
				+ "</tr>"
				+ "</table>"
				+ "<br />"
				+ "<input type=\"submit\" value=\"送信\" name=\"flag\"> "
				+ "<input type=\"reset\" value=\"リセット\"> "
				+ "<input type=\"submit\" value=\"削除\" name=\"flag\"> "
				+ "<input type=\"submit\" value=\"更新\" name=\"flag\"> "
				+ "<input type=\"submit\" value=\"ファイルから読み込み\" name=\"flag\"> "
				+ "<input type=\"submit\" value=\"名前検索\" name=\"flag\"> "
				+ "<input type=\"submit\" value=\"メモ検索\" name=\"flag\"> "
				+ "<br />(検索にのみ使用)<br />"
				+ "<input type=\"radio\" name=\"serch\" value=\"perfect\">完全一致 "
				+ "<input type=\"radio\" name=\"serch\" value=\"partial\" checked=\"checked\">部分一致"
				+ "<br /><br /><a href=\"index.jsp\">戻る</a>"
				+ "</form>"
				+ "</body>"
				+ "</html>";
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(HEAD_TAG);
		stringBuffer.append(BODY);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(stringBuffer.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
