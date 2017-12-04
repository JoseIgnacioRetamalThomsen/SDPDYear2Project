package fileanalyzergui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import fileanalyzer.EnglishPlainText;
import fileanalyzer.SpanishPlainText;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import templates.Archive;
import templates.Displayable;
import templates.FileNotSupportedException;
import templates.MessageDialog;
import fileanalyzergui.MainWindows.SetFile;;

/*
 * Jose Retaml SDPD project
 * Class for import file using FileChosser from javaFX
 * 
 */

public class ImportFile
{
	// class variables
	static final String IMPORTS_FOLDER = "Archives";
	static final String[] ARCHIVES_SUPPORTED =
	{ "EnglishPlainText", "SpanishPlainText" };

	FileChooser fileChooser;

	Archive type;

	File fileToImport;
	File targetDirectory;

	Stage stage;

	VBox filesLeftVB;
	Label midLetterCountLabel;
	Label midTextLabel;

	int position;

	// constructor where we import the file and added to Array list
	public ImportFile(String typeString, File targetDirectory, Stage stage, java.util.List<Displayable> filesImported,
			VBox filesLeftVB, Label midLetterCountLabel, Label midTextLabel, SetFile setFile)
	{
		int typeOffile = 0; // 0 for English 1 for Spanish

		boolean isFile = true;

		this.filesLeftVB = filesLeftVB;
		this.midLetterCountLabel = midLetterCountLabel;
		this.midTextLabel = midTextLabel;
		// argument initialization
		// create type of object to import
		if (typeString.equals("EnglishPlainText"))
		{
			type = new EnglishPlainText();
			typeOffile = 0;
			// look if file exist

		} else if (typeString.equals("SpanishPlainText"))
		{
			type = new SpanishPlainText();
			typeOffile = 1;
		}

		this.stage = stage;

		// create javafx fileChoooser
		fileChooser = new FileChooser();
		// setup fileChooser
		fileChooser.getExtensionFilters().add((new FileChooser.ExtensionFilter("all", "*.*")));
		int i = 0;
		for (@SuppressWarnings("unused")
		String filter : type.extension)
		{
			String extenFormat = "*." + type.extension[i];
			fileChooser.getExtensionFilters().add((new FileChooser.ExtensionFilter(type.extension[i], extenFormat)));
			i++;
		}

		// get file
		fileToImport = fileChooser.showOpenDialog(this.stage);

		if (fileToImport == null)
		{
			// nothing happens if null means close the windows with out choose
		} else
		{
			// copy file
			File importedFile = null;

			try
			{
				File archivesFole = new File(IMPORTS_FOLDER);
				File englishPlainTextFolder = new File(archivesFole, ARCHIVES_SUPPORTED[typeOffile]);
				importedFile = new File(englishPlainTextFolder, fileToImport.getName());
				Scanner scanner = new Scanner(fileToImport);

				if (scanner.hasNext() == false)
				{
					scanner.close();
					throw new FileNotSupportedException();
				}
				PrintWriter print = new PrintWriter(new FileWriter(importedFile));
				while (scanner.hasNext())
				{
					String line = scanner.nextLine();
					if (line.length() > 10000)
					{
						scanner.close();
						print.close();
						importedFile.delete();
						throw new FileNotSupportedException();
					}
					print.println(line);

				}
				print.close();
				scanner.close();
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
				new MessageDialog("Error", "Not posible to open file, posible no permision to open.", stage);
				isFile = false;

			} catch (FileNotSupportedException e)
			{
				new MessageDialog("Error", "File not suport or empty.", stage);
				isFile = false;

			} catch (Exception e)
			{
				e.printStackTrace();
				isFile = false;
			}
			if (isFile)
			{
				// set file
				type.setFile(importedFile);

				type.setFileName(importedFile.toString());
				// add to array of files
				filesImported.add((Displayable) type);
				// set position in the array for return
				position = filesImported.size() - 1;

				try
				{
					type.analyzeArchive();
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// add to GUI
				Label tempLabel = new Label(importedFile.getName());
				filesLeftVB.getChildren().add(tempLabel);
				// add listeners
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
						setFile.setFileWithPosition(position);
						/*
						try
						{
							if (typeString.equals("EnglishPlainText"))
							{
								//((EnglishPlainText) type).displayCount(midLetterCountLabel);
								//((EnglishPlainText) type).displayArchive(midTextLabel);
								setFile.setFileWithPosition(position);
							} else if (typeString.equals("SpanishPlainText"))
							{
								//((SpanishPlainText) type).displayCount(midLetterCountLabel);
								//((SpanishPlainText) type).displayArchive(midTextLabel);
								setFile.setFileWithPosition(position);
							}

						} catch (Exception e)
						{
							System.out.println(e);
						}*/
					}
				});

			}
		}
	}// end constructor

	// return the position where the file was added
	public int getPosition()
	{
		return this.position;
	}
}
