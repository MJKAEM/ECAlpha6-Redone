package ecalpha6super;

import java.util.ArrayList;

import processing.core.PApplet;
import ecalpha6super.entity.AbstractEnemy;
import ecalpha6super.entity.AbstractEntity;
import ecalpha6super.entity.IEntity;

/**
 * The laser is a weapon used by the player to attack enemies.
 * 
 * @author Martino
 * 
 */
public class Laser extends AbstractEntity
{
	public Laser(final PApplet p)
	{
		super(p);

		SetImage(ContentLoader.LaserImage(0));
	}

	@Override
	public void Show()
	{
		Simon.image(GetImage(), GetPosition().x, GetPosition().y);
	}

	@Override
	public void Act()
	{
		if (!GetDead())
		{
			SetPosition(GetPosition().x, GetPosition().y - 10);

			if (OffGrid())
			{
				SetDead(true);
				return;
			}
			// KillEnemy() must be last to prevent NullPointerException.
			//
			KillEnemy();
		}
		else
		{
			super.Act();
		}
	}

	protected void KillEnemy()
	{
		ArrayList<AbstractEnemy> tempEnemyList = GetGrid().GetEnemyList();

		for (int i = tempEnemyList.size() - 1; i >= 0; --i)
		{
			AbstractEnemy tempEntity = tempEnemyList.get(i);

			if (CollisionDetect(tempEntity))
			{
				SetDead(true);
				tempEntity.HurtMe();
				return;
			}
		}
	}

	protected boolean OffGrid()
	{
		if (GetPosition().y + GetImage().height <= 0)
		{
			return true;
		}
		return false;
	}

	@Deprecated
	protected void DepKillEnemy1()
	{
		ArrayList<AbstractEnemy> tempEnemyList = GetGrid().GetEnemyList();

		for (int i = tempEnemyList.size() - 1; i >= 0; --i)
		{
			IEntity tempEntity = tempEnemyList.get(i);

			// Prevents lasers from attacking players and other lasers.
			//
			if (tempEntity instanceof Player || tempEntity instanceof Laser)
			{
				continue;
			}
			if (CollisionDetect(tempEntity))
			{
				RemoveSelfFromGrid();
				((AbstractEnemy) tempEntity).HurtMe();
				return;
			}

		}
	}

	@Deprecated
	protected void DepKillEnemy2()
	{
		ArrayList<AbstractEnemy> tempEnemyList = GetGrid().GetEnemyList();

		for (int i = tempEnemyList.size() - 1; i >= 0; --i)
		{
			IEntity tempEntity = tempEnemyList.get(i);

			if (tempEntity instanceof AbstractEnemy
					&& CollisionDetect(tempEntity))
			{
				RemoveSelfFromGrid();
				((AbstractEnemy) tempEntity).HurtMe();
				return;
			}

		}
	}
}
