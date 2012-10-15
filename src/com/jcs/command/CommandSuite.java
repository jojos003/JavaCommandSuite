package com.jcs.command;

import java.util.ArrayList;
import java.util.List;

import com.jcs.command.defaults.CommandCommand;
import com.jcs.command.defaults.HelpCommand;
import com.jcs.options.ArrayOption;
import com.jcs.options.CommandLineOption;
import com.jcs.options.SwitchOption;
import com.jcs.options.TagOption;

/**
 * 
 * 
 * @author jonathan
 *
 */
public class CommandSuite {
	
	List<CommandLineOption> options;
	List<Command> commands;
	Command currentCommand;
	List<String> arguments;
	
	public CommandSuite() {
		options = new ArrayList<CommandLineOption>();
		commands = new ArrayList<Command>();
		currentCommand = null;
		arguments = new ArrayList<String>();
		
		addCommand(new HelpCommand());
		addCommand(new CommandCommand());
	}
	
	public List<CommandLineOption> getOptions() {
		return options;
	}

	public void setOptions(List<CommandLineOption> options) {
		this.options = options;
	}

	public boolean addOption(CommandLineOption option) {
		return options.add(option);
	}
	
	public boolean removeOption(CommandLineOption option) {
		return options.remove(option);
	}
	
	public CommandLineOption getOption(String name) {
		for (CommandLineOption option : options) {
			if(option.getName().equals(name))
				return option;
			for(String alias : option.getAliases()){
				if(alias.equals(name))
					return option;
			}
		}
		return null;
	}
	
	public boolean hasOption(String name) {
		return getOption(name) != null;
	}

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}
	
	public boolean addCommand(Command command) {
		return commands.add(command);
	}
	
	public boolean removeCommand(Command command) {
		return commands.remove(command);
	}
	
	public Command getCommand(String name) {
		for (Command command : commands) {
			if(command.getName().equals(name))
				return command;
		}
		return null;
	}
	
	public boolean hasCommand(String name) {
		return getCommand(name) != null;
	}

	public CommandSuite parse(String[] args) throws Exception {
		Command command = null;
		
		boolean commandDefined = false;
		boolean inGlobalOptions = true;
		
		String arg = null;
		CommandLineOption currentOption = null;
		for (int i = 0; i < args.length; i++) {
			arg = args[i];
			if(!commandDefined) {
				command = getCommand(arg);
				if(command != null) {
					commandDefined = true;
					inGlobalOptions = false;
					continue;
				}
				else {
					currentOption = getOption(arg);
				}
			}
			else {
				currentOption = command.getOption(arg);
				if(currentOption == null) {
					arguments.add(arg);
					continue;
				}
			}
			
			if(currentOption instanceof SwitchOption)
				((SwitchOption)currentOption).setValue(true);
			else if(currentOption instanceof TagOption)
				((TagOption)currentOption).setValue(args[++i]);
			else if(currentOption instanceof ArrayOption)
				((ArrayOption)currentOption).addValue(args[++i]);
			else {
				if(inGlobalOptions) 
					throw new Exception("Global option " + arg + " doesn't exists.");
				else
					throw new Exception("Global option " + arg + " doesn't exists for command " + command.getName() + ".");
			}
		}
		currentCommand = command;
		return this;
	}
	
	public void execute() {
		if(currentCommand != null) {
			currentCommand.action(this, arguments);
		}
	}
}
