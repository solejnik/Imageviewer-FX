package com.capgemini.starterkit.solejnik.imageviewerFX.controller;

import java.io.File;

import org.apache.log4j.Logger;

import com.capgemini.starterkit.solejnik.imageviewerFX.dateprovider.DateProvider;
import com.capgemini.starterkit.solejnik.imageviewerFX.dateprovider.data.MyImage;
import com.capgemini.starterkit.solejnik.imageviewerFX.model.ImageViewerModel;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;

public class ImageViewerController {

	@FXML
	TableView<MyImage> resultTable;
	@FXML
	TableColumn<MyImage, String> imageColumn;
	@FXML
	ImageView imageView;
	@FXML
	Button loadImagesButton;
	@FXML
	Button loadImageButton;
	@FXML
	Button nextButton;
	@FXML
	Button previousButton;
	@FXML
	Button rotate90Button;
	@FXML
	Button rotateMinus90Button;
	@FXML
	Button slideshowStartButton;
	@FXML
	Button slideshowStopButton;

	private final static Logger LOG = Logger.getLogger(ImageViewerController.class);
	private ImageViewerModel model = new ImageViewerModel();
	private DateProvider provider = DateProvider.INSTANCE;
	private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), ae -> nextImage()));

	@FXML
	private void initialize() {
		LOG.debug("Initializing");
		initializeTable();
		resultTable.itemsProperty().bindBidirectional(model.resultProperty());
	}

	private void initializeTable() {
		imageColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
		resultTable.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
			MyImage myImage = resultTable.getSelectionModel().getSelectedItem();
			if (myImage != null) {
				imageView.setFitHeight(myImage.getImage().getHeight());
				imageView.setFitWidth(myImage.getImage().getWidth());
				imageView.setImage(myImage.getImage());
				imageView.setRotate(0);
			}
		});
	}

	@FXML
	private void loadImagesButtonAction(ActionEvent event) {
		stopSlideShow();
		LOG.debug("Loading images");
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(loadImagesButton.getScene().getWindow());
		if (selectedDirectory != null) {
			Runnable backgroundTask = new Runnable() {
				@Override
				public void run() {
					File folderPath = selectedDirectory.getAbsoluteFile();
					provider.setImages(folderPath);
					model.setResult(provider.getImages());
				}
			};
			new Thread(backgroundTask).start();
		}
	}

	@FXML
	private void loadImageButtonAction(ActionEvent event) {
		stopSlideShow();
		LOG.debug("Loading single image");
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("GIF", "*.gif"), new FileChooser.ExtensionFilter("BMP", "*.bmp"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));
		File file = fileChooser.showOpenDialog(getPrimaryStage());
		if (file != null) {
			Runnable backgroundTask = new Runnable() {
				@Override
				public void run() {
					provider.setImage(file);
					model.setResult(provider.getImages());
				}
			};
			new Thread(backgroundTask).start();
		}
	}

	@FXML
	private void nextButtonAction(ActionEvent event) {
		LOG.debug("Next button clicked");
		nextImage();
	}

	@FXML
	private void previousButtonAction(ActionEvent event) {
		LOG.debug("Previous button clicked");
		previousImage();
	}

	@FXML
	private void rotation90ButtonAction(ActionEvent event) {
		LOG.debug("Rotation 90 degrees button clicked");
		imageView.setRotate(imageView.getRotate() + 90);
	}

	@FXML
	private void rotationMinus90ButtonAction(ActionEvent event) {
		LOG.debug("Rotation minus 90 degrees button clicked");
		imageView.setRotate(imageView.getRotate() - 90);
	}

	@FXML
	private void slideshowButtonAction(ActionEvent event) {
		LOG.debug("Start slideshow button clicked");
		if (!model.resultProperty().isEmpty()) {
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
		}
	}

	@FXML
	private void stopSlideshowButton() {
		LOG.debug("Stop slideshow button clicked");
		stopSlideShow();
	}

	private void stopSlideShow() {
		if (timeline != null) {
			timeline.stop();
		}
	}

	private Window getPrimaryStage() {
		return resultTable.getScene().getWindow();
	}

	private void nextImage() {
		if (resultTable.getSelectionModel().getSelectedIndex() == resultTable.getItems().size() - 1) {
			resultTable.getSelectionModel().selectFirst();
		} else {
			resultTable.getSelectionModel().selectNext();
		}
	}

	private void previousImage() {
		if (resultTable.getSelectionModel().getSelectedIndex() == 0) {
			resultTable.getSelectionModel().selectLast();
			;
		} else {
			resultTable.getSelectionModel().selectPrevious();
		}
	}
}
