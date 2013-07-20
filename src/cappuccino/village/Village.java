package cappuccino.village;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import cappuccino.village.listener.VillagePlayerListener;
import cappuccino.village.player.VillagePlayer;

public class Village extends JavaPlugin {
	
	static HashMap<String, VillagePlayer>	RegisteredPlayers;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(
				new VillagePlayerListener(), this);
		RegisteredPlayers = new HashMap<String, VillagePlayer>();
	}
	
	@Override
	public void onDisable() {
		RegisteredPlayers = null;
	}
	
	static public HashMap<String, VillagePlayer> getRegisterdPlayers() {
		return RegisteredPlayers;
	}
}
