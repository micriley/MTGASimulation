
public class OldVsNewLargeTest {
	public static void main(String[]args) {
		int totalPacks = 60;
		int iterations = 100000;
		
		OldModel averageOld = new OldModel();
		NewModel averageNew = new NewModel();
		System.out.println("OLD MODEL");
		for(int i = 0;i < iterations;i++) {
			if(i % 500 == 0) {
				System.out.println("Old Model iteration " + i);
			}
			OldModel oldModel = new OldModel();
			for(int j = 1;j <= totalPacks;j++) {
				oldModel.addPack();
			}
			averageOld.add(oldModel);
		}
		System.out.println("NEW MODEL");
		for(int i = 0;i < iterations;i++) {
			if(i % 500 == 0) {
				System.out.println("New Model iteration " + i);
			}
			NewModel newModel = new NewModel();
			for(int j = 1;j <= totalPacks;j++) {
				newModel.addPack();
			}
			averageNew.add(newModel);
		}
		
		averageOld.devide(iterations);
		averageNew.devide(iterations);
		
		System.out.println("Old model averages");
		System.out.println(averageOld.printCSVHeader());
		System.out.println(averageOld.printCSV());
		System.out.println("New model averages");
		System.out.println(averageNew.printCSVHeader());
		System.out.println(averageNew.printCSV());
	}
}
