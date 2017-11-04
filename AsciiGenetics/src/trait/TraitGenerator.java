package trait;

import engine.Utility;

public class TraitGenerator {

	/**
	 * Generates a new Trait based on part number
	 * 
	 * @param partNum
	 *            int signifying body type: 0 = arm, 1 = body, 2 = head, 3 =
	 *            hand, 4 = mouth, 5 = tail
	 * @return random Trait based on part number
	 */
	public static Trait genTrait(int partNum, int max) {
		switch (partNum) {
		case 0: // numbers always ordered the same way - refer to documentation
			return new ArmTrait(Utility.randColor(), new int[] {
					Utility.pray(max), Utility.pray(max), Utility.pray(max) }); // random Color and Attributes
		case 1:
			return new BodyTrait(Utility.randColor(), new int[] {
					Utility.pray(max), Utility.pray(max), Utility.pray(max),
					Utility.pray(max), Utility.pray(max) });
		case 2:
			return new HeadTrait(Utility.randColor(), new int[] {
					Utility.pray(max), Utility.pray(max), Utility.pray(max),
					Utility.pray(max) });
		case 3:
			return new HandTrait(Utility.randColor(), new int[] {
					Utility.pray(max), Utility.pray(max), Utility.pray(max) });
		case 4:
			return new MouthTrait(Utility.randColor(), new int[] {
					Utility.pray(max), Utility.pray(max), Utility.pray(max),
					Utility.pray(max) });
		case 5:
			return new TailTrait(Utility.randColor(), new int[] {
					Utility.pray(max), Utility.pray(max), Utility.pray(max),
					Utility.pray(max), Utility.pray(max) });
		default:
			return null;
		}
	}

}
