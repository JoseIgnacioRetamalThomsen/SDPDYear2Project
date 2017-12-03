package fileanalyzergui;

import templates.*;
import fileanalyzer.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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

public class MainWindows extends Application implements EventHandler<ActionEvent>
{

	// constants
	static final String IMPORTS_FOLDER = "Archives";
	static final String[] ARCHIVES_SUPPORTED =
	{ "EnglishPlainText" };

	List<Displayable> filesImported;
	int filePosition = 0;

	Label leftFileFoundLabel;

	// GUI static variables
	// containers
	private static VBox mainVB;
	private static BorderPane borderPane;
	private static HBox topHB;
	private static VBox leftEverythingVB;
	private static VBox leftFilesMenuVB;
	private static HBox leftSearchHB;
	private static HBox midAllHB;
	private static VBox midLettesCoundVB;
	private static VBox midTextVB;
	private static HBox leftSearchInFileHB;
	// menu bar
	// menus
	private static MenuBar menuBar;
	private static Menu menuFile;
	private static Menu menuImport;
	private static Menu menuHelp;
	// sub-menus
	// file
	private static MenuItem exitMI;
	// import
	private static MenuItem importEnglishPlainTextFileMI;
	// help
	private static MenuItem aboutMI;

	private static Label topFileNameLabel;

	private static ScrollPane leftFilesSP;
	private static TextField leftSearchFilesTF;
	private static Button leftSeachFilesButton;

	private static Label midLetterCountLabel;

	private static Label midTextLabel;
	private static ScrollPane miLettersCountSP;
	private static ScrollPane midTexSP;

	private static TextField leftSearchInFileTF;
	private static Button leftSearchInFileButton;
	private static Label leftSearchInFileResultLabel;

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

		// create GUI
		createGUI();
	}

	private void createGUI()
	{
		// Create main Containers
		mainVB = new VBox();
		borderPane = new BorderPane();

		leftSearchHB = new HBox();

		// menuBard
		menuBar = new MenuBar();
		// menus
		menuFile = new Menu("File");
		menuImport = new Menu("Import");
		menuHelp = new Menu("Help");
		// sub-menus
		// file
		exitMI = new MenuItem("Exit");
		menuFile.getItems().addAll(exitMI);
		// import
		importEnglishPlainTextFileMI = new MenuItem("English PLain Text File");
		menuImport.getItems().addAll(importEnglishPlainTextFileMI);
		// help
		aboutMI = new MenuItem("about");
		menuHelp.getItems().addAll(aboutMI);
		// add all to menu bar
		menuBar.getMenus().addAll(menuFile, menuImport, menuHelp);

		// top
		topHB = new HBox();// top container
		topFileNameLabel = new Label();
		topHB.getChildren().add(topFileNameLabel);
		borderPane.setTop(topHB);// add to border pane
		topHB.setAlignment(Pos.CENTER);// style
		topFileNameLabel.setScaleX(1.5);// style
		topFileNameLabel.setScaleY(1.5);// style

		// left
		leftEverythingVB = new VBox();
		leftFilesMenuVB = new VBox();
		leftFilesSP = new ScrollPane(leftFilesMenuVB);
		// search files
		leftSearchFilesTF = new TextField();
		leftSeachFilesButton = new Button("SEARCH");
		leftSearchHB.getChildren().addAll(leftSearchFilesTF, leftSeachFilesButton);
		leftFileFoundLabel = new Label();
		new VBox();
		
		leftSearchInFileHB = new HBox();
		leftSearchInFileTF = new TextField();
		leftSearchInFileButton = new Button("SEARCH");
		leftSearchInFileHB.getChildren().addAll(leftSearchInFileTF, leftSearchInFileButton);
		leftSearchInFileResultLabel = new Label();
		leftEverythingVB.getChildren().addAll(leftFilesSP, leftSearchHB, leftFileFoundLabel);// to leftVB
		leftEverythingVB.getChildren().addAll(leftSearchInFileHB, leftSearchInFileResultLabel);// to leftVB
		borderPane.setLeft(leftEverythingVB);// to border pane
		// style
		leftFilesMenuVB.setPrefSize(150, 150);
		leftFilesMenuVB.maxHeight(150);
		leftSearchFilesTF.setPromptText("Serch Files");
		leftSearchInFileTF.setPromptText("Serch in selected file");

		// middle (center)
		midAllHB = new HBox();
		midLettesCoundVB = new VBox();
		midTextVB = new VBox();
		midLetterCountLabel = new Label();
		miLettersCountSP = new ScrollPane(midLettesCoundVB);
		midTextLabel = new Label();
		midTexSP = new ScrollPane(midTextVB);
		midLettesCoundVB.getChildren().add(midLetterCountLabel);
		midTextVB.getChildren().add(midTextLabel);
		midAllHB.getChildren().addAll(miLettersCountSP, midTexSP); // add to midALLHB
		borderPane.setCenter(midAllHB);// set on border
		// style
		miLettersCountSP.setPrefWidth(200);
		midTexSP.setMaxWidth(600);
		midTexSP.setPrefWidth(600);

		// add to mainVB
		// MenuBar
		mainVB.getChildren().add(menuBar);
		// borderPane
		mainVB.getChildren().add(borderPane);

	}

	@Override
	public void start(Stage stageOne)
	{

		// create Scene and set main container in scene
		Scene scene = new Scene(mainVB, 900, 350);
		// set title to stage
		stageOne.setTitle("File Analyzer");
		// set scene to stage
		stageOne.setScene(scene);
		stageOne.show();

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
			Label leftFileLabel;
			leftFileLabel = new Label(archive.getFileNameNoExtension());
			leftFilesMenuVB.getChildren().add(leftFileLabel);

			// add box when mouse move over
			leftFileLabel.setOnMouseMoved((Event) ->
			{
				leftFileLabel.setStyle("-fx-border-color:black");
			});

			leftFileLabel.setOnMouseExited((Event) ->
			{
				leftFileLabel.setStyle("-fx-border-color:transparent");
			});

			leftFileLabel.setOnMouseClicked((Event) ->
			{
				try
				{
					int position = filesImported.indexOf(archive);
					setFile.setFileWithPosition(position);
				} catch (Exception e)
				{
				}
			});
		} // for:archive

		// listeners
		// MenuBar listeners
		importEnglishPlainTextFileMI.setOnAction((ActionEvent t) ->
		{
			ImportFile importFile = new ImportFile("EnglishPlainText", new File("this"), new Stage(), filesImported,
					leftFilesMenuVB, midLetterCountLabel, midTextLabel);
			setFile.setFileWithPosition(importFile.getPosition());

		});// end import file action event

		// search for file listener
		leftSeachFilesButton.setOnAction((ActionEvent) ->
		{
			String textToSearch = leftSearchFilesTF.getText();
			for (Displayable file : filesImported)
			{
				if (file.getFileNameNoExtension().equalsIgnoreCase(textToSearch))
				{
					leftFileFoundLabel.setText(textToSearch);

					leftFileFoundLabel.setOnMouseMoved((Event) ->
					{
						leftFileFoundLabel.setStyle("-fx-border-color:black");
					});

					leftFileFoundLabel.setOnMouseExited((Event) ->
					{
						leftFileFoundLabel.setStyle("-fx-border-color:transparent");
					});

					leftFileFoundLabel.setOnMouseClicked((Event) ->
					{
						try
						{
							setFile.setFileWithPosition(filesImported.indexOf(file));

						} catch (Exception e)
						{
						}
					});

					break;
				}
			}
		});
		// search in file listener
		leftSearchInFileButton.setOnAction((ActionEvent) ->
		{
			String textToSearch = leftSearchInFileTF.getText();
			try
			{
				filesImported.get(filePosition).searchOnFile(textToSearch, leftSearchInFileResultLabel);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}// end start()

	public static void main(String[] args)
	{
		launch(args);

	}

	@Override
	public void handle(ActionEvent arg0)
	{

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