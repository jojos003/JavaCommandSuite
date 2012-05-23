package com.jcs.options;

/**
 * <b>Represent a tag option which lets the user to enter a parameter for a the option.</b> 
 * <p>This option contents a value of {@link String} type.</p>
 * 
 * @see CommandLineOption
 * @see SwitchOption
 * @see ArrayOption
 * @author jonathan
 *
 */
public class TagOption extends CommandLineOption {

	/**
	 * The expected value.<br/>
	 * Default is <code>null</code>.
	 */
	String value;
	
	/**
	 * Create a new TagOption.
	 * 
	 * @see CommandLineOption
	 * @param name
	 * @param description
	 * @param longDescription
	 */
	public TagOption(String name, String description, String longDescription) {
		super(name, description, longDescription);
		value = null;
	}

	/**
	 * Return the option's value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Define the option's value.
	 * 
	 * @param value The {@link String} value.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return getName() + ": " + getValue();
	}
}
