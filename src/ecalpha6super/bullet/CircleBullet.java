package ecalpha6super.bullet;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PVector;
import ecalpha6super.grid.Grid;

public class CircleBullet extends AbstractBullet
{
	public final static int DEFAULT_RADIUS = 20;
	public final static int STROKE_WEIGHT = 2;

	private int radius;

	public CircleBullet(final PApplet p, final PVector destination,
			final Color color)
	{
		this(p, destination, color, DEFAULT_SPEED, DEFAULT_RADIUS);
	}

	public CircleBullet(final PApplet p, final PVector destination,
			final Color color, final int speed, final int radius)
	{
		super(p, destination, color, speed);

		this.radius = radius;
	}

	@Override
	public void Act()
	{
		if (!GetDead())
		{
			GoToDestination();
		}
		else
		{
			super.Act();
		}
	}

	public int GetRadius()
	{
		return this.radius;
	}

	@Override
	public void PutSelfInGrid(Grid grid, final PVector position)
	{
		super.PutSelfInGrid(grid, position);

		SetSpeed();
	}

	public void SetRadius(final int radius)
	{
		this.radius = radius;
	}

	@Override
	public void Show()
	{
		/*
		 * Simon.fill(GetColor().getRed(), GetColor().getGreen(), GetColor()
		 * .getBlue());
		 */
		Simon.fill(255);
		Simon.strokeWeight(STROKE_WEIGHT);
		Simon.stroke(GetColor().getRed(), GetColor().getGreen(), GetColor()
				.getBlue());
		Simon.ellipse(GetPosition().x, GetPosition().y, radius * 2, radius * 2);
	}
}
