package ecalpha6super;

import processing.core.PApplet;
import processing.core.PImage;

public final class UserInterface
{
	private final PApplet Simon;

	private PImage stoneImage;

	private static int numLives, numBombs, numScore, powerLevel;

	public static int GetNumBombs()
	{
		return numBombs;
	}

	public static int GetNumLives()
	{
		return numLives;
	}

	public static int GetNumScore()
	{
		return numScore;
	}

	public static int GetPowerLevel()
	{
		return powerLevel;
	}

	public static void SetNumBombs(final int value)
	{
		if (value < 0)
		{
			throw new IllegalArgumentException("Value cannot be less than 0.");
		}
		numBombs = value;
	}

	public static void SetNumLives(final int value)
	{
		if (value < 0)
		{
			throw new IllegalArgumentException("Value cannot be less than 0.");
		}
		numLives = value;
	}

	public static void SetNumScore(final int value)
	{
		if (value < 0)
		{
			throw new IllegalArgumentException("Value cannot be less than 0.");
		}
		numScore = value;
	}

	public static void SetPowerLevel(final int value)
	{
		if (value < 0)
		{
			throw new IllegalArgumentException("Value cannot be less than 0.");
		}
		powerLevel = value;
	}

	public UserInterface(final PApplet p)
	{
		Simon = p;

		stoneImage = ContentLoader.UiImage(0);
	}

	public void Show()
	{
		// Draw the back layer of the user interface.
		//
		Simon.image(stoneImage, 440, 0, 200, 600);

		// Setup the code.
		Simon.fill(255);

		Simon.stroke(0);
		Simon.strokeWeight(1);

		Simon.textFont(ContentLoader.GetFont(1));

		// Draw the score on the screen
		//
		Simon.fill(0, 0, 255);

		if (numScore <= 9999999)
		{
			Simon.text("Score " + numScore, 455, 50);
		}
		else
		{
			numScore = 9999999;
			Simon.text("Score 9999999", 455, 50);
		}

		// Display lives
		//
		Simon.fill(255, 0, 0);
		Simon.text("Lives", 505, 150);
		Simon.fill(255);

		for (int i = 7; i >= 0; --i)
		{
			Simon.ellipse(455 + (i * 25), 180, 16, 16);
		}

		Simon.fill(255, 0, 0);

		// Displays the number of lives with circles. Do not add break except in
		// the last case. This is to allow all the ellipses to show up. Or you
		// can, but you'd have to manually write the code in.
		//
		switch (numLives)
		{
			case 8:
				Simon.ellipse(630, 180, 16, 16);
			case 7:
				Simon.ellipse(605, 180, 16, 16);
			case 6:
				Simon.ellipse(580, 180, 16, 16);
			case 5:
				Simon.ellipse(555, 180, 16, 16);
			case 4:
				Simon.ellipse(530, 180, 16, 16);
			case 3:
				Simon.ellipse(505, 180, 16, 16);
			case 2:
				Simon.ellipse(480, 180, 16, 16);
			case 1:
				Simon.ellipse(455, 180, 16, 16);
				break;
		}

		// Display bombs
		//
		Simon.fill(0, 255, 0);
		Simon.text("Bombs", 495, 225);
		Simon.fill(255);

		for (int i = 7; i >= 0; --i)
		{
			Simon.ellipse(455 + (i * 25), 255, 16, 16);
		}

		/*
		 * ellipse(455, 255, 16, 16); ellipse(480, 255, 16, 16); ellipse(505,
		 * 255, 16, 16); ellipse(530, 255, 16, 16); ellipse(555, 255, 16, 16);
		 * ellipse(580, 255, 16, 16); ellipse(605, 255, 16, 16); ellipse(630,
		 * 255, 16, 16);
		 */

		Simon.fill(0, 255, 0);

		// Displays the number of bombs with circles. Do not add break except in
		// the last case. This is to allow all the ellipses to show up. Or you
		// can, but you'd have to manually write the code in.
		//
		switch (numBombs)
		{
			case 8:
				Simon.ellipse(630, 255, 16, 16);
			case 7:
				Simon.ellipse(605, 255, 16, 16);
			case 6:
				Simon.ellipse(580, 255, 16, 16);
			case 5:
				Simon.ellipse(555, 255, 16, 16);
			case 4:
				Simon.ellipse(530, 255, 16, 16);
			case 3:
				Simon.ellipse(505, 255, 16, 16);
			case 2:
				Simon.ellipse(480, 255, 16, 16);
			case 1:
				Simon.ellipse(455, 255, 16, 16);
				break;
		}

		// Display power level
		//
		Simon.fill(255, 0, 255);
		Simon.text("Power : " + powerLevel, 455, 300);
	}
}