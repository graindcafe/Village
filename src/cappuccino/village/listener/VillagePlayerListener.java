package cappuccino.village.listener;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
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
		
		if (rp.get(player.getName()).getActionPoints() <= 0)
			player.teleport(player.getLocation());
		else {
			if (!(player.getLocation().getBlockX() == rp.get(player.getName())
					.getPlayerX())
					|| !(player.getLocation().getBlockZ() == rp.get(
							player.getName()).getPlayerZ())) {
				rp.get(player.getName()).setPlayerX(
						player.getLocation().getBlockX());
				rp.get(player.getName()).setPlayerZ(
						player.getLocation().getBlockZ());
				
				rp.get(player.getName()).setActionPoints(
						rp.get(player.getName()).getActionPoints() - 1);
				if (rp.get(player.getName()).getActionPoints() % 6 == 0)
					player.setFoodLevel(player.getFoodLevel() - 2);
				
			}
		}
	}
	
	@EventHandler
	public void PlayerItemConsumeEvent(final PlayerItemConsumeEvent event) {
		final Player player = event.getPlayer();
		final HashMap<String, VillagePlayer> rp = Village.getRegisterdPlayers();
		
		rp.get(player.getName()).setActionPoints(60);
		player.setFoodLevel(20);
	}
	
	@EventHandler
	public void PlayerFoodLevelChangeEvent(final FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}
}