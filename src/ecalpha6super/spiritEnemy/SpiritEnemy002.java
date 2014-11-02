package ecalpha6super.spiritEnemy;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PVector;
import ecalpha6super.bullet.CircleBullet;
import ecalpha6super.entity.IEntity;
import ecalpha6super.entity.IRangeAttacker;
import ecalpha6super.entity.IRunAway;
import ecalpha6super.entity.RunDirection;

/**
 * SpiritEnemy002 moves down in a straight line and fires bullets. It will stop
 * after reaching a certain point, then move in a specified direction. It
 * removes itself when it goes off screen.
 * 
 * @author Martino
 * 
 */
public class SpiritEnemy002 extends AbstractSpiritEnemy implements
		IRangeAttacker, IRunAway
{
	private float stopPosY;
	private int timeToStay;
	private RunDirection runDir;

	/**
	 * Spawns a SpiritEnemy002 that moves in a straight line down at a speed of
	 * 2 pixels per frame and stops during movement for 2 seconds after it
	 * touches a specific 'invisible' line on the y-axis. After that, it will
	 * run away in any random.
	 * 
	 * @param p
	 *            Pass PApplet
	 * @param stopPosY
	 *            The position in the y-axis to stop
	 * @param hitpoints
	 *            The amount of hits this enemy will take
	 */
	public SpiritEnemy002(PApplet p, float stopPosY, int hitpoints)
	{
		this(p, stopPosY, hitpoints, 2);
	}

	/**
	 * Spawns a SpiritEnemy002 that moves in a straight line down and stops
	 * during movement for 2 seconds after it touches a specific 'invisible'
	 * line on the y-axis. After that, it will run away in any random direction.
	 * 
	 * @param p
	 *            Pass PApplet
	 * @param stopPosY
	 *            The position in the y-axis to stop
	 * @param hitpoints
	 *            The amount of hits this enemy will take
	 * @param speed
	 */
	public SpiritEnemy002(PApplet p, float stopPosY, int hitpoints, int speed)
	{
		// Believe me, this long pile of code will get a random direction.
		//
		this(p, stopPosY, RunDirection.Custom.GetDirectionFromValue((int) (Math
				.random() * 4)), hitpoints, speed);
	}

	/**
	 * Spawns a SpiritEnemy002 that moves in a straight line down and stops
	 * during movement for a specified amount of time after it touches a
	 * specific 'invisible' line on the y-axis. After that, it will run away in
	 * any specified direction.
	 * 
	 * @param p
	 *            Pass PApplet
	 * @param stopPosY
	 *            The position in the y-axis to stop
	 * @param runDir
	 *            The direction the enemy will run away to
	 * @param timeToStay
	 *            The time that an enemy will stay at <code>stopPosY</code>, in
	 *            seconds
	 * @param hitpoints
	 *            The amount of hits this enemy will take
	 * @param speed
	 *            The rate in pixels per frame this enemy will move
	 */
	public SpiritEnemy002(PApplet p, float stopPosY, RunDirection runDir,
			double timeToStay, int hitpoints, int speed)
	{
		this(p, stopPosY, runDir, (float) timeToStay, hitpoints, speed);
	}

	/**
	 * Spawns a SpiritEnemy002 that moves in a straight line down and stops
	 * during movement for a specified amount of time after it touches a
	 * specific 'invisible' line on the y-axis. After that, it will run away in
	 * any specified direction.
	 * 
	 * @param p
	 *            Pass PApplet
	 * @param stopPosY
	 *            The position in the y-axis to stop
	 * @param runDir
	 *            The direction the enemy will run away to
	 * @param timeToStay
	 *            The time that an enemy will stay at <code>stopPosY</code>, in
	 *            seconds
	 * @param hitpoints
	 *            The amount of hits this enemy will take
	 * @param speed
	 *            The rate in pixels per frame this enemy will move
	 */
	public SpiritEnemy002(PApplet p, float stopPosY, RunDirection runDir,
			float timeToStay, int hitpoints, int speed)
	{
		super(p, hitpoints, speed);

		this.stopPosY = stopPosY;
		this.runDir = runDir;
		this.timeToStay = (int) (timeToStay * 60);
	}

	/**
	 * Spawns a SpiritEnemy002 that moves in a straight line down and stops
	 * during movement for 2 seconds after it touches a specific 'invisible'
	 * line on the y-axis. After that, it will run away in any specified
	 * direction.
	 * 
	 * @param p
	 *            Pass PApplet
	 * @param stopPosY
	 *            The position in the y-axis to stop
	 * @param runDir
	 *            The direction the enemy will run away to
	 * @param hitpoints
	 *            The amount of hits this enemy will take
	 * @param speed
	 *            The rate in pixels per frame this enemy will move
	 */
	public SpiritEnemy002(PApplet p, float stopPosY, RunDirection runDir,
			int hitpoints, int speed)
	{
		this(p, stopPosY, runDir, 2, hitpoints, speed);
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
			RemoveSelfFromGrid();
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

	@Override
	public RunDirection GetRunDirection()
	{
		return this.runDir;
	}

	private void Move()
	{
		if (GetPosition().y < stopPosY)
		{
			SetPosition(GetPosition().x, GetPosition().y + GetMoveSpeed());
		}
		else
		{
			if (timeToStay > 0)
			{
				--timeToStay;
			}
			else
			{
				RunAway();
			}
		}
	}

	@Override
	public void RangeAttack(IEntity target)
	{
		// Player tempPlayer = (Player)tempEntity;
		/*
		 * PVector tempDestination = new PVector( tempEntity.GetPosition().x +
		 * (tempEntity.GetImage().width / 2), tempEntity.GetPosition().y +
		 * (tempEntity.GetImage().height / 2));
		 */

		PVector tempDestination = PVector.add(target.GetPosition(),
				new PVector(target.GetImage().width / 2,
						target.GetImage().height / 2));

		CircleBullet tempBullet = new CircleBullet(Simon, tempDestination,
				Color.RED, 2, 10);

		tempBullet.PutSelfInGrid(GetGrid(), GetPosition());
		SetAtackDelay(ATTACK_DELAY_START_VALUE);
	}

	@Override
	public int ReturnID()
	{
		return 2;
	}

	@Override
	public void RunAway()
	{
		switch (runDir)
		{
		case Right:
			if (GetPosition().x < 440)
			{
				SetPosition(GetPosition().x + GetMoveSpeed(), GetPosition().y);
			}
			else
			{
				SetDead(true);
			}
			break;

		case Up:
			if (GetPosition().y + GetImage().height > 0)
			{
				SetPosition(GetPosition().x, GetPosition().y - GetMoveSpeed());
			}
			else
			{
				SetDead(true);
			}
			break;

		case Left:
			if (GetPosition().x + GetImage().width > 0)
			{
				SetPosition(GetPosition().x - GetMoveSpeed(), GetPosition().y);
			}
			else
			{
				SetDead(true);
			}
			break;

		case Down:
			if (GetPosition().y > Simon.height)
			{
				SetPosition(GetPosition().x, GetPosition().y + GetMoveSpeed());
			}
			else
			{
				SetDead(true);
			}
			break;
		default:
			break;
		}
	}
};
