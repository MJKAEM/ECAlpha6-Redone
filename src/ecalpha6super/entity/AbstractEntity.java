package ecalpha6super.entity;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import ecalpha6super.grid.Grid;

public abstract class AbstractEntity implements IEntity
{
	protected final PApplet Simon;

	private Grid myGrid;
	private boolean isDead;

	/**
	 * <b>WARNING: Do not use any constructor or <code>SetPosition()</code> to
	 * initialize this value.</b>
	 */
	private PVector position;
	private PImage spriteImage;

	protected AbstractEntity(PApplet p)
	{
		this.Simon = p;
	}

	/**
	 * This implementation of Act removes an entity from the grid when it acts.
	 * To prevent unnecessary if-statements, make sure you write
	 * <code>super.Act()</code> by itself. Here is the code to this method:
	 * 
	 * <pre>
	 * if (isDead)
	 * {
	 * 	RemoveSelfFromGrid();
	 * }
	 * </pre>
	 * 
	 * {@code text}
	 */
	@Override
	public void Act()
	{
		// This is done to prevent IndexOutOfRangeExceptions by having it remove
		// itself on the next update.
		//
		if (isDead)
		{
			RemoveSelfFromGrid();
		}
	}

	@Override
	public boolean CollisionDetect(IEntity entity)
	{
		// Top part is code for collision on left side of this entity.
		//
		// Bottom part is code for collision on right side of this entity.
		//
		if (position.x <= entity.GetPosition().x + entity.GetImage().width
				&& position.x + spriteImage.width >= entity.GetPosition().x)
		{
			// Top part is code for collision on top side of this entity.
			//
			// Bottom part is code for collision on bottom side of this entity.
			//
			if (position.y <= entity.GetPosition().y + entity.GetImage().height
					&& position.y + spriteImage.height >= entity.GetPosition().y)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean GetDead()
	{
		return this.isDead;
	}

	@Override
	public final Grid GetGrid()
	{
		return this.myGrid;
	}

	@Override
	public PImage GetImage()
	{
		return this.spriteImage;
	}

	@Override
	public final PVector GetPosition()
	{
		return this.position;
	}

	@Override
	public void PutSelfInGrid(Grid grid, PVector position)
	{
		try
		{
			grid.Put(position, this);
			this.myGrid = grid;
			this.position = position;
		}
		catch (NullPointerException e)
		{
			throw e;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public final void RemoveSelfFromGrid()
	{
		try
		{
			myGrid.Remove(this);
			this.myGrid = null;
			this.position = null;
		}
		catch (NullPointerException e)
		{
			throw e;
		}
	}

	@Override
	public void SetDead(boolean isDead)
	{
		this.isDead = isDead;
	}

	@Override
	public void SetImage(PImage image)
	{
		this.spriteImage = image;
	}

	@Override
	public final void SetPosition(double PosX, double PosY)
	{
		this.position = new PVector((float) PosX, (float) PosY);
	}

	@Override
	public final void SetPosition(double PosX, float PosY)
	{
		this.position = new PVector((float) PosX, PosY);
	}

	@Override
	public final void SetPosition(float PosX, double PosY)
	{
		this.position = new PVector(PosX, (float) PosY);
	}

	@Override
	public final void SetPosition(float PosX, float PosY)
	{
		this.position = new PVector(PosX, PosY);
	}

	@Override
	@Deprecated
	public final void SetPosition(PVector position)
	{
		this.position = position;
	}

	@Override
	public abstract void Show();
};
