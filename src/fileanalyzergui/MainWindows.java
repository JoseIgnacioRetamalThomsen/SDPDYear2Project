package fileanalyzergui;

import templates.*;
import fileanalyzer.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import java.util.*;
import java.io.*;
import java.util.ArrayList;

import background.BackgroundTasks;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;

/*
 * Jose Retaml SDPD project
 * Main Windows of the application
 */

public class MainWindows extends Application
{

	// constants
	static final String IMPORTS_FOLDER = "Archives";
	static final String[] ARCHIVES_SUPPORTED =
	{ "EnglishPlainText", "SpanishPlainText" };

	///****************ArryList declared polymorphically 
	List<Displayable> filesImported;
	// variable for the actual file that is selected in GUI
	int filePosition = 0;

	
	

	// errFile = new PrintWriter(new FileOutputStream(new
	// File("data\\savez\\udvhf.klf"),false));
	PrintWriter errFile;
	String ERR_FILE_NAME = "erroFile.dat";

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
	private static MenuItem importSpanishPlainTextFileMI;
	// help
	private static MenuItem helptMI;
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

	//label for show files name, many of this will be created
	Label leftFileFoundLabel;
	
	public MainWindows()
	{

		// fill FilesImported
		File importsFolder = new File(IMPORTS_FOLDER);
		//Initiate ArrayList of Displayable which hold all files analized.
		filesImported = new ArrayList<Displayable>();

		File filesToImportEnglish[];
		File filesToImportSpanish[];
		for (String archiveType : ARCHIVES_SUPPORTED)
		{
			File specificTypeArchiveFolder = new File(importsFolder, archiveType);
			// select English or Spanish
			if (archiveType.equals("EnglishPlainText"))
			{
				// return array with all files in folder
				filesToImportEnglish = specificTypeArchiveFolder.listFiles();
				if (filesToImportEnglish != null)
				{
					for (File file : filesToImportEnglish)
					{
						// create 
						EnglishPlainText toADd = new EnglishPlainText(file);
						try
						{
							toADd.analyzeArchive();
							//add File type EnglishPlainText to ArrayList
							filesImported.add(toADd);
						} catch (Exception e)
						{
							//print to error file
							try
							{
								errFile = new PrintWriter(new FileOutputStream(new File(ERR_FILE_NAME), true));
							} catch (FileNotFoundException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							errFile.println("888888 "+e.toString());
							errFile.close();
						}

					}
				} // end for:filesToImport
			} else if (archiveType.equals("SpanishPlainText"))
			{
				// return array with all files in folder
				filesToImportSpanish = specificTypeArchiveFolder.listFiles();
				if (filesToImportSpanish != null)
				{
					for (File file : filesToImportSpanish)
					{
						// create
						SpanishPlainText toADd = new SpanishPlainText(file);
						try
						{
							toADd.analyzeArchive();
							//add File type SpanishPlainText to ArrayList
							filesImported.add(toADd);
						} catch (Exception e)
						{
							//print to error file
							try
							{
								errFile = new PrintWriter(new FileOutputStream(new File(ERR_FILE_NAME), true));
							} catch (FileNotFoundException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							errFile.println("888888 "+e.toString());
							errFile.close();
						}

					}
				} // end for:filesToImport
			}
			// end if archiveType

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
		importSpanishPlainTextFileMI = new MenuItem("Spanish PLain Text File");
		menuImport.getItems().addAll(importEnglishPlainTextFileMI, importSpanishPlainTextFileMI);
		// help
		helptMI = new MenuItem("help");
		aboutMI = new MenuItem("about");
		menuHelp.getItems().addAll(helptMI, aboutMI);
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
		topHB.setPadding(new Insets(5, 10, 5, 10));

		// left
		leftEverythingVB = new VBox();
		leftFilesMenuVB = new VBox();
		leftFilesSP = new ScrollPane(leftFilesMenuVB);
		// search files
		leftSearchFilesTF = new TextField();
		leftSeachFilesButton = new Button("SEARCH");
		leftSearchHB.getChildren().addAll(leftSearchFilesTF, leftSeachFilesButton);
		leftFileFoundLabel = new Label();
		// style
		leftEverythingVB.setPadding(new Insets(0, 5, 0, 5));
		leftFilesMenuVB.setPadding(new Insets(0, 5, 0, 5));
		leftFileFoundLabel.setPadding(new Insets(0, 5, 0, 5));

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
		leftSearchInFileResultLabel.setPadding(new Insets(0, 5, 0, 5));

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
		miLettersCountSP.setPrefWidth(250);
		midTexSP.setPrefWidth(550);
		midLetterCountLabel.setPadding(new Insets(0, 5, 0, 5));
		midTextLabel.setPadding(new Insets(0, 5, 0, 5));
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
		Scene scene = new Scene(mainVB, 950, 350);
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
					try
					{
						errFile = new PrintWriter(new FileOutputStream(new File(ERR_FILE_NAME), true));
						errFile.println("888888 "+e.toString());
						errFile.close();
					} catch (FileNotFoundException e1)
					{

					}

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
			leftFileLabel.setPadding(new Insets(2, 5, 2, 5));// style
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
					try
					{
						errFile = new PrintWriter(new FileOutputStream(new File(ERR_FILE_NAME), true));
					} catch (FileNotFoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					errFile.println(e.toString());
					errFile.close();
				}
			});
		} // for:archive

		// listeners
		// MenuBar listeners
		// file
		// exit
		exitMI.setOnAction((ActionEvent t) ->
		{
			System.exit(0);

		});
		// import English
		importEnglishPlainTextFileMI.setOnAction((ActionEvent t) ->
		{
			ImportFile importFile = new ImportFile("EnglishPlainText", new File("this"), new Stage(), filesImported,
					leftFilesMenuVB, midLetterCountLabel, midTextLabel);
			setFile.setFileWithPosition(importFile.getPosition());

		});// end import file action event
			// import Spanish
		importSpanishPlainTextFileMI.setOnAction((ActionEvent t) ->
		{
			ImportFile importFile = new ImportFile("SpanishPlainText", new File("this"), new Stage(), filesImported,
					leftFilesMenuVB, midLetterCountLabel, midTextLabel);
			setFile.setFileWithPosition(importFile.getPosition());

		});// end import file action event
			// help
			// help
		helptMI.setOnAction((ActionEvent t) ->
		{
			new MessageDialog("Help",
					"For help please look at the file help.txt that is all ready imported"
							+ "\n you can search for the file in the \"Search for File\" search file"
							+ "\n and then click on it.",
					new Stage());
		});
		// about
		aboutMI.setOnAction((ActionEvent t) ->
		{
			new MessageDialog("About this Program",
					"This program was develop by Jose Retamal "
							+ "\n As a final project for the Software Design and Program Develop  Courser   "
							+ "\nLecturer Naomi Hurley " + "\n GMIT Galway 2017.",
					new Stage());
		});

		// end menu bard

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
							try
							{
								errFile = new PrintWriter(new FileOutputStream(new File(ERR_FILE_NAME), true));
							} catch (FileNotFoundException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							errFile.println("888888 "+e.toString());
							errFile.close();
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
				try
				{
					errFile = new PrintWriter(new FileOutputStream(new File(ERR_FILE_NAME), true));
				} catch (FileNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				errFile.println("888888 "+e.toString());
				errFile.close();
			}
		});

	}// end start()

	public static void main(String[] args)
	{
		
		//background task thread
		Thread backGroundThread = new Thread(new BackgroundTasks());
		backGroundThread.start();
		//app main thread
		launch(args);
			
		
	}

}
