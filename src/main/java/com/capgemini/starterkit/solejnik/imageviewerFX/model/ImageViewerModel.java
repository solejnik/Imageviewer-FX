package com.capgemini.starterkit.solejnik.imageviewerFX.model;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.starterkit.solejnik.imageviewerFX.dateprovider.data.MyImage;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class ImageViewerModel {
	private final ListProperty<MyImage> result = new SimpleListProperty<MyImage>(
			FXCollections.observableList(new ArrayList<MyImage>()));

	public ListProperty<MyImage> resultProperty() {
		return result;
	}

	public final void setResult(List<MyImage> files) {
		result.setAll(files);
	}

	public final List<MyImage> getResult() {
		return result.get();
	}

}
