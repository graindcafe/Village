package cappuccino.village.command;

import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cappuccino.locales.Messages;
import cappuccino.village.Village;
import cappuccino.village.VillagePlugin;

public class VillageCommand implements CommandExecutor {
	VillagePlugin plugin;

	public VillageCommand(VillagePlugin plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length == 0)
			return false;
		if (args[0].equalsIgnoreCase("new")
				|| args[0].equalsIgnoreCase("create")) {
			if (args.length > 1)
				plugin.newVillage(args[1]);
			else if (sender instanceof Player)
				plugin.newVillage((Player) sender);
			else {
				sender.sendMessage(Messages.getString("Message.NotCreated")); //$NON-NLS-1$
				return false;
			}
			sender.sendMessage(Messages.getString("Message.Created")); //$NON-NLS-1$
			return true;
		}
		if (args[0].equalsIgnoreCase("join") && (sender instanceof Player)
				&& (args.length > 1)) {
			switch (plugin.joinVillage((Player) sender, args[1])) {
			case FULL:
				sender.sendMessage(Messages.getString("Message.Full")); //$NON-NLS-1$
				return true;
			case ALREADYTHERE:
				sender.sendMessage(Messages.getString("Message.AlreadyThere")); //$NON-NLS-1$
				return true;
			case NONEXISTINGPLAYER:
				sender.sendMessage(Messages
						.getString("Message.NonExistingPlayer")); //$NON-NLS-1$
				return true;
			case NONEXISTINGVILLAGE:
				sender.sendMessage(Messages
						.getString("Message.NonExistingVillage")); //$NON-NLS-1$
				return true;
			case NONE:
			default:
				sender.sendMessage(Messages.getString("Message.Joined")); //$NON-NLS-1$
				return true;
			}
		}
		if (args[0].equalsIgnoreCase("list")) {
			String msg = "Villages:\n";
			for (Entry<String, Village> e : plugin.getVillages()) {
				msg += "  " + e.getKey() + ": "
						+ e.getValue().getVillagerCount() + "\n";
			}
			sender.sendMessage(msg);
			return true;
		}
		return false;
	}

}
