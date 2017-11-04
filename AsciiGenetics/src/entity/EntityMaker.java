package entity;

import java.util.ArrayList;
import java.util.Random;
import engine.Environment;
import part.PartPicker;

public class EntityMaker {

	private Random rngesus = new Random();
	public Environment environment;
	public PartPicker partPicker;

	/**
	 * EntityMaker Constructor
	 * 
	 * @param enivronment
	 *            the current Environment
	 * @param partPicker
	 *            PartPicker used to make random Entity
	 */
	public EntityMaker(Environment enivronment, PartPicker partPicker) {
		this.environment = enivronment;
		partPicker = new PartPicker();
	}

	/**
	 * Generates new random Entity
	 * 
	 * @param gender
	 *            gender of new entity (true for male : false for female)
	 * @return random Entity based on params
	 */
	public Entity newRandomEntity(boolean gender) {
		Entity newEntity = new Entity(new Position(pray(environment.getX()),
				pray(environment.getY())), environment, gender ? '#' : '@'); // concrete
																				// Entity
		newEntity.generateRandomParts(); // random Parts
		return newEntity;
	}

	/**
	 * Generates multiple random Entities
	 * 
	 * @param num
	 *            number of new Entities
	 * @return ArrayList of new (random) Entities
	 */
	public ArrayList<Entity> generateRandomEntities(int num) {
		ArrayList<Entity> returnList = new ArrayList<Entity>();
		returnList.addAll(generateRandomFemaleEntities(num));
		returnList.addAll(generateRandomMaleEntities(num));
		return returnList;
	}

	/**
	 * Generates multiple random male Entities
	 * 
	 * @param num
	 *            number of new male Entities
	 * @return ArrayList of new (random) male Entities
	 */
	public ArrayList<Entity> generateRandomMaleEntities(int num) {
		ArrayList<Entity> returnList = new ArrayList<Entity>();
		for (int i = 0; i < num; i++)
			returnList.add(newRandomEntity(true));
		return returnList;
	}

	/**
	 * Generates multiple random female Entities
	 * 
	 * @param num
	 *            number of new female Entities
	 * @return ArrayList of new (random) female Entities
	 */
	public ArrayList<Entity> generateRandomFemaleEntities(int num) {
		ArrayList<Entity> returnList = new ArrayList<Entity>();
		for (int i = 0; i < num; i++)
			returnList.add(newRandomEntity(false));
		return returnList;
	}

	/**
	 * Generates random number
	 * 
	 * @param num
	 *            max value
	 * @return rand number: [0, num}
	 */
	public int pray(int num) {
		return rngesus.nextInt(num);
	}

}