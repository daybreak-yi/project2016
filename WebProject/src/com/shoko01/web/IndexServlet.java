package com.shoko01.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.jsp")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String HEAD_TAG = ""
				+ "<html>"
				+ "<head>"
				+ "<title>Web画面インデックス</title>"
				+ "</head>";
		final String BODY = ""
				+ "<body>"
				+ "<h1>Web画面インデックス</h1>"
				+ "<a href=\"input.jsp\">データ登録</a>"
				+ "<br />"
				+ "<a href=\"list.jsp\">データ確認</a>"
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
