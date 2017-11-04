package engine;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import part.PartPicker;
import entity.Entity;
import entity.EntityMaker;
import entity.Position;

public class Environment {

	private int fENum;
	private int mENum;
	private List<Entity> entities = new ArrayList<Entity>();

	private List<Entity> fE;
	int fEIndex;

	private List<Entity> mE;
	int mEIndex;

	private List<Entity> deadList = new ArrayList<Entity>();

	private PartPicker pP = new PartPicker();
	private EntityMaker eM = new EntityMaker(this, pP);

	private double minSurv = 2.5;

	// private List<>
	
	/**
	 * Environment constructor that generates random entities
	 */
	public Environment() {
		try {
			mENum = Integer.parseInt(JOptionPane.showInputDialog(null, "How many male Entities would you like to generate?", "Number of Male Entities", JOptionPane.INFORMATION_MESSAGE));
			fENum = Integer.parseInt(JOptionPane.showInputDialog(null, "How many female Entities would you like to generate?", "Number of Female Entities", JOptionPane.INFORMATION_MESSAGE));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Cannot Parse the Number", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		mE = eM.generateRandomMaleEntities(mENum); //Generate 100 Random Male Entities
		fE = eM.generateRandomFemaleEntities(fENum); //Generate 100 Random Female Entities
		entities.addAll(fE); //Add male entities to the arraylist to be displayed
		entities.addAll(mE); //Add female entities to the arraylist to be displayed
	}

	/**
	 * Processes a new generation of Entities based on the current Environment
	 * and other Entities
	 */
	public void processGen() {
		ArrayList<Entity> temp = new ArrayList<Entity>(); //Temp arraylist
		for (Entity e : entities) // goes through each entity
			temp.add(Entity.copyEntity(e)); // copies it to temp arraylist
		for (int i = 0; i < temp.size(); i++) { // thru each element in temp
			if (temp.get(i).getSurvivalRating() < minSurv) { // checks survival rating
				deadList.add(temp.get(i)); // if can't survive, then dies
				entities.remove(i);
			}
			ArrayList<Entity> newGeneration = processReproduction(); // process reproduction after removing dead entities
			if (newGeneration.size() < 100) { // ensures min size of 100
				int a = newGeneration.size();
				newGeneration.addAll(eM
						.generateRandomMaleEntities((mENum - a) / 2));
				newGeneration.addAll(eM
						.generateRandomFemaleEntities((fENum - a) / 2));
			}

			fE = new ArrayList<Entity>(); // females
			mE = new ArrayList<Entity>(); // males
			entities = new ArrayList<Entity>(); // all entities

			for (Entity e : newGeneration) {
				if (e.getGender() == '@') // adds females
					fE.add(e);
				else
					mE.add(e); // adds males
				entities.add(e); //adds entities
			}
		}
	}

	/**
	 * Get size of the array
	 * @return size of the entities array
	 */
	public int getSize() {
		return entities.size();
	}

	/**
	 * Processes Reproduction between males and females
	 * @return ArrayList of Entities that are children
	 */
	private ArrayList<Entity> processReproduction() {

		ArrayList<Entity> newReproductions = new ArrayList<Entity>(); //New ArrayList where children will be stored
		int min = (int) Math.min(fE.size(), mE.size()); //Finds the limiting factor of participants
		for (int i = 0; i < min; i++) //Goes through all of the entities in each gender
			if (!(deadList.contains(fE.get(i)) || deadList.contains(mE.get(i))))
				newReproductions.add(fE.get(i).reproduce(mE.get(i))); //Adds baby depending on the female and male entity
		return newReproductions; //Return babies
	}

	/**
	 * Generate random Position
	 * @return Position object with randomly generated position
	 */
	public static Position randPos() {
		return new Position(Utility.pray(86), Utility.pray(39));
	}

	/**
	 * Add an entity to the Arraylist of entities
	 * @param entity Entity to be added
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	/**
	 * Get an entity from the Arraylist of entities
	 * @param index Index of the array
	 * @return Entity that exist in index
	 */
	public Entity getEntity(int index) {
		return entities.get(index);
	}

	/**
	 * Number of cols in environment
	 * @return Max number of column
	 */
	public int getX() {
		return 86;
	}

	/**
	 * Number of rows in environment
	 * @return Max number of rows
	 */
	public int getY() {
		return 39;
	}

	/**
	 * Get Next Female entity in the fE arraylist
	 * @return Female Entity
	 */
	public Entity getNextFemale() {
		Entity next = fE.get(mEIndex);
		mEIndex = (fEIndex + 1) % fE.size();
		return next;
	}

	/**
	 * Get Next Male entity in the fE arraylist
	 * @return Male Entity
	 */
	public Entity getNextMale() {
		Entity next = mE.get(mEIndex);
		mEIndex = (mEIndex + 1) % mE.size();
		return next;
	}

}
