package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import model.Board;
import model.Player;
import model.Token;
import model.UserData;

/**
 * This is the main class, it will execute all the operations of the video game
 * "Rick and Morty"
 * 
 * @author Gabriel Restrepo
 * @author Yessica Santander
 * @author Camilo Gonzalez
 *
 */
public class Main {
	private static Board board;
	static Scanner sc = new Scanner(System.in);

	/**
	 * Main method of JAVA
	 * 
	 * @param args, String[], contains the information supplied to the command line
	 *              arguments as an Array of String objects.
	 */
	public static void main(String[] args) {
		/**
		 * if (fileExists("data/UserData.txt")) { loadUserData(); }
		 */
		int rickIndex;
		int mortyIndex;
		System.out.println("Rick: ");
		rickIndex = login();
		System.out.println("Morty: ");
		mortyIndex = login();
		initBoard(rickIndex, mortyIndex);
		showMenu();
	}

	/**
	 * This method asks for the user its <b>nickname</b>, in order to verify if it
	 * is currently registered. If the user is'n registered yet, it will be added to
	 * the static variable <b>dataUser</b>
	 * 
	 * @return playerIndex, int, this is the index of the player that is trying to
	 *         login, it will be the size of the array if its a new player,
	 *         otherwise, it will correspond to its first occurrence into the array
	 */
	private static int login() {
		// TODO Auto-generated method stub

		System.out.println("Type your username: ");
		String nickname = sc.next();
		Player newPlayer = new Player(nickname);

		if (!fileExists("data/UserData.txt")) {
			UserData.userData.add(newPlayer);
			saveAsJavaByteCode();
		} else if (!verifyUserExists(nickname)) {
			UserData.userData.add(newPlayer);
		}

		int playerIndex = UserData.userData.indexOf(newPlayer);
		return playerIndex;

	}

	/**
	 * This method verifies if the user with the nickname entered as a parameter
	 * exists.
	 * 
	 * @param nickname, String, this is the nickname of the player to be found
	 * @return True, it returns true if the user with the nickname exists, false
	 *         otherwise
	 */
	private static boolean verifyUserExists(String nickname) {
		for (int i = 0; i < UserData.userData.size(); i++) {
			if (UserData.userData.get(i).getNickName().equals(nickname)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method confirms if the file named "UserData" exists
	 * 
	 * @return out, true if the data file exists
	 */
	public static boolean fileExists(String path) {
		boolean out = false;
		File aux = new File(path);
		if (aux.exists()) {
			out = true;
		}
		return out;

	}

	/**
	 * This method saves all the data contained on the ArrayList
	 * "CineController.userData"
	 */
	public static void saveAsJavaByteCode() {
		try {
			ArrayList<Player> userList = UserData.userData;
			File ref = new File("data/UserData.txt");
			FileOutputStream fos = new FileOutputStream(ref);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This Method loads the serialized information in the static variable
	 * UserData.UserData for its later use in execution
	 */
	public static void loadUserData() {

		try {
			File file = new File("data/UserData.txt");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Player> userList = (ArrayList<Player>) ois.readObject();
			UserData.userData = userList;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * This is the method in charge of showing the different action options of the
	 * system, it also reads what the user wants to do according to the numbers
	 * assigned to each of the options
	 */
	public static void showMenu() {
		boolean exit = false;
		int opcion = 0;

		while (exit != true) {
			System.out.println("---What do u wanna do?----\n" + "1. Roll dice\n" + "2. See board\n" + "3. See links\n"
					+ "4. Scoreboard \n" + "0. Exit\n" +

					"\nEnter an option\n");
			opcion = sc.nextInt();

			switch (opcion) {

			case 1:
				throwDice();
				break;
			case 2:
				showBoard();
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
			default:
				System.out.println("Please, type a valid option");
				break;
			}
		}
	}

	/**
	 * This method is responsible for generating a random integer between 1 and 6,
	 * representing the dice. When the die is generated and displayed, the user is
	 * asked if they want to move forward or backward.
	 */
	public static void throwDice() {
		int option = 1;
		int dice = 1 + (int) (Math.random() * 6);
		System.out.println(dice + " is the value of the dice!\n");

		do {
			if (option != 1 && option != 2) {
				System.out.println("Type a valid value :)");
			}
			System.out.println("Where should you move to?\n" + "1. Forward \n" + "2. Backward");

			option = sc.nextInt();
		} while (option != 1 && option != 2);

		switch (option) {
		case 1:
			moveForward(dice);
			break;

		case 2:
			moveBackward(dice);
			break;
		}

	}

	/**
	 * This method shows a graphical representation in ASCII characters of the game
	 * board.
	 */
	public static void showBoard() {
		System.out.println(board.showBoard());
	}

	/**
	 * This method shows a graphical representation in ASCII characters of the links
	 * of the game board.
	 */
	public static void showLinks() {

	}

	/**
	 * This method returns a historical score table of the users who have played the
	 * game
	 */
	public static void showScore() {
		// board.score();
	}

	/**
	 * This method triggers player movement forward
	 * 
	 * @param diceValue, int, Number of times the player will move forward
	 */
	public static void moveForward(int diceValue) {
		board.getBoard().movePlayerForward(diceValue, new Token('M'));
		System.out.println(board.showBoard());
	}

	/**
	 * This method triggers player movement backward
	 * 
	 * @param diceValue, int, Number of times the player will move backward
	 */
	public static void moveBackward(int diceValue) {
		board.getBoard().movePlayerBackward(diceValue, new Token('M'));
		System.out.println(board.showBoard());
	}

	/**
	 * This method reads the parameters that the user wants their board to have.
	 * 
	 * @param rickIndex,  int, this is the index of the player 1, "Rick", that is
	 *                    trying to login, it will be the size of the array if its a
	 *                    new player, otherwise, it will correspond to its first
	 *                    occurrence into the array
	 * @param mortyIndex, int, this is the index of the player 2, "Morty", that is
	 *                    trying to login, it will be the size of the array if its a
	 *                    new player, otherwise, it will correspond to its first
	 *                    occurrence into the array
	 */
	public static void initBoard(int rickIndex, int mortyIndex) {

		int n, m, p, q;
		System.out.println("Enter the number of rows");
		m = sc.nextInt();
		System.out.println("Enter the number of columns");
		n = sc.nextInt();
		do {
			System.out.println("Enter the quantity of links");
			p = sc.nextInt();
			if (p > (n * m) / 2) {
				System.out.println("The amount of links cannot be less than " + (n * m) / 2);
			}
		} while (p > (n * m) / 2);

		System.out.println("Enter the quantity of seeds");
		do {
			q = sc.nextInt();
		} while (q >= n * m);

		board = new Board(rickIndex, mortyIndex, m, n, q, p);

	}

}
