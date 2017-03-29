
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このページへのアクセスURLはhttp://127.0.0.1:8080/Kadai2/Sampleです。
 */
@WebServlet("/Sample")
public class Sample extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sample() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final File file = new File("/home/admin/git/project2016/Kadai2/data.txt");
		final FileReader reader = new FileReader(file);
		final BufferedReader bufferedReader = new BufferedReader(reader);

		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			response.getWriter().append(str);
		}
		bufferedReader.close();
		reader.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
