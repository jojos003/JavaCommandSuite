package com.jcs.options;

import java.util.ArrayList;
import java.util.List;

import com.jcs.command.Command;
import com.jcs.command.CommandSuite;

/**
 * <b>Represent a command line option.</b>
 * 
 * <p>Object of this class could be add both with {@link CommandSuite} or {@link Command} for use it,
 * respectively, like a global option or a command option.</p>
 * 
 * <p>This class is abstract, you can use one of is subclass below :
 * <ul>
 * <li>{@link SwitchOption}</li>
 * <li>{@link TagOption}</li>
 * </ul>
 * </p>
 * 
 * <p>It implements the interface {@link Comparable} to give a free sorter method on is name.</p>
 * 
 * @author jonathan
 * @version 1.0.0
 * @see TagOption
 * @see SwitchOption
 * @see ArrayOption
 */
public abstract class CommandLineOption implements Comparable<CommandLineOption> {
	
	/**
	 * The option's name.
	 * <p>This is the first name to search when parse the command line.
	 * It could be a long form like this <code>--a-long-form-name</code> or a short like <code>-a</code>.
	 * First character must be <code>-</code> and could contains a second, otherwhise, 
	 * this option will be treat has an argument.</p>
	 */
	String name;
	/**
	 * The option's aliases.
	 * <p>Define other name that could be more expressive compare to the name. For exemple, a option's name 
	 * could be <code>-r</code> and one of is aliases <code>--recursive</code>. The conditions to write an aliases
	 * are the same as name.</p>
	 */
	List<String> aliases;
	/**
	 * The option's description.
	 * <p>A short text to explain in one line the option's role.</p>
	 */
	String description;
	/**
	 * The option's long description.
	 * <p>A more details description.</p>
	 */
	String longDescription;
	
	/**
	 * This is the default constructor to create an option.
	 * 
	 * @param name A {@link String} to indicate is name (cannot be null neither empty).
	 * @param description A {@link String} to give a short description.
	 * @param longDescription A {@link String} to give a complete description.
	 */
	public CommandLineOption(String name, String description, String longDescription) {
		this.name = name;
		this.aliases = new ArrayList<String>();
		this.description = description;
		this.longDescription = longDescription;
	}

	/* accessors */
	
	/**
	 * 
	 * @return Is name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return Is aliases.
	 */
	public List<String> getAliases() {
		return aliases;
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
	 * This method must be override by subclasses to indicates the return value.
	 * @return An object represent the value.
	 */
	public abstract Object getValue();
	
	/**
	 * Give some help for use this option.
	 * 
	 * @return A {@link String} help text.
	 */
	public String help() {
		StringBuilder output = new StringBuilder();
		output.append(getName());
		for (String alias : getAliases()) {
			output.append(" " + alias);
		}
		if(!getDescription().trim().equals("")) {
			output.append("   ");
			output.append(getDescription());
		}
		
		return output.toString();
	}
	
	/* override from Object */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandLineOption other = (CommandLineOption) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/* override from Comparable */
	
	@Override
	public int compareTo(CommandLineOption o) {
		if(o != null) {
			return this.getName().compareTo(o.getName());
		}
		return 1;
	}

}
