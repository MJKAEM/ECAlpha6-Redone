package ecalpha6super.spiritboss;

import processing.core.PApplet;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ecalpha6super.ContentLoader;
import ecalpha6super.entity.AbstractBoss;

public class SpiritBoss extends AbstractBoss
{

	public SpiritBoss(PApplet p)
	{
		super(p);

		SetImage(ContentLoader.BossSpiritImage(0));
	}

	@Override
	public void Attack()
	{
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public void Act()
	{
		if (!GetDead())
		{
			throw new NotImplementedException();
		}
		else
		{
			super.Act();
		}
	}

	@Override
	public int ReturnID()
	{
		return 1;
	}
};
