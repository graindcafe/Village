package cappuccino.village.listener;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import cappuccino.village.Village;
import cappuccino.village.player.VillagePlayer;

public class VillagePlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		if (Village.getRegisterdPlayers().size() == 20
				&& !Village.getRegisterdPlayers().containsKey(player.getName()))
			player.kickPlayer("Sorry the game has started/is full");
		if (!Village.getRegisterdPlayers().containsKey(player.getName()))
			Village.getRegisterdPlayers().put(player.getName(),
					new VillagePlayer());
	}
	
	@EventHandler
	public void OnPlayerMove(final PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		final HashMap<String, VillagePlayer> rp = Village.getRegisterdPlayers();
		
		if (rp.get(player.getName()).getPlayerX() == 0
				&& rp.get(player.getName()).getPlayerZ() == 0) {
			rp.get(player.getName()).setPlayerX(
					player.getLocation().getBlockX());
			rp.get(player.getName()).setPlayerZ(
					player.getLocation().getBlockZ());
		}
		
		if (!(player.getLocation().getBlockX() == rp.get(player.getName())
				.getPlayerX())
				|| !(player.getLocation().getBlockZ() == rp.get(
						player.getName()).getPlayerZ())) {
			rp.get(player.getName()).setPlayerX(
					player.getLocation().getBlockX());
			rp.get(player.getName()).setPlayerZ(
					player.getLocation().getBlockZ());
			
			player.setFoodLevel(player.getFoodLevel() - 1);
			if (player.getFoodLevel() <= 5)
				player.setFoodLevel(player.getFoodLevel() + 10);
		}
	}
}