package cappuccino.village.player;

public class VillagePlayer {
	private int	playerX;
	private int	playerZ;
	private int	ActionPoints;
	
	public VillagePlayer() {
		this.playerX = 0;
		this.playerZ = 0;
		this.ActionPoints = 100;
	}
	
	public int getActionPoints() {
		return ActionPoints;
	}
	
	public void setActionPoints(int actionPoints) {
		ActionPoints = actionPoints;
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
