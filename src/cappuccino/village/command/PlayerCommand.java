package cappuccino.village.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PlayerCommand implements CommandExecutor {
	public PlayerCommand() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args[0].equalsIgnoreCase("pa")) {
			System.out.println(sender.getName());
			return true;
		}
		return false;
	}
	
}
