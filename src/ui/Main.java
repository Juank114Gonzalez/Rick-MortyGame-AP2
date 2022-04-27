package ui;
import java.util.Scanner;

public class Main {
	static Scanner  sc = new Scanner (System.in);
	
	public static void main(String[] args) {
		initBoard();
		showMenu();
	}
	
	public static void showMenu() {
		boolean exit = false;
		int opcion = 0;
		
		while(exit != true) {
			System.out.println("---What do u wanna do?----\n"+
					"1. Roll dice\n"+
					"2. See board\n"+
					"3. See links\n"+
					"4. Marker\n"+
					"0. Exit\n"+
					
					"\nEnter an option\n");
			opcion = sc.nextInt ();
			
			switch (opcion) {
			
			case 1:
				throwDice();
				break;
			case 2:
				showBorard();
				break;
			case 3:
				showLinks();
				break;
			case 4:
				showScore();
				break;
			case 0:
				exit = true;
				break;
			}	
		}
	}

	public static void throwDice() {
		
	}
	
	public static void showBorard() {
		
	}

	public static void showLinks() {
		
	}

	public static void showScore() {
		
	}
	
	public static void moveAlong() {
		
	}
	
	public static void moveBack() {
		
	}
	
	
	public static void initBoard() {
		
		System.out.println("Enter the number of rows");
		int n = sc.nextInt();	
		System.out.println("Enter the number of columns");
		int m = sc.nextInt();
		System.out.println("Enter the quantity of seeds");
		int q = sc.nextInt();
		System.out.println("Enter the quantity of links");
		int p = sc.nextInt();
		
		
	}

}
