package ecalpha6super.spiritEnemy;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PVector;
import ecalpha6super.bullet.AbstractBullet;
import ecalpha6super.bullet.CircleBullet;
import ecalpha6super.entity.IEntity;
import ecalpha6super.entity.IRangeAttacker;

/**
 * SpiritEnemy001 moves down in a straight line and fires bullets. It will not
 * stop in its path. It removes itself when it goes off screen.
 * 
 * @author Art Margatroid
 * 
 */
public class SpiritEnemy001 extends AbstractSpiritEnemy implements
		IRangeAttacker
{
	private AbstractBullet bullet;

	/**
	 * <b>Do not use this constructor.</b><br>
	 * Spawns a SpiritEnemy001 that moves in a straight line down and disappear
	 * once it is off screen. It dies in two hits.<br>
	 * <b>Do not use this constructor.</b>
	 * 
	 * @param p
	 *            Pass PApplet
	 */
	@Deprecated
	public SpiritEnemy001(PApplet p)
	{
		this(p, 2);
	}

	/**
	 * Spawns a SpiritEnemy001 that moves in a straight line down 2 pixels per
	 * frame and disappears once it is off screen. It will die in 2 hits.<br>
	 * <br>
	 * Use this constructor if you only want to spawn something that will go
	 * straight down and leave.
	 * 
	 * @param p
	 *            Pass PApplet
	 * @param hitpoints
	 *            The amount of hits this enemy will take
	 */
	public SpiritEnemy001(PApplet p, int hitpoints)
	{
		this(p, p.height + 20, hitpoints);
	}

	/**
	 * Spawns a SpiritEnemy001 that moves in a straight line down and disappears
	 * once it is off screen.
	 * 
	 * @param p
	 *            Pass PApplet
	 * @param hitpoints
	 *            The amount of hits this enemy will take
	 * @param speed
	 *            The rate in pixels per frame this enemy will move
	 */
	public SpiritEnemy001(PApplet p, int hitpoints, float speed)
	{
		super(p, hitpoints, speed);
	}

	/**
	 * Spawns a SpiritEnemy001 that moves in a straight line down and disappears
	 * once it is off screen.
	 * 
	 * @param p
	 *            Pass PApplet
	 * @param hitpoints
	 *            The amount of hits this enemy will take
	 * @param speed
	 *            The rate in pixels per frame this enemy will move
	 */
	public SpiritEnemy001(PApplet p, int hitpoints, float speed,
			AbstractBullet bullet)
	{
		super(p, hitpoints, speed);
	}

	@Override
	public void Act()
	{
		if (!GetDead())
		{
			Move();
			AnimateTimer();

			Attack();
		}
		else
		{
			super.Act();
		}
	}

	@Override
	public void Attack()
	{
		if (GetAttackDelay() > 0)
		{
			SetAtackDelay(GetAttackDelay() - 1);
			return;
		}

		/*
		 * ArrayList<IEntity> tempEntityList = GetGrid().GetEntityList();
		 * 
		 * for (int i = tempEntityList.size() - 1; i >= 0; --i) { IEntity
		 * tempEntity = tempEntityList.get(i);
		 * 
		 * if (tempEntity instanceof Player) { RangeAttack(tempEntity); } }
		 */

		RangeAttack(GetGrid().GetPlayer());
	}

	private void Move()
	{
		if (GetPosition().y < Simon.height)
		{
			SetPosition(GetPosition().x, GetPosition().y + GetMoveSpeed());
		}
		else
		{
			SetDead(true);
		}
	}

	@Override
	public void RangeAttack(IEntity target)
	{
		PVector tempDestination = PVector.add(target.GetPosition(),
				new PVector(target.GetImage().width / 2,
						target.GetImage().height / 2));

		CircleBullet tempBullet = new CircleBullet(Simon, tempDestination,
				Color.RED, 3, 10);

		tempBullet.PutSelfInGrid(GetGrid(), GetPosition());
		SetAtackDelay(ATTACK_DELAY_START_VALUE);
	}

	@Override
	public final int ReturnID()
	{
		return 1;
	}
};
