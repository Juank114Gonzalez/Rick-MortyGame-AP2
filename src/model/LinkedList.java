package model;

public class LinkedList {

	// Head
	private Node head;

	private int boxesQuant;

	public LinkedList(int boxesQuant) {
		this.boxesQuant = boxesQuant;
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

		if (head == null) {
			boxesQuant = n;
			Node node = new Node(1);
			head = (Node) node;
			head.setNext(head);
			head.setPrev(head);
		} else if (n == 0) {

			setValuesOfBoard(seeds, links);

			return;
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
	}

	/**
	 * This method randomly places Rick and Morty around the board.
	 */
	private void setRickAndMorty() {
		int i = 1 + (int) (Math.random() * boxesQuant - 1);
		int j = 1 + (int) (Math.random() * boxesQuant - 1);
		if (i != j && !get(head, i).getSeed() && !get(head, j).getSeed()) {
			get(head, i).setRick(new Token('R'));
			get(head, j).setMorty(new Token('M'));
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
	public String toString(int m) {
		return toString(head, "", m, 0, 0);
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
	public String toString(Node current, String acc, int m, int i, int j) {
		if (j == m) {
			return acc;
		}
		if (j % m == 0 && j != 0) {
			if (i % 2 != 0) {
				acc += acc + "\n";
			} else {
				acc += "\n" + acc;
			}
			i++;
		}

		acc += valueOfNode(current);
		return toString(current.getNext(), acc, m, i, j + 1);
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

		return out;
	}

}