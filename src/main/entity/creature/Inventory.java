package main.entity.creature;

import java.util.ArrayList;

import main.item.Item;

public class Inventory {
	
	private ArrayList<Item> items;
	private int size;
	
	public Inventory(int size){
		this.size = size;
		items = new ArrayList<>();
	}
	
	public int getSize(){
		return size;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
