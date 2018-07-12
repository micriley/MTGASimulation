import java.security.InvalidParameterException;
import java.util.Random;

/**
 * 
 * @author TurboCop
 * Model Class for representing a set of mtg cards we're collecting.
 * Also handles adding random cards to the collection
 */
public class MTGSet implements Cloneable{
	public static final Random rand = new Random();
	public final int totalCommons;
	public final int totalUncommons;
	public final int totalRares;
	public final int totalMythics;
	
	public int[] commons;
	public int[] uncommons;
	public int[] rares;
	public int[] mythics;
	
	public MTGSet() {
		this(101,80,53,15);
	}
	
	public MTGSet(int numOfCommons,int numOfUncommons,int numOfRares,int numOfMythics) {
		totalCommons = numOfCommons;
		totalUncommons = numOfUncommons;
		totalRares = numOfRares;
		totalMythics = numOfMythics;
		
		commons = new int[totalCommons];
		uncommons = new int[totalUncommons];
		rares = new int[totalRares];
		mythics = new int[totalMythics];
	}
	
	/**
	 * 
	 * @param number specific card in the rarity list
	 * @param type which type of rarity we're adding
	 * @return False if we're adding a 5th copy/duplicate. True if added as a new card successfully 
	 */
	public boolean addCard(int number,String type) throws InvalidParameterException{
		int[] targetList = null;
		if(type.equalsIgnoreCase("Common"))
			targetList = commons;
		else if(type.equalsIgnoreCase("Uncommon"))
			targetList = uncommons;
		else if(type.equalsIgnoreCase("Rare"))
			targetList = rares;
		else if(type.equalsIgnoreCase("Mythic"))
			targetList = mythics;
		else
			throw new InvalidParameterException("Type must be common, uncommon, rare, or mythic");
		
		try {
			if(targetList[number] == 4)
				return false;
			targetList[number]++;
		}
		catch(IndexOutOfBoundsException e) {
			throw new InvalidParameterException("Bad card number called");
		}
		return true;
	}
	
	public boolean addCommon() {
		int number = rand.nextInt(totalCommons);
		return addCard(number, "Common");
	}
	
	public boolean addUncommon() {
		int number = rand.nextInt(totalUncommons);
		return addCard(number, "Uncommon");
	}
	
	public boolean addRare() {
		int number = rand.nextInt(totalRares);
		return addCard(number, "Rare");
	}
	
	public boolean addMythic() {
		int number = rand.nextInt(totalMythics);
		return addCard(number, "Mythic");
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}
