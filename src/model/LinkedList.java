package model;

public class LinkedList {

	// Head
	private Board board;
	private Node head;
	private int size;
	private Token rick = new Token('R',0);
	private Token morty = new Token('M',0);
	private int boxesQuant;
	private int seed = 0;


	public LinkedList(int boxesQuant) {
		this.boxesQuant = boxesQuant;
		setSize(0);
	}

	/**
	 * 
	 * This method initializes the board with the parameters that the user has
	 * chosen
	 * 
	 * @param n,     int, Number of boxes, given by the product between the rows and
	 *               the columns previously entered by the user
	 * @param seeds, int, Number of seeds that the user decided to generate on the
	 *               board
	 * @param links, int Number of links that the user decided to generate on the
	 *               board
	 */
	public void init(int n, int seeds, int links) {
		if (n == 0) {
			setValuesOfBoard(seeds, links);
			return;
		}
		size++;

		if (head == null) {
			boxesQuant = n;
			Node node = new Node(1);
			head = (Node) node;
			head.setNext(head);
			head.setPrev(head);
			init(n - 1, seeds, links);

		} else {
			Node node = new Node();
			Node tail = node;

			tail.setNext(head);
			tail.setPrev(head.getPrev());

			tail.setIndex(tail.getPrev().getIndex() + 1);

			head.getPrev().setNext(tail);
			head.setPrev(tail);
			init(n - 1, seeds, links);
		}
	}

	/**
	 * This method randomly generates the seeds and links on the board, and also
	 * randomly places Rick and Morty.
	 * 
	 * @param seeds, int, Number of seeds that the user decided to generate on the
	 *               board
	 * @param links, int Number of links that the user decided to generate on the
	 *               board
	 */
	private void setValuesOfBoard(int seeds, int links) {
		// TODO Auto-generated method stub
		putSeeds(seeds);
		generateLinks(links);
		setRickAndMorty();
	}

	/**
	 * This method randomly places Rick and Morty around the board.
	 */
	private void setRickAndMorty() {
		int i = 1 + (int) (Math.random() * boxesQuant - 1);
		int j = 1 + (int) (Math.random() * boxesQuant - 1);
		if (i != j && !get(head, i).getSeed() && !get(head, j).getSeed()) {
			get(head, i).setRick(rick);
			get(head, j).setMorty(morty);
		} else {
			setRickAndMorty();
		}

	}

	/**
	 * This method randomly generates the pairs of links that the user has decided
	 * to generate on the board
	 * 
	 * @param links, int Number of links that the user decided to generate on the
	 *               board
	 */
	private void generateLinks(int links) {
		int i = 1 + (int) (Math.random() * boxesQuant - 1);
		int j = 1 + (int) (Math.random() * boxesQuant - 1);

		if (links != 0) {
			if (get(head, i).getLink() != null || get(head, j).getLink() != null || i != j) {
				generateLinks(links);
			} else {
				get(head, i).setLink(get(head, j));
				get(head, j).setLink(get(head, i));
				generateLinks(links - 1);
			}
		}
	}

	/**
	 * This method randomly generates the seeds that the user has decided to
	 * generate on the board
	 * 
	 * @param seeds, int, Number of seeds that the user decided to generate on the
	 *               board
	 */
	private void putSeeds(int seeds) {
		int i = 1 + (int) (Math.random() * boxesQuant - 1);

		if (seeds != 0) {
			// If the node with the index i has already a seed, the program loops again
			if (get(head, i).getSeed()) {
				putSeeds(seeds);
			} else {
				get(head, i).setSeed(true);
				putSeeds(seeds - 1);
			}
		}
	}

	/**
	 * This method returns a node given the ordinal that identifies it
	 * 
	 * @param current, Node, This is the node over which the recursion iteration is
	 *                 being performed
	 * @param i,       int, This is the ordinal that identifies the node we want the
	 *                 method to return
	 * @return Node, Null if the node with the indicated ordinal is not found on the
	 *         board, otherwise it returns the node to which the ordinal belongs
	 */
	private Node get(Node current, int i) {
		if (current.getNext() != head) {
			if (current.getIndex() == i) {
				return current;
			}
			return get(current.getNext(), i);
		} else {
			return null;
		}

	}

	/**
	 * Trigger method of the to String method
	 * 
	 * @param m, int, stop condition
	 * @return it returns an ASCII representation of the board
	 */
	public String toString(int m, int n) {
		return toString(head, "", m, n, 0);
	}

	/**
	 * This method creates a graphical representation in ASCII characters of the
	 * board.
	 * 
	 * @param acc, String, ASCII representation of the board
	 * @param m,   int, columns quantity and stop condition of recursion
	 * @param i,   int, control variable for the recursion. It represents number of
	 *             rows
	 * @param j,   int, control variable for the recursion. It represents number of
	 *             columns
	 * @return
	 */
	public String toString(Node current, String acc, int m, int n, int j) {
		System.out.println(j);
		if (j == n) {
			return acc;
		}

		if (j % 2 == 0) {
			acc += getIndexesRight(current, 0, m, "") + "\n";
		} else {
			acc += getIndexesLeft(current, 0, m, "") + "\n";
		}

		return toString(increaseNode(current, 0, m), acc, m, n, j + 1);
	}

	/**
	 * 
	 * @param current
	 * @param i
	 * @param limit
	 * @return
	 */
	public Node increaseNode(Node current, int i, int limit) {
		if (i == limit) {
			return current;
		}
		return increaseNode(current.getNext(), i + 1, limit);
	}

	/**
	 * This method gets the index from a node until it
	 * 
	 * @param current
	 * @param i
	 * @return
	 */
	public String getIndexesRight(Node current, int i, int limit, String output) {
		if (i == limit) {
			return output;
		}
		output += valueOfNode(current);
		return getIndexesRight(current.getNext(), i + 1, limit, output);

	}

	/**
	 * This method gets the index from a node until it
	 * 
	 * @param current
	 * @param i
	 * @return
	 */
	public String getIndexesLeft(Node current, int i, int limit, String output) {
		if (i == limit) {
			return output;
		}
		output = valueOfNode(current) + output;
		return getIndexesLeft(current.getNext(), i + 1, limit, output);

	}

	/**
	 * This method gives a graphical representation of the value of Node: 1. The
	 * cells containing seeds are represented by a special ASCII character with the
	 * code 248 ("ø"). 2. If Rick is in the box, it is represented by a capital r
	 * ("R"). If Morty is in the box, it is represented by a capital m ("M"). 3. If
	 * both players, Rick and Morty, are in the same box, it will be represented
	 * with a special ASCII character, it has the code 127 ("").
	 * 
	 * @param node, Node, Node to which you want to represent its value
	 * 
	 * @return out, String, ASCII representation of the value of the variable node
	 */
	private String valueOfNode(Node node) {
		String out = "";
		boolean flag = false;
		if (node.getRick() != null && node.getMorty() != null) {
			char a = (char) 127;
			out = "" + a;
			flag = true;
		} else {
			if (node.getRick() != null) {
				out = "R";
			} else if (node.getMorty() != null) {
				out = "M";
			} else if (node.getSeed()) {
				char a = (char) 248;
				out = "" + a;
			} else {
				out = node.getIndex() + "";
			}
		}

		return "[ " + out + " ]";
	}

	public void movePlayerBackward(int dice, Token tokenToMove) {
		Node playerNode = findPlayer(head, tokenToMove);

		// movePlayer(playerNode, dice);
		Token t = new Token();
		if (tokenToMove.equals(rick)) {
			t = playerNode.getRick();
			playerNode.setRick(null);
		} else {
			t = playerNode.getMorty();
			playerNode.setMorty(null);
		}
		movePlayerBackward(playerNode, t, dice, tokenToMove);
		
	}

	public void movePlayerForward(int dice, Token tokenToMove) {
		Node playerNode = findPlayer(head, tokenToMove);

		// movePlayer(playerNode, dice);
		Token t = new Token();
		if (tokenToMove.equals(rick)) {
			t = playerNode.getRick();
			playerNode.setRick(null);
		} else {
			t = playerNode.getMorty();
			playerNode.setMorty(null);
		}
		movePlayerForward(playerNode, t, dice, tokenToMove);

	}

	private void movePlayerForward(Node current, Token t, int dice, Token tokenToMove) {
		// condicion de parada
		if (dice == 0) {
			if (tokenToMove.equals(rick)) {
				current.setRick(t);
				
			} else {
				current.setMorty(t);
				
				///
				if (current.getSeed()) {
					System.out.println("semilla");
					current.setSeed(false);
				
					
				}
			}

			return;
		}
		// Metodo recursivo
		movePlayerForward(current.getNext(), t, dice - 1, tokenToMove);
	

	}

	private void movePlayerBackward(Node current, Token t, int dice, Token tokenToMove) {
		// condicion de parada
		if (dice == 0) {
			if (tokenToMove.equals(rick)) {
				current.setRick(t);
			} else {
				current.setMorty(t);
			}

			return;
		}
		// Metodo recursivo
		movePlayerBackward(current.getPrev(), t, dice - 1, tokenToMove);
	}

	private Node findPlayer(Node current, Token tokenToFind) {
		if (current.getMorty() != null || current.getMorty() != null) {
			return current;
		}
		return findPlayer(current.getNext(), tokenToFind);
	}

	/**
	 * @return the size
	 */
	public int size() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	


}