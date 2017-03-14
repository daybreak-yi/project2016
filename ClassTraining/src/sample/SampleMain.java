package sample;

public class SampleMain {

	public static void main(String[] args) {
		{
			System.out.println("■■■勝利テスト■■■");
			final SampleIntf obj = new SampleExecClass(true);
			final int returnValue = obj.exec();
			System.out.println(returnValue);
		}
		
		{
			System.out.println("■■■敗北テスト■■");
			final SampleIntf obj = new SampleExecClass(false);
			final int returnValue = obj.exec();
			System.out.println(returnValue);
		}
	}
}
