package question1;

public abstract class Question1BaseClass {

	private boolean flg;

	/** デフォルトコンストラクタ */
	public Question1BaseClass(final boolean flg) {
		this.flg = flg;
	}

	public int exec() {

		if (this.flg) {
			System.out.println(winnerExec());
			return 1;
		}

		System.out.println(loseExec());
		return 0;
	}

	public abstract String winnerExec();

	public abstract String loseExec();

}
