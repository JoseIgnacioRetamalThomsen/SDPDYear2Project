package templates;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

/*
 * Jose Retamal
 * interface for all those files that can be display 
 */

public interface Displayable
{
	void displayArchive(Label container) throws Exception;
	void displayCount(Label container) throws Exception;
	void searchOnFile(String toSearch, Label containerReturn) throws Exception;
	String getFileNameNoExtension();
	
	
}
