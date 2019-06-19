package edu.handong.bonus.files;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FileSave {
	
	
	public void FFOption(String filename) {
		File files = new File (filename);
		if (!files.isDirectory()) {
			files = files.getParentFile();
		}
		String line = "";
		for (File file : files.listFiles()) {
			if (file.isDirectory()) 
				line += file.getName()+"/";
			else if (file.canExecute()) 
				line+= file.getName()+"*";
			else 
				line += file.getName();
			
		}
		ArrayList<String> filenames = new ArrayList<String>();
		filenames.add(line);
		OptionsPrint.print(filenames);
	}
	
	public void AOption(String filename) {
		File files = new File (filename);
		ArrayList<String> fileSort = new ArrayList<String>();
		if (!files.isDirectory()) {
			files = files.getParentFile();
		}
		String lines = "";
		for (File file : files.listFiles()) {
			fileSort.add(file.getName());
		}
		
		fileSort.sort(null);
		
		for (String line : fileSort) {
			lines += line + "\t";
		}
		ArrayList<String> filenames = new ArrayList<String>();
		filenames.add(lines);
		
		OptionsPrint.print(filenames);
	}

	public void FOption(String filename) {
		File files = new File (filename);
		if (!files.isDirectory()) {
			files = files.getParentFile();
		}
		String lines = "";
		for (File file : files.listFiles())
			lines += file.getName() + "\t";
		ArrayList<String> filenames = new ArrayList<String>();
		filenames.add(lines);
		
		OptionsPrint.print(filenames);
	}
	
	public void TOption(String filename) {
		HashMap<Long, String> fileSort = new HashMap<Long, String>();
		ArrayList<Long> modified = new ArrayList<Long>();
		String line = "";
 		File files = new File (filename);
 		if (!files.isDirectory()) {
			files = files.getParentFile();
		}

		for (File file : files.listFiles()) {
			fileSort.put(file.lastModified(), file.getName());
			modified.add(file.lastModified());
		}
		
		modified.sort(null);
		
		for (long modi : modified) 
			line = fileSort.get(modi) + "\t" + line;
		
		ArrayList<String> filenames = new ArrayList<String>();
		filenames.add(line);
		
		OptionsPrint.print(filenames);
	}

	public void ROption(String filename, String path) {
		File files = new File (filename);
		if (!files.isDirectory()) {
			files = files.getParentFile();
		}

		ArrayList<String> fi = freeOption(filename);
		System.out.println();
		for (String filenames : fi) {
			File file = new File(filename+"/"+filenames);
			
			if (file.isDirectory() && !file.isHidden()) {
				
				path += "/"+file.getName();
				System.out.println("." + path+":");
				ROption(filename+"/"+file.getName(), path);
			}
		}
	}
	
	public ArrayList<String> freeOption (String filename) {
		File files = new File (filename);
		ArrayList<String> fileSort = new ArrayList<String>();
		if (files.isFile()) {
			files = files.getParentFile();
		}
		String lines = "";
		for (File file : files.listFiles()) {
			if (!file.isHidden())
				fileSort.add(file.getName());
		}
		
		fileSort.sort(null);
		int i = 0;
		for (String line : fileSort) {
			lines += line + "\t";
			i++;
			if (i%3 == 0) lines += "\n";
		}
		ArrayList<String> filenames = new ArrayList<String>();
		filenames.add(lines);
		
		OptionsPrint.print(filenames);
		return fileSort;
	}
	

}
