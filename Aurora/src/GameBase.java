import java.util.Random;
import java.util.Scanner;

public class GameBase {
	
	public static int getRand(int number) {
		//乱数の種
		long seed = System.currentTimeMillis() + Runtime.getRuntime().freeMemory();
		Random random = new Random(seed);
		
		int num = random.nextInt(number) + 1;
		
		return num;
	}
	
	public static int setNumber() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		return num;
		
	}

}
