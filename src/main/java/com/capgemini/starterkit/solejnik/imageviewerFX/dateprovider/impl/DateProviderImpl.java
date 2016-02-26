package com.capgemini.starterkit.solejnik.imageviewerFX.dateprovider.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.starterkit.solejnik.imageviewerFX.dateprovider.DateProvider;
import com.capgemini.starterkit.solejnik.imageviewerFX.dateprovider.data.MyImage;

import javafx.scene.image.Image;

public class DateProviderImpl implements DateProvider {
	private List<MyImage> images = new ArrayList<MyImage>();
	private final static List<String> EXTENSIONS = new ArrayList<String>(Arrays.asList("jpg", "png", "bmp", "gif"));
	private final static Logger LOG = Logger.getLogger(DateProviderImpl.class);
	public void setImages(File folder) {
		List<MyImage> images = new ArrayList<MyImage>();
		List<File> fileList = new ArrayList<File>(Arrays.asList(folder.listFiles()));
		for (final File file : fileList) {
			String path = file.getPath();
			int lastDotIndex = path.lastIndexOf('.');
			if (EXTENSIONS.contains(file.toString().substring(lastDotIndex + 1))) {
				MyImage image = new MyImage(new Image("file:" + path)) ;
				image.setName(file.getName());
				LOG.debug("Add image "+image.getName());
				images.add(image);
			}
		}
		this.images = images;
	}

	public List<MyImage> getImages() {
		return images;
	}

	public void setImage(File file) {
		LOG.debug("Clean images list");
		images.clear();
		MyImage image = new MyImage(new Image("file:" + file.getPath()));
		image.setName(file.getName());
		images.add(image);
		LOG.debug("Add image "+image.getName());
	}
}
