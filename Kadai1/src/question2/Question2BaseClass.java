package question2;

import java.math.BigDecimal;

public abstract class Question2BaseClass {

	/**
	 * 計算実行
	 * 
	 * @param amount 単価
	 * @param quantity 数量
	 * @return 単価、数量、割引、送料、消費税を加味した合計金額
	 */
	public int exec(final int amount, final int quantity) {

		// 単価*数量で仮合計金額を計算する
		int total = amount * quantity;

		// セールを適用する
		total = saleCalc(amount, quantity);

		// 消費税を加算する
		total = total + new BigDecimal(total * 0.08).intValue();
		
		// 割引後の合計金額が2000円以上の場合、送料無料
		int soryo = 500;
		if (total > 2000) {
			soryo = 0;
		}

		return total;
	}
	
	/** セール価格を適用する */
	protected abstract int saleCalc(int amount, int quantity);

}
