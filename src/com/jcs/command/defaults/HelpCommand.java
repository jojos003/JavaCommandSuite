package com.jcs.command.defaults;

import java.util.List;

import com.jcs.command.Command;
import com.jcs.command.CommandSuite;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("help");
	}

	@Override
	public void action(CommandSuite cs, List<String> arguments) {
		if (arguments != null && !arguments.isEmpty()) {
			Command cmd = null;
			for (String string : arguments) {
				cmd = cs.getCommand(string);
				if(cmd != null)
					System.out.println(cmd.help());
			}
		}
		else {
			System.out.println("Commandes : ");
			System.out.println();
			for (Command cmd : cs.getCommands()) {
				System.out.println("  " + cmd.getName());
			}
		}
	}

}
