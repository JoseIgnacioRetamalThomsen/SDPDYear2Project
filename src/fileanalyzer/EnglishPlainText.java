//jose Retamal 
package fileanalyzer;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import templates.Archive;
import templates.Displayable;

import javafx.scene.control.Label;

/*
 * Jose Retamal FileAnalyzer
 * This class will be use for count leters of and english plain text file (txt,log)
 * 
 */

public class EnglishPlainText extends Archive implements Displayable
{

	// 0 : number of lines, 1: extra charactes count (numbers,$,etc) , 2 total
	// letters
	final char CHARACTERS[] =
	{ '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', ' ', '1', '2' };

	int charactersCount[] = new int[30];

	StringBuilder stringBuilder;
	
	public EnglishPlainText()
	{
		super();
		extension = new String[]
		{ "txt", "log" };
	}

	public EnglishPlainText(String file)
	{
		super(file);

		extension = new String[]
		{ "txt", "log" };
		// TODO Auto-generated constructor stub
	}

	public EnglishPlainText(File file)
	{
		super(file);
		extension = new String[]
		{ "txt", "log" };
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

				while (!isFound && position < this.CHARACTERS.length - 2)
				{
					position++;
					if (temporalChar == this.CHARACTERS[position])
					{
						this.charactersCount[position]++;// add the character if found

						if (position > 0 && position < 27)
							this.charactersCount[29]++;// count letter
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

	}//Analyze file

	

	@Override
	public void displayArchive(Label container) throws Exception
	{
		Scanner scanner;

		stringBuilder = new StringBuilder("");

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
		double percentOfLetter;
		if (charactersCount[0] == 0)// mean no lines so nothing to display
		{
			throw new Exception();
		} else
		{
			stringBuilder.append("Lines: " + charactersCount[0] + "\n");
			stringBuilder.append("Total letters: " + charactersCount[29] + "\n");
			for (int i = 1; i < 27; i++)
			{
				// calculate percent of appearance of the letter
				percentOfLetter = (charactersCount[i] / (double) charactersCount[29]) * 100;
				stringBuilder.append(String.format("%-9c:%-15d %2c: %2.2f\n", CHARACTERS[i], charactersCount[i], '%',
						percentOfLetter));
			}
			stringBuilder.append(String.format("%-9s:%-15d\n", "space", charactersCount[27]));
			stringBuilder.append(String.format("%-9s:%-15d", "other", charactersCount[28]));
		}

		container.setText(stringBuilder.toString());

	}

	// have to be call before displayArchive
	@Override
	public void searchOnFile(String toSearch, Label containerReturn) throws Exception
	{
		Scanner scanner;
		String temporalLine;
		StringBuilder result = new StringBuilder();
		boolean notFound = true;
		int howManyTimes = 0;
		int line = 0, columFound;

		scanner = new Scanner(new FileReader(this.getFileName()));

		toSearch = toSearch.toLowerCase();
		while (scanner.hasNext())
		{
			notFound = true;
			line++;
			System.out.println(line);
			temporalLine = scanner.nextLine().toLowerCase();
			while (notFound == true)
			{
				if (temporalLine.contains(toSearch))
				{
					howManyTimes++;
					columFound = temporalLine.indexOf(toSearch);
					temporalLine = temporalLine.substring(columFound + 1);

				} else
				{
					notFound = false;
				}
			}
		}
		scanner.close();

		// print result to label
		result.append(toSearch);
		if (howManyTimes == 0)
		{
			result.append(" was not found.");
		} else
		{
			result.append(" was found " + howManyTimes);
		}
		containerReturn.setText(result.toString());

	}
}
