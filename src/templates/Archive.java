package templates;

import java.io.File;

/*
 * File Create 25/11/2017 Jose Retamal
 * Parent Abstract class for use as template 
 * 
 */

public abstract class Archive
{
	//class member
	private String fileName;

	private File file;
	

	public  String extension[] ;
	
	// constructors
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

	public File getFile()
	{
				return this.file;
	}
}
