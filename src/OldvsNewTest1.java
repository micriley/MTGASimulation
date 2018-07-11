
public class OldvsNewTest1 {
	public static void main(String[]args) {
		int totalPacks = 200;
		OldModel oldModel = new OldModel();
		NewModel newModel = new NewModel();
		System.out.println("OLD MODEL");
		System.out.println(oldModel.printCSVHeader());
		for(int i = 1;i <= totalPacks;i++) {
			oldModel.addPack();
			if(i % 5 == 0) {
				System.out.println(oldModel.printCSV());
			}
		}
		System.out.println("NEW MODEL");
		System.out.println(newModel.printCSVHeader());
		for(int i = 1;i <= totalPacks;i++) {
			newModel.addPack();
			if(i % 5 == 0) {
				System.out.println(newModel.printCSV());
			}
		}
	}
}
