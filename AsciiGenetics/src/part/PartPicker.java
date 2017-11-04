package part;

import trait.TraitGenerator;
import engine.Utility;

public class PartPicker {

	// literal symbols for ASCII representation
	private char[] mouthSymbols = { '<', '-', 'C', 'D', '~' };
	private char[] armSymbols = { '|', '!', 'I', 'T', 'X', '!', ':', 'l', '[' };
	private char[] handSymbols = { 'x', 'X', 'V', '^', 'o', '*', ';', 'S', '+' };
	private char[] bodySymbols = { 'X', 'H', '#', 'O' };
	private char[] tailSymbols = { '=', '>', 'L', 'H', '~' };
	private char[] headSymbols = { 'O', '0', 'Q' };

	/**
	 * Chooses random mouth symbols and trait to make Part
	 * 
	 * @param max
	 *            the maximum value for the randomly generated trait survival
	 *            value
	 * @return a random Part based on entered parameters
	 */
	public Part generateRandomMouth(int max) {
		return new Part(TraitGenerator.genTrait(4, max), Utility.randColor(),
				mouthSymbols[Utility.pray(mouthSymbols.length)],
				mouthSymbols[Utility.pray(mouthSymbols.length)]);
	}

	/**
	 * Chooses random arm symbols and trait to make Part
	 * 
	 * @param max
	 *            the maximum value for the randomly generated trait survival
	 *            value
	 * @return a random Part based on entered parameters
	 */
	public Part generateRandomArm(int max) {
		return new Part(TraitGenerator.genTrait(0, max), Utility.randColor(),
				armSymbols[Utility.pray(armSymbols.length)],
				armSymbols[Utility.pray(armSymbols.length)]);
	}

	/**
	 * Chooses random hand symbols and trait to make Part
	 * 
	 * @param max
	 *            the maximum value for the randomly generated trait survival
	 *            value
	 * @return a random Part based on entered parameters
	 */
	public Part generateRandomHand(int max) {
		return new Part(TraitGenerator.genTrait(3, max), Utility.randColor(),
				handSymbols[Utility.pray(handSymbols.length)],
				handSymbols[Utility.pray(handSymbols.length)]);
	}

	/**
	 * Chooses random body symbols and trait to make Part
	 * 
	 * @param max
	 *            the maximum value for the randomly generated trait survival
	 *            value
	 * @return a random Part based on entered parameters
	 */
	public Part generateRandomBody(int max) {
		return new Part(TraitGenerator.genTrait(1, max), Utility.randColor(),
				bodySymbols[Utility.pray(bodySymbols.length)],
				bodySymbols[Utility.pray(bodySymbols.length)]);
	}

	/**
	 * Chooses random tail symbols and trait to make Part
	 * 
	 * @param max
	 *            the maximum value for the randomly generated trait survival
	 *            value
	 * @return a random Part based on entered parameters
	 */
	public Part generateRandomTail(int max) {
		return new Part(TraitGenerator.genTrait(5, max), Utility.randColor(),
				tailSymbols[Utility.pray(tailSymbols.length)],
				tailSymbols[Utility.pray(tailSymbols.length)]);
	}

	/**
	 * Chooses random tail symbols and trait to make Part
	 * 
	 * @param max
	 *            the maximum value for the randomly generated trait survival
	 *            value
	 * @return a random Part based on entered parameters
	 */
	public Part generateRandomHead(int max) {
		return new Part(TraitGenerator.genTrait(2, max), Utility.randColor(),
				headSymbols[Utility.pray(headSymbols.length)],
				headSymbols[Utility.pray(headSymbols.length)]);
	}

}