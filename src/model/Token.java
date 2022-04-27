package model;

public class Token {
	
	private int collectedSeeds;
	private Character asciiSymbol;
	
	
	
	public Token(int collectedSeeds, Character asciiSymbol) {
		super();
		this.collectedSeeds = collectedSeeds;
		this.asciiSymbol = asciiSymbol;
	}



	public int getCollectedSeeds() {
		return collectedSeeds;
	}



	public void setCollectedSeeds(int collectedSeeds) {
		this.collectedSeeds = collectedSeeds;
	}



	public Character getAsciiSymbol() {
		return asciiSymbol;
	}



	public void setAsciiSymbol(Character asciiSymbol) {
		this.asciiSymbol = asciiSymbol;
	}
	
	
	
}
