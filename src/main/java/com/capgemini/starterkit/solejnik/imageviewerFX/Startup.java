package com.capgemini.starterkit.solejnik.imageviewerFX;

import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Startup extends Application {
	private static final Logger LOG = Logger.getLogger(Startup.class);
    @Override
    public void start(Stage stage) throws Exception {
    	LOG.debug("Starting application");
        Parent root = FXMLLoader.load(getClass().getResource("/com/capgemini/starterkit/solejnik/imageviewerFX/view/ImageViewer.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/com/capgemini/starterkit/solejnik/imageviewerFX/css/Styles.css");
        
        stage.setTitle("Images gallery");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
    	LOG.debug("Launching application");
        launch(args);
    }

}
