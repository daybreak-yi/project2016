//フィボナッチ数列を算出します
public class Fibonacci {
	public static void main(String[] args) {
		//Fn+2 = Fn + Fn+1
		//フィボナッチ数列の公式にちなんだ名前にしています
		int f0 = 0;
		int f1 = 1;
		int fn = 1;

		while (fn < 1000) {
			
			System.out.println(fn);
			
			fn = f0 + f1;
			
			f0 = f1;
			f1 = fn;

		}
	}
}
