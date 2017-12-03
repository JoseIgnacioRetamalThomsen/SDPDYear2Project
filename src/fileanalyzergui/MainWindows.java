package fileanalyzergui;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.*;
import java.io.*;

import java.util.ArrayList;

import javafx.concurrent.Task;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

/*
 * Jose Retaml SDPD project
 * Main Windows of the application
 */

public class MainWindows extends Application
{
	static final String IMPORTS_FOLDER = "Archives";
	static final String[] ARCHIVES_SUPPORTED =
	{ "EnglishPlainText" };

	List<Displayable> filesImported;
	int filePosition = 0;

	Label foundLabel;

	public MainWindows()
	{

		// fill FilesImported
		File importsFolder = new File(IMPORTS_FOLDER);

		filesImported = new ArrayList<Displayable>();

		
			File filesToImport[];
			for (String archiveType : ARCHIVES_SUPPORTED)
			{
				File specificTypeArchiveFolder = new File(importsFolder, archiveType);

				if (archiveType.equals("EnglishPlainText"))
				{
					// return array with all files in folder
					filesToImport = specificTypeArchiveFolder.listFiles();
					if (filesToImport != null)
					{
						for (File file : filesToImport)
						{
							// add file to list
							EnglishPlainText toADd = new EnglishPlainText(file);
							try
							{
								toADd.analyzeArchive();
								filesImported.add(toADd);
							} catch (Exception e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					} // end for:filesToImport
				} // end if archiveType

			} // end for:ARCHIVES_SUPPORTED

	
	}

	VBox mainVB;

	@Override
	public void start(Stage stageOne)
	{

		// create main container
		mainVB = new VBox();
		// create Scene and set main container in scene
		Scene scene = new Scene(mainVB, 900, 350);
		// set title to stage
		stageOne.setTitle("File Analyzer");
		// set scene to stage
		stageOne.setScene(scene);

		// Menu Bar
		MenuBar menuBar = new MenuBar();
		// create File item for menu bar menuBar
		Menu menuFile = new Menu("File");
		// create items inside file
		MenuItem importFileMI = new MenuItem("Import File");
		MenuItem exitMI = new MenuItem("Exit");
		// add items to menuFile
		menuFile.getItems().addAll(importFileMI, exitMI);

		// add items to menuBar
		menuBar.getMenus().addAll(menuFile);

		// add menuBar to mainVB
		mainVB.getChildren().add(menuBar);

		// body
		// we will only use left, mid and bottom of borderPane
		BorderPane borderPane = new BorderPane();
		// add border pane to main
		mainVB.getChildren().add(borderPane);

		// top for actual file name
		HBox topHB = new HBox();
		Label topFileNameLabel = new Label();
		topHB.getChildren().add(topFileNameLabel);
		borderPane.setTop(topHB);
		;

		// VBox on left for show files
		VBox mainLeftVB = new VBox();
		VBox filesLeftVB = new VBox();
		// set size
		filesLeftVB.setPrefSize(150, 150);
		filesLeftVB.maxHeight(150);

		// scroll panel for files
		ScrollPane filesLeftSP = new ScrollPane(filesLeftVB);
		// add to main VBox
		mainLeftVB.getChildren().add(filesLeftSP);

		// GUI for search for files
		HBox searchLeftHBox = new HBox();
		TextField searchTextFieldLeft = new TextField();
		searchTextFieldLeft.setPromptText("Serch Files");
		Button searchButtonLeft = new Button("SEARCH");

		searchLeftHBox.getChildren().addAll(searchTextFieldLeft, searchButtonLeft);
		mainLeftVB.getChildren().add(searchLeftHBox);

		foundLabel = new Label();
		mainLeftVB.getChildren().add(foundLabel);
		borderPane.setLeft(mainLeftVB);

		// mid HBox and VBox
		HBox midAllHB = new HBox();
		VBox midLettesCoundVB = new VBox();

		Label midLetterCountLabel = new Label();

		midLettesCoundVB.getChildren().add(midLetterCountLabel);
		VBox midTextVB = new VBox();
		Label midTextLabel = new Label();
		midTextVB.getChildren().add(midTextLabel);
		ScrollPane miLettersCountSP = new ScrollPane(midLettesCoundVB);
		miLettersCountSP.setPrefWidth(200);
		ScrollPane midTexSP = new ScrollPane(midTextVB);
		midTexSP.setMaxWidth(600);
		midTexSP.setPrefWidth(600);
		midAllHB.getChildren().addAll(miLettersCountSP, midTexSP);

		// left for search on file
		VBox rightSearchfileVB = new VBox();
		HBox righSearchFileHB = new HBox();
		TextField rightSearchOnFileTF = new TextField();
		rightSearchOnFileTF.setPromptText("Serch in selected file");
		Button rightSearchButton = new Button("Search");
		righSearchFileHB.getChildren().addAll(rightSearchOnFileTF, rightSearchButton);
		Label rightShowResult = new Label();

		rightSearchfileVB.getChildren().addAll(righSearchFileHB, rightShowResult);
		borderPane.setRight(rightSearchfileVB);

		
		borderPane.setCenter(midAllHB);

		// inner class for set file in mid
		class SetFile
		{
			public void setFileWithPosition(int position)
			{
				try
				{
					// ((EnglishPlainText)filesImported.get(position)).analyzeArchive();
					filesImported.get(position).displayCount(midLetterCountLabel);
					filesImported.get(position).displayArchive(midTextLabel);
					filePosition = position;
					// put file name in top
					topFileNameLabel.setText(filesImported.get(position).getFileNameNoExtension());
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}// SetFile

		// set file to help when is first time
		SetFile setFile = new SetFile();
		setFile.setFileWithPosition(0);

		// fill filesLeftWith existing file from filesImported

		for (Displayable archive : filesImported)
		{
			Label labelToAdd;
			labelToAdd = new Label(archive.getFileNameNoExtension());// getFileName().replace('\\', '0').split("0")[2]);
			filesLeftVB.getChildren().add(labelToAdd);

			// add box when mouse move over
			labelToAdd.setOnMouseMoved(new EventHandler<Event>()
			{
				public void handle(Event t)
				{

					labelToAdd.setStyle("-fx-border-color:black");
				}
			});

			labelToAdd.setOnMouseExited(new EventHandler<Event>()
			{
				public void handle(Event t1)
				{

					labelToAdd.setStyle("-fx-border-color:transparent");
				}
			});

			labelToAdd.setOnMouseClicked(new EventHandler<Event>()
			{
				public void handle(Event t)
				{

					try
					{

						// ((EnglishPlainText) archive).analyzeArchive();
						// ((EnglishPlainText) archive).displayCount(midLetterCountLabel);
						// ((EnglishPlainText) archive).displayArchive(midTextLabel);
						int position = filesImported.indexOf(archive);
						setFile.setFileWithPosition(position);

					} catch (Exception e)
					{
					}
				}
			});
		} // for:archive

		// import file

		importFileMI.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent t)
			{

				ImportFile importFile = new ImportFile("EnglishPlainText", new File("this"), new Stage(), filesImported,
						filesLeftVB, midLetterCountLabel, midTextLabel);
				setFile.setFileWithPosition(importFile.getPosition());
				/*
				 * ImportFile test = new ImportFile("EnglishPlainText",new File("this"),new
				 * Stage(),filesImported,filesLeftVB,midLetterCountLabel,midTextLabel); Thread
				 * tt = new Thread(test);
				 * 
				 * tt.start();
				 */

			}// end handle
		});// end impor file action event

		// listener to search left button

		searchButtonLeft.setOnAction((ActionEvent) ->
		{
			String textToSearch = searchTextFieldLeft.getText();
			for (Displayable file : filesImported)
			{
				if (file.getFileNameNoExtension().equalsIgnoreCase(textToSearch))
				{
					foundLabel.setText(textToSearch);

					foundLabel.setOnMouseMoved(new EventHandler<Event>()
					{
						public void handle(Event t)
						{

							foundLabel.setStyle("-fx-border-color:black");
						}
					});

					foundLabel.setOnMouseExited(new EventHandler<Event>()
					{
						public void handle(Event t1)
						{

							foundLabel.setStyle("-fx-border-color:transparent");
						}
					});

					foundLabel.setOnMouseClicked(new EventHandler<Event>()
					{
						public void handle(Event t)
						{

							try
							{

								// ((EnglishPlainText) archive).analyzeArchive();
								((EnglishPlainText) file).displayCount(midLetterCountLabel);
								((EnglishPlainText) file).displayArchive(midTextLabel);

							} catch (Exception e)
							{
							}
						}
					});

					break;
				}
			}
		});
		rightSearchButton.setOnAction((ActionEvent) ->
		{
			String textToSearch = rightSearchOnFileTF.getText();
			try
			{
				filesImported.get(filePosition).searchOnFile(textToSearch,rightShowResult);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});


		// show stage
		stageOne.show();

	}

	public static void main(String[] args)
	{
		launch(args);

	}

}

class test extends Application implements Runnable
{
	Stage st;

	public test(Stage stage)
	{
		st = stage;
	}

	public void start(Stage s)
	{
		// create main container
		VBox mainVB = new VBox();
		// create Scene and set main container in scene
		Scene scene = new Scene(mainVB, 900, 350);
		// set title to stage
		s.setTitle("File Analyzer");
		// set scene to stage
		s.setScene(scene);
	}

	public void run()
	{
		this.start(st);
	}
}