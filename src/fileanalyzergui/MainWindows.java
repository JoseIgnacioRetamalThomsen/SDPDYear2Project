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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.*;
import java.io.*;
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

	List<Archive> filesImported;

	public MainWindows()
	{
		// fill FilesImported
		File importsFolder = new File(IMPORTS_FOLDER);
		File filesToImport[];

		filesImported = new ArrayList<Archive>();

		for (String archiveType : ARCHIVES_SUPPORTED)
		{
			File specificTypeArchiveFolder = new File(importsFolder, archiveType);

			if (archiveType.equals("EnglishPlainText"))
			{
				filesToImport = specificTypeArchiveFolder.listFiles();
				if (filesToImport != null)
				{
					for (File file : filesToImport)
					{
						// add file to list
						filesImported.add(new EnglishPlainText(file));
					}
				} // end for:filesToImport
			} // end if archiveType

		} // end for:ARCHIVES_SUPPORTED

		for (Archive a : filesImported)
		{
			System.out.println(a.toString());
		}
	}

	@Override
	public void start(Stage stageOne)
	{
		// create main container
		VBox mainVB = new VBox();
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

		// VBox on left for show files
		VBox filesLeftVB = new VBox();
		// add to borderPane
		borderPane.setLeft(filesLeftVB);

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
		midTexSP.setMaxWidth(800);
		midAllHB.getChildren().addAll(miLettersCountSP, midTexSP);

		borderPane.setCenter(midAllHB);

		// fill filesLeftWith existing file from filesImported

		for (Archive archive : filesImported)
		{
			Label labelToAdd;
			labelToAdd = new Label(archive.getFileName().replace('\\', '0').split("0")[2]);
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

						((EnglishPlainText) archive).analyzeArchive();
						((EnglishPlainText) archive).displayCount(midLetterCountLabel);
						((EnglishPlainText) archive).displayArchive(midTextLabel);

					} catch (Exception e)
					{
					}
				}
			});
		}//for:archive
		
		//import file
		importFileMI.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent t)
			{
				
					Thread thread = new Thread(new Runnable() {
						public void run()
						{
							FileChooser fileChooser = new FileChooser();
							fileChooser.setTitle("Open Resource File");
							fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all","*.*"),new FileChooser.ExtensionFilter("txt","*.txt"),new FileChooser.ExtensionFilter("log","*.log"));
							File file = fileChooser.showOpenDialog(stageOne);
							
							//get to folder
							File archivesFole = new File(IMPORTS_FOLDER);
							File englishPlainTextFolder = new File(IMPORTS_FOLDER,ARCHIVES_SUPPORTED[0]);
							
								System.out.println(file.getName());
						
							//create the file
							File importedFile = new File(englishPlainTextFolder,file.getName());
							//OpenFile(file);
							//copy file to import folder
							try
							{
								Scanner scanner = new Scanner(file);
								PrintWriter print = new PrintWriter(new FileWriter(importedFile));
								while(scanner.hasNext())
								{
									print.println(scanner.nextLine());
								}
								System.out.println("End");
								print.close();
								scanner.close();
							}catch(IOException ex)
							{
								
							}
							//create Archive
							Archive inportedArchive = new EnglishPlainText(file);
							//analize
							try
							{
								inportedArchive.analyzeArchive();
							} catch (Exception e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//add to list
							filesImported.add(inportedArchive);
							//add to GUI
							Label tempLabel=new Label(file.getName());
							filesLeftVB.getChildren().add(tempLabel);
							//add listeners
							tempLabel.setOnMouseMoved(new EventHandler<Event>()
							{
								public void handle(Event t)
								{

									tempLabel.setStyle("-fx-border-color:black");
								}
							});

							tempLabel.setOnMouseExited(new EventHandler<Event>()
							{
								public void handle(Event t1)
								{

									tempLabel.setStyle("-fx-border-color:transparent");
								}
							});

							tempLabel.setOnMouseClicked(new EventHandler<Event>()
							{
								public void handle(Event t)
								{

									try
									{

										((EnglishPlainText) inportedArchive).analyzeArchive();
										((EnglishPlainText) inportedArchive).displayCount(midLetterCountLabel);
										((EnglishPlainText) inportedArchive).displayArchive(midTextLabel);

									} catch (Exception e)
									{
									}
								}
							});
							
						}
					});
					thread.run();
				
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
