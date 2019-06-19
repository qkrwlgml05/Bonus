package edu.handong.bonus;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.File;

import edu.handong.bonus.files.FileSave;

public class LsOptionImplement {
	private boolean help;
	private boolean F;
	private boolean a;
	private boolean f;
	private boolean t;
	private boolean R;
	
	private String name;
	
	
	public void run(String[] args) {
		Options options = createOptions();
		
		if (parseOption(options, args)) {
			name = args[args.length-1];
			System.out.println(args.length);
			System.out.println(name);
			
			if (help) {
				printHelp(options);
				return;
			} else {
				if (name.contains("-")) {
					name = System.getProperty("user.dir");
				}
				
				FileSave fi = new FileSave();
				if (F) {
					fi.FFOption(name);
				} else if (a) {
					fi.AOption(name);
				} else if (f) {
					fi.FOption(name);
				} else if (t) {
					fi.TOption(name);
				} else if (R) {
					fi.ROption(name, "");
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
			F = cmd.hasOption("F");
			a = cmd.hasOption("a");
			f = cmd.hasOption("f");
			t = cmd.hasOption("t");
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
		
		options.addOption(Option.builder("F").longOpt("classify")
				.desc("appends a character")
				.build());
		
		options.addOption(Option.builder("a").longOpt("all")
				.desc("list all files.")
				.build());
		
		options.addOption(Option.builder("f")
				.desc("do not sort.")
				.build());
		
		options.addOption(Option.builder("t").longOpt("classify")
				.desc("sort the list of files by modified time.")
				.build());

		options.addOption(Option.builder("R")
				.desc("show information.")
				.build());
		
		return options;
	}
}
