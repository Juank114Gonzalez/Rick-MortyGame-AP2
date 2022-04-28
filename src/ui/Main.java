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

	public static void main(String[] args) {
		/**if (fileExists("data/UserData.txt")) {
			loadUserData();
		}*/
		int rickIndex;
		int mortyIndex;
		System.out.println("Rick: ");
		rickIndex = login();
		System.out.println("Morty: ");
		mortyIndex = login();
		initBoard(rickIndex,mortyIndex);
		System.out.println(board.showBoard());
		//showMenu();
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
		
		if(!fileExists("data/UserData.txt")) {
			UserData.userData.add(newPlayer);
			saveAsJavaByteCode();
		}else if (!verifyUserExists(nickname)) {
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
	 * This method saves all the data contained on the ArrayList "CineController.userData"
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

	public static void showMenu() {
		boolean exit = false;
		int opcion = 0;

		while (exit != true) {
			System.out.println("---What do u wanna do?----\n" + "1. Roll dice\n" + "2. See board\n" + "3. See links\n"
					+ "4. Marker\n" + "0. Exit\n" +

					"\nEnter an option\n");
			opcion = sc.nextInt();

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

	public static void initBoard(int rickIndex, int mortyIndex) {

		int n, m, p, q;
		System.out.println("Enter the number of rows");
		n = sc.nextInt();
		System.out.println("Enter the number of columns");
		m = sc.nextInt();
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

		board = new Board(rickIndex,mortyIndex,n,m,q,p);
		
	}

}
