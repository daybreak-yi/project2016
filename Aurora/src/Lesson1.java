public class Lesson1 {
	public static void main(final String[] args) {
		/*
		  int x = -2; 
		  if (x < 0) {
		   System.out.println("負"); 
		   } else {
		   System.out.println("正"); 
		   }
		 */

		/*
		 int x = 88; 
		  if (x % 2 == 0) { 
		  System.out.println("偶数"); 
		  }else {
		  System.out.println("奇数"); 
		  }
		*/
	
		/*
		int x = 2;
		int y = 5;
		
		if(x%y == 0){
			System.out.println(x/y);
		}else{
			System.out.println("割り切れません");
		}
		*/
		
		/*
		for(int n = 1; n <= 3; n++) {
			System.out.println("繰り返し処理 " + n);
		}
		*/
		
		/*
		for(int n = 2; n >= 0; n--) {
			System.out.println("繰り返し処理 " + n);
		}
		*/
		
		/*
		for(int n = 0; n < 100; n++) {
			if(n == 49) {
				System.out.println("50回");
				break;
			}
		}
		*/
		
		/*
		int x = 0;
		for(int n = 0; n < 10; n++) {
			if(n % 2 == 0) {
				x += n;
			} else {
				System.out.println(n);
			}
		}
		System.out.println("合計 " + x);
		*/
		
		int x = 0;
		for(int n = 0; n < 10; n++) {
			if(n % 2 == 0) {
				x += n;
			} else {
				continue;
			}
		}
		System.out.println("合計 " + x);
	}
}
