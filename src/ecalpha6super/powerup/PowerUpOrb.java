package ecalpha6super.powerup;

import ecalpha6super.ContentLoader;
import ecalpha6super.UserInterface;
import ecalpha6super.entity.AbstractEntity;
import ecalpha6super.entity.IEntity;
import processing.core.PApplet;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PowerUpOrb extends AbstractEntity
{
	private PowerUpNames MyName;

	private float speedDown;
	private int rotateCounter;

	public PowerUpOrb(PApplet p, PowerUpNames name)
	{
		super(p);

		this.MyName = name;

		// It throws itself upward once it spawns.
		//
		speedDown = -5;

		switch (name)
		{
		case Score:
			SetImage(ContentLoader.ScorePowerUpImage(0));
			break;
			
		case Power:
			SetImage(ContentLoader.PowerPowerUpImage(0));
			break;
			
		case Life:
			SetImage(ContentLoader.LifePowerUpImage(0));
			break;
			
		case Bomb:
			SetImage(ContentLoader.BombPowerUpImage(0));
			break;
		}
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

	@Override
	public final boolean CollisionDetect(IEntity entity)
	{
		// Top part is code for collision on left side of this entity.
		//
		// Bottom part is code for collision on right side of this entity.
		//
		if (GetPosition().x - (GetImage().width / 2) <= entity.GetPosition().x
				+ entity.GetImage().width
				&& GetPosition().x + (GetImage().width / 2) >= entity
						.GetPosition().x)
		{
			// Top part is code for collision on top side of this entity.
			//
			// Bottom part is code for collision on bottom side of this entity.
			//
			if (GetPosition().y - (GetImage().height / 2) <= entity
					.GetPosition().y + entity.GetImage().height
					&& GetPosition().y + (GetImage().height / 2) >= entity
							.GetPosition().y)
			{
				return true;
			}
		}
		return false;
	}

	public PowerUpNames GetName()
	{
		return this.MyName;
	}

	public int GetID()
	{
		return MyName.GetID();
	}

	public float GetSpeedDown()
	{
		return this.speedDown;
	}

	public void SetSpeedDown(float speedDown)
	{
		this.speedDown = speedDown;
	}
};
