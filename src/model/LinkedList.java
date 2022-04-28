package model;

public class LinkedList {

	// Head
	private Node head;

	private int boxesQuant;

	public LinkedList(int boxesQuant) {
		this.boxesQuant = boxesQuant;
	}

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

	private void setValuesOfBoard(int seeds, int links) {
		// TODO Auto-generated method stub
		putSeeds(seeds);
		generateLinks(links);
	}

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

	public String toString(int m) {
		return toString(head, "", m, 0, 0);
	}

	/**
	 * 
	 * @param acc
	 * @param m
	 * @param i
	 * @param j
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

	private String valueOfNode(Node node) {
		String out = "";
		boolean flag = false;
		if (node.getRick() != null && node.getMorty() != null) {
			char a = (char) 234;
			out = "" + a;
			flag = true;
		} else {
			if (node.getRick() != null) {
				out = "R";
			} else if (node.getMorty() != null) {
				out = "M";
			} else if (node.getSeed()) {
				char a = (char) 233;
				out = "" + a;
			} else {
				out = node.getIndex() + "";
			}
		}

		return out;
	}

}