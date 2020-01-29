package by.library.scanner;

import java.util.Scanner;

public class DataScanner {

	@SuppressWarnings("resource")
	public static String enterStringFromConsole(){		
			
			Scanner sc = new Scanner(System.in);
			
			String a = "";		
			
			while(!sc.hasNextLine()) {
				sc.next();
			}
			
			a=sc.nextLine();
			
			return a;
		}
	
	@SuppressWarnings("resource")
	public static int enterIntFromConsole(){		
			
			Scanner sc = new Scanner(System.in);
			
			int a = 0;		
			
			while(!sc.hasNextInt()) {
				sc.next();
			}
			
			a=sc.nextInt();
			
			return a;
		}
	
}