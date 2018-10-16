
public class SingletonCollectionTest {
	
	public static float mean(int[] input) {
		if(input.length == 0) return 0;
		float total = 0;
		for(int i=0;i<input.length;i++) {
			total += input[i];
		}
		return total / input.length;
	}
	
	public static void main(String[]args) {
		int numOfRuns = 10000;
		int[] commonSingletonResult = new int[numOfRuns];
		int[] uncommonSingletonResult = new int[numOfRuns];
		int[] rareSingletonResult = new int[numOfRuns];
		int[] mythicSingletonResult = new int[numOfRuns];
		for(int i = 0; i < numOfRuns; i++) {
			NewModel newModel = new NewModel();
			boolean commonSingleton = false;
			boolean uncommonSingleton = false;
			boolean rareSingleton = false;
			boolean mythicSingleton = false;
			int n = 0;
			while(!mythicSingleton || !uncommonSingleton || !rareSingleton || !commonSingleton) {
				n++;
				newModel.addPack();
				if(!commonSingleton && newModel.collectionSet.collectionOf("common", 1)) {
					commonSingletonResult[i] = n;
					commonSingleton = true;
				}
				if(!uncommonSingleton && newModel.collectionSet.collectionOf("uncommon", 1)) {
					uncommonSingletonResult[i] = n;
					uncommonSingleton = true;
				}
				if(!rareSingleton && newModel.collectionSet.collectionOf("rare", 1)) {
					rareSingletonResult[i] = n;
					rareSingleton = true;
				}
				if(!mythicSingleton && newModel.collectionSet.collectionOf("mythic", 1)) {
					mythicSingletonResult[i] = n;
					mythicSingleton = true;
				}
			}
		}
		
		System.out.println("CommonSingletonMean: "+mean(commonSingletonResult));
		System.out.println("UncommonSingletonMean: "+mean(uncommonSingletonResult));
		System.out.println("RareSingletonMean: "+mean(rareSingletonResult));
		System.out.println("MythicSingletonMean: "+mean(mythicSingletonResult));
	}
}
