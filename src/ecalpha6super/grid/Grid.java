package ecalpha6super.grid;

import java.util.ArrayList;

import processing.core.PVector;
import ecalpha6super.Laser;
import ecalpha6super.Player;
import ecalpha6super.bullet.AbstractBullet;
import ecalpha6super.entity.AbstractEnemy;
import ecalpha6super.entity.IEntity;
import ecalpha6super.powerup.AbstractPowerUp;
import ecalpha6super.powerup.PowerUpOrb;

/**
 * <code>Grid</code> is a two dimensional plane where entities reside. It is
 * based on the GridWorld Case Study for AP Computer Science A.
 * 
 * @author Art Margatroid
 * 
 */
public class Grid
{
	private Player player;
	private ArrayList<AbstractEnemy> EnemyList;
	private ArrayList<AbstractBullet> BulletList;
	private ArrayList<Laser> LaserList;
	private ArrayList<PowerUpOrb> PowerUpList;

	public Grid()
	{
		// The stupid thing keeps sending IndexOutOfBoundExceptions, so the
		// ArrayList must start with a capacity of 0 to prevent them.
		//
		this.EnemyList = new ArrayList<AbstractEnemy>(0);
		this.BulletList = new ArrayList<AbstractBullet>(0);
		this.LaserList = new ArrayList<Laser>(0);
		this.PowerUpList = new ArrayList<PowerUpOrb>(0);
	}

	/**
	 * Adds an entity to the grid.<br>
	 * <br>
	 * <b>Preconditions:</b> <br>
	 * (1) The <code>position</code> is not <code>null</code>.<br>
	 * (2) The <code>entity</code> is not <code>null</code>.
	 * 
	 * @param position
	 *            The position in (x, y) to place <code>entity</code>
	 * @param entity
	 *            The entity to add to the level
	 */
	public void Put(PVector position, IEntity entity)
	{
		// Preconditions
		//
		if (position == null)
		{
			throw new IllegalArgumentException("The position cannot be null");
		}
		if (entity == null)
		{
			throw new IllegalArgumentException("The entity cannot be null");
		}

		
		// Add the enities to their correct lists.
		//
		if (entity instanceof Laser)
		{
			LaserList.add((Laser) entity);
		}
		else if (entity instanceof AbstractBullet)
		{
			BulletList.add((AbstractBullet) entity);
		}
		else if (entity instanceof AbstractEnemy)
		{
			EnemyList.add((AbstractEnemy) entity);
		}
		else if (entity instanceof PowerUpOrb)
		{
			PowerUpList.add((PowerUpOrb) entity);
		}
		else if (entity instanceof Player)
		{
			player = (Player) entity;
		}

		else
		{
			throw new IllegalArgumentException("What entity did you add?");
		}
	}

	/**
	 * Removes an entity from the grid.<br>
	 * <br>
	 * 
	 * <b>Preconditions:</b> <br>
	 * (1) The value <code>entity</code> is not <code>null</code><br>
	 * (2) The value <code>entity</code> is contained in <code>EntityList</code><br>
	 * <br>
	 * 
	 * <b>Postcondition:</b> <br>
	 * (1) The size of <code>entity</code> is reduced by one.
	 * 
	 * @param entity
	 *            Entity to remove
	 */
	public void Remove(IEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException("The entity cannot be null.");
		}

		/*
		 * for (int i = EntityList.size() - 1; i >= 0; --i) { if
		 * (EntityList.get(i).equals(entity)) { EntityList.remove(i); return; }
		 * }
		 */

		if (entity instanceof Laser)
		{
			for (int i = LaserList.size() - 1; i >= 0; --i)
			{
				if (LaserList.get(i).equals(entity))
				{
					LaserList.remove(i);
					return;
				}
			}
		}
		else if (entity instanceof AbstractBullet)
		{
			for (int i = BulletList.size() - 1; i >= 0; --i)
			{
				if (BulletList.get(i).equals(entity))
				{
					BulletList.remove(i);
					return;
				}
			}
		}
		else if (entity instanceof AbstractEnemy)
		{
			for (int i = EnemyList.size() - 1; i >= 0; --i)
			{
				if (EnemyList.get(i).equals(entity))
				{
					EnemyList.remove(i);
					return;
				}
			}
		}
		else if (entity instanceof PowerUpOrb)
		{
			for (int i = PowerUpList.size() - 1; i >= 0; --i)
			{
				if (PowerUpList.get(i).equals(entity))
				{
					PowerUpList.remove(i);
					return;
				}
			}
		}
		else if (entity instanceof Player)
		{
			player = null;
			return;
		}
		throw new IllegalArgumentException(
				"The entity is not contained in this grid.");
	}
	
	public ArrayList<AbstractBullet> GetBulletList()
	{
		return this.BulletList;
	}

	public ArrayList<AbstractEnemy> GetEnemyList()
	{
		return this.EnemyList;
	}

	public ArrayList<Laser> GetLaserList()
	{
		return this.LaserList;
	}

	public ArrayList<PowerUpOrb> GetPowerUpList()
	{
		return this.PowerUpList;
	}

	public Player GetPlayer()
	{
		return this.player;
	}
};
