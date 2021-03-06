package trait;

import java.awt.Color;

public class TailTrait extends Trait {

	/**
	 * Constructor for a trait. Adds the attributes that each trait gives along
	 * with the correct values as well as setting the color.
	 * 
	 * @param col
	 *            The color modifier of the trait.
	 */
	public TailTrait(Color col, int[] vals) {
		super(vals);
		addAttributes();
		color = col;
	}

	/**
	 * Adds the attributes to an ArrayList for the Trait
	 */
	public void addAttributes() {
		attributes.add(new Attribute("Ability to Heal", values[0]));
		attributes.add(new Attribute("Visibility", values[1]));
		attributes.add(new Attribute("Metabolism", values[2]));
		attributes.add(new Attribute("Reproduction", values[3]));
		attributes.add(new Attribute("Armor", values[4]));
	}

}