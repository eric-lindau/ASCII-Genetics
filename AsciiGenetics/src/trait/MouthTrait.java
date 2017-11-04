package trait;

import java.awt.Color;

public class MouthTrait extends Trait {

	/**
	 * Constructor for a trait. Adds the attributes that each trait gives along
	 * with the correct values as well as setting the color.
	 * 
	 * @param col
	 *            The color modifier of the trait.
	 */
	public MouthTrait(Color col, int[] vals) {
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
		attributes.add(new Attribute("Damage", values[2]));
		attributes.add(new Attribute("Type", values[3]));
	}

}
