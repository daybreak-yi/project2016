package sample;

public abstract class SampleBaseClass implements SampleIntf {

	private static final String CLASS_NAME = SampleBaseClass.class.getSimpleName();

	protected boolean flg;

	@Override
	public int exec() {

		System.out.println(CLASS_NAME + "." + "exec");

		System.out.println(CLASS_NAME + "." + "flg=" + flg);
		if (this.flg) {
			winnerExec();
			return 0;
		}

		loseExec();
		return 0;
	}

	public abstract void winnerExec();

	public abstract void loseExec();

}
