import java.util.Scanner;

public class Janken extends GameBase{
	public static void main(String[] args) {
		//じゃんけんの手
		String[] hands = {"グー", "チョキ", "パー"};
		//デバッグモード切り替え用
		String change = new String();
		int limit = 3;
		//cpuの手
		int cpu = 0;
		//勝敗フラグ
		boolean isWin = false;
		boolean isLose = false;
		//デバッグモード切り替えフラグ
		boolean isLook = false;
		//プレイヤーの手
		int hand = 0;
		//入力用
		Scanner scanner = new Scanner(System.in);
		//勝敗結果
		int result = 0;
		
		//デバッグモード切り替え
		System.out.println("相手の手は見ますか? y:はい、n:いいえ");
		change = scanner.next();
		if(change.equals("y")) {
			isLook = true;
			System.out.println("デバッグモード");
		} else {
			isLook = false;
		}
		
		System.out.println("1〜3を入力してください。1:グー、2:チョキ、3:パー");
		
		while(!(isWin || isLose)) {
			cpu = getRand(limit);
			
			if(isLook) {
				System.out.println("相手の手 : " + hands[cpu-1]);
			}
			
			hand = setNumber();
			
			if(hand < 1 || 3 < hand) {
				System.out.println("該当しません。入力し直してください。");
				continue;
			}
			
			result = (hand - cpu + 3) % 3;
			
			switch(result) {
				case 0:
					System.out.println("あいこ");
					break;
					
				case 1:
					System.out.println("あなたの負け");
					isLose = true;
					break;
					
				case 2:
					System.out.println("あなたの勝ち");
					isWin = true;
					break;
			}
		}
		
		scanner.close();
	}
}
