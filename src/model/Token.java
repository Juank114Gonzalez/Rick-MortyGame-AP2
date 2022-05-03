package model;

public class Token {
	
	private int collectedSeeds;
	private Character asciiSymbol;
	
	
	
	public Token( Character asciiSymbol) {
		this.collectedSeeds = 0;
		this.asciiSymbol = asciiSymbol;
	}
	public Token() {
		
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
