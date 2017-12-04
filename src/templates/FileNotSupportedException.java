package templates;

/**
 * Jose Retamal Exception to be throw when trying to import a not suported file
 */
public class FileNotSupportedException extends Exception
{

	private static final long serialVersionUID = 1L;

	public FileNotSupportedException()
	{

	}

	public FileNotSupportedException(String arg0)
	{
		super(arg0);

	}

	public FileNotSupportedException(Throwable arg0)
	{
		super(arg0);
	}

	public FileNotSupportedException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	public FileNotSupportedException(String arg0, Throwable arg1, boolean arg2, boolean arg3)
	{
		super(arg0, arg1, arg2, arg3);
	}
}
