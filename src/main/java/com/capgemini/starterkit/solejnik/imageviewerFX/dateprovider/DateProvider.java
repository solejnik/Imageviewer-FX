package com.capgemini.starterkit.solejnik.imageviewerFX.dateprovider;

import java.io.File;
import java.util.List;

import com.capgemini.starterkit.solejnik.imageviewerFX.dateprovider.data.MyImage;
import com.capgemini.starterkit.solejnik.imageviewerFX.dateprovider.impl.DateProviderImpl;

public interface DateProvider {
	public DateProvider INSTANCE = new DateProviderImpl();
	public void setImages(File folder);
	public List<MyImage> getImages();
	public void setImage(File file);
}
