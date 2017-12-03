package templates;

import java.io.File;

/*
 * File Create 25/11/2017 Jose Retamal
 * 
 */

public abstract class Archive
{

	private String fileName;

	private File file;
	

	public  String extension[] ;
	
	// constructors
	//create with string
	
	public Archive()
	{
		this.fileName="no define";
		this.file = null;
	}
	
	public Archive(String fileName)
	{
		this.fileName = fileName;
		file = new File(fileName);
	}

	//create with file
	public Archive(File file)
	{
		this.file = file;
		this.fileName = file.toString();
	}
	
	public Archive(String fileName, String extension)
	{
		this.fileName = fileName + "." + extension;
	}

	// get and set
	public String getFileName()
	{
		return this.fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFileNameNoExtension()
	{
		return this.file.getName();
	}

	public String getFileExtension()
	{
		return this.fileName.split("\\.")[1];
	}

	public void setFile(File file)
	{
		this.file= new File(file.toString());
		//this.setFileName(this.getFileNameNoExtension());
	}
	
	//abstract methods
	public abstract void analyzeArchive() throws Exception;
	
	// to String
	@Override
	public String toString()
	{
		return String.format("Name : %s\n", this.fileName);
	}
	//equals
	@Override
	public boolean equals(Object obj)
	{
		boolean isEquals = false;
		if (obj instanceof Archive)
		{
			if (((Archive) obj).getFileName().equals(this.fileName))
			{
				isEquals = true;
			}
		}

		return isEquals;
	}
/*
	public static void main(String[] args)
	{
		class Test extends Archive
		{
			public Test(String n)
			{
				super(n);
			}
			public  void analyzeArchive() {}
			
		}
		Test t = new Test("myFile.txt");

		System.out.println(t.getFileNameNoExtension());
		System.out.println(t.getFileExtension());

	}*/

	public File getFile()
	{
		// TODO Auto-generated method stub
		return this.file;
	}

}
