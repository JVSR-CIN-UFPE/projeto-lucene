package demo;

public class LuceneDemo {

	public static final String FILES_TO_INDEX_DIRECTORY = "filesToIndex";
	public static final String INDEX_DIRECTORY = "indexDirectory";

	public static final String FIELD_PATH = "path";
	public static final String FIELD_CONTENTS = "contents";

	public static void main(String[] args) {
		createIndex();
		searchIndex("mushrooms");
		searchIndex("steak");
		searchIndex("steak AND cheese");
		searchIndex("steak and cheese");
		searchIndex("bacon OR cheese");

	}
	
	public static void createIndex() {
		
	}
	
	public static void searchIndex(String busca) {
		
	}
}
