package com.reminder.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


class SystemCommands
{
	private InputStream inputStream = null;
	private InputStreamReader inputStreamReader = null;
	private BufferedReader bufferedReader = null;
	private ProcessBuilder processBuilder = null;
	private Process process = null;
	
	public void executeCommand(String[] command) throws IOException
	{
		processBuilder = new ProcessBuilder(command);
    	process = processBuilder.start();
    	
    	System.out.println("Executing command: "+Arrays.toString(command)+"----------------------\n");
    	
    	displayOutput("error");
    	displayOutput("");
	}
	
	
    private void displayOutput(String type) throws IOException 
    {
    	String line = "";
    	
    	while ((line = getOutputStream(type).readLine()) !=null)
    	{
    		System.out.println(line);
    	}
    	
    	try 
    	{
    		int exitValue = process.waitFor();
    		//System.out.println("Exited with value: "+ exitValue);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
	}
    
    private BufferedReader getOutputStream(String type)
    {
    	if(type.equals("error")) {
    		inputStream = process.getErrorStream();
    		inputStreamReader = new InputStreamReader(inputStream);
        	bufferedReader = new BufferedReader(inputStreamReader);
        	return bufferedReader;
    	}
    	inputStream = process.getInputStream();
    	inputStreamReader = new InputStreamReader(inputStream);
    	bufferedReader = new BufferedReader(inputStreamReader);
    	return bufferedReader;
    }


	public static void main(String[] args) throws InterruptedException, IOException 
    {
		SystemCommands system = new SystemCommands();
		
		system.executeCommand(new String[]{"javac","-verbose","Test.java"});
		system.executeCommand(new String[]{"java","Test"});
    	
    }
}

