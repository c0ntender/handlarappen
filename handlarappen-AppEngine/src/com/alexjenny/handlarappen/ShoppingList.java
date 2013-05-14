package com.alexjenny.handlarappen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class ShoppingList {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private int id;



	@Persistent
	private Set<ShoppableItem> itemsToBuy;
	
	public int getId() {
		return id;
	}
	
	public List<ShoppableItem> getItems() {
		return new ArrayList<ShoppableItem>(itemsToBuy);
	}

	public void removeItem(ShoppableItem item) {
		itemsToBuy.remove(item);
		
	}

	public void addItem(ShoppableItem item) {
		itemsToBuy.add(item);		
	}

}
