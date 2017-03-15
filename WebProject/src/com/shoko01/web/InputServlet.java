package com.shoko01.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InputServlet
 */
@WebServlet("/input.jsp")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		final String HEAD_TAG = "<html>"
				+ "<head>"
				+ "<title>Web画面データ登録画面</title>"
				+ "</head>";
		final String BODY = "<body>"
				+ "<h1>Web画面データ登録画面</h1>"
				+ "<p>名前とメモには,を入力しないでください</p>"
				+"<p>入力文字数には制限があります（名前は20文字 メモは100文字）"
				+ "<form action=\"credit.jsp\" method=\"get\">"
				+ "<table border=\"2\">"
				+ "<tr>"
				+ "<td>名前</td>"
				+ "<td><input name=\"username\" type=\"text\" value=\"\" maxlength=\"20\"></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>メモ</td>"
				+ "<td><textarea name=\"memo\" rows=\"3\" cols=\"40\" maxlength=\"100\"></textarea></td>"
				+ "</tr>"
				+ "</table>"
				+ "<br />"
				+ "<input type=\"submit\" value=\"送信\"> "
				+ "<input type=\"reset\" value=\"リセット\">"
				+ "</form>"
				+ "</body>"
				+ "</html>";
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(HEAD_TAG);
		stringBuffer.append(BODY);
		response.getWriter().println(stringBuffer.toString());
			
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
