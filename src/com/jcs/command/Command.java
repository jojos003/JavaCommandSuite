package com.jcs.command;

import java.util.ArrayList;
import java.util.List;

import com.jcs.options.CommandLineOption;

/**
 * <b>Represent a command to execute.</b>
 * <p>
 * This command contains a name and a {@link List} of options. To execute this command, 
 * the name of it has to be pass to the command line suite.
 * </p>
 * 
 * @author jonathan
 *
 */
public abstract class Command {

	private static final Object LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * The command's name.
	 */
	String name;
	/**
	 * The command's options.
	 */
	List<CommandLineOption> options;
	/**
	 * The command's description.
	 * <p>A short text to explain in one line the option's role.</p>
	 */
	String description;
	/**
	 * The command's long description.
	 * <p>A more details description.</p>
	 */
	String longDescription;
	
	/**
	 * Construct a new {@link Command} whith a name.
	 * 
	 * @param name The command's name
	 */
	public Command(String name) {
		this.name = name;
		options = new ArrayList<CommandLineOption>();
		description = "";
		longDescription = "";
	}
	
	/**
	 * @return The command name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @see CommandLineOption
	 * @return The comman's options.
	 */
	public List<CommandLineOption> getOptions() {
		return options;
	}
	
	/**
	 * Define the options.
	 * 
	 * @see CommandLineOption
	 * @param options The options to replace with.
	 */
	public void setOptions(List<CommandLineOption> options) {
		this.options = options;
	}

	/**
	 * Add a {@link CommandLineOption} to this command.
	 * 
	 * @see CommandLineOption
	 * @param option The option to add.
	 * @return True if option added.
	 */
	public boolean addOption(CommandLineOption option) {
		return options.add(option);
	}
	
	/**
	 * Remove a {@link CommandLineOption} to this command.
	 * 
	 * @see CommandLineOption
	 * @param option The option to remove.
	 * @return True if option removed.
	 */
	public boolean removeOption(CommandLineOption option) {
		return options.remove(option);
	}
	
	/**
	 * Search and return the option for name.
	 * <p>Search for option's name or one of it aliases.</p>
	 * 
	 * @see CommandLineOption
	 * @param name The option's name or alias.
	 * @return An option if found, null if not.
	 */
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
	
	/**
	 * Indicates if option with name exists for this command.
	 * <p>Search on aliases's option too. See <code>getOption(String name)</code> for more details.</p> 
	 * 
	 * @param name The option's name or alias.
	 * @return <code>true</code> if found, <code>false</code> otherwise.
	 */
	public boolean hasOption(String name) {
		return getOption(name) != null;
	}
	
	/**
	 * 
	 * @return Is short description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @return Is long description.
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * Define is short description.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Define is long description.
	 * @param longDescription
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	/**
	 * Give help to inform how to use this command.
	 * <p>The default behaviour is like that :
	 * <pre>
	 * Usage : list [options] args
	 * 
	 * Options :
	 * -a --all  list all
	 * -r --recursive   list recursively
	 * 
	 * Description :
	 * List command bla bla bla bla...
	 * </pre>
	 * 
	 * @return A {@link String} help text.
	 */
	public String help() {
		StringBuilder output = new StringBuilder();
		output.append("Usage : ");
		output.append(LINE_SEPARATOR);
		output.append(getName() + " [options] args ");
		output.append(LINE_SEPARATOR);
		if(!getOptions().isEmpty()) {
			output.append(LINE_SEPARATOR);
			output.append("Options :");
			output.append(LINE_SEPARATOR);
			for (CommandLineOption option : getOptions())
				output.append(option.help());
			output.append(LINE_SEPARATOR);
		}
		if(!getLongDescription().trim().endsWith("")) {
			output.append(LINE_SEPARATOR);
			output.append("Description :");
			output.append(LINE_SEPARATOR);
			output.append(getLongDescription());
			output.append(LINE_SEPARATOR);
		}
		return output.toString();
	}
	
	/**
	 * 
	 * 
	 * @param commandSuite
	 * @param arguments
	 */
	public abstract void action(CommandSuite commandSuite, List<String> arguments);

	@Override
	public String toString() {
		return getName();
	}
	
}
