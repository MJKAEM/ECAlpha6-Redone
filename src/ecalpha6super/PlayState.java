package ecalpha6super;

import processing.core.PApplet;
import processing.core.PVector;
import ecalpha6super.grid.Level;
import ecalpha6super.grid.TestLevel;

/**
 * <code>PlayState</code> is a <code>GameState</code> that is self explanatory.
 * This is based on my adventures in LWJGL's Slick2D library.
 * 
 * @author Martino
 * 
 */
public final class PlayState implements IGameState
{
	private final FramesCounter framesCounter;
	private final UserInterface userInterface;

	private final Level[] levelArray;
	private Player player;

	public PlayState(final PApplet p)
	{
		userInterface = new UserInterface(p);
		framesCounter = new FramesCounter(p);

		player = new Player(p);
		levelArray = new Level[6];

		levelArray[0] = new TestLevel(p);

		levelArray[0].Add(
				new PVector(player.GetPosition().x, player.GetPosition().y),
				player);
	}

	@Override
	public void Draw()
	{
		levelArray[0].Show();

		userInterface.Show();
		framesCounter.Show();
	}

	@Override
	public void KeyPressed()
	{
		player.KeyPressed();
	}

	@Override
	public void KeyReleased()
	{
		framesCounter.ExtendedAct();
		player.KeyReleased();
	}

	@Override
	public int ReturnID()
	{
		return 2;
	}

	@Override
	public void Update()
	{
		levelArray[0].Update();

		UserInterface.SetNumBombs(player.GetNumBombs());
		UserInterface.SetNumLives(player.GetNumLives());
		UserInterface.SetPowerLevel(player.GetPowerLevel());
	}
}
