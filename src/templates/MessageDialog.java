package templates;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets; 

/*
 * Jose Retamal
*  GUI class used to prompt messages to user
*  
 */

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
		HBox labelHB = new HBox();
		HBox buttonHB = new HBox();
		labelHB.getChildren().add(messageLabel);
		buttonHB.getChildren().add(okButton);
		//style
		labelHB.setAlignment(Pos.CENTER);
		buttonHB.setAlignment(Pos.CENTER);
		labelHB.setPadding(new Insets(5,10,5,10));
		
		Scene scene = new Scene(rootVB, 450, 100);
		
		rootVB.getChildren().addAll(labelHB,buttonHB);
		
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
