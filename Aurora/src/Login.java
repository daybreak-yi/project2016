import java.io.*;

public class Login {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int a = 0;
		final String pass = "zxcv";
		while (true) {
			a++;

			if (a == 1) {
				System.out.println("パスワードを入力して下さい");
			} else {
				System.out.println("リトライ" + a + "回目 パスワードを入力して下さい");
			}
			String str = br.readLine();

			if (str.equals(pass)) {
				System.out.println("ログインに成功しました");
				break;
			}

		}
	}
}
