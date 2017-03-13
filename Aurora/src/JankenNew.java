import java.util.Scanner;
import java.util.Random;

public class JankenNew {
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		//自分の手
		int hand = 0;
		//相手の手
		int cpu = 0;
		//デバッグモード変更用
		String changeStr = new String();
		String[] handNames = {"グー", "チョキ", "パー"};
		//デバッグモード用フラグ
		boolean isDebug = false;
		
		//デバッグモード変更
		System.out.println("デバッグモードにしますか? （y:はい、n:いいえ）");
		changeStr = scanner.next();
		if(changeStr.equals("y")) {
			isDebug = true;
		}
			
		while(true) {
			Random random = new Random(System.currentTimeMillis() + Runtime.getRuntime().freeMemory());
			cpu = random.nextInt(3) + 1;
			
			if(isDebug) {
				System.out.println("相手の手は" + handNames[cpu - 1]);
			}
			
			System.out.println("1〜3を入力してください。（1:グー、2:チョキ、3:パー）");
			hand = scanner.nextInt();
			
			if(hand == cpu) {
				System.out.println("あいこ");
			} else if(hand == 1 && cpu == 2) {
				System.out.println("勝ち");
				break;
			} else if(hand == 1 && cpu == 3) {
				System.out.println("負け");
				break;
			} else if(hand == 2 && cpu == 1) {
				System.out.println("負け");
				break;
			} else if(hand == 2 && cpu == 3) {
				System.out.println("勝ち");
				break;
			} else if(hand == 3 && cpu == 1) {
				System.out.println("勝ち");
				break;
			} else if(hand == 3 && cpu == 2) {
				System.out.println("負け");
				break;
			} else if(hand < 1 || 3 < hand) {
				System.out.println("入力しなおしてください。");
			}
			
		}
	}

}
