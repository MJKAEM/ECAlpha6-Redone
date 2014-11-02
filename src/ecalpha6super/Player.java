package ecalpha6super;

import java.awt.event.KeyEvent;
import java.util.BitSet;

import processing.core.PApplet;
import processing.core.PVector;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ecalpha6super.bullet.AbstractBullet;
import ecalpha6super.bullet.CircleBullet;
import ecalpha6super.entity.AbstractEnemy;
import ecalpha6super.entity.AbstractEntity;
import ecalpha6super.entity.IEntity;
import ecalpha6super.powerup.PowerUpOrb;

public final class Player extends AbstractEntity
{
	private final static int ATTACK_DELAY_START = 10;
	private final static int TAP_ATTACK_SET = 5;
	private final static int HITBOX_RADIUS = 5;
	private final static int HITBOX_CIRCLE_BORDER_SIZE = 1;
	private final static int INVINCIBLE_START_TIMER = 5 * 60;
	private final static int TIME_BEFORE_NEXT_FLASH = (int) (0.2 * 60);
	private final static int DEATH_NO_MOVE_START_TIMER = (60);

	/**
	 * This value is used to allow for multiple key presses. 0 = Right, 1 = Up,
	 * 2 = Left, 3 = Down. Using BitSet to substitute for boolean array due to
	 * improved performance.
	 */
	private final BitSet keyPressed;

	/**
	 * For invulnerability purposes, this int helps time the flashes.
	 */
	private int timeInvFlash, timeDeathNoMove;

	private boolean isFocusing, isAttacking;
	private int attackDelay, invincibleTimer;

	private int numLives, numBombs, powerLevel;

	public Player(final PApplet p)
	{
		super(p);

		SetImage(ContentLoader.TurtleImage(0));

		keyPressed = new BitSet(4);
		SetPosition(200, 430);

		numLives = 4;
		numBombs = 5;
	}

	@Override
	public void Show()
	{
		// If still invincible...
		//
		if (invincibleTimer > 0)
		{
			if (timeInvFlash > 0)
			{
				--timeInvFlash;
			}
			else
			{
				timeInvFlash = TIME_BEFORE_NEXT_FLASH;
			}

			// Simulate invulnerability flashes.
			//
			if (timeInvFlash < TIME_BEFORE_NEXT_FLASH / 2)
			{
				return;
			}
		}

		Simon.image(GetImage(), GetPosition().x, GetPosition().y);

		if (isFocusing)
		{
			Simon.stroke(0);
			Simon.fill(255);
			Simon.strokeWeight(1);
			Simon.ellipse(GetPosition().x + (GetImage().width / 2),
					GetPosition().y + (GetImage().height / 2),
					HITBOX_RADIUS * 2, HITBOX_RADIUS * 2);
		}
	}

	/**
	 * Do not call <code>super.Act()</code>!
	 */
	@Override
	public void Act()
	{
		if (!GetDead())
		{
			// If the player is attacked, it cannot move until it is aware of
			// its position.
			//
			if (timeDeathNoMove <= 0)
			{
				Attack();
				Movement();
				CollectPowerUpOrb();
			}
			else
			{
				--timeDeathNoMove;
			}

			// If the player is invisible, then hit detection should be off to
			// simulate invulnerability.
			//
			if (invincibleTimer <= 0)
			{
				for (int i = GetGrid().GetEnemyList().size() - 1; i >= 0; --i)
				{
					if (TouchingEntity(GetGrid().GetEnemyList().get(i)))
					{
						SetDead(true);
					}
				}

				for (int i = GetGrid().GetBulletList().size() - 1; i >= 0; --i)
				{
					if (TouchingEntity(GetGrid().GetBulletList().get(i)))
					{
						SetDead(true);
					}
				}
			}
			else
			{
				--invincibleTimer;
			}
		}
		else
		{
			if (--numLives > 0)
			{
				SetPosition(200, 430);
				powerLevel /= 2;
				invincibleTimer = INVINCIBLE_START_TIMER;
				timeDeathNoMove = DEATH_NO_MOVE_START_TIMER;
				SetDead(false);
			}
		}
	}

	/**
	 * By holding down the Z key, the player will spawn a laser for the purpose
	 * of attacking enemies from a distance.
	 */
	public void Attack()
	{
		if (isAttacking && attackDelay <= 0)
		{
			Laser laser = new Laser(Simon);

			laser.PutSelfInGrid(GetGrid(), new PVector(
					GetPosition().x + (GetImage().width / 2) - 2,
					GetPosition().y));

			attackDelay = ATTACK_DELAY_START;
		}
		else if (attackDelay > 0)
		{
			--attackDelay;
		}
	}

	public void CollectPowerUpOrb()
	{
		for (int i = GetGrid().GetPowerUpList().size() - 1; i >= 0; --i)
		{
			PowerUpOrb tempPowerUpOrb = GetGrid().GetPowerUpList().get(i);

			if (TouchingEntity(tempPowerUpOrb))
			{
				switch (tempPowerUpOrb.GetName())
				{
					case Score:
						UserInterface
								.SetNumScore(UserInterface.GetNumScore() + 100);
						break;
					case Power:
						++powerLevel;
						break;
					case Life:
						++numLives;
						break;
					case Bomb:
						++numBombs;
						break;

					default:
						throw new RuntimeException(
								"Unknown power-up orb detected!");
				}
				tempPowerUpOrb.SetDead(true);
			}
		}
	}

	public void KeyPressed()
	{
		StartAttack();
		StartMovement();
	}

	public void KeyReleased()
	{
		ReleaseAttack();
		ReleaseMovement();
	}

	public void Movement()
	{
		boolean lockDirectionX = false, lockDirectionY = false;

		// This code tells the game to not move if buttons of opposite
		// directions are pressed.
		//
		if (keyPressed.get(0) && keyPressed.get(2))
		{
			lockDirectionX = true;
		}
		if (keyPressed.get(1) && keyPressed.get(3))
		{
			lockDirectionY = true;
		}

		if (!lockDirectionX)
		{
			// If RIGHT key is pressed
			//
			if (keyPressed.get(0))
			{
				if (GetPosition().x + 18 + 13 < 438)
				{
					if (isFocusing)
					{
						SetPosition(GetPosition().x + 2, GetPosition().y);
					}
					else
					{
						SetPosition(GetPosition().x + 3, GetPosition().y);
					}
				}
			}
			// If LEFT key is pressed
			//
			else if (keyPressed.get(2))
			{
				if (GetPosition().x + 18 > 0)
				{
					if (isFocusing)
					{
						SetPosition(GetPosition().x - 2, GetPosition().y);
					}
					else
					{
						SetPosition(GetPosition().x - 3, GetPosition().y);
					}
				}
			}
		}

		if (!lockDirectionY)
		{
			// If UP key is pressed
			//
			if (keyPressed.get(1))
			{
				if (GetPosition().y + 20 > 0)
				{
					if (isFocusing)
					{
						SetPosition(GetPosition().x, GetPosition().y - 2);
					}
					else
					{
						SetPosition(GetPosition().x, GetPosition().y - 3);
					}
				}
			}
			// If DOWN key is pressed
			//
			else if (keyPressed.get(3))
			{
				if (GetPosition().y + 20 + 13 < 478)
				{
					if (isFocusing)
					{
						SetPosition(GetPosition().x, GetPosition().y + 2);
					}
					else
					{
						SetPosition(GetPosition().x, GetPosition().y + 3);
					}
				}
			}
		}
	}

	public void ReleaseAttack()
	{
		if (Simon.keyCode == KeyEvent.VK_Z)
		{
			// Intended feature to allow advance 'tapping' gameplay, taken from
			// Touhou 2.
			//
			if (attackDelay > TAP_ATTACK_SET)
			{
				attackDelay = TAP_ATTACK_SET;
			}

			isAttacking = false;
		}
	}

	public void ReleaseMovement()
	{
		switch (Simon.keyCode)
		{
			case KeyEvent.VK_RIGHT:
				keyPressed.set(0, false);
				break;

			case KeyEvent.VK_UP:
				keyPressed.set(1, false);
				break;

			case KeyEvent.VK_LEFT:
				keyPressed.set(2, false);
				break;

			case KeyEvent.VK_DOWN:
				keyPressed.set(3, false);
				break;

			case KeyEvent.VK_SHIFT:
				isFocusing = false;
				break;
		}
	}

	public void StartAttack()
	{
		if (Simon.keyCode == KeyEvent.VK_Z)
		{
			isAttacking = true;
		}
	}

	public void StartMovement()
	{
		switch (Simon.keyCode)
		{
			case KeyEvent.VK_RIGHT:
				keyPressed.set(0, true);
				break;

			case KeyEvent.VK_UP:
				keyPressed.set(1, true);
				break;

			case KeyEvent.VK_LEFT:
				keyPressed.set(2, true);
				break;

			case KeyEvent.VK_DOWN:
				keyPressed.set(3, true);
				break;

			case KeyEvent.VK_SHIFT:
				isFocusing = true;
				break;
		}
	}

	public boolean TouchingEntity(IEntity entity)
	{
		if (entity instanceof AbstractEnemy)
		{
			return TouchingEnemy((AbstractEnemy) entity);
		}
		else if (entity instanceof AbstractBullet)
		{
			return TouchingBullet((AbstractBullet) entity);
		}
		else if (entity instanceof PowerUpOrb)
		{
			PowerUpOrb tempPowerUpOrb = (PowerUpOrb) entity;

			if (tempPowerUpOrb.CollisionDetect(this))
			{
				return true;
			}
		}
		else
		{
			throw new IllegalArgumentException(
					"What entity did you put in here?");
		}
		return false;
	}

	/**
	 * This method uses the Math.sin(...) and Math.cos(...) functions to
	 * determine whether or not the player is touching the enemy hitbox.<br>
	 * <br>
	 * <b>Postcondition:</b><br>
	 * (1) The <code>enemy</code> must not be modified.<br>
	 * (2) The <code>enemy</code> must be contained in the same
	 * <code>grid</code> as the <code>player</code>.<br>
	 * 
	 * @param enemy
	 *            The enemy to check if player is collidinng with it.
	 * @return Whether or not the player touches the enemy
	 */
	private boolean TouchingEnemy(final AbstractEnemy enemy)
	{
		if (enemy == null)
		{
			throw new IllegalArgumentException("The enemy cannot be null.");
		}
		else if (!(enemy.GetGrid().equals(GetGrid())))
		{
			throw new IllegalArgumentException(
					"The enemy must be contained in the same grid.");
		}
		// Obtains the center points of the player's hitbox
		//
		float tempPositionCenterOfHitboxX = (GetPosition().x + (GetImage().width / 2));
		float tempPositionCenterOfHitboxY = (GetPosition().y + (GetImage().height / 2));

		float tempPositionCenterOfEnemyX = enemy.GetPosition().x +
				(enemy.GetImage().width / 2);
		float tempPositionCenterOfEnemyY = enemy.GetPosition().y +
				(enemy.GetImage().height / 2);

		// If the temp position is at the Quadrant 1 side of the enemy
		//
		if (tempPositionCenterOfHitboxX < tempPositionCenterOfEnemyX &&
				tempPositionCenterOfHitboxY >= tempPositionCenterOfEnemyY)
		{
			for (int i = 89; i >= 0; --i)
			{
				double PosX = tempPositionCenterOfHitboxX + ((HITBOX_RADIUS + HITBOX_CIRCLE_BORDER_SIZE) *
						PlayerPIValues.ALL_COS_VALUES[i]);

				double PosY = tempPositionCenterOfHitboxY - ((HITBOX_RADIUS + HITBOX_CIRCLE_BORDER_SIZE) *
						PlayerPIValues.ALL_SIN_VALUES[i]);

				if (enemy.GetPosition().x <= PosX &&
						enemy.GetPosition().y + enemy.GetImage().height >= PosY)
				{
					return true;
				}
			}
		}
		// Quadrant 2
		//
		else if (tempPositionCenterOfHitboxX >= tempPositionCenterOfEnemyX &&
				tempPositionCenterOfHitboxY > tempPositionCenterOfEnemyY)
		{
			for (int i = 89; i >= 0; --i)
			{
				double PosX = tempPositionCenterOfHitboxX - ((HITBOX_RADIUS + HITBOX_CIRCLE_BORDER_SIZE) *
						PlayerPIValues.ALL_COS_VALUES[i]);
				double PosY = tempPositionCenterOfHitboxY - ((HITBOX_RADIUS + HITBOX_CIRCLE_BORDER_SIZE) *
						PlayerPIValues.ALL_SIN_VALUES[i]);

				if (enemy.GetPosition().x + enemy.GetImage().width >= PosX &&
						enemy.GetPosition().y + enemy.GetImage().height >= PosY)
				{
					return true;
				}
			}
		}
		// Quadrant 3
		//
		else if (tempPositionCenterOfHitboxX > tempPositionCenterOfEnemyX &&
				tempPositionCenterOfHitboxY <= tempPositionCenterOfEnemyY)
		{
			for (int i = 89; i >= 0; --i)
			{
				double PosX = tempPositionCenterOfHitboxX - ((HITBOX_RADIUS + HITBOX_CIRCLE_BORDER_SIZE) *
						PlayerPIValues.ALL_COS_VALUES[i]);
				double PosY = tempPositionCenterOfHitboxY + ((HITBOX_RADIUS + HITBOX_CIRCLE_BORDER_SIZE) *
						PlayerPIValues.ALL_SIN_VALUES[i]);

				if (enemy.GetPosition().x + enemy.GetImage().width >= PosX &&
						enemy.GetPosition().y <= PosY)
				{
					return true;
				}
			}
		}
		// Quadrant 4
		//
		else if (tempPositionCenterOfHitboxX <= tempPositionCenterOfEnemyX
				&& tempPositionCenterOfHitboxY < tempPositionCenterOfEnemyY)
		{
			for (int i = 89; i >= 0; --i)
			{
				double PosX = tempPositionCenterOfHitboxX + ((HITBOX_RADIUS + HITBOX_CIRCLE_BORDER_SIZE) *
						PlayerPIValues.ALL_COS_VALUES[i]);
				double PosY = tempPositionCenterOfHitboxY + ((HITBOX_RADIUS + HITBOX_CIRCLE_BORDER_SIZE) *
						PlayerPIValues.ALL_SIN_VALUES[i]);

				if (enemy.GetPosition().x <= PosX &&
						enemy.GetPosition().y <= PosY)
				{
					return true;
				}
			}
		}
		return false;
	}

	private boolean TouchingBullet(final AbstractBullet bullet)
	{
		if (bullet instanceof CircleBullet)
		{
			CircleBullet tempCicleBullet = (CircleBullet) bullet;

			PVector myVectorCentered = PVector.add(
					GetPosition(),
					new PVector(GetImage().width / 2,
							GetImage().height / 2));

			double sideX = Math
					.pow(tempCicleBullet.GetPosition().x - myVectorCentered.x,
							2);
			double sideY = Math
					.pow(tempCicleBullet.GetPosition().y - myVectorCentered.y,
							2);

			double hypo = Math.sqrt(sideX + sideY);

			if (hypo < (HITBOX_RADIUS /* + strokeSize */) + tempCicleBullet
					.GetRadius() + CircleBullet.STROKE_WEIGHT)
			{
				return true;
			}
		}
		return false;
	}

	public int GetNumBombs()
	{
		return this.numBombs;
	}

	public int GetNumLives()
	{
		return this.numLives;
	}

	public int GetPowerLevel()
	{
		return this.powerLevel;
	}

	public void SetNumBombs(final int value)
	{
		if (value < 0)
		{
			throw new IllegalArgumentException("Value cannot be less than 0.");
		}
		this.numBombs = value;
	}

	public void SetNumLives(final int value)
	{
		if (value < 0)
		{
			throw new IllegalArgumentException("Value cannot be less than 0.");
		}
		this.numLives = value;
	}

	public void SetPowerLevel(final int value)
	{
		if (value < 0)
		{
			throw new IllegalArgumentException("Value cannot be less than 0.");
		}
		this.powerLevel = value;
	}

	@Deprecated
	public void DeprecatedMovement1()
	{
		if (Simon.keyPressed)
		{
			if (Simon.keyCode == KeyEvent.VK_LEFT)
			{
				if (GetPosition().x + 18 > 0)
				{
					if (isFocusing)
					{
						SetPosition(new PVector(GetPosition().x - 2,
								GetPosition().y));
						// GetPosition().x -= 2;
					}
					else
					{
						SetPosition(new PVector(GetPosition().x - 3,
								GetPosition().y));
						// GetPosition().x -= 3;
					}
				}
			}

			else if (Simon.keyCode == KeyEvent.VK_RIGHT)
			{
				if (GetPosition().x + 18 + 13 < 438)
				{
					if (isFocusing)
					{
						SetPosition(new PVector(GetPosition().x + 2,
								GetPosition().y));
						// GetPosition().x += 2;
					}
					else
					{
						SetPosition(new PVector(GetPosition().x + 3,
								GetPosition().y));
						// GetPosition().x += 3;
					}
				}
			}

			if (Simon.keyCode == KeyEvent.VK_UP)
			{
				if (GetPosition().y + 20 > 0)
				{
					if (isFocusing)
					{
						SetPosition(new PVector(GetPosition().x,
								GetPosition().y - 2));
						// GetPosition().y -= 2;
					}
					else
					{
						SetPosition(new PVector(GetPosition().x,
								GetPosition().y - 3));
						// GetPosition().y -= 3;
					}
				}
			}

			else if (Simon.keyCode == KeyEvent.VK_DOWN)
			{
				if (GetPosition().y + 20 + 13 < 478)
				{
					if (isFocusing)
					{
						SetPosition(new PVector(GetPosition().x,
								GetPosition().y + 2));
						// GetPosition().y += 2;
					}
					else
					{
						SetPosition(new PVector(GetPosition().x,
								GetPosition().y + 3));
						// GetPosition().y += 3;
					}
				}
			}

			if (Simon.keyCode == KeyEvent.VK_SHIFT)
			{
				isFocusing = true;
			}
		}
	}

	@Deprecated
	public void DeprecatedMovement2()
	{
		if (Simon.keyPressed)
		{
			switch (Simon.keyCode)
			{
				case KeyEvent.VK_LEFT:
					keyPressed.set(0, true);
					if (GetPosition().x + 18 > 0)
					{
						if (isFocusing)
						{
							SetPosition(new PVector(GetPosition().x - 2,
									GetPosition().y));
							// GetPosition().x -= 2;
						}
						else
						{
							SetPosition(new PVector(GetPosition().x - 3,
									GetPosition().y));
							// GetPosition().x -= 3;
						}
					}
					break;

				case KeyEvent.VK_RIGHT:
					if (GetPosition().x + 18 + 13 < 438)
					{
						if (isFocusing)
						{
							SetPosition(new PVector(GetPosition().x + 2,
									GetPosition().y));
							// GetPosition().x += 2;
						}
						else
						{
							SetPosition(new PVector(GetPosition().x + 3,
									GetPosition().y));
							// GetPosition().x += 3;
						}
					}
					break;

				case KeyEvent.VK_UP:
					if (GetPosition().y + 20 > 0)
					{
						if (isFocusing)
						{
							SetPosition(new PVector(GetPosition().x,
									GetPosition().y - 2));
							// GetPosition().y -= 2;
						}
						else
						{
							SetPosition(new PVector(GetPosition().x,
									GetPosition().y - 3));
							// GetPosition().y -= 3;
						}
					}
					break;

				case KeyEvent.VK_DOWN:
					if (GetPosition().y + 20 + 13 < 478)
					{
						if (isFocusing)
						{
							SetPosition(new PVector(GetPosition().x,
									GetPosition().y + 2));
							// GetPosition().y += 2;
						}
						else
						{
							SetPosition(new PVector(GetPosition().x,
									GetPosition().y + 3));
							// GetPosition().y += 3;
						}
					}
					break;

				case KeyEvent.VK_SHIFT:
					isFocusing = true;
					break;
			}
		}
	}
};
