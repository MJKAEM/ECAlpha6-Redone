package ecalpha6super.entity;

import processing.core.PApplet;
import ecalpha6super.UserInterface;
import ecalpha6super.powerup.PowerUpNames;
import ecalpha6super.powerup.PowerUpOrb;

public abstract class AbstractEnemy extends AbstractEntity
{
	private int hitpoints;
	private int attackDelay;

	/**
	 * Please note that using this constructor will initialize an Enemy with 2
	 * hp.
	 * 
	 * @param p
	 *            Pass PApplet
	 */
	@Deprecated
	protected AbstractEnemy(final PApplet p)
	{
		this(p, 2);
	}

	protected AbstractEnemy(final PApplet p, final int hitpoints)
	{
		super(p);
		this.hitpoints = hitpoints;
	}

	@Override
	public void Show()
	{
		Simon.image(GetImage(), GetPosition().x, GetPosition().y);
	}

	@Override
	public void Act()
	{
		if (GetDead())
		{

			/*
			 * if(tempRandomNum < 50) { // Do nothing!!! } else if(tempRandomNum
			 * >= 50 && ) {
			 * 
			 * } else if(tempRandomNum < 50)
			 */

			super.Act();
		}
	}

	public abstract void Attack();

	public void HurtMe()
	{
		--hitpoints;
		UserInterface.SetNumScore(UserInterface.GetNumScore() + 15);

		if (hitpoints <= 0)
		{
			SetDead(true);
			SpawnPowerUp();
			UserInterface.SetNumScore(UserInterface.GetNumScore() + 30);
		}
	}

	public void SpawnPowerUp()
	{
		// Spawn a power up on death, if it rolls.
		//
		int tempRandomNum = (int) (Math.random() * 3);

		PowerUpOrb tempLifePowerUp = null;

		switch (tempRandomNum)
		{

		// Do nothing.
		//
			case 0:
				// Required to prevent execution of default case and placing of
				// a null PowerUpOrb in the grid.
				//
				return;

				// Spawn score points.
				//
			case 1:
				tempLifePowerUp = new PowerUpOrb(Simon, PowerUpNames.Score);
				break;

			// Spawn power boost.
			//
			case 2:
				tempLifePowerUp = new PowerUpOrb(Simon, PowerUpNames.Power);
				break;

			default:
				throw new RuntimeException(
						"The magic number generator screwed up.");
		}

		tempLifePowerUp.PutSelfInGrid(GetGrid(), GetPosition());
	}

	public final int GetAttackDelay()
	{
		return this.attackDelay;
	}

	public final int GetHitPoints()
	{
		return this.hitpoints;
	}

	public final void SetAtackDelay(final int attackDelay)
	{
		this.attackDelay = attackDelay;
	}

	public final void SetHitPoints(final int value)
	{
		this.hitpoints = value;
	}

	public abstract int ReturnID();
};
