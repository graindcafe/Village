package cappuccino.village;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import cappuccino.village.command.VillageCommand;

public class VillagePlugin extends JavaPlugin implements Listener {
	Map<String, Village> bukkitAssociation;

	@Override
	public void onEnable() {
		bukkitAssociation = new HashMap<String, Village>();

		getServer().getPluginManager().registerEvents(this, this);
		getCommand("village").setExecutor(new VillageCommand(this));
		// FIXME
		// getCommand("pa").setExecutor(new PlayerCommand());

	}

	public Village newVillage(Player owner) {
		Village v = newVillage((OfflinePlayer) owner);
		if (v != null)
			v.addPlayer(owner);
		return v;
	}

	public Village newVillage(OfflinePlayer owner) {
		if (owner == null)
			return null;
		Village village = new Village(this);
		bukkitAssociation.put(owner.getName(), village);
		getServer().getPluginManager().registerEvents(village, this);
		return village;
	}

	public Village newVillage(String ownerName) {
		return newVillage(getServer().getOfflinePlayer(ownerName));
	}

	@Override
	public void onDisable() {
		bukkitAssociation.clear();
	}

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		Village village = bukkitAssociation.get(player.getName());
		if (village != null) {
			village.addPlayer(player);
			player.sendMessage("Welcome back villager");
		} else
			player.sendMessage("You're not a villager");
	}

	@EventHandler
	public void onPlayerQuit(final PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		Village village = bukkitAssociation.get(player);
		if (village != null)
			village.removePlayer(event.getPlayer());
	}

	public ErrorCode joinVillage(Player p, String ownerName) {
		OfflinePlayer owner = getServer().getOfflinePlayer(ownerName);
		if (owner == null)
			return ErrorCode.NONEXISTINGPLAYER;
		Village v = bukkitAssociation.get(owner);
		if (v != null)
			return v.addPlayer(p);
		return ErrorCode.NONEXISTINGVILLAGE;
	}

	public Set<String> getVillageOwners() {
		return bukkitAssociation.keySet();
	}

	public Set<Entry<String, Village>> getVillages() {
		return bukkitAssociation.entrySet();
	}
}
