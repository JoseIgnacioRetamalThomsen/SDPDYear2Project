//Jose Retamal -2017 
package testcases;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import fileanalyzer.EnglishPlainText;

public class TestEnglishLetterCount
{

	@Test
	public void testA()
	{
		EnglishPlainText testing = new EnglishPlainText(new File("TestingFiles\\testEnglish.txt"));
		try
		{
			testing.analyzeArchive();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("there are 5 a in the text so should be 5",5,testing.charactersCount[1]);
	}
	@Test
	public void testB()
	{
		EnglishPlainText testing = new EnglishPlainText(new File("TestingFiles\\testEnglish.txt"));
		try
		{
			testing.analyzeArchive();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("there are 2 b in the text so should be 2",2,testing.charactersCount[2]);
	}
	@Test
	public void testLines()
	{
		EnglishPlainText testing = new EnglishPlainText(new File("TestingFiles\\testEnglish.txt"));
		try
		{
			testing.analyzeArchive();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("there are 5 lines in the text so should be 5",5,testing.charactersCount[0]);
	}

	@Test
	public void testOtherCharacters()
	{
		EnglishPlainText testing = new EnglishPlainText(new File("TestingFiles\\testEnglish.txt"));
		try
		{
			testing.analyzeArchive();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("there are 9 lines in the text so should be 5",9,testing.charactersCount[28]);
	}

}
