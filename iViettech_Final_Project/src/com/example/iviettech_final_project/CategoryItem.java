package com.example.iviettech_final_project;

public class CategoryItem {
	private String nameItem;
	private int imageID;
	
	public CategoryItem(String nameItem, int imageID) {
		this.nameItem = nameItem;
		this.imageID = imageID;
	}

	public String getNameItem() {
		return nameItem;
	}

	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	
}
