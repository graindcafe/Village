package cappuccino.village.player;

import org.bukkit.entity.Player;

public class VillagePlayer {
	private final Player player;
	private int playerX;
	private int playerZ;
	private int actionPoints;

	public VillagePlayer(Player p) {
		this.playerX = 0;
		this.playerZ = 0;
		this.actionPoints = 60;
		this.player = p;
	}

	public int getActionPoints() {
		return actionPoints;
	}

	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;

		if (actionPoints % 6 == 0)
			player.setFoodLevel(player.getFoodLevel() - 2);
	}

	public void decrementActionPoints() {
		actionPoints--;
	}

	public int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public int getPlayerZ() {
		return playerZ;
	}

	public void setPlayerZ(int playerZ) {
		this.playerZ = playerZ;
	}
}
