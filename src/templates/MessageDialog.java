package templates;


import templates.*;
import fileanalyzer.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.*;
import java.io.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;

import javafx.application.Application;
import javafx.stage.Stage;

public class MessageDialog extends Application
{

	String title;
	String message;
	Label messageLabel;
	Button okButton;
	Stage stage;
	
	public MessageDialog(String title,String message,Stage stage)
	{
		this.title = title;
		this.message = message;
		this.stage = stage;
		
		messageLabel = new Label(message);
		okButton = new Button("OK");
	
		stage.initModality(Modality.WINDOW_MODAL);
		this.start(stage);
	}
	
	@Override
	public void start(Stage stage1) 
	{
		VBox rootVB = new VBox();
		Scene scene = new Scene(rootVB, 400, 100);
		
		rootVB.getChildren().addAll(messageLabel,okButton);
		
		stage1.setTitle(title);
		// set scene to stage
		stage1.setScene(scene);
		
		okButton.setOnAction((ActionEvent actionEvent)->
		{
			stage1.close();
		});
		
		stage1.show();
	}

}
