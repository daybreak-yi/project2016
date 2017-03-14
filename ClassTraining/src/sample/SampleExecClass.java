package sample;

public class SampleExecClass extends SampleBaseClass {

	private static final String CLASS_NAME = SampleExecClass.class.getSimpleName();

	public SampleExecClass(final boolean flg) {
		System.out.println(CLASS_NAME + "." + "flg=" + flg);
		this.flg = flg;
	}

	@Override
	public void winnerExec() {
		System.out.println(CLASS_NAME + "." + "winnerExec");
		System.out.println("\tあなたは勝利しました");
	}

	@Override
	public void loseExec() {
		System.out.println(CLASS_NAME + "." + "winnerExec");
		System.out.println("\tあなたは敗北しました");
	}

}
