package com.alexjenny.handlarappen;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Represents a shoppable item
 * 
 */
@PersistenceCapable
//@Unique(name = "name_unique", members = { "name" })
public class ShoppableItem {

	@PrimaryKey
	@Persistent
	String name;

	@Persistent
	Category category;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return this.category;
	}

}
