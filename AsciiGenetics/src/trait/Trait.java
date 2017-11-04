package trait;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Trait {
	protected Color color;
	protected ArrayList<Attribute> attributes = new ArrayList<Attribute>();
	protected int[] values;

	/**
	 * Adds the attributes to an ArrayList for the Trait
	 */
	public abstract void addAttributes();

	/**
	 * Constructor
	 * 
	 * @param vals
	 *            new array values
	 */
	public Trait(int[] vals) {
		values = vals;
	}

	/**
	 * Returns color
	 * 
	 * @return the Color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets value
	 * 
	 * @param index
	 *            index of new value
	 * @param newVal
	 *            new value
	 */
	public void setValue(int index, int newVal) {
		values[index] = newVal;
	}

	/**
	 * Gives the Attributes of the current Trait
	 * 
	 * @return an ArrayList of the Attributes of the Trait
	 */
	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public class Attribute {
		private String name;
		private double value;

		/**
		 * Constructor for Attribute - specified name and value for each
		 * Attribute
		 * 
		 * @param name
		 *            a String for the name for the Attribute
		 * @param value
		 *            a double for the numerical value for the Attribute
		 */
		public Attribute(String name, double value) {
			this.name = name;
			this.value = value;
		}

		/**
		 * Gives the name of the current Attribute
		 * 
		 * @return a String of the name of the Attribute
		 */
		public String getName() {
			return name;
		}

		/**
		 * Changes the name of the current Attribute
		 * 
		 * @param name
		 *            a String of the new name of the Attribute
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * Gives the value of the current Attribute
		 * 
		 * @return a String of the value of the Attribute
		 */
		public double getValue() {
			return value;
		}

		/**
		 * Changes the value of the current Attribute
		 * 
		 * @param a
		 *            String of the the new value of the Attribute
		 */
		public void setValue(float value) {
			this.value = value;
		}
	}

}