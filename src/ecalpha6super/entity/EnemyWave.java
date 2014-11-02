package ecalpha6super.entity;

import java.util.ArrayList;

import processing.core.PVector;

public class EnemyWave
{
	private final ArrayList<AbstractEnemy> EnemyList;
	private final ArrayList<PVector> EnemySpawnLocationList;

	private final int timeBeforeNextSpawn;

	/**
	 * Creates a new EnemyWave that stores a list of enemies and their
	 * respective positions to spawn.
	 * 
	 * @param timeBeforeNextSpawn
	 *            The time before the next wave of enemies spawn.
	 */
	public EnemyWave(final int timeBeforeNextSpawn)
	{
		this.timeBeforeNextSpawn = timeBeforeNextSpawn;
		this.EnemyList = new ArrayList<AbstractEnemy>(0);
		this.EnemySpawnLocationList = new ArrayList<PVector>(0);
	}

	/**
	 * Adds and enemy to the current wave of enemies to spawn.<br>
	 * <br>
	 * <b>Preconditions:</b><br>
	 * (1) The <code>position</code> must not be <code>null</code>.<br>
	 * (2) The <code>enemy</code> must not be <code>null</code>.
	 * 
	 * @param position
	 *            The position in (x, y) coordinates to place the enemy.
	 * @param enemy
	 *            The enemy to add to the spawn wave.
	 */
	public void Add(final PVector position, final AbstractEnemy enemy)
	{
		EnemyList.add(enemy);
		EnemySpawnLocationList.add(position);
	}

	/**
	 * Replaces the current enemy at a specified index with the one specified.
	 * If there is no enemy at that position, it will simply set it to that
	 * index.<br>
	 * <br>
	 * <b>Do not use this method to add enemies.</b><br>
	 * <br>
	 * <b>Preconditions:</b><br>
	 * (1) The <code>index</code> must not be out of bounds.<br>
	 * (2) The <code>enemy</code> must not be <code>null</code>.
	 * 
	 * @param index
	 *            The index of the enemy to set.
	 * @param enemy
	 *            The enemy to set at the specified position.
	 */
	public void Set(final int index, AbstractEnemy enemy)
	{
		if (enemy == null)
		{
			throw new IllegalArgumentException(
					"The enemy cannot be null.");
		}
		if (index < 0 || index >= EnemyList.size())
		{
			throw new IllegalArgumentException(
					"The index would be out of bounds!");
		}
		EnemyList.set(index, enemy);
	}

	public ArrayList<AbstractEnemy> GetEnemyList()
	{
		return this.EnemyList;
	}

	public ArrayList<PVector> GetEnemySpawnLocationList()
	{
		return this.EnemySpawnLocationList;
	}

	public int GetTimeBeforeNextSpawn()
	{
		return this.timeBeforeNextSpawn;
	}
}
