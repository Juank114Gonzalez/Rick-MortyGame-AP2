package model;

public class Board {

	private int rick; // this is the index of the player which moves as rick on the game
	private int morty; // this is the index of the player which moves as morty on the game
	private int n; // this is the amount of rows
	private int m; // this is the amount of columns

	private LinkedList board = new LinkedList(n*m);
	
	/**
	 * This is the constructor method for the class Board
	 * @param rick, Player, this is the player which is going to move over the table as Rick 
	 * @param morty, Player, this is the player which is going to move over the table as Morty
	 * @param n, int, this is the amount of rows
	 * @param m, int, this is the amount of columns
	 * @param q, int, this is the amount of seeds over the board 
	 * @param p, int, this is the amount of links over the boards
	 */
	public Board(int rick, int morty, int n, int m, int q, int p) {
		this.rick = rick;
		this.morty = morty;
		createBoard(n,m,q,p);
		
	}
	
	public void createBoard(int n, int m, int q, int p) {
		// int n, int seeds, int links
		board.init((n * m), q, p);
	}


	/**
	 * @return the rick
	 */
	public int getRick() {
		return rick;
	}

	/**
	 * @param rick the rick to set
	 */
	public void setRick(int rick) {
		this.rick = rick;
	}

	/**
	 * @return the morty
	 */
	public int getMorty() {
		return morty;
	}

	/**
	 * @param morty the morty to set
	 */
	public void setMorty(int morty) {
		this.morty = morty;
	}

	/**
	 * @return the n
	 */
	public int getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}

	/**
	 * @return the m
	 */
	public int getM() {
		return m;
	}

	/**
	 * @param m the m to set
	 */
	public void setM(int m) {
		this.m = m;
	}

	public LinkedList getBoard() {
		return board;
	}

	public void setBoard(LinkedList board) {
		this.board = board;
	}

	public String showBoard() {
		return board.toString(m);
	}

	public void showLinks() {

	}
	
	

}
