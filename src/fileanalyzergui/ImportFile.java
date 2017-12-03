package fileanalyzergui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.*;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import fileanalyzer.EnglishPlainText;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import templates.Archive;
import templates.Displayable;
import templates.FileNotSupportedException;
import templates.MessageDialog;
import java.util.*;

public class ImportFile
{
	static final String IMPORTS_FOLDER = "Archives";
	static final String[] ARCHIVES_SUPPORTED =
	{ "EnglishPlainText" };

	FileChooser fileChooser;

	Archive type;

	File fileToImport;
	File targetDirectory;

	Stage stage;

	VBox filesLeftVB;
	Label midLetterCountLabel;
	Label midTextLabel;

	int position ;
	
	public ImportFile(String typeString, File targetDirectory, Stage stage, java.util.List<Displayable> filesImported,
			VBox filesLeftVB, Label midLetterCountLabel, Label midTextLabel)
	{

		boolean isFile = true;

		this.filesLeftVB = filesLeftVB;
		this.midLetterCountLabel = midLetterCountLabel;
		this.midTextLabel = midTextLabel;
		// argument init
		if (typeString.equals("EnglishPlainText"))
		{
			System.out.println("not gettin");
			type = new EnglishPlainText();
		}

		// System.out.println(type.extension[0]);
		this.stage = stage;

		// other init
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add((new FileChooser.ExtensionFilter("all", "*.*")));
		int i = 0;
		for (String filter : type.extension)
		{
			String extenFormat = "*." + type.extension[i];
			fileChooser.getExtensionFilters().add((new FileChooser.ExtensionFilter(type.extension[i], extenFormat)));
			i++;
		}

		// get file
		fileToImport = fileChooser.showOpenDialog(this.stage);

		if (fileToImport == null)
		{
			System.out.println("null");
		} else
		{
			System.out.println(fileToImport.toString());

			// copy file
			File importedFile = null;
			;
			try
			{
				File archivesFole = new File(IMPORTS_FOLDER);
				File englishPlainTextFolder = new File(archivesFole, ARCHIVES_SUPPORTED[0]);

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
					print.println(scanner.nextLine());

				}
				print.close();
				scanner.close();
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				new MessageDialog("Error", "Not posible to open file, posible no permision to open.", stage);
				isFile = false;

			} catch (FileNotSupportedException e)
			{
				// TODO Auto-generated catch block

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
				//set position in the array for return
				position = filesImported.size() - 1;
				System.out.println(position);
				System.out.println(type.getFile().toString());
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

						try
						{

							((EnglishPlainText) type).displayCount(midLetterCountLabel);
							((EnglishPlainText) type).displayArchive(midTextLabel);

						} catch (Exception e)
						{
						}
					}
				});

			}
		}
	}// en constructor

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public int getPosition()
	{
		return this.position;
	}

	

}
