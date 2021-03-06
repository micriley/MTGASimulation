import java.util.Random;

/**
 * 
 * @author TurboCop
 * An implementation of the old Pre-M19 system.
 * NOTE: This model does not take into the account the edge case
 * of getting a rare pity WC AND a mythic pity WC at the same time
 *
 */
public class OldModel extends CollectionModel implements Cloneable{
	private static final Random rand = new Random();
	public OldModel() {
		collectionSet = new MTGSet();
		commonWCRate = 0.33f; // 1 in 3
		uncommonWCRate = 0.33f; // 1 in 3
		rareWCRate = 0.12f; // 1 in 8
		mythicWCRate = 0.05f; // 1 in 20
		
		vaultProgressPacks = 3.333f;
		vaultProgressDupCommons = 0.1f;
		vaultProgressDupUncommons = 0.3f;
		vaultProgressDupRares = 0.5f;
		vaultProgressDupMythics = 1f;
		
		maxRarePityTimer = 15;
		maxMythicPityTimer = 30;
	}
	
	
	@Override
	public void addPack() {
		totalPacksOpened++;
		//Add commons to the collection
		for(int i = 0; i < commonSlots; i ++) {
			if(i == 0 && rand.nextFloat() < commonWCRate) { //If it's the first slot check for wildcards
				naturalCommonWC++;
			}
			else {
				boolean dup = collectionSet.addCommon();
				if(dup) {
					handleDup("Common");
				}
			}
		}
		//Add uncommons to the collection
		for(int i = 0; i < uncommonSlots; i ++) {
			if(i == 0 && rand.nextFloat() < uncommonWCRate) { //If it's the first slot check for wildcards
				naturalUncommonWC++;
			}
			else {
				boolean dup = collectionSet.addUncommon();
				if(dup) {
					handleDup("Uncommon");
				}
			}
		}
		
		//Add rares to the collection
		//Check for rare WC
		if(currentRarePityTimer == maxRarePityTimer || rand.nextFloat() < rareWCRate) {
			naturalRareWC++;
			currentRarePityTimer = 0;
		}
		//If no rare WC, check for mythic WC
		else if(currentMythicPityTimer == maxMythicPityTimer || rand.nextFloat() < mythicWCRate) {
			naturalMythicWC++;
			currentMythicPityTimer = 0;
		}
		//If no WC check for mythic upgrad
		else if(rand.nextFloat() < mythicChance) {
			boolean dup = collectionSet.addMythic();
			if(dup) {
				handleDup("Mythic");
			}
		}
		//Last case is just getting a rare
		else {
			boolean dup = collectionSet.addRare();
			if(dup) {
				handleDup("Rare");
			}
		}
		//Add vault progress from packs
		currentVaultProgress += vaultProgressPacks;
		totalVaultProgress += vaultProgressPacks;
		totalVaultProgressThroughPacks += vaultProgressPacks;
		handleVaultOpening();
	}
	
	/**
	 * Handles the event of a 5th card
	 * @param type rarity of dup we're handling.
	 */
	private void handleDup(String type) {
		if(type.equalsIgnoreCase("Common")) {
			currentVaultProgress += vaultProgressDupCommons;
			totalVaultProgress += vaultProgressDupCommons;
			totalVaultProgressThroughCommons += vaultProgressDupCommons;
		}
		else if(type.equalsIgnoreCase("Uncommon")) {
			currentVaultProgress += vaultProgressDupUncommons;
			totalVaultProgress += vaultProgressDupUncommons;
			totalVaultProgressThroughUncommons += vaultProgressDupUncommons;
		}
		if(type.equalsIgnoreCase("Rare")) {
			currentVaultProgress += vaultProgressDupRares;
			totalVaultProgress += vaultProgressDupRares;
			totalVaultProgressThroughRares += vaultProgressDupRares;
		}
		if(type.equalsIgnoreCase("Mythic")) {
			currentVaultProgress += vaultProgressDupMythics;
			totalVaultProgress += vaultProgressDupMythics;
			totalVaultProgressThroughMythics += vaultProgressDupMythics;
		}
	}
	/**
	 * Checks and handles the event of a vault opening
	 */
	private void handleVaultOpening() {
		if(currentVaultProgress >= 100f) {
			currentVaultProgress -= 100f;
			vaultUncommonWC += 3;
			vaultRareWC += 2;
			vaultMythicWC += 1;
		}
	}

	@Override
	public String printCSVHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("totalPacksOpened,")
		.append("totalVaultProgress,")
		.append("totalVaultProgressThroughCommons,")
		.append("totalVaultProgressThroughUncommons,")
		.append("totalVaultProgressThroughRares,")
		.append("totalVaultProgressThroughMythics,")
		.append("totalVaultProgressThroughPacks,")
		.append("totalCommonWC,")
		.append("totalUncommonWC,")
		.append("totalRareWC,")
		.append("totalMythicWC,")
		.append("naturalCommonWC,")
		.append("naturalUncommonWC,")
		.append("naturalRareWC,")
		.append("naturalMythicWC,")
		.append("vaultUncommonWC,")
		.append("vaultRareWC,")
		.append("vaultMythicWC");
		return sb.toString();
	}
	
	@Override
	public String printCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(totalPacksOpened + ",")
		.append(totalVaultProgress + ",")
		.append(totalVaultProgressThroughCommons + ",")
		.append(totalVaultProgressThroughUncommons + ",")
		.append(totalVaultProgressThroughRares + ",")
		.append(totalVaultProgressThroughMythics + ",")
		.append(totalVaultProgressThroughPacks + ",")
		.append(naturalCommonWC + ",")
		.append(getTotalUncommonsWC() + ",")
		.append(getTotalRareWC() + ",")
		.append(getTotalMythicWC() + ",")
		.append(naturalCommonWC + ",")
		.append(naturalUncommonWC + ",")
		.append(naturalRareWC + ",")
		.append(naturalMythicWC + ",")
		.append(vaultUncommonWC + ",")
		.append(vaultRareWC + ",")
		.append(vaultMythicWC);
		return sb.toString();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

}