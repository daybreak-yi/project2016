package jp.com.daybreak;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DayBreakCalendarTest {

	@Test
	public void testExecution() throws Exception {
		final DayBreakCalendar calendar = new DayBreakCalendar();
		final String result = calendar.execution("201703");

		StringBuffer actual = new StringBuffer();
		actual.append("2017年03月");
		actual.append(System.lineSeparator());
		actual.append("日 月 火 水 木 金 土");
		actual.append(System.lineSeparator());
		actual.append("--------------------");
		actual.append(System.lineSeparator());
		actual.append("         01 02 03 04");
		actual.append(System.lineSeparator());
		actual.append("05 06 07 08 09 10 11");
		actual.append(System.lineSeparator());
		actual.append("12 13 14 15 16 17 18");
		actual.append(System.lineSeparator());
		actual.append("19 20 21 22 23 24 25");
		actual.append(System.lineSeparator());
		actual.append("26 27 28 29 30 31 ");

		assertEquals(result, actual.toString());
	}

}
