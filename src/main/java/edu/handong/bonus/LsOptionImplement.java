package edu.handong.bonus;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.IOException;

import edu.handong.bonus.files.FileSave;

public class LsOptionImplement {
	private boolean help;
	//private boolean F;
	private boolean a;
	private boolean f;
	private boolean t;
	private boolean R;
	private boolean rt;
	private boolean r;
	
	private String name;
	
	
	public void run(String[] args) {
		Options options = createOptions();
		
		if (parseOption(options, args)) {
			name = args[args.length-1];
			
			if (help) {
				printHelp(options);
				return;
			} else {
				if (name.contains("-") || name.equals("ls")) {
					name = System.getProperty("user.dir");
				} else if (!name.contains("/")) {
					name = System.getProperty("user.dir") + "/" + name;
				}
				
				FileSave fi = new FileSave();
				/*if (F) {
					try {
						fi.FFOption(name);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else */if (a) {
					fi.AOption(name);
				} else if (f) {
					fi.FOption(name);
				} else if (t) {
					fi.TOption(name, true);
				} else if (rt) {
					fi.TOption(name, false);
				} else if (r) {
					fi.freeOption(name, false);
				} else if (R) {
					System.out.println(".:");
					fi.ROption(name, "");
				}else {
					fi.freeOption(name, true);
				}
			}
		}
	}


	private void printHelp(Options options) {
		// TODO Auto-generated method stub
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "ls Options Program";
		String footer ="\nPlease input the file name at last.";
		formatter.printHelp("CLIExample", header, options, footer, true);
		
	}


	private boolean parseOption(Options options, String[] args) {
		// TODO Auto-generated method stub
		CommandLineParser parser = new DefaultParser(); 
		
		try {
			CommandLine cmd = parser.parse(options, args);
			
			help = cmd.hasOption("h");
			//F = cmd.hasOption("F");
			a = cmd.hasOption("a");
			f = cmd.hasOption("f");
			t = cmd.hasOption("t");
			rt = cmd.hasOption("rt");
			r = cmd.hasOption("r");
			R = cmd.hasOption("R");
		} catch (ParseException e) {
			printHelp(options);
			return false;
		}
		return true;
	}


	private Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("h").longOpt("help")
				.desc("help")
				.build());
		/*
		options.addOption(Option.builder("F").longOpt("classify")
				.desc("appends a character revealing the nature of a file, for example, * for an executable, or / for a directory. Regular files have no suffix.")
				.build());
		*/
		options.addOption(Option.builder("a").longOpt("all")
				.desc(" lists all files in the given directory, including those whose names start with \".\" (which are hidden files in Unix). By default, these files are excluded from the list.\n")
				.build());
		
		options.addOption(Option.builder("f")
				.desc("do not sort. Useful for directories containing large numbers of files.")
				.build());
		
		options.addOption(Option.builder("t")
				.desc("sort the list of files by modification time.")
				.build());
		
		options.addOption(Option.builder("rt").longOpt("classify")
				.desc("reverse order while sorting the list of files by modification time.")
				.build());
		
		options.addOption(Option.builder("r").longOpt("reverse")
				.desc("reverse order while sorting.")
				.build());

		options.addOption(Option.builder("R").longOpt("recursive")
				.desc("recursively lists subdirectories. The command ls -R / would therefore list all files.")
				.build());
		
		return options;
	}
}
