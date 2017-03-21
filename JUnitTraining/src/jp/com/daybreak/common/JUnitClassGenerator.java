package jp.com.daybreak.common;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.com.daybreak.DayBreakCalendar;

public class JUnitClassGenerator {

	public static void main(String[] args) {
		exec(DayBreakCalendar.class);
	}

	// ここから下は触らないこと
	/** LINE_SEPARATOR */
	protected static String LINE_SEPARATOR = System.lineSeparator();

	/** CLASS_TEMPLATE */
	protected static String CLASS_TEMPLATE = ""
			+ "{PACKAGE};" + LINE_SEPARATOR 
			+ "" + LINE_SEPARATOR
			+ "import static org.junit.Assert.assertEquals;" + LINE_SEPARATOR
			+ "import static org.junit.Assert.fail;"+ LINE_SEPARATOR
			+ "" + LINE_SEPARATOR 
			+ "import org.junit.Test;" + LINE_SEPARATOR 
			+ "" + LINE_SEPARATOR + "/**" + LINE_SEPARATOR
			+ " * {CLASS_NAME}のテスト" + LINE_SEPARATOR 
			+ " */" + LINE_SEPARATOR 
			+ "public class {CLASS_NAME}Test {" + LINE_SEPARATOR 
			+ "" + LINE_SEPARATOR 
			+ "{METHOD_AREA}" + LINE_SEPARATOR 
			+ "" + LINE_SEPARATOR 
			+ "}" + LINE_SEPARATOR;

	/** METHOD_TEMPLATE */
	protected static String METHOD_TEMPLATE = ""
			+ "	/**" + LINE_SEPARATOR
			+ "	 * {METHOD_NAME}のテスト" + LINE_SEPARATOR
			+ "	 */" + LINE_SEPARATOR
			+ "	@Test" + LINE_SEPARATOR
			+ "	public void test{METHOD_NAME2}() {" + LINE_SEPARATOR 
			+ "		try {" + LINE_SEPARATOR
			+ "			/* 変数初期化 */" + LINE_SEPARATOR
			+ "{PARAMS_AREA}" + LINE_SEPARATOR
			+ "" + LINE_SEPARATOR 
			+ "			/* テスト実行部 */" + LINE_SEPARATOR
			+ "{EXEC_AREA}" + LINE_SEPARATOR 
			+ "" + LINE_SEPARATOR 
			+ "			/* 比較部 */" + LINE_SEPARATOR
			+ "			assertEquals({EXPECTED}, result);" + LINE_SEPARATOR
			+ "{EXCEPTION_AREA}"
			+ "		} catch(final Throwable e) {" + LINE_SEPARATOR
			+ "			fail(\"想定しないエラーが発生\");" + LINE_SEPARATOR
			+ "		} finally {" + LINE_SEPARATOR
			+ "			// 必要に応じて処理を記載" + LINE_SEPARATOR
			+ "		}" + LINE_SEPARATOR
			+ "	}" + LINE_SEPARATOR + LINE_SEPARATOR;

	/** PACKAGE */
	protected static String PACKAGE = "{PACKAGE}";

	/** CLASS_NAME */
	protected static String CLASS_NAME = "{CLASS_NAME}";

	/** METHOD_AREA */
	protected static String METHOD_AREA = "{METHOD_AREA}";

	/** METHOD_NAME */
	protected static String METHOD_NAME = "{METHOD_NAME}";

	/** METHOD_NAME2 */
	protected static String METHOD_NAME2 = "{METHOD_NAME2}";

	/** PARAMS_AREA */
	protected static String PARAMS_AREA = "{PARAMS_AREA}";

	/** EXEC_AREA */
	protected static String EXEC_AREA = "{EXEC_AREA}";
	
	/** EXCEPTION_AREA */
	protected static String EXCEPTION_AREA = "{EXCEPTION_AREA}";
	
	/** EXPECTED */
	protected static String EXPECTED = "{EXPECTED}";

	public static void exec(final Class<?> clazz) {

		String template = CLASS_TEMPLATE;
		template = template.replace(PACKAGE, clazz.getPackage().toString());
		template = template.replace(CLASS_NAME, clazz.getSimpleName());

		// メソッド一覧の取得
		final StringBuffer methodBuffer = new StringBuffer();
		final List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());
		for (final Method method : methods) {

			final Class<?> returnType = method.getReturnType();
			if (returnType == void.class) {
				continue;
			}
			
			if (!Modifier.isPublic(method.getModifiers())) {
				continue;
			}

			String methodTemplate = METHOD_TEMPLATE;

			// メソッド名に関する処理
			final String name = method.getName();
			methodTemplate = methodTemplate.replace(METHOD_NAME, name);
			methodTemplate = methodTemplate.replace(METHOD_NAME2, name.substring(0, 1).toUpperCase() + name.substring(1));

			// 変数初期化
			final List<String> variables = new ArrayList<String>();
			final StringBuffer paramsBuffer = new StringBuffer();
			final List<Class<?>> params = Arrays.asList(method.getParameterTypes());
			int i = 1;
			for (final Class<?> paramClazz : params) {
				final String variable;
				if (paramClazz == int.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = 0;", paramClazz.getSimpleName(), variable));
					continue;
				}

				if (paramClazz == long.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = 0l;", paramClazz.getSimpleName(), variable));
					continue;
				}

				if (paramClazz == float.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = 0.0f;", paramClazz.getSimpleName(), variable));
					continue;
				}

				if (paramClazz == double.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = 0.0d;", paramClazz.getSimpleName(), variable));
					continue;
				}

				if (paramClazz == boolean.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = false;", paramClazz.getSimpleName(), variable));
					continue;
				}

				if (paramClazz == Integer.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = 0;", paramClazz.getSimpleName(), variable));
					continue;
				}

				if (paramClazz == Long.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = 0l;", paramClazz.getSimpleName(), variable));
					continue;
				}

				if (paramClazz == Float.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = 0.0f;", paramClazz.getSimpleName(), variable));
					continue;
				}

				if (paramClazz == Double.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = 0.0d;", paramClazz.getSimpleName(), variable));
					continue;
				}

				if (paramClazz == Boolean.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = false;", paramClazz.getSimpleName(), variable));
					continue;
				}

				if (paramClazz == BigDecimal.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = BigDecimal.ZERO;", paramClazz.getSimpleName(), variable));
					continue;
				}

				if (paramClazz == String.class) {
					variable = String.format("arg%d", i);
					variables.add(variable);
					paramsBuffer.append(String.format("\t\t\tfinal %s %s = \"\";", paramClazz.getSimpleName(), variable));
					continue;
				}

				// それ以外の型
				variable = String.format("arg%d", i);
				variables.add(variable);
				paramsBuffer.append(String.format("\t\t\tfinal %s %s = new %s();", paramClazz.getSimpleName(), variable, paramClazz.getSimpleName()));
				continue;
			}
			methodTemplate = methodTemplate.replace(PARAMS_AREA, paramsBuffer.toString());

			// テスト実行部
			final StringBuffer execBuffer = new StringBuffer();
			if (Modifier.isStatic(method.getModifiers())) {
				// 静的メソッド
				execBuffer.append(String.format("\t\t\tfinal %s result = %s.%s(", returnType.getSimpleName(), clazz.getSimpleName(), name));
			} else {
				// インスタンスメソッド
				String w = "";
				w = w + clazz.getSimpleName().substring(0, 1).toLowerCase();
				w = w + clazz.getSimpleName().substring(1);
				execBuffer.append(String.format("\t\t\tfinal %s %s = new %s();", clazz.getSimpleName(), w, clazz.getSimpleName()));
				execBuffer.append(LINE_SEPARATOR);
				execBuffer.append(String.format("\t\t\tfinal %s result = %s.%s(", returnType.getSimpleName(), w, name));
			}

			if (variables.size() > 0) {
				for (int j = 0; j < variables.size() - 1; j++) {
					final String variable = variables.get(j);
					execBuffer.append(String.format("%s, ", variable));
				}
				execBuffer.append(String.format("%s);", variables.get(variables.size() -1)));
			} else {
				execBuffer.append(String.format(");", variables.get(variables.size())));
			}
			
			methodTemplate = methodTemplate.replace(EXEC_AREA, execBuffer.toString());

			// 比較部
			String expected = "";
			if (returnType == int.class) {
				expected = String.format("%s", "0");
			} else if (returnType == long.class) {
				expected = String.format("%s", "0l");
			} else if (returnType == float.class) {
				expected = String.format("%s", "0f");
			} else if (returnType == double.class) {
				expected = String.format("%s", "0d");
			} else if (returnType == boolean.class) {
				expected = String.format("%s", "false");
			} else if (returnType == Integer.class) {
				expected = String.format("%s", "0");
			} else if (returnType == Long.class) {
				expected = String.format("%s", "0l");
			} else if (returnType == Float.class) {
				expected = String.format("%s", "0f");
			} else if (returnType == Double.class) {
				expected = String.format("%s", "0d");
			} else if (returnType == BigDecimal.class) {
				expected = String.format("%s", "BigDecimal.ZERO");
			} else if (returnType == Boolean.class) {
				expected = String.format("%s", "false");
			} else if (returnType == String.class) {
				expected = String.format("%s", "\"ここに想定値を設定\"");
			} else {
				expected = String.format("%s", "null");
			}
			
			methodTemplate = methodTemplate.replace(EXPECTED, expected);
			
			// 例外
			final StringBuffer exceptionBuffer = new StringBuffer();
			final List<Class<?>> exceptionTypes = Arrays.asList(method.getExceptionTypes());
			int j = 1;
			for (final Class<?> exceptionType : exceptionTypes) {
				exceptionBuffer.append("\t\t");
				exceptionBuffer.append(String.format("} catch (final %s e%d) {", exceptionType.getSimpleName(), j));
				exceptionBuffer.append(LINE_SEPARATOR);
				exceptionBuffer.append("\t\t\t//ここに固有の例外処理を記載");
				exceptionBuffer.append(LINE_SEPARATOR);
				exceptionBuffer.append("\t\t\tfail(\"\");");
				exceptionBuffer.append(LINE_SEPARATOR);
				j++;
			}
			methodTemplate = methodTemplate.replace(EXCEPTION_AREA, exceptionBuffer.toString());
			methodBuffer.append(methodTemplate);
		}

		template = template.replace(METHOD_AREA, methodBuffer.toString());

		System.out.println(template);
	}
}
