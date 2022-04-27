package ui;
import java.util.Scanner;

public class Main {
	static Scanner  sc = new Scanner (System.in);
	//static List list = new List();
	//static Board board = new Board();
	public static void main(String[] args) {
		initBoard();
		showMenu();
	}
	
	public static void showMenu() {
		boolean exit = false;
		int opcion = 0;
		
		while(exit != true) {
			System.out.println("---¿Qué deseas hacer?----\n"+
					"1. Roll dice\n"+
					"2. See board\n"+
					"3. See links\n"+
					"4. Marker\n"+
					
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
	}

}
