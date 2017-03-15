import java.util.Scanner;

//ログイン処理のクラス
public class Rogin {
	public static void main(String[] args) {

		// 試行回数
		int count = 1;
		// パスワード
		final String pass = "abcd";
		// パスワード入力用
		String userPass = new String();
		// 入力用のScannerインスタンス
		Scanner input = new Scanner(System.in);
		// ログインの判定フラグ
		boolean isNotRogin = true;

		while (isNotRogin) {

			switch (count) {
				case 1:
					System.out.println("パスワードを入力してください");
					break;

				default:
					System.out.println("リトライ" + count + "回目 パスワードを入力してください");
					break;
			}

			userPass = input.nextLine();

			switch (userPass) {
				case pass:
					isNotRogin = false;
					break;
				default:
					break;
			}

			count++;

		}

		System.out.println("ログインに成功しました");

	}

}
