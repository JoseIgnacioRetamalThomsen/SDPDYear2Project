//jose Retamal 
package fileanalyzer;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import templates.Archive;
import templates.Displayable;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

/*
 * Jose Retamal FileAnalyzer
 * This class will be use for count leters of and english plain text file (txt,log)
 * 
 */

public class EnglishPlainText extends Archive implements Displayable
{

	// 0 : number of lines, 1: charactes count (numbers,$,etc)
	final char CHARACTERS[] =
	{ '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', ' ', '1' };

	int charactersCount[] = new int[29];

	String extension[] =
	{ "txt", "log" };

	
	public EnglishPlainText(String file)
	{
		super(file);
	
		// TODO Auto-generated constructor stub
	}

	public EnglishPlainText(File file)
	{
		super(file);
		// TODO Auto-generated constructor stub
	}

	// method to count lettes
	@Override
	public void analyzeArchive() throws Exception
	{
		Scanner scanner;
		String temporalLine;
		char temporalChar;
		boolean isFound;
		int position;

		scanner = new Scanner(new FileReader(this.getFileName()));

			while (scanner.hasNext())
			{
				temporalLine = scanner.nextLine();

				this.charactersCount[0]++;// count the line.

				for (int i = 0; i < temporalLine.length(); i++)
				{
					temporalChar = Character.toLowerCase(temporalLine.charAt(i));

					isFound = false;
					position = -1;

					while (!isFound && position < this.CHARACTERS.length - 1)
					{
						position++;
						if (temporalChar == this.CHARACTERS[position])
						{
							this.charactersCount[position]++;// add the character if found
							isFound = true;

						} // if

					} // end while from search
					if (isFound == false)
					{
						this.charactersCount[28]++;// if not found it was not a letter so we add to other
													// character count
					}
				}
			}
			scanner.close();

	}

	@Override
	public void displayArchive(Label container) throws Exception
	{
		Scanner scanner;

		StringBuilder stringBuilder = new StringBuilder("");

		scanner = new Scanner(new FileReader(this.getFileName()));
		while (scanner.hasNext())
		{
			
			stringBuilder.append(scanner.nextLine() + "\n");
			
		}
		container.setText(stringBuilder.toString());
		scanner.close();
	}

	

	@Override
	public void displayCount(Label container) throws Exception
	{
		StringBuilder stringBuilder = new StringBuilder("");
		if(charactersCount[0]==0)//mean no lines so nothing to display
		{
			throw new Exception();
		}else
		{
			stringBuilder.append("Lines: " + charactersCount[0]+"\n");
			for(int i=1;i<27;i++)
			{
				stringBuilder.append(CHARACTERS[i]+": " + charactersCount[i]+"\n");
			}
			stringBuilder.append("space: " + charactersCount[27]+"\n");
			stringBuilder.append("other: " + charactersCount[28]+"\n");
		}
		
		container.setText(stringBuilder.toString());
		
	}
}
