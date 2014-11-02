package ecalpha6super.powerup;

public enum PowerUpNames
{
	Score(1), Power(2), Life(3), Bomb(4);
	
	private int IDvalue;
	
	private PowerUpNames(int IDvalue)
	{
		this.IDvalue = IDvalue;
	}
	
	public int GetID()
	{
		return this.IDvalue;
	}
};
