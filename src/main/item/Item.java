package main.item;

import java.io.Serializable;

public class Item implements Serializable{
	
	private ItemType type;
	private int amount;
	
	public Item(ItemType type){
		this.type = type;
	}

	public ItemType getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int stack) {
		this.amount = stack;
	}
}
