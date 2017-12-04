package templates;

/*
 * Jose Retamal
* Parent task class for checking program performance tasks
*  
 */
public abstract class Tasks
{
	private String taskID;

	public void setTaskID(String taskID)
	{
		this.taskID = taskID;
	}

	public String getTaskID()
	{
		return this.taskID;
	}
}
