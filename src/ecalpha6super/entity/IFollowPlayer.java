package ecalpha6super.entity;

import processing.core.PVector;

/**
 * This specific type of entity follows the player.
 * 
 * @author Martino
 * 
 */
public interface IFollowPlayer extends IEntity
{
	/**
	 * This method makes the bullet follow the player. Add it to
	 * <code>Act()</code>, or <code>Move()</code>.
	 * 
	 * @param position
	 *            The player's position
	 */
	void FollowPlayer(PVector position);
};
