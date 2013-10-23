package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Compiler extends JFrame implements ActionListener
{
	JTextArea textInput,textOutput;
	JButton compileButton;
	
	public Compiler() 
	{
		Container container = new Container();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		textInput = new JTextArea("Enter java code here");
		textOutput = new JTextArea("--- COMPILER OUTPUT ---");
		textOutput.setEditable(false);
		
		compileButton = new JButton("Compile");
		compileButton.addActionListener(this);
		
		container.add(textInput);
		container.add(compileButton);
		container.add(textOutput);

		this.add(container);
		this.setSize(1000,500);
		this.setVisible(true);
	}
	

	public static void main(String[] args) 
	{	
		new Compiler();
	}


	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == compileButton)
		{
			try
			{
				makeJavaFileFromInput(textInput.getText());
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
			
			
			
			//ProcessBuilder process = new ProcessBuilder(command);
			System.out.println("Button clicked");
		}
		
	}


	private void makeJavaFileFromInput(String text) throws IOException 
	{
		String filename = getFileNameFromInput(text);
		FileWriter fileWriter = new FileWriter(new File(filename+".java"));
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(text);
		bufferedWriter.close();		
	}


	private String getFileNameFromInput(String input)
	{	
		return input.substring(input.indexOf("class")+5, input.indexOf("{")).trim();
	}

}
