package ecalpha6super.grid;

import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PVector;
import ecalpha6super.Laser;
import ecalpha6super.bullet.AbstractBullet;
import ecalpha6super.entity.AbstractEnemy;
import ecalpha6super.entity.EnemyWave;
import ecalpha6super.entity.IEntity;
import ecalpha6super.powerup.PowerUpNames;
import ecalpha6super.powerup.PowerUpOrb;

public class Level
{
	protected PApplet Simon;

	private Grid MyGrid;
	private ArrayList<EnemyWave> EnemyWaveList;

	private int currentSpawnIndex, maxSpawnIndex;
	private int timeBeforeNextSpawn;

	public Level(PApplet p)
	{
		this.Simon = p;
		this.MyGrid = new Grid();
		this.EnemyWaveList = new ArrayList<EnemyWave>(0);
	}

	/**
	 * Adds an entity to the level. I recommend using this to put an
	 * <code>entity</code> into the <code>grid</code> because it calls
	 * <code>IEntity.PutSelfInGrid()</code>, which in turn calls
	 * <code>Grid.Put()</code>.<br>
	 * <br>
	 * 
	 * <b>Precondition:</b> <br>
	 * (1) The values <code>position</code> and <code>entity</code> are not
	 * <code>null</code>.
	 * 
	 * @param position
	 *            The position in (x, y) to place <code>entity</code>
	 * @param entity
	 *            The entity to add to the level
	 */
	public void Add(PVector position, IEntity entity)
	{
		entity.PutSelfInGrid(MyGrid, position);
	}

	public Grid GetGrid()
	{
		return this.MyGrid;
	}

	/**
	 * Removes an entity from the level.<br>
	 * <br>
	 * 
	 * <b>Precondition:</b> <br>
	 * (1) The value <code>entity</code> is not <code>null</code>.
	 * 
	 * @param entity
	 *            The entity to remove from the level
	 */
	public void Remove(IEntity entity)
	{
		entity.RemoveSelfFromGrid();
	}

	public void SetGrid(Grid grid)
	{
		if (grid == null)
		{
			throw new IllegalArgumentException();
		}
		this.MyGrid = grid;
	}

	/**
	 * Displays the level and its entities.
	 */
	public void Show()
	{
		ArrayList<AbstractBullet> tempBulletList = MyGrid.GetBulletList();
		ArrayList<AbstractEnemy> tempEnemyList = MyGrid.GetEnemyList();
		ArrayList<Laser> tempLaserList = MyGrid.GetLaserList();
		ArrayList<PowerUpOrb> tempPowerUpList = MyGrid.GetPowerUpList();

		for (int i = tempLaserList.size() - 1; i >= 0; --i)
		{
			try
			{
				tempLaserList.get(i).Show();
			}
			catch (IndexOutOfBoundsException e)
			{
				throw e;
			}
			catch (NullPointerException e)
			{
				throw e;
			}
		}

		for (int i = tempEnemyList.size() - 1; i >= 0; --i)
		{
			try
			{
				tempEnemyList.get(i).Show();
			}
			catch (IndexOutOfBoundsException e)
			{
				throw e;
			}
			/*
			 * catch (NullPointerException e) { throw e; }
			 */
		}

		MyGrid.GetPlayer().Show();

		for (int i = tempBulletList.size() - 1; i >= 0; --i)
		{
			try
			{
				tempBulletList.get(i).Show();
			}
			catch (IndexOutOfBoundsException e)
			{
				throw e;
			}
			catch (NullPointerException e)
			{
				throw e;
			}
		}

		for (int i = tempPowerUpList.size() - 1; i >= 0; --i)
		{
			try
			{
				tempPowerUpList.get(i).Show();
			}
			catch (IndexOutOfBoundsException e)
			{
				throw e;
			}
			catch (NullPointerException e)
			{
				throw e;
			}
		}
	}

	/**
	 * Updates the behaviors of all the entities.
	 */
	public void Update()
	{
		ArrayList<AbstractBullet> tempBulletList = MyGrid.GetBulletList();
		ArrayList<AbstractEnemy> tempEnemyList = MyGrid.GetEnemyList();
		ArrayList<Laser> tempLaserList = MyGrid.GetLaserList();
		ArrayList<PowerUpOrb> tempPowerUpList = MyGrid.GetPowerUpList();

		for (int i = tempEnemyList.size() - 1; i >= 0; --i)
		{
			try
			{
				tempEnemyList.get(i).Act();
			}
			catch (IndexOutOfBoundsException e)
			{
				throw e;
			}
			catch (NullPointerException e)
			{
				throw e;
			}
		}

		for (int i = tempBulletList.size() - 1; i >= 0; --i)
		{
			try
			{
				tempBulletList.get(i).Act();
			}
			catch (IndexOutOfBoundsException e)
			{
				throw e;
			}
			catch (NullPointerException e)
			{
				throw e;
			}
		}

		for (int i = tempLaserList.size() - 1; i >= 0; --i)
		{
			try
			{
				tempLaserList.get(i).Act();
			}
			catch (IndexOutOfBoundsException e)
			{
				throw e;
			}
			catch (NullPointerException e)
			{
				throw e;
			}
		}

		for (int i = tempPowerUpList.size() - 1; i >= 0; --i)
		{
			try
			{
				tempPowerUpList.get(i).Act();
			}
			catch (IndexOutOfBoundsException e)
			{
				throw e;
			}
			catch (NullPointerException e)
			{
				throw e;
			}
		}

		MyGrid.GetPlayer().Act();

		// If there are no more to spawn, don't execute any more spawn code.
		//
		if (currentSpawnIndex < maxSpawnIndex)
		{
			if (timeBeforeNextSpawn <= 0)
			{
				// Adds a temporary variable to make it easier to read.
				//
				EnemyWave tempCurrentWave = EnemyWaveList
						.get(currentSpawnIndex);

				// Puts the enemies into the grid, into their respective
				// positions, one by one.
				//
				for (int i = tempCurrentWave.GetEnemyList().size() - 1; i >= 0; --i)
				{
					tempCurrentWave.GetEnemyList().get(i).PutSelfInGrid(
							MyGrid,
							tempCurrentWave.GetEnemySpawnLocationList().get(i));
				}

				timeBeforeNextSpawn = EnemyWaveList.get(currentSpawnIndex)
						.GetTimeBeforeNextSpawn();

				++currentSpawnIndex;
			}
			else
			{
				--timeBeforeNextSpawn;
			}
		}
	}

	protected void AddEnemyWave(EnemyWave enemyWave)
	{
		if (enemyWave == null)
		{
			throw new IllegalArgumentException();
		}

		EnemyWaveList.add(enemyWave);

		++maxSpawnIndex;
	}

	public ArrayList<EnemyWave> GetEnemyWaveList()
	{
		return this.EnemyWaveList;
	}

	public void SetEnemyWaveList(ArrayList<EnemyWave> enemyWave)
	{
		this.EnemyWaveList = enemyWave;
	}
};
