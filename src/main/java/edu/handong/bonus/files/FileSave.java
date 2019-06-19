package edu.handong.bonus.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileSave {
	
	/*
	public void FFOption(String filename) throws IOException {
		ArrayList<String> filenames = new ArrayList<String>();
		ArrayList<String> fileSort = new ArrayList<String>();
		File files = new File (filename);
		
		
		if (!files.exists()) {
			files = files.getParentFile();
		} else if (!files.isDirectory()) {
			System.out.println(files.getName());
			return;
		}
		
		for (File file : files.listFiles()) {
			fileSort.add(file.getName());
		}
		
		fileSort.sort(null);
		
		for (String file : fileSort) {
			File fl = new File(filename+"/"+file);
			if (fl.isDirectory()) 
				filenames.add(fl.getName()+"/");
			else if (fl.canExecute()) 
				filenames.add(fl.getName()+"*");
			else if (isSymlink(fl))
				filenames.add(fl.getName()+"@");
			else if (!fl.isHidden())
				filenames.add(fl.getName());
		}
		
		OptionsPrint.print(filenames);
	}
	
	public static boolean isSymlink(File file) throws IOException {
		  if (file == null)
		    throw new NullPointerException();
		  File canon;
		  if (file.getParent() == null) {
		    canon = file;
		  } else {
		    File canonDir = file.getParentFile().getCanonicalFile();
		    canon = new File(canonDir, file.getName());
		  }
		  return !canon.getCanonicalFile().equals(canon.getAbsoluteFile());
	}*/
	
	public void AOption(String filename) {
		File files = new File (filename);
		ArrayList<String> fileSort = new ArrayList<String>();
		ArrayList<String> filenames = new ArrayList<String>();
		if (!files.exists()) {
			files = files.getParentFile();
		} else if (!files.isDirectory()) {
			System.out.println(files.getName());
			return;
		}
		
		for (File file : files.listFiles()) {
			fileSort.add(file.getName());
		}
		
		fileSort.sort(String.CASE_INSENSITIVE_ORDER);
		
		for (String line : fileSort) {
			filenames.add(line);
		}
		
		filenames.add(0, ".");
		filenames.add(0, "..");
		
		OptionsPrint.print(filenames);
	}

	public void FOption(String filename) {
		File files = new File (filename);
		ArrayList<String> filenames = new ArrayList<String>();
		if (!files.exists()) {
			files = files.getParentFile();
		} else if (!files.isDirectory()) {
			System.out.println(files.getName());
			return;
		}
		
		for (File file : files.listFiles())
			filenames.add(file.getName());

		filenames.add(0, ".");
		filenames.add(0, "..");
		
		OptionsPrint.print(filenames);
	}
	
	public void TOption(String filename, boolean check) {
		HashMap<Long, String> fileSort = new HashMap<Long, String>();
		ArrayList<Long> modified = new ArrayList<Long>();
 		File files = new File (filename);
 		if (!files.exists()) {
			files = files.getParentFile();
		} else if (!files.isDirectory()) {
			System.out.println(files.getName());
			return;
		}

		for (File file : files.listFiles()) {
			if (!file.isHidden()) {
				fileSort.put(file.lastModified(), file.getName());
				modified.add(file.lastModified());
			}
		}
		
		modified.sort(null);
		ArrayList<String> filenames = new ArrayList<String>();
		if (check) {
			for (long modi : modified)
				filenames.add(0, fileSort.get(modi));
		}else {
			for (long modi : modified)
				filenames.add(fileSort.get(modi));
		}
		
		OptionsPrint.print(filenames);
	}

	public void ROption(String filename, String path) {
		File files = new File (filename);
		if (!files.exists()) {
			files = files.getParentFile();
		} else if (!files.isDirectory()) {
			System.out.println(files.getName());
			return;
		}

		ArrayList<String> fi = freeOption(filename, true);
		System.out.println();
		for (String filenames : fi) {
			File file = new File(filename+"/"+filenames);
			
			if (file.isDirectory() && !file.isHidden()) {
				System.out.println("." + path+"/"+file.getName()+":");
				ROption(filename+"/"+file.getName(), path+"/"+file.getName());
			}
		}
	}
	
	public ArrayList<String> freeOption (String filename, boolean check) {
		File files = new File (filename);
		ArrayList<String> fileSort = new ArrayList<String>();
		ArrayList<String> filenames = new ArrayList<String>();
		if (files.isFile()) {
			files = files.getParentFile();
		}
		String lines = "";
		for (File file : files.listFiles()) {
			if (!file.isHidden())
				fileSort.add(file.getName());
		}
		
		fileSort.sort(String.CASE_INSENSITIVE_ORDER);
		if (check) {
			for (String line : fileSort) 
				filenames.add(line);
		} else {
			for (String line : fileSort)
				filenames.add(0, line);
		}
		
		
		filenames.add(lines);
		
		OptionsPrint.print(filenames);
		return fileSort;
	}
}
