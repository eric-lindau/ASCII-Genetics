package part;

import java.awt.Color;
import java.util.ArrayList;

import trait.Trait;
import trait.Trait.Attribute;

public class Part {

	private char symbol1, symbol2;
	private boolean firstSymbol = false;
	private Color color;
	private Trait trait;

	/**
	 * Constructor for Part
	 * 
	 * @param t
	 *            main Trait
	 * @param c
	 *            main Color
	 * @param c1
	 *            symbol 1
	 * @param c2
	 *            symbol 2
	 */
	public Part(Trait t, Color c, char c1, char c2) {
		color = c;
		symbol1 = c1;
		symbol2 = c2;
		trait = t;
	}

	/**
	 * Gets color
	 * 
	 * @return the Color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets symbol
	 * 
	 * @param c1
	 *            symbol 1
	 * @param c2
	 *            symbol 2
	 */
	public void setSymbols(char c1, char c2) {
		symbol1 = c1;
		symbol2 = c2;
	}

	/**
	 * Gets symbol - alternates between the two
	 * 
	 * @return a symbol (alternated each call)
	 */
	public char getSymbol() {
		firstSymbol = !firstSymbol; // alternate
		return firstSymbol ? symbol1 : symbol2;
	}

	/**
	 * Determines survival rating of the Part
	 * 
	 * @return the survival rating of the Part
	 */
	public int getSurvivalRating() {
		int surv = 0;
		ArrayList<Attribute> ats = trait.getAttributes();
		for (Attribute a : ats)
			surv += a.getValue(); // adds all attribute values
		return surv;
	}

	/**
	 * Gets trait
	 * 
	 * @return the main Trait
	 */
	public Trait getTrait() {
		return trait;
	}

	/**
	 * Sets trait
	 * 
	 * @param trait
	 *            the new Trait
	 */
	public void setTrait(Trait trait) {
		this.trait = trait;
	}

	/**
	 * Sets color
	 * 
	 * @param color
	 *            the new Color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
