package engine;
import java.awt.Color;
import java.util.Random;

public class Utility {

	private static Random rngesus = new Random();

	/**
	 * Averages two colors to create a "middle" color
	 * 
	 * @param color1
	 *            the first color
	 * @param color2
	 *            the second color
	 * @return an average of the two colors
	 */
	public static Color avgColor(Color color1, Color color2) {
		int r, g, b;
		r = (color1.getRed() + color2.getRed()) / 2; // avg. red
		g = (color1.getGreen() + color2.getGreen()) / 2; // avg. green
		b = (color1.getBlue() + color2.getBlue()) / 2; // avg. blue
		return new Color(r, g, b);
	}

	/**
	 * Creates a random Color
	 * 
	 * @return a random Color
	 */
	public static Color randColor() {
		return new Color(pray(255), pray(255), pray(255)); // random r, g, and b
	}

	/**
	 * Generates random number based on parameter
	 * 
	 * @param num
	 *            max number to be generated
	 * @return random number: [0, num}
	 */
	public static int pray(int num) {
		return rngesus.nextInt(num);
	}
}
