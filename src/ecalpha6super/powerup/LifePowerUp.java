package ecalpha6super.powerup;

import processing.core.PApplet;
import ecalpha6super.ContentLoader;

@Deprecated
public class LifePowerUp extends AbstractPowerUp
{
	public LifePowerUp(PApplet p)
	{
		super(p);

		SetImage(ContentLoader.LifePowerUpImage(0));
	}

	@Override
	public int GetID()
	{
		return 3;
	}

};
