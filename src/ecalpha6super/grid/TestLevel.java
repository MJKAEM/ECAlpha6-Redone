package ecalpha6super.grid;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import ecalpha6super.entity.AbstractEnemy;
import ecalpha6super.entity.EnemyWave;
import ecalpha6super.entity.RunDirection;
import ecalpha6super.spiritEnemy.SpiritEnemy001;
import ecalpha6super.spiritEnemy.SpiritEnemy002;

public class TestLevel extends Level
{
	public TestLevel(PApplet p)
	{
		super(p);

		int tempArraySize = 4;

		// 1st wave of enemies
		//
		AbstractEnemy[] tempEnemyArray = new AbstractEnemy[tempArraySize];

		tempEnemyArray[0] = new SpiritEnemy001(p, 2, 2);
		tempEnemyArray[1] = new SpiritEnemy001(p, 2, 2);
		tempEnemyArray[2] = new SpiritEnemy002(p, 50, RunDirection.Left, 1, 2,
				2);
		tempEnemyArray[3] = new SpiritEnemy002(p, 50, RunDirection.Right, 1, 2,
				2);

		PVector[] tempVectorArray = new PVector[tempArraySize];

		tempVectorArray[0] = new PVector(25, -50);
		tempVectorArray[1] = new PVector(375, -50);
		tempVectorArray[2] = new PVector(75, -50);
		tempVectorArray[3] = new PVector(325, -50);

		EnemyWave tempEnemyWave = new EnemyWave(200);

		AddArrayOfEnemies(tempEnemyWave, tempVectorArray, tempEnemyArray);

		// 2nd wave of enemies
		//
		tempArraySize = 2;

		tempEnemyArray = new AbstractEnemy[tempArraySize];

		tempEnemyArray[0] = new SpiritEnemy001(p, 2, 2);
		tempEnemyArray[1] = new SpiritEnemy001(p, 2, 2);

		tempVectorArray = new PVector[tempArraySize];

		tempVectorArray[0] = new PVector(25, -50);
		tempVectorArray[1] = new PVector(375, -50);

		tempEnemyWave = new EnemyWave(60);

		AddArrayOfEnemies(tempEnemyWave, tempVectorArray, tempEnemyArray);

		// 3rd wave of enemies
		//
		tempArraySize = 2;

		tempEnemyArray = new AbstractEnemy[tempArraySize];

		tempEnemyArray[0] = new SpiritEnemy001(p, 2, 2);
		tempEnemyArray[1] = new SpiritEnemy001(p, 2, 2);

		tempVectorArray = new PVector[tempArraySize];

		tempVectorArray[0] = new PVector(100, -50);
		tempVectorArray[1] = new PVector(300, -50);

		tempEnemyWave = new EnemyWave(60);

		AddArrayOfEnemies(tempEnemyWave, tempVectorArray, tempEnemyArray);

		// 4th wave of enemies
		//
		tempArraySize = 2;

		tempEnemyArray = new AbstractEnemy[tempArraySize];

		tempEnemyArray[0] = new SpiritEnemy001(p, 2, 2);
		tempEnemyArray[1] = new SpiritEnemy001(p, 2, 2);

		tempVectorArray = new PVector[tempArraySize];

		tempVectorArray[0] = new PVector(175, -50);
		tempVectorArray[1] = new PVector(225, -50);

		tempEnemyWave = new EnemyWave(60);

		AddArrayOfEnemies(tempEnemyWave, tempVectorArray, tempEnemyArray);
	}

	/**
	 * This is a shortcut code to add enemies to the game.
	 * 
	 * Precondition: Assume <code>positionArray</code> and
	 * <code>enemyArray</code> are the same size.
	 * 
	 * @param positionArray
	 *            The array of PVectors
	 * @param enemyArray
	 *            The array of enemies
	 */
	private void AddArrayOfEnemies(EnemyWave enemyWave,
			final PVector[] positionArray,
			final AbstractEnemy[] enemyArray)
	{
		for (int i = enemyArray.length - 1; i >= 0; --i)
		{
			enemyWave.Add(positionArray[i], enemyArray[i]);
		}

		AddEnemyWave(enemyWave);
	}
};
