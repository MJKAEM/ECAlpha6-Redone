package ecalpha6super.entity;

import processing.core.PImage;
import processing.core.PVector;
import ecalpha6super.grid.Grid;

public interface IEntity
{
	void Act();

	boolean CollisionDetect(IEntity entity);

	boolean GetDead();

	/**
	 * Gets the grid in which this actor is located.
	 * 
	 * @return The grid that contains the <code>entity</code>, or
	 *         <code>null</code> if the <code>entity</code> is not contained in
	 *         a grid
	 */
	Grid GetGrid();

	PImage GetImage();

	PVector GetPosition();

	/**
	 * Places the <code>entity</code> into the <code>grid</code>. I do NOT
	 * recommend using this to place an <code>entity</code> into a grid. Use
	 * <code>Level.Add()</code> instead.
	 * 
	 * @param grid
	 *            The grid to put the entity
	 * @param position
	 *            The position to place the entity
	 */
	void PutSelfInGrid(Grid grid, PVector position);

	/**
	 * Removes the <code>entity</code> from the <code>grid</code>. I recommend
	 * using this to remove an <code>entity</code> from a grid.<br/>
	 * <br/>
	 * <b>Important note! Do not use this function to make one entity remove
	 * another entity. (Ex: <code>Laser</code> removes
	 * <code>AbstractEnemy</code>). Instead, use <code>SetDead()</code> so that
	 * the entity removes itself when it calls <code>Act()</code>.</b>
	 */
	void RemoveSelfFromGrid();

	void SetDead(boolean isDead);

	void SetImage(PImage image);

	void SetPosition(double PosX, double PosY);

	void SetPosition(double PosX, float PosY);

	void SetPosition(float PosX, double PosY);

	void SetPosition(float PosX, float PosY);

	@Deprecated
	void SetPosition(PVector position);

	void Show();
}
