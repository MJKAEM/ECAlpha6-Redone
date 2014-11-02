package ecalpha6super.grid;

import processing.core.PApplet;
import processing.core.PVector;
import ecalpha6super.entity.AbstractEnemy;
import ecalpha6super.entity.EnemyWave;
import ecalpha6super.entity.RunDirection;
import ecalpha6super.spiritEnemy.SpiritEnemy001;
import ecalpha6super.spiritEnemy.SpiritEnemy002;

public class TestEnemyWave extends EnemyWave
{
	public TestEnemyWave(PApplet p, int timeBeforeNextSpawn)
	{
		super(timeBeforeNextSpawn);
		
		AbstractEnemy[] tempEnemyArray = new AbstractEnemy[4];

		// 1st wave of enemies
		//
		tempEnemyArray[0] = new SpiritEnemy001(p, 2, 2);
		tempEnemyArray[1] = new SpiritEnemy001(p, 2, 2);
		tempEnemyArray[2] = new SpiritEnemy002(p, 50, RunDirection.Left, 1, 2,
				2);
		tempEnemyArray[3] = new SpiritEnemy002(p, 50, RunDirection.Right, 1, 2,
				2);

		PVector[] tempVectorArray = new PVector[4];

		tempVectorArray[0] = new PVector(25, -50);
		tempVectorArray[1] = new PVector(375, -50);
		tempVectorArray[2] = new PVector(50, -50);
		tempVectorArray[3] = new PVector(350, -50);
		
		for(int i = tempEnemyArray.length - 1; i >= 0; --i)
		{
			Add(tempVectorArray[i], tempEnemyArray[i]);
		}	
	}
}
