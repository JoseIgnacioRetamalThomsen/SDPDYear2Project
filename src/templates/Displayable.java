package templates;

import javafx.scene.control.Label;

/*
 * Jose Retamal
 * interface for all those files that can be display 
 * basically all files are going to be able to display
 * but with particular implementation of the methods.
 */

public interface Displayable
{
	//abstract methods
	void displayArchive(Label container) throws Exception;
	void displayCount(Label container) throws Exception;
	void searchOnFile(String toSearch, Label containerReturn) throws Exception;
	String getFileNameNoExtension();
	
	
}
