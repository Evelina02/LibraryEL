package by.library.scanner;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileScanner {
	
	@SuppressWarnings("resource")
	public static String enterStringFromFile(String str) throws IOException{		
			
			FileReader fr = new FileReader(str);
			Scanner sc = new Scanner(fr);
			String result = "";
			String a = "";		
			
			while(sc.nextLine()!= "\n")//??????
			{
				a = sc.nextLine();
				
				result = result + a + " ";
			}
			
			fr.close();
			return result;
		}
}

