package ecalpha6super.bullet;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PVector;
import ecalpha6super.entity.AbstractEntity;

public abstract class AbstractBullet extends AbstractEntity
{
	public final static int DEFAULT_SPEED = 2;

	private Color color;
	private PVector destination;

	private PVector moveSpeed;

	private float speed;

	@Deprecated
	protected AbstractBullet(final PApplet p)
	{
		super(p);
	}

	protected AbstractBullet(final PApplet p, final PVector destination, final Color color,
			int speed)
	{
		super(p);

		this.destination = destination;
		this.color = color;
		this.speed = speed;
	}

	@Override
	public void Act()
	{
		if (!GetDead())
		{
			if (GetPosition().x < -100 || GetPosition().x > Simon.width + 100)
			{
				SetDead(true);
			}
			else if (GetPosition().y < -100
					|| GetPosition().y > Simon.height + 100)
			{
				SetDead(true);
			}
		}
		super.Act();
	}

	protected Color GetColor()
	{
		return this.color;
	}

	protected void GoToDestination()
	{
		SetPosition(GetPosition().x + moveSpeed.x, GetPosition().y
				+ moveSpeed.y);
	}

	protected void SetColor(final Color color)
	{
		if (color == null)
		{
			throw new IllegalArgumentException("The color cannot be null.");
		}
		this.color = color;
	}

	public void SetDestination(final float x, final float y)
	{
		this.destination = new PVector(x, y);
	}

	protected void SetSpeed()
	{
		float tempPosX = destination.x - GetPosition().x;
		float tempPosY = destination.y - GetPosition().y;

		float squareX = tempPosX * tempPosX;
		float squareY = tempPosY * tempPosY;

		double hyp = Math.sqrt(squareX + squareY);

		// Provides the angles
		//
		double speedX = (tempPosX / hyp) * speed;
		double speedY = (tempPosY / hyp) * speed;

		moveSpeed = new PVector((float) speedX, (float) speedY);
	}
	
	/**
	 * Deprecated due to algorithms and less memory usage.
	 */
	@Deprecated
	protected void DeprecatedSetSpeed1()
	{
		PVector tempVector = PVector.sub(destination, GetPosition());

		double squareX = Math.pow(tempVector.x, 2);
		double squareY = Math.pow(tempVector.y, 2);

		double hyp = Math.sqrt(squareX + squareY);

		// Provides the angles
		//
		double speedX = (tempVector.x / hyp) * speed;
		double speedY = (tempVector.y / hyp) * speed;

		moveSpeed = new PVector((float) speedX, (float) speedY);
	}

	@Override
	public abstract void Show();
}
