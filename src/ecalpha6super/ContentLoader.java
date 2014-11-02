package ecalpha6super;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * ContentLoader loads all the content and keeps track of them. Please keep the
 * content in alphabetical order, by value name, not path name.
 * 
 * @author Martino
 * 
 */
public final class ContentLoader
{
	private static final PImage[] bossSpiritImage = new PImage[1];
	private static final PImage[] enemySpiritImage = new PImage[3];
	private static final PImage[] enemyGoatImage = new PImage[1];
	private static final PImage[] laserImage = new PImage[1];

	private static final PImage[] turtleImage = new PImage[1];
	private static final PImage[] uiImage = new PImage[5];

	// Power ups
	//
	private static final PImage[] bombPowerUpImage = new PImage[1];
	private static final PImage[] lifePowerUpImage = new PImage[1];
	private static final PImage[] powerPowerUpImage = new PImage[1];
	private static final PImage[] scorePowerUpImage = new PImage[1];

	private static final PFont[] fontArray = new PFont[2];

	public static final void LoadContent(PApplet p)
	{
		// Loads all the required images.
		//
		bossSpiritImage[0] = p.loadImage("Sprites/SpiritBoss.png");

		enemySpiritImage[0] = p.loadImage("Sprites/Spirit001/Spirit1.png");
		enemySpiritImage[1] = p.loadImage("Sprites/Spirit001/Spirit2.png");
		enemySpiritImage[2] = p.loadImage("Sprites/Spirit001/Spirit3.png");
		
		enemyGoatImage[0] = p.loadImage("Sprites/GoatSpirit/GoatSpirit.png");

		laserImage[0] = p.loadImage("Sprites/Laser.png");

		// Power up images
		//
		bombPowerUpImage[0] = p.loadImage("Sprites/BombOrb2.png");
		lifePowerUpImage[0] = p.loadImage("Sprites/LifeOrb2.png");
		powerPowerUpImage[0] = p.loadImage("Sprites/PowerOrb2.png");
		scorePowerUpImage[0] = p.loadImage("Sprites/ScoreOrb2.png");

		turtleImage[0] = p.loadImage("Sprites/Turtle.png");

		// Loads all the images relevant to the UI.
		//
		uiImage[0] = p.loadImage("Sprites/Stone.jpg");

		// Loads all the required fonts.
		//
		fontArray[0] = p.loadFont("Fonts/ArialMT-12.vlw");
		fontArray[1] = p.loadFont("Fonts/CenturyGothic-Bold-25.vlw");
	}

	public static final PFont GetFont(final int index)
	{
		if (index >= fontArray.length)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		if (fontArray[index] == null)
		{
			throw new NullPointerException();
		}
		return fontArray[index];
	}

	public static final PImage BombPowerUpImage(final int index)
	{
		if (index >= bombPowerUpImage.length)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		if (bombPowerUpImage[index] == null)
		{
			throw new NullPointerException();
		}
		return bombPowerUpImage[index];
	}

	public static final PImage BossSpiritImage(final int index)
	{
		if (index >= bossSpiritImage.length)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		if (bossSpiritImage[index] == null)
		{
			throw new NullPointerException();
		}
		return bossSpiritImage[index];
	}

	public static final PImage EnemySpiritImage(final int index)
	{
		if (index >= enemySpiritImage.length)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		if (enemySpiritImage[index] == null)
		{
			throw new NullPointerException();
		}
		return enemySpiritImage[index];
	}

	public static final PImage LaserImage(final int index)
	{
		if (index >= laserImage.length)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		if (laserImage[index] == null)
		{
			throw new NullPointerException();
		}
		return laserImage[index];
	}

	public static final PImage ScorePowerUpImage(final int index)
	{
		if (index >= scorePowerUpImage.length)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		if (scorePowerUpImage[index] == null)
		{
			throw new NullPointerException();
		}
		return scorePowerUpImage[index];
	}

	public static final PImage LifePowerUpImage(final int index)
	{
		if (index >= lifePowerUpImage.length)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		if (lifePowerUpImage[index] == null)
		{
			throw new NullPointerException();
		}
		return lifePowerUpImage[index];
	}

	public static final PImage PowerPowerUpImage(final int index)
	{
		if (index >= powerPowerUpImage.length)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		if (powerPowerUpImage[index] == null)
		{
			throw new NullPointerException();
		}
		return powerPowerUpImage[index];
	}

	public static final PImage TurtleImage(final int index)
	{
		if (index >= turtleImage.length)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		if (turtleImage[index] == null)
		{
			throw new NullPointerException();
		}
		return turtleImage[index];
	}

	public static final PImage UiImage(final int index)
	{
		if (index >= uiImage.length)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		if (uiImage[index] == null)
		{
			throw new NullPointerException();
		}
		return uiImage[index];
	}
};
