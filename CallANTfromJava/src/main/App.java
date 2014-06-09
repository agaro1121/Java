package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.logging.Logger;

import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.Expand;

public class App{

	public static void main(String[] args) throws FileNotFoundException {
		 File buildFile = new File("sampleANT.xml");
		   Project p = new Project();
		   p.setUserProperty("ant.file", buildFile.getAbsolutePath());
		   DefaultLogger consoleLogger = getConsoleLogger();
		  
		   p.addBuildListener(consoleLogger);
		   p.addBuildListener(getFileLogger());
		   
		   p.init();
		   ProjectHelper helper = ProjectHelper.getProjectHelper();
		   p.addReference("ant.projectHelper", helper);
		   helper.parse(p, buildFile);
		   
		   p.executeTarget("myTarget");
		   p.fireBuildFinished(null);////says "BUILD SUCCESSFUL.."
	}

	private static DefaultLogger getConsoleLogger() {
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
         
        return consoleLogger;
    }
	
	private static DefaultLogger getFileLogger() throws FileNotFoundException{
		DefaultLogger fileLogger = new DefaultLogger();
		fileLogger.setErrorPrintStream(System.err);
		fileLogger.setOutputPrintStream(new PrintStream("log_test"));
		fileLogger.setMessageOutputLevel(Project.MSG_INFO);
		return fileLogger;
		
	}
}
