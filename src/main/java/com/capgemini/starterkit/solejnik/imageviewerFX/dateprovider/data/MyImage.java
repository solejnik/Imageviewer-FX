package com.capgemini.starterkit.solejnik.imageviewerFX.dateprovider.data;

import javafx.scene.image.Image;

public class MyImage {
	private Image image;
	private String name;

	public MyImage(Image image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

}
