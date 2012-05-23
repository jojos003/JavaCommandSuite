package com.jcs.options;

/**
 * <b>Represent a switch option.</b> 
 * <p>This option contents a value of boolean type.</p>
 * 
 * @see CommandLineOption
 * @see TagOption
 * @see ArrayOption
 * @author jonathan
 *
 */
public class SwitchOption extends CommandLineOption {

	/**
	 * The expected value.<br/>
	 * Default is <code>false</code>.
	 */
	boolean value;

	/**
	 * Create a new SwtichOption.
	 * 
	 * @see CommandLineOption
	 * @param name
	 * @param description
	 * @param longDescription
	 */
	public SwitchOption(String name, String description,
			String longDescription) {
		super(name, description, longDescription);
		value = false;
	}

	/**
	 * Define the value.
	 * 
	 * @param b
	 */
	public void setValue(boolean b) {
		this.value = b;
	}
	
	/**
	 * Indicate that this option is set.
	 * 
	 * @return <code>true</code> if this option is set, <code>false</code> otherwise. 
	 */
	public boolean isSet() {
		return value;
	}
	
	@Override
	public Object getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return getName() + ": " + getValue();
	}
	
}
