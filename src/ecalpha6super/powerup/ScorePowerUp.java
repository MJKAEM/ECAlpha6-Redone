package ecalpha6super.powerup;

import processing.core.PApplet;
import ecalpha6super.ContentLoader;

@Deprecated
public class ScorePowerUp extends AbstractPowerUp
{
	public ScorePowerUp(PApplet p)
	{
		super(p);

		SetImage(ContentLoader.ScorePowerUpImage(0));
	}
	
	@Override
	public int GetID()
	{
		return 1;
	}

};
