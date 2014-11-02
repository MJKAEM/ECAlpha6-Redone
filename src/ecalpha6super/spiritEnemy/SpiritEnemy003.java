package ecalpha6super.spiritEnemy;

import processing.core.PApplet;
import processing.core.PVector;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ecalpha6super.ContentLoader;

public class SpiritEnemy003 extends AbstractSpiritEnemy
{
	/**
	 * Work in progress.
	 * 
	 * Spawns a new SpriteEnemy003 that will move down in a straight line, spawn
	 * at (500, 200), and die in two hits.
	 * 
	 * @param p
	 *            Pass the <code>PApplet</code> parameter.
	 */
	public SpiritEnemy003(PApplet p)
	{
		this(p, 2);
	}

	public SpiritEnemy003(PApplet p, int hitpoints)
	{
		this(p, 200, hitpoints);
	}

	public SpiritEnemy003(PApplet p, int stopPosY, int hitpoints)
	{
		this(p, new PVector(500, stopPosY), hitpoints);
	}

	public SpiritEnemy003(PApplet p, PVector stopPos, int hitpoints)
	{
		this(p, new PVector(stopPos.x, p.height + 20), stopPos, hitpoints);
	}

	public SpiritEnemy003(PApplet p, PVector startStopPos, PVector stopPos,
			int hitpoints)
	{
		super(p, hitpoints, 2);
		SetImage(ContentLoader.EnemySpiritImage(0));

		// this.startStopPos = startStopPos;
		// this.stopPos = stopPos;

		throw new NotImplementedException();
	}

	@Override
	public void Act()
	{
		throw new NotImplementedException();
	}

	@Override
	public void Attack()
	{
		throw new NotImplementedException();
	}

	@Override
	public int ReturnID()
	{
		return 3;
	}
}
