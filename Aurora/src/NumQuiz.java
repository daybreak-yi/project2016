import java.util.Scanner;

public class NumQuiz extends GameBase{
	public static void main(String[] args) {
		//乱数の上限
		int limit = 1000;
		//ループカウンタ
		int count = 1;
		int countMax = 0;
		//int型乱数
		int random = getRand(limit);
		//入力用
		int number = 0;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("何回勝負にしますか?");
		countMax = scanner.nextInt();
		
		System.out.println("1〜" + limit + "までの値を入力してください。");
		
		while(count <= countMax) {
			System.out.println(count + "回目");
			number = setNumber();
			
			if(number == random) {
				System.out.println("正解");
				break;
			} else if(number < random) {
				System.out.println("小さすぎます");
			} else if(number > random) {
				System.out.println("大きすぎます");
			}
			
			count++;
		}
		
		System.out.println("正解は" + random + "でした。");
		scanner.close();
	}
}
