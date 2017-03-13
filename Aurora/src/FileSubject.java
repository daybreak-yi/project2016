import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileSubject {
	/*
	 * public static void main(String[] args) { try { BufferedReader br = new
	 * BufferedReader(new FileReader("//home/admin/Documents/file_石井.txt"));
	 * String[] str = new String[2]; for(int i=0; i < str.length; i++) { str[i]
	 * = br.readLine(); System.out.println(str[i]); } br.close(); }
	 * catch(IOException e) { System.out.println("入出力エラーです"); } }
	 */

	/*
	 * public static void main(String[] args) { try { File file = new
	 * File("//home/admin/Documents/石井.txt"); file.createNewFile(); PrintWriter
	 * pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	 * pw.println("名前:石井和人"); pw.println("自己紹介文:よろしくお願いします");
	 * System.out.println("ファイルに書き込みました"); pw.close(); } catch(IOException e) {
	 * System.out.println("入出力エラーです"); } }
	 */

	/*
	 * public static void main(String[] args) { try { File file = new
	 * File("//home/admin/Documents/file_石井.txt"); PrintWriter pw = new
	 * PrintWriter(new BufferedWriter(new FileWriter(file, true)));
	 * pw.println("出席者:石井和人"); pw.println("講義内容:IF文とFOR文の練習、FILE処理の読込と出力");
	 * pw.println("感想:前回と今回の条件分岐と繰り返しから、いきなりファイル処理になって少々驚きましたが、" +
	 * "なんとか、やれました。次回でいきなり難易度が跳ね上がるといったことがないことを切に望みます。");
	 * System.out.println("ファイルに追記しました"); pw.close(); } catch(IOException e) {
	 * System.out.println("入出力に失敗しました"); } }
	 */

	public static void main(String[] args) {
		//ディレクトリ
		String copyDir = "//home/admin/Documents/copy/";
		//コピーしたファイル名
		String copyFileName;
		//コピー元
		final File origine = new File("//home/admin/Documents/file_石井.txt");
		File copy     = new File(copyDir);
		//ファイルのリスト
		File[] files = copy.listFiles();
		
		Scanner sc = new Scanner(System.in);
		
		StringBuffer buf = new StringBuffer();
		
		try {
			//ディレクトリ作成
			copy.mkdir();
			
			//コピー元の有無
			if(!origine.exists()) {
				System.out.println("コピー元がありません。終了します");
				System.exit(0);
			}
			
			//ファイルの名前変更処理
			while(true) {
				boolean isCopy = true;
				buf.setLength(0);
				String tmpStr;
				System.out.println("ファイル名を入力してください(拡張子あり)");
				copyFileName = sc.nextLine();
				buf.append(copyDir);
				buf.append(copyFileName);
				
				if(files != null) {
					for (File tmp : files) {
						tmpStr = tmp.getName();
						if (tmpStr.equals(copyFileName)) {
							isCopy = false;
							System.out.println("同名ファイルが存在します");
						} else {
							break;
						}
					}
				}
				
				if(isCopy) {
					break;
				}
			}
			
			copy = new File(buf.toString());
			
			copyFile(origine, copy);
			
			//内容表示
			char[] data = new char[2048];
			FileReader fr = new FileReader(copy);
			fr.read(data);
			fr.close();
			
			for(char ch : data) {
				System.out.print(ch);
			}
			
			//追記
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(copy,true)));
			pw.println("\n2016年12月15日");
			pw.println("講師：八辻");
			pw.println("出席者：石井和人");
			
			pw.close();
			
			origine.delete();
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			
			
		}
	}

	//ファイルコピー用メソッド
	public static void copyFile(File in, File out) throws IOException {
		FileInputStream input = new FileInputStream(in);
		FileOutputStream output = new FileOutputStream(out);

		try {
			byte[] buf = new byte[2048];
			int i = 0;

			while ((i = input.read(buf)) != -1) {
				output.write(buf, 0, i);
			}

		} catch (IOException e) {
			throw e;

		} finally {
			input.close();
			output.close();
		}
	}
}
