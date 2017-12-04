package fileanalyzer;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import javafx.scene.control.Label;
import templates.Archive;
import templates.Displayable;

public class SpanishPlainText extends Archive implements Displayable {

	// 0 : number of lines, 1: extra characters count (numbers,$,etc) , 2 total
	// , 3 accents
	// letters //á í é ñ ú ó
	final char CHARACTERS[] = { '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', '1', '2', '3' };// total
																						// 32

	int charactersCount[] = new int[32];

	StringBuilder stringBuilder;

	// constructors
	public SpanishPlainText() {
		super();
		extension = new String[] { "txt", "log" };
	}

	public SpanishPlainText(String file) {
		super(file);

		extension = new String[] { "txt", "log" };
	}

	public SpanishPlainText(File file) {
		super(file);
		extension = new String[] { "txt", "log" };
	}

	@Override
	public void displayArchive(Label container) throws Exception {
		Scanner scanner;

		stringBuilder = new StringBuilder("");

		scanner = new Scanner(new FileReader(this.getFileName()));
		while (scanner.hasNext()) {

			stringBuilder.append(scanner.nextLine() + "\n");

		}
		container.setText(stringBuilder.toString());
		scanner.close();

	}

	@Override
	public void displayCount(Label container) throws Exception {
		StringBuilder stringBuilder = new StringBuilder("");
		double percentOfLetter;
		if (charactersCount[0] == 0)// mean no lines so nothing to display
		{
			throw new Exception();
		} else {
			stringBuilder.append("Lines: " + charactersCount[0] + "\n");
			stringBuilder.append("Total letters: " + charactersCount[30] + "\n");
			for (int i = 1; i < 28; i++) {
				// calculate percent of appearance of the letter
				percentOfLetter = (charactersCount[i] / (double) charactersCount[30]) * 100;
				stringBuilder.append(String.format("%-9c:%-15d %2c: %2.2f\n", CHARACTERS[i], charactersCount[i], '%',
						percentOfLetter));
			}
			stringBuilder.append(String.format("%-9s:%-15d\n", "space", charactersCount[28]));
			stringBuilder.append(String.format("%-9s:%-15d\n", "other", charactersCount[29]));
			stringBuilder.append(String.format("%-9s:%-15d", "accents", charactersCount[31]));
		}

		container.setText(stringBuilder.toString());

	}

	@Override
	public void searchOnFile(String toSearch, Label containerReturn) throws Exception {
		Scanner scanner;
		String temporalLine;
		StringBuilder result = new StringBuilder();
		boolean notFound = true;
		int howManyTimes = 0;
		int columFound;

		scanner = new Scanner(new FileReader(this.getFileName()));

		toSearch = toSearch.toLowerCase();
		while (scanner.hasNext()) {
			notFound = true;
			temporalLine = scanner.nextLine().toLowerCase();
			while (notFound == true) {
				if (temporalLine.contains(toSearch)) {
					howManyTimes++;
					columFound = temporalLine.indexOf(toSearch);
					temporalLine = temporalLine.substring(columFound + 1);

				} else {
					notFound = false;
				}
			}
		}
		scanner.close();

		// print result to label
		result.append(toSearch);
		if (howManyTimes == 0) {
			result.append(" was not found.");
		} else {
			result.append(" was found " + howManyTimes);
		}
		containerReturn.setText(result.toString());
	}

	@Override
	public void analyzeArchive() throws Exception {
		Scanner scanner;
		String temporalLine;
		char temporalChar;
		boolean isFound;
		int position;

		scanner = new Scanner(new FileReader(this.getFileName()));

		while (scanner.hasNext()) {
			temporalLine = scanner.nextLine();

			this.charactersCount[0]++;// count the line.

			for (int i = 0; i < temporalLine.length(); i++) {
				temporalChar = Character.toLowerCase(temporalLine.charAt(i));

				isFound = false;
				position = -1;

				while (!isFound && position < this.CHARACTERS.length - 3) {
					position++;
					if (temporalChar == this.CHARACTERS[position]) {
						this.charactersCount[position]++;// add the character if
															// found

						if (position > 0 && position < 28)
							this.charactersCount[30]++;// count letter
						isFound = true;

					} // if
					else if (temporalChar == 'á')// add for extra leters with
													// accent
					{
						this.charactersCount[1]++;// add to a
						this.charactersCount[31]++;// add to accents
					} else if (temporalChar == 'é')// add for extra leters with
													// accent
					{
						this.charactersCount[5]++;// add to e
						this.charactersCount[31]++;// add to accents
					} else if (temporalChar == 'í')// add for extra leters with
													// accent
					{
						this.charactersCount[9]++;// add to e
						this.charactersCount[31]++;// add to accents
					} else if (temporalChar == 'ó')// add for extra leters with
													// accent
					{
						this.charactersCount[16]++;// add to o
						this.charactersCount[31]++;// add to accents
					} else if (temporalChar == 'ú')// add for extra leters with
													// accent
					{
						this.charactersCount[22]++;// add to o
						this.charactersCount[31]++;// add to accents
					}

				} // end while from search
				if (isFound == false) {
					this.charactersCount[28]++;// if not found it was not a
												// letter so we add to other
												// character count
				}
			}
		}
		scanner.close();

	}// analyzeArchive

}
