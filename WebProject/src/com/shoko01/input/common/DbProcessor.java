package com.shoko01.input.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.shoko01.input.dto.BaseDTO;

public class DbProcessor {

	/** デフォルトコンストラクタ */
	private DbProcessor() {

	}

	/**
	 * SELECT文を実行する
	 * 
	 * @param statement {@link Statement}
	 * @param sql SQL
	 * @param clazz データクラスの型
	 * @return SQL実行結果のリスト
	 * @throws SQLException
	 */
	public static <T extends BaseDTO> List<T> select(final Statement statement, final String sql, final Class<T> clazz) throws SQLException {
		ResultSet resultSet = null;
		try {
			final long startTime = System.nanoTime();
			resultSet = statement.executeQuery(sql);
			final long execTime = System.nanoTime() - startTime;
			final long fetchSize = new BigDecimal(resultSet.getFetchSize()).longValue();
			long rownum = 0L;
			final List<T> list = new ArrayList<T>();
			while (resultSet.next()) {
				T obj = clazz.newInstance();
				obj.setSql(sql);
				obj.setNanoTime(execTime);
				obj.setTotalRecords(fetchSize);
				obj.setRownum(rownum);

				// T型のプライベートフィールド一覧を取得する
				final List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
				for (final Field field : fields) {
					// アクセサなし、またはpublicのフィールドを無視する
					final int fieldModifiers = field.getModifiers();
					if (!(Modifier.isPrivate(fieldModifiers) || Modifier.isProtected(fieldModifiers))) {
						continue;
					}

					// Staticフィールドを無視する
					if (Modifier.isStatic(fieldModifiers)) {
						continue;
					}

					// 基本的な情報を取得する
					final String fieldName = field.getName();
					final String wkFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					final Class<?> fieldType = field.getType();
					final String setterName = String.format("set%s", wkFieldName);
					final Method setterMethod = clazz.getDeclaredMethod(setterName, fieldType);

					// フィールド名をスネークケースに変換する
					final String columnName = camelToSnake(fieldName).toUpperCase();

					// Javaの型に合わせてDBから取得する
					if (fieldType == String.class) {
						final String value = resultSet.getString(columnName);
						setterMethod.invoke(obj, value);
						continue;
					} else if (fieldType == boolean.class || fieldType == Boolean.class) {
						final Boolean value = resultSet.getBoolean(columnName);
						setterMethod.invoke(obj, value);
						continue;
					} else if (fieldType == int.class || fieldType == Integer.class) {
						final Integer value = resultSet.getInt(columnName);
						setterMethod.invoke(obj, value);
						continue;
					} else if (fieldType == short.class || fieldType == Short.class) {
						final Short value = resultSet.getShort(columnName);
						setterMethod.invoke(obj, value);
						continue;
					} else if (fieldType == long.class || fieldType == Long.class) {
						final Long value = resultSet.getLong(columnName);
						setterMethod.invoke(obj, value);
						continue;
					} else if (fieldType == float.class || fieldType == Float.class) {
						final Float value = resultSet.getFloat(columnName);
						setterMethod.invoke(obj, value);
						continue;
					} else if (fieldType == double.class || fieldType == Double.class) {
						final Double value = resultSet.getDouble(columnName);
						setterMethod.invoke(obj, value);
						continue;
					} else if (fieldType == byte.class || fieldType == Byte.class) {
						final Byte value = resultSet.getByte(columnName);
						setterMethod.invoke(obj, value);
						continue;
					} else if (fieldType == Date.class) {
						final Date value = resultSet.getDate(columnName);
						setterMethod.invoke(obj, value);
						continue;
					} else if (fieldType == Time.class) {
						final Time value = resultSet.getTime(columnName);
						setterMethod.invoke(obj, value);
						continue;
					} else if (fieldType == BigDecimal.class) {
						final BigDecimal value = resultSet.getBigDecimal(columnName);
						setterMethod.invoke(obj, value);
						continue;
					}
				}

				list.add(obj);

				// 次のループ用処理
				rownum++;
			}
			return list;
		} catch (final InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (final Throwable e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * キャメルケースをスネークケースに変換する。
	 * 
	 * @param camel キャメル文字列
	 * @return スネークケース
	 */
	private static final String camelToSnake(final String camel) {
		if (camel == null || camel.isEmpty()) {
			return camel;
		}
		final StringBuilder sb = new StringBuilder(camel.length() + camel.length());
		for (int i = 0; i < camel.length(); i++) {
			final char c = camel.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(sb.length() != 0 ? '_' : "").append(Character.toLowerCase(c));
			} else {
				sb.append(Character.toLowerCase(c));
			}
		}
		return sb.toString();
	}

}
