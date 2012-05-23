import java.util.List;

import com.jcs.command.Command;
import com.jcs.command.CommandSuite;
import com.jcs.options.TagOption;

public class Main {

	public static void main(String[] args) {
		
		for (String string : args) {
			System.out.println(string);
		}
		
		CommandSuite cs = new CommandSuite();
		cs.getOptions().add(new TagOption("-d", "", ""));
		
		Command execute = new Command("execute"){

			@Override
			public void action(CommandSuite cs, List<String> arguments) {
				System.out.println(cs.getOptions());
				System.out.println(getName());
				System.out.println(getOptions());
				System.out.println(arguments);
			}
			
		};
		execute.getOptions().add(new TagOption("--processlist", "", ""));
		cs.getCommands().add(execute);
		
		try {
			cs.parse(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
