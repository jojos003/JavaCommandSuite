package com.jcs.options;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Represent an array option which lets the user to enter multiple parameters for an option.</b> 
 * <p>This option contents a value of {@link List} type.</p>
 * 
 * @see CommandLineOption
 * @see SwitchOption
 * @see TagOption
 * @author jonathan
 *
 */
public class ArrayOption extends CommandLineOption {

	/**
	 * 
	 */
	List<String> values;
	
	/**
	 * Create a new ArrayOption.
	 * 
	 * @see CommandLineOption
	 * @param name
	 * @param description
	 * @param longDescription
	 */

	public ArrayOption(String name, String description, String longDescription) {
		super(name, description, longDescription);
		values = new ArrayList<String>();
	}

	/**
	 * Add a value to the list.
	 * 
	 * @param value A new value to add.
	 */
	public void addValue(String value) {
		values.add(value);
	}
	
	/**
	 * All values given were return as a {@link String} array.
	 * 
	 * @return A {@link String} array.
	 */
	public String[] getValues() {
		return values.toArray(new String[values.size()]);
	}
	
	@Override
	public Object getValue() {
		return values;
	}

}
