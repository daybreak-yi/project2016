//3のときにFizz 5のときにBuzz 両方あてはまるときはFizzBuzzと出力
public class FizzBuzz {
	public static void main(String[] args) {
		
		for (int i = 1; i <= 100; i++) {
			System.out.println(FizzBuzzCheck(i));
		}

	}
	
	//FizzBuzz判定用メソッド 
	public static String FizzBuzzCheck(int num){
		if(num % 15 == 0){
			return "FIzzBuzz";
		} else if(num % 5 == 0) {
			return "Buzz";
		} else if(num % 3 == 0) {
			return "Fizz";
		} else {
			return Integer.toString(num);
		}
	}
}
