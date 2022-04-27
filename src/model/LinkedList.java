package model;

public class LinkedList{
	
	//Head
	private Node head;
	
	private int boxesQuant;

	public void init(int n, int seeds, int links) {
		
		if(head == null) {
			boxesQuant = n;
			Node node = new Node(1);
			head = (Node) node;
			head.setNext(head);
			head.setPrev(head);
		}else if(n==0) {
			
			setValuesOfBoard(seeds, links);
			
			return;
		}
		else {
			Node node = new Node();
			Node tail = node;
			
			
			
			tail.setNext(head);
			tail.setPrev(head.getPrev());
			
			tail.setIndex(tail.getPrev().getIndex()+1);
			
			head.getPrev().setNext(tail);
			head.setPrev(tail);
			init(n-1, seeds, links);
		}
	}


	private void setValuesOfBoard(int seeds, int links) {
		// TODO Auto-generated method stub
		putSeeds(seeds);
		generateLinks(links);
	}
	
	private void generateLinks(int links) {
		int i = 1+(int) (Math.random()*boxesQuant-1);
		int j = 1+(int) (Math.random()*boxesQuant-1);
		
		if(links!=0) {
			if(get(head, i).getLink() != null || get(head, j).getLink() != null) {
				putSeeds(links);
			}else {
				get(head, i).setLink(get(head, j));
				get(head, j).setLink(get(head, i));
				putSeeds(links-1);
			}
		}
	}


	private void putSeeds(int seeds) {
		int i = 1+(int) (Math.random()*boxesQuant-1);
		
		if(seeds!=0) {
			if(get(head, i).getSeed()) {
				putSeeds(seeds);
			}else {
				get(head, i).setSeed(true);
				putSeeds(seeds-1);
			}
		}
	}


	private Node get(Node current, int i) {
		if(current.getNext() != head) {
			if(current.getIndex() == i) {
				return current;
			}
			return get(current.getNext(), i);
		}else {
			return null;
		}
		
	}

}