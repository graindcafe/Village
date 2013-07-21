package cappuccino.village.listener;

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
	Village plugin;

	public VillagePlayerListener(Village plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		plugin.addPlayer(player);
	}

	@EventHandler
	public void OnPlayerMove(final PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		final VillagePlayer vplayer = plugin.getVillagePlayer(player);
		if (vplayer == null)
			return;
		if (vplayer.getActionPoints() <= 0)
			player.teleport(player.getLocation());
		else {
			if (!(player.getLocation().getBlockX() == vplayer.getPlayerX())
					|| !(player.getLocation().getBlockZ() == vplayer
							.getPlayerZ())) {
				vplayer.setPlayerX(player.getLocation().getBlockX());
				vplayer.setPlayerZ(player.getLocation().getBlockZ());
				vplayer.decrementActionPoints();
			}
		}
	}

	@EventHandler
	public void PlayerItemConsumeEvent(final PlayerItemConsumeEvent event) {
		final Player player = event.getPlayer();
		final VillagePlayer vplayer = plugin.getVillagePlayer(player);
		if (vplayer == null)
			return;
		vplayer.setActionPoints(60);
		player.setFoodLevel(20);
	}

	@EventHandler
	public void PlayerFoodLevelChangeEvent(final FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}
}