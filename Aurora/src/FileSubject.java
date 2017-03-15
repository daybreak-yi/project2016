import java.io.*;

public class FileSubject {
	private static File file2;

	public static void main(String[] args) throws Exception {
		/*
		 * 1 try{ BufferedReader br = new BufferedReader(new
		 * FileReader("//home/admin/Documents/file_伊藤.txt")); String str1 =
		 * br.readLine(); String str2 = br.readLine(); System.out.println(str1);
		 * System.out.println(str2); br.close(); } catch(IOException e){
		 * System.out.println("入出力エラーです。"); }
		 */

		/*
		  2 File newfile = new File("//home/admin/Documents/伊藤.txt"); 
		  try{
		  newfile.createNewFile(); PrintWriter pw = new PrintWriter(new
		  BufferedWriter(new FileWriter("//home/admin/Documents/伊藤.txt")));
		  pw.println("名前:伊藤佑真"); pw.println("自己紹介文:伊藤佑真です。これからよろしくお願いします");
		  pw.close(); 
		  } 
		  catch(IOException e){
		   System.out.println("出力エラーです。"); 
		  }
		 */
		/*
		  try{ BufferedReader br = new BufferedReader(new
		  FileReader("//home/admin/Documents/file_伊藤.txt")); PrintWriter pw =
		  new PrintWriter(new BufferedWriter(new
		  FileWriter("//home/admin/Documents/file_伊藤.txt",true)));
		 
		  pw.println("出席者:伊藤佑真");
		  pw.println("講義内容:IF文とFOR文の練習、FILE処理の読み込みと出力");
		  pw.println("感想:今までの課題ならついていけています。"); br.close(); pw.close(); }
		  catch(IOException e){ System.out.println("入出力エラーです。"); }
		 */
		
		File file1 = new File("//home/admin/Desktop/copy");
		
		if (file1.exists()) {
			File new1 = new File("//home/admin/Desktop/copy1");
			new1.mkdir();
			file2 = new File("//home/admin/Desktop/copy1/copy.txt");
			file2.createNewFile();
		} else {
        	File new2 = new File("//home/admin/Desktop/copy");
			new2.mkdir();
			file2 = new File("//home/admin/Desktop/copy/copy.txt");
			file2.createNewFile();
        }
		
		if(file2.exists()){	
			String reed = "//home/admin/Documents/file_伊藤.txt";	
			try
			{
				File f = new File(reed);
				byte[] b = new byte[(int) f.length()];
				FileInputStream fi = new FileInputStream(f);
			
				String s;
				fi.read(b);	
				s = new String(b);
				
				fi.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}	
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
			pw.println(reed);
			pw.close();
			
		
		}
		
		
			
		
		

	}
}
