package com.jcs.command.defaults;

import java.util.List;

import com.jcs.command.Command;
import com.jcs.command.CommandSuite;

public class CommandCommand extends Command {

	public CommandCommand() {
		super("command");
	}

	@Override
	public void action(CommandSuite cs, List<String> arguments) {
		for (Command cmd : cs.getCommands()) {
			System.out.println("  " + cmd.getName());
		}
	}

}
