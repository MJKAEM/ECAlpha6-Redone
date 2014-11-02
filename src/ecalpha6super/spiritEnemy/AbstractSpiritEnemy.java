package ecalpha6super.spiritEnemy;

import processing.core.PApplet;
import ecalpha6super.ContentLoader;
import ecalpha6super.entity.AbstractEnemy;

public abstract class AbstractSpiritEnemy extends AbstractEnemy
{
	/**
	 * {@value}
	 */
	public static final int ATTACK_DELAY_START_VALUE = (int) (60 * 0.75);

	protected boolean backwardAnimationCycle;
	protected int animationTimer;
	protected int curAnimation;

	private float movementSpeed;

	@Deprecated
	protected AbstractSpiritEnemy(PApplet p)
	{
		super(p);
		SetImage(ContentLoader.EnemySpiritImage(0));
	}

	protected AbstractSpiritEnemy(PApplet p, int hitpoints, float speed)
	{
		super(p, hitpoints);

		this.movementSpeed = speed;
		SetImage(ContentLoader.EnemySpiritImage(0));
	}

	protected void AnimateSprite()
	{
		SetImage(ContentLoader.EnemySpiritImage(curAnimation));
		/*
		 * switch(curAnimation) { case 0:
		 * SetImage(ContentLoader.EnemySpiritImage[0]); break;
		 * 
		 * case 1: break;
		 * 
		 * case 2: break; }
		 */
	}

	protected void AnimateTimer()
	{
		if (animationTimer >= 8)
		{
			if (curAnimation >= 2)
			{
				backwardAnimationCycle = true;
			}
			else if (curAnimation <= 0)
			{
				backwardAnimationCycle = false;
			}

			if (backwardAnimationCycle)
			{
				--curAnimation;
			}
			else
			{
				++curAnimation;
			}

			animationTimer = 0;

			AnimateSprite();
		}
		else
		{
			++animationTimer;
		}
	}

	public float GetMoveSpeed()
	{
		return this.movementSpeed;
	}

	public void SetMoveSpeed(float movementSpeed)
	{
		this.movementSpeed = movementSpeed;
	}
};
