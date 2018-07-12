
public class OldVsNewLargeTest {
	public static void main(String[]args) {
		int totalPacks = 200;
		int iterations = 10000;
		
		OldModel totalOld = new OldModel();
		NewModel totalNew = new NewModel();
		
		System.out.println("OLD MODEL");
		System.out.println(totalOld.printCSVHeader());
		for(int packs = 5; packs <= totalPacks; packs += 5){
			totalOld = new OldModel();
			for(int i = 0;i < iterations;i++) {
				OldModel oldModel = new OldModel();
				for(int j = 1;j <= packs;j++) {
					oldModel.addPack();
				}
				totalOld.add(oldModel);
			}
			OldModel average = new OldModel();
			try {
				average = (OldModel)totalOld.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			average.devide(iterations);
			System.out.println(average.printCSV());
		}
		
		System.out.println("NEW MODEL");
		System.out.println(totalNew.printCSVHeader());
		for(int packs = 5; packs <= totalPacks; packs += 5){
			totalNew = new NewModel();
			for(int i = 0;i < iterations;i++) {
				NewModel newModel = new NewModel();
				for(int j = 1;j <= packs;j++) {
					newModel.addPack();
				}
				totalNew.add(newModel);
			}
			NewModel average = new NewModel();
			try {
				average = (NewModel)totalNew.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			average.devide(iterations);
			System.out.println(average.printCSV());
		}
	}
}
