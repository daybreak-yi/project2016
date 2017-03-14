package mobile.phone.company;

import java.io.*;
import java.util.Scanner;

class display{
	 int display(){
		 System.out.println(baseprice);
		 System.out.println(callcharge);
		 System.out.println(useprice);
		
	}
}
public class PhoneCalcMain {


	public static void main(String[] args) {
		Scanner username = new Scanner(System.in);
		Scanner userage = new Scanner(System.in);
		Scanner profession = new Scanner(System.in);
		Scanner calltime =  new Scanner(System.in);
		
	
		
		final CalcTalkFee calcTalkFee = new CalcTalkFee();
		calcTalkFee.exec();
	}


	
}
