package background;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import templates.Tasks;

public class BackgroundTasks extends Tasks implements Runnable {

	// 999999 : program open , 888888 expections
	int timesProgramOpen = 0;
	int exceptionThrowed = 0;

	//constructors
	public BackgroundTasks()
	{
		this.setTaskID("backTask");
	}
	
	@Override
	public void run() {
		
		addProgramRunned();
		countTimes();
		printReport();
		
	}

	private void addProgramRunned() {
		// print to eror file that the program have ben use
		PrintWriter errFile;
		String ERR_FILE_NAME = "erroFile.dat";
		try {
			errFile = new PrintWriter(new FileOutputStream(new File(ERR_FILE_NAME), true));
			errFile.println("999999 program run");
			errFile.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void countTimes() {
		Scanner errFile = null;
		try {
			errFile = new Scanner(new File("erroFile.dat"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (errFile.hasNext()) {
			if (errFile.next().equals("999999")) {
				timesProgramOpen++;
			} else if (errFile.next().equals("999999")) {
				exceptionThrowed++;
			}

		}
	}
	
	private void printReport()
	{
		PrintWriter errFile;
		String ERR_FILE_NAME = "reportFile.dat";
		try {
			errFile = new PrintWriter(new FileOutputStream(new File(ERR_FILE_NAME), true));
			errFile.println("new report, program have run :" +timesProgramOpen+ " and have throw " + exceptionThrowed + " Exceptions" );
			errFile.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
