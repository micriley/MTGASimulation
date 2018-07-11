
/**
 * 
 * 
 * @author TurboCop
 * Model of MTGA's collection process via packs
 * Tracks collection over tie
 * Allows you to change duplicate rarities, as well as make your own pack addition function
 *
 */
public abstract class CollectionModel {
	//Track a collection here
	protected MTGSet collectionSet;
	//Counts the number of packs opened
	protected int totalPacksOpened = 0;
	//Pack information
	protected int commonSlots = 5;
	protected int uncommonSlots = 2;
	protected float mythicChance = 0.125f; // 1 in 8
	
	//Current Total vault progress and the seperate ways in which you gain vault progress 
	protected float currentVaultProgress = 0;
	protected float totalVaultProgress = 0;
	protected float totalVaultProgressThroughCommons = 0;
	protected float totalVaultProgressThroughUncommons = 0;
	protected float totalVaultProgressThroughRares = 0;
	protected float totalVaultProgressThroughMythics = 0;
	protected float totalVaultProgressThroughPacks = 0;
	//Tracks how many natural wildcard drops you obtain
	protected int naturalCommonWC = 0;
	protected int naturalUncommonWC = 0;
	protected int naturalRareWC = 0;
	protected int naturalMythicWC = 0;
	//Tracks the number of vault wildcards you obtain
	protected int vaultUncommonWC = 0;
	protected int vaultRareWC = 0;
	protected int vaultMythicWC = 0;
	//Rates of wildcard drops
	protected float commonWCRate;
	protected float uncommonWCRate;
	protected float rareWCRate;
	protected float mythicWCRate;
	//The progress each event gets you, either opening packs or getting dups
	protected float vaultProgressPacks;
	protected float vaultProgressDupCommons;
	protected float vaultProgressDupUncommons;
	protected float vaultProgressDupRares;
	protected float vaultProgressDupMythics;
	//Pity timer stats
	protected int currentRarePityTimer = 0;
	protected int maxRarePityTimer;
	protected int currentMythicPityTimer = 0;
	protected int maxMythicPityTimer;
	
	public abstract void addPack();
	public abstract String printCSVHeader();
	public abstract String printCSV();
	
	public int getTotalUncommonsWC() {
		return naturalUncommonWC + vaultUncommonWC;
	}
	
	public int getTotalRareWC() {
		return naturalRareWC + vaultRareWC;
	}
	
	public int getTotalMythicWC() {
		return naturalMythicWC + vaultMythicWC;
	}
}
