package entity;

import java.awt.Color;
import java.util.ArrayList;
import engine.Environment;
import engine.Utility;
import part.Part;
import part.PartPicker;
import trait.Trait.Attribute;
import trait.TraitGenerator;

/**
 * The main class for each "creature" within the Environment
 * 
 * @author Eric Lindau, Fisher Darling, Kevin Park
 */
public class Entity {

	private Position position;
	private Environment environment;
	private char gender;
	private Part arm, body, head, hand, mouth, tail;
	private PartPicker picker;

	/**
	 * Default Entity constructor
	 */
	public Entity() {

	}

	/**
	 * Main Constructor for Entity
	 * 
	 * @param position
	 *            Entity's Position
	 * @param environment
	 *            Entity's Environment
	 * @param gender
	 *            Entity's gender as char
	 */
	public Entity(Position position, Environment environment, char gender) {
		this.position = position;
		this.environment = environment;
		this.gender = gender;
		this.picker = new PartPicker();
		generateRandomParts();
	}

	/**
	 * Constructor that copies Entity
	 * 
	 * @param e
	 *            Entity to be copied
	 * @return Entity; copy of e
	 */
	public static Entity copyEntity(Entity e) {
		Entity newE = new Entity(e.getPosition(), e.getEnvironment(),
				e.getGender());
		newE.setParts(e.getPartList());
		return newE;
	}

	/**
	 * Sets the parts of the Entity
	 * 
	 * @param partList
	 *            Parts to set
	 */
	public void setParts(ArrayList<Part> partList) {
		arm = partList.get(0);
		body = partList.get(1);
		head = partList.get(2);
		hand = partList.get(3);
		mouth = partList.get(4);
		tail = partList.get(5);
	}

	/**
	 * Generates random parts for Entity
	 */
	public void generateRandomParts() {
		arm = picker.generateRandomArm(10);
		body = picker.generateRandomBody(10);
		head = picker.generateRandomHead(10);
		hand = picker.generateRandomHand(10);
		mouth = picker.generateRandomMouth(10);
		tail = picker.generateRandomTail(10);
	}

	/**
	 * Gives the ASCII figure of the Entity
	 * 
	 * @return a char[][] that is a graphical rendition of the Entity in ASCII
	 *         characters
	 */
	public char[][] getFigure() { // literal rendition of characters
		char[][] figure = {
				{ '-', '-', '-', '-', hand.getSymbol(), '-', '-', '-', '-' },
				{ '-', '-', '-', '-', arm.getSymbol(), '-', '-', '-', '-' },
				{ '-', '-', '-', '-', arm.getSymbol(), '-', '-', '-', '-' },
				{ '-', '-', '-', body.getSymbol(), body.getSymbol(),
						body.getSymbol(), '-', '-', '-' },
				{ '-', mouth.getSymbol(), head.getSymbol(), body.getSymbol(),
						gender, body.getSymbol(), tail.getSymbol(), '-', '-' },
				{ '-', '-', '-', body.getSymbol(), body.getSymbol(),
						body.getSymbol(), '-', '-', '-' },
				{ '-', '-', '-', '-', arm.getSymbol(), '-', '-', '-', '-' },
				{ '-', '-', '-', '-', arm.getSymbol(), '-', '-', '-', '-' },
				{ '-', '-', '-', '-', hand.getSymbol(), '-', '-', '-', '-' }, };
		return figure;
	}

	/**
	 * Gives the colors for each Part of the Entity
	 * 
	 * @return a char[][] that represents the colors to match each char in
	 *         getFigure()
	 */
	public Color[][] getFigureColor() { // literal rendition of Colors
		Color[][] figure = {
				{ null, null, null, null, hand.getColor(), null, null, null,
						null },
				{ null, null, null, null, arm.getColor(), null, null, null,
						null },
				{ null, null, null, null, arm.getColor(), null, null, null,
						null },
				{ null, null, null, body.getColor(), body.getColor(),
						body.getColor(), null, null, null },
				{ null, mouth.getColor(), head.getColor(), body.getColor(),
						getColor(), body.getColor(), tail.getColor(), null,
						null },
				{ null, null, null, body.getColor(), body.getColor(),
						body.getColor(), null, null, null },
				{ null, null, null, null, arm.getColor(), null, null, null,
						null },
				{ null, null, null, null, arm.getColor(), null, null, null,
						null },
				{ null, null, null, null, hand.getColor(), null, null, null,
						null }, };
		return figure;
	}

	/**
	 * Gets gender
	 * 
	 * @return gender as a char
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * Gives the Position of the current Entity
	 * 
	 * @return a Position that matches the current Position of the Entity
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Creates a new Entity based on the qualities of this Entity and another
	 * 
	 * @param otherEntity
	 *            other Entity to "reproduce" with
	 * @return a reproduced Entity
	 */
	public Entity reproduce(Entity otherEntity) {
		Entity child = new Entity(Environment.randPos(), environment,
				(int) (Math.random() * 2) == 0 ? '#' : '@'); // child created

		ArrayList<Part> selfParts = this.getPartList();
		ArrayList<Part> mateParts = this.getPartList();
		ArrayList<Part> childParts = this.getPartList();
		int partSize = selfParts.size();

		for (int i = 0; i < partSize; i++) { // add parts in order (parts always
												// in this order - alphabetical)
			Part selfPart = selfParts.get(i);
			Part matePart = mateParts.get(i);
			Color avg = Utility.avgColor(selfPart.getColor(),
					matePart.getColor()); // averages Colors
			childParts.add(new Part(TraitGenerator.genTrait(i, 5), avg,
					selfPart.getSymbol(), matePart.getSymbol())); // weaker than
																	// parent -
																	// 5 instead
																	// of 10
		}

		return child;
	}

	/**
	 * Gets attributes of Entity
	 * 
	 * @param e
	 *            Entity to search for Attributes
	 * @return ArrayList of e's Attributes
	 */
	public static ArrayList<ArrayList<Attribute>> getAttributeLists(Entity e) {
		ArrayList<ArrayList<Attribute>> attributeList = new ArrayList<ArrayList<Attribute>>();
		attributeList.add(e.getArm().getTrait().getAttributes());
		attributeList.add(e.getBody().getTrait().getAttributes());
		attributeList.add(e.getHead().getTrait().getAttributes());
		attributeList.add(e.getHand().getTrait().getAttributes());
		attributeList.add(e.getMouth().getTrait().getAttributes());
		attributeList.add(e.getTail().getTrait().getAttributes());

		return attributeList;
	}

	/**
	 * Checks if this Entity is equal to another
	 * 
	 * @param e
	 *            other Entity to check for equality
	 * @return boolean declaring whether this is equal to e
	 */
	public boolean equals(Entity e) {
		if (position != e.getPosition() || environment != e.getEnvironment()
				|| gender != e.getGender()) // based on pos, environ, gender
			return false;
		for (int i = 0; i < 6; i++)
			// also based on each Part
			if (getPartList().get(i) != e.getPartList().get(i))
				return false;
		return true;
	}

	// -----------------------------GETTERS and
	// SETTERS-----------------------------//

	/**
	 * Gets part list
	 * 
	 * @return ArrayList of Parts
	 */
	public ArrayList<Part> getPartList() {
		ArrayList<Part> retList = new ArrayList<Part>();
		retList.add(arm);
		retList.add(body);
		retList.add(head);
		retList.add(hand);
		retList.add(mouth);
		retList.add(tail);
		return retList;
	}

	/**
	 * Gets Color (combines Colors of all Parts)
	 * 
	 * @return combined Color of all Parts
	 */
	public Color getColor() {
		int r = 0;
		int g = 0;
		int b = 0;

		ArrayList<Part> list = getPartList();
		int num = list.size();

		for (Part p : list) { // for each Part, add its Color values (r, g, b)
								// to this Color
			Color col = p.getColor();
			r += col.getRed();
			g += col.getGreen();
			b += col.getBlue();
		}

		r /= num;
		g /= num;
		b /= num;

		return new Color(r, g, b);
	}

	/**
	 * Combines survival ratings of all Parts
	 * 
	 * @return the combined survival rating of all Parts
	 */
	public int getSurvivalRating() {
		int value = 0;

		ArrayList<Part> parts = getPartList();
		for (Part part : parts)
			value += part.getSurvivalRating(); // add to value

		return value;
	}

	/**
	 * Sets Position
	 * 
	 * @param position
	 *            new Position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Gets Environment
	 * 
	 * @return current Environment
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * Sets Environment
	 * 
	 * @param environment
	 *            new Environment
	 */
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	/**
	 * Gets Arm
	 * 
	 * @return current Arm
	 */
	public Part getArm() {
		return arm;
	}

	/**
	 * Sets Arm
	 * 
	 * @param arm
	 *            new Arm
	 */
	public void setArm(Part arm) {
		this.arm = arm;
	}

	/**
	 * Gets Body
	 * 
	 * @return current Body
	 */
	public Part getBody() {
		return body;
	}

	/**
	 * Sets Body
	 * 
	 * @param body
	 *            new Body
	 */
	public void setBody(Part body) {
		this.body = body;
	}

	/**
	 * Gets Head
	 * 
	 * @return current Head
	 */
	public Part getHead() {
		return head;
	}

	/**
	 * Sets Head
	 * 
	 * @param head
	 *            new Head
	 */
	public void setHead(Part head) {
		this.head = head;
	}

	/**
	 * Gets Hand
	 * 
	 * @return current Hand
	 */
	public Part getHand() {
		return hand;
	}

	/**
	 * Sets Hand
	 * 
	 * @param hand
	 *            new Hand
	 */
	public void setHand(Part hand) {
		this.hand = hand;
	}

	/**
	 * Gets Mouth
	 * 
	 * @return current Mouth
	 */
	public Part getMouth() {
		return mouth;
	}

	/**
	 * Sets Mouth
	 * 
	 * @param mouth
	 *            new Mouth
	 */
	public void setMouth(Part mouth) {
		this.mouth = mouth;
	}

	/**
	 * Gets Tail
	 * 
	 * @return current Tail
	 */
	public Part getTail() {
		return tail;
	}

	/**
	 * Sets Tail
	 * 
	 * @param tail
	 *            new Tail
	 */
	public void setTail(Part tail) {
		this.tail = tail;
	}

	/**
	 * Gets PartPicker
	 * 
	 * @return current PartPicker
	 */
	public PartPicker getPicker() {
		return picker;
	}

	/**
	 * Sets PartPicker
	 * 
	 * @param picker
	 *            new PartPicker
	 */
	public void setPicker(PartPicker picker) {
		this.picker = picker;
	}

	/**
	 * Sets gender (char)
	 * 
	 * @param gender
	 *            new char representing gender
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}
}