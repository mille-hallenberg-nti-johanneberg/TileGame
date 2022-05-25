package main.item;

import java.io.Serializable;

public class Item implements Serializable{
	
	private ItemType type;
	
	public Item(ItemType type){
		this.type = type;
	}

	public ItemType getType() {
		return type;
	}
}
