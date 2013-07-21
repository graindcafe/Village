package cappuccino.village;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import cappuccino.village.player.VillagePlayer;

public class Village implements Listener {
	VillagePlugin plugin;
	HashMap<Player, VillagePlayer> villagers;

	public Village(VillagePlugin plugin) {
		this.plugin = plugin;
		villagers = new HashMap<Player, VillagePlayer>();
	}

	public ErrorCode addPlayer(Player player) {
		if (villagers.size() == 20)
			return ErrorCode.FULL;
		if (!villagers.containsKey(player))
			villagers.put(player, new VillagePlayer(player));
		else
			return ErrorCode.ALREADYTHERE;
		return ErrorCode.NONE;
	}

	public void removePlayer(Player p) {
		villagers.remove(p);
	}

	public VillagePlayer getVillagePlayer(Player p) {
		return villagers.get(p);
	}

	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		final VillagePlayer vplayer = getVillagePlayer(player);
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
	public void onPlayerItemConsumeEvent(final PlayerItemConsumeEvent event) {
		final Player player = event.getPlayer();
		final VillagePlayer vplayer = getVillagePlayer(player);
		if (vplayer == null)
			return;
		vplayer.setActionPoints(60);
		player.setFoodLevel(20);
	}

	@EventHandler
	public void onPlayerFoodLevelChangeEvent(final FoodLevelChangeEvent event) {
		if (!event.isCancelled())
			event.setCancelled(true);
	}

	public int getVillagerCount() {
		return villagers.size();
	}
}