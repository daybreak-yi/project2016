package jp.com.daybreak;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 営業日カレンダー
 */
public class DayBreakCalendar {

	/**
	 * 営業日カレンダーを作成する
	 * 
	 * @param yearMonth YYYYMM形式の半角数字
	 * @return 営業日カレンダー文字列
	 * @throws Exception Exception
	 */
	public String execution(final String yearMonth) throws Exception {
		final StringBuffer stringBuffer = new StringBuffer();

		// 月初日を取得する
		Date firstDate = null;
		try {
			final DateFormat format = new SimpleDateFormat("yyyyMMdd");
			firstDate = format.parse(yearMonth + "01");
		} catch (final Throwable e) {
			throw new Exception("不正な文字列が入力されました。");
		}

		// 月初日を元に月末日を取得する
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		final Date lastDate = calendar.getTime();

		// ヘッダー情報を出力
		final String year = yearMonth.substring(0, 4);
		final String month = yearMonth.substring(4);
		stringBuffer.append(year);
		stringBuffer.append("年");
		stringBuffer.append(month);
		stringBuffer.append("月");
		stringBuffer.append(System.lineSeparator());
		stringBuffer.append("日 月 火 水 木 金 土");
		stringBuffer.append(System.lineSeparator());
		stringBuffer.append("--------------------");
		stringBuffer.append(System.lineSeparator());

		// 月初日の曜日を取得する
		// 日曜日:1
		// 月曜日:2
		// 火曜日:3
		// 水曜日:4
		// 木曜日:5
		// 金曜日:6
		// 土曜日:7
		calendar.setTime(firstDate);
		final int firstWeekDate = calendar.get(Calendar.DAY_OF_WEEK);

		// 月初日の曜日に合わせてスペースを挿入
		for (int i = 1; i < firstWeekDate; i++) {
			stringBuffer.append("   ");
		}

		Date currentDate = firstDate;
		do {
			// 表示用の文字列を取得する
			calendar.setTime(currentDate);
			final int date = calendar.get(Calendar.DATE);
			final String printWord = String.format("%02d", date);
			stringBuffer.append(printWord);

			// 曜日を取得する
			calendar.setTime(currentDate);
			final int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
			if (weekDay == 7) {
				stringBuffer.append(System.lineSeparator());
			} else {
				stringBuffer.append(" ");
			}

			// 1日加算する
			calendar.setTime(currentDate);
			calendar.add(Calendar.DATE, 1);
			currentDate = calendar.getTime();

		} while (currentDate.compareTo(lastDate) <= 0);

		return stringBuffer.toString();
	}
}
