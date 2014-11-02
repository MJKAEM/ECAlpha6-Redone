package ecalpha6super;

import processing.core.PApplet;

/**
 * <pre>
 * Changelog:
 * 
 * Anything before and up to version ALPHA 1.6 are not recorded.
 * 
 * Version 1.6.SP (1.6.Super):
 * - Cleaned up code. You better not try to use the old code to do anything because you CAN'T.
 *   - To understand how I cleaned it, I used the style from Visual Studio, C# to be exact. It looks nice.
 *     Here are some of the ideas I took:
 *     - Capitalize the first letter of all names of methods and classes. The accepted standard for Java is to
 *       captitalize method and class names except the first letter.
 *     - Open up all braces. Never let the opening braces touch the parenthesis.
 *   - I also added semicolons at the end of class definitions because it is required in C/C++ and it tells
 *     me that I am at the end of a class file. (Actually, maybe it's all fluff. I just like how it looks.)
 * - Removed all useless numbers that didn't change. (They were similar to constants, but did were not named
 *   so explicitly.)
 * - Changed all unnecessary arrays into ArrayLists.
 * - Added public, private, and protected modifiers. (Processing automatically makes them public if not
 *   explicitly stated.)
 * - Added Exception handlers and some javadocs. Exception throwing is to make it easier on me for debugging.
 *   Exception throwing shouldn't happen at this point.
 * - Added new code that places entities into a grid.
 * - Added algorithms that legitimately determines the hitbox. (Originally, it was a bunch)
 * </pre>
 * 
 **/

public final class ECalpha6Super extends PApplet
{
	private static final long serialVersionUID = 8372818334622046550L;

	public static final String version = "1.6.SP";

	public static void main(String _args[])
	{
		PApplet.main(new String[]
		{ ecalpha6super.ECalpha6Super.class.getName() });
	}

	private MainMenuState mainMenuState;
	private PlayState playState;

	@Override
	public final void draw()
	{
		background(0);

		playState.Draw();
		playState.Update();
	}

	@Override
	public final void keyPressed()
	{
		playState.KeyPressed();
	}

	@Override
	public final void keyReleased()
	{
		playState.KeyReleased();
	}

	@Override
	public final void setup()
	{
		super.size(640, 480);

		PlayerPIValues.SetAllPIValues();

		ContentLoader.LoadContent(this);

		playState = new PlayState(this);
	}
};
