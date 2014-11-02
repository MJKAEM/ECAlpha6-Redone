package ecalpha6super.powerup;

import processing.core.PApplet;
import ecalpha6super.entity.AbstractEntity;

@Deprecated
public abstract class AbstractPowerUp extends AbstractEntity
{
	private float speedDown;
	private int rotateCounter;

	protected AbstractPowerUp(PApplet p)
	{
		super(p);

		// It throws itself upward once it spawns.
		//
		speedDown = -5;
	}

	@Override
	public void Act()
	{
		if (!GetDead())
		{
			SetPosition(GetPosition().x, GetPosition().y + speedDown);

			// The power up will go back down.
			//
			if (speedDown < 3)
			{
				speedDown += 0.1;
			}
		}
		else
		{
			super.Act();
		}
	}

	@Override
	public void Show()
	{
		Simon.translate(GetPosition().x, GetPosition().y);

		Simon.rotate((float) (Math.toRadians(rotateCounter)));

		Simon.image(GetImage(), -(GetImage().width / 2),
				-(GetImage().height / 2));

		Simon.rotate((float) -(Math.toRadians(rotateCounter)));

		Simon.translate(-GetPosition().x, -GetPosition().y);

		if (rotateCounter < 360)
		{
			rotateCounter += 4;
		}
		else
		{
			rotateCounter = 0;
		}
	}

	public abstract int GetID();

	public float GetSpeedDown()
	{
		return this.speedDown;
	}

	public void SetSpeedDown(float speedDown)
	{
		this.speedDown = speedDown;
	}
};
