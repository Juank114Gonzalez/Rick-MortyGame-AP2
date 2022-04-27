package model;

public class Node {
	//Datos
	private int value;
	//private Player player;
	private Rick rick;
	private Morty morty;

	
	//Enlaces
	private Node prev;
	private Node next;
	
	
	public Node() {
		
	}
	
	public Node(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Rick getRick() {
		return rick;
	}

	public void setRick(Rick rick) {
		this.rick = rick;
	}

	public Morty getMorty() {
		return morty;
	}

	public void setMorty(Morty morty) {
		this.morty = morty;
	}

}
