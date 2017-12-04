//Jose Retamal -2017 
package testcases;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/*
 * Jose Retamal
 * class for test Spanish plain text counting 
 */

import fileanalyzer.SpanishPlainText;

public class TestSpanishCount
{
	@Test
	public void testA()
	{
		SpanishPlainText testing = new SpanishPlainText(new File("testSpanish.txt"));
		try
		{
			testing.analyzeArchive();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("there are 9 a in the text so should be 9",9,testing.charactersCount[1]);
	}
	@Test
	public void testB()
	{
		SpanishPlainText testing = new SpanishPlainText(new File("testSpanish.txt"));
		try
		{
			testing.analyzeArchive();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("there are 2 b in the text so should be 2",1,testing.charactersCount[2]);
	}
	@Test
	public void testLines()
	{
		SpanishPlainText testing = new SpanishPlainText(new File("testSpanish.txt"));
		try
		{
			testing.analyzeArchive();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("there are 4 lines in the text so should be 4",4,testing.charactersCount[0]);
	}

	@Test
	public void testOtherCharacters()
	{
		SpanishPlainText testing = new SpanishPlainText(new File("testSpanish.txt"));
		try
		{
			testing.analyzeArchive();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("there are 11 other characters in the text so should be 10",11,testing.charactersCount[29]);
	}

}
