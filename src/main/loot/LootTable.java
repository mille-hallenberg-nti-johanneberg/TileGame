package main.loot;

import java.util.HashMap;

import main.entity.statics.*;

public class LootTable {
	
	private static final String staticEntityLootPath = "src/main/loot/staticEntityLoot";
	
	private static HashMap<Class, String> staticEntityLootTable = new HashMap<>();
	
	public LootTable(String path){
		
	}
	
	public static void init(){
		staticEntityLootTable.put(Tree.class, "Ayo");
	}
}
