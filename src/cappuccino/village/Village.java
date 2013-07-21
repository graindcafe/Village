package cappuccino.village;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import cappuccino.village.listener.VillagePlayerListener;
import cappuccino.village.player.VillagePlayer;

public class Village extends JavaPlugin {

	HashMap<Player, VillagePlayer> registeredPlayers;

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(
				new VillagePlayerListener(this), this);
		// FIXME
		// getCommand("pa").setExecutor(new PlayerCommand());
		registeredPlayers = new HashMap<Player, VillagePlayer>();
	}

	@Override
	public void onDisable() {
		registeredPlayers = null;
	}

	public boolean addPlayer(Player player) {
		if (getRegisterdPlayers().size() == 20
				&& !getRegisterdPlayers().containsKey(player)) {
			player.kickPlayer("Sorry the game has started/is full");
			return false;
		}
		if (!getRegisterdPlayers().containsKey(player))
			registeredPlayers.put(player, new VillagePlayer(player));
		return true;
	}

	public HashMap<Player, VillagePlayer> getRegisterdPlayers() {
		return registeredPlayers;
	}

	public VillagePlayer getVillagePlayer(Player p) {
		return registeredPlayers.get(p);
	}
}
