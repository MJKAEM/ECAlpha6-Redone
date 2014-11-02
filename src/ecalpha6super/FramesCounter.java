package ecalpha6super;

import java.awt.event.KeyEvent;

import processing.core.PApplet;

public final class FramesCounter
{
	private PApplet Simon;

	private int currentFrames, fpsTimer;
	private boolean isEnabled;

	public FramesCounter(final PApplet p)
	{
		this.Simon = p;
	}

	public FramesCounter(final PApplet p, final boolean isEnabled)
	{
		this(p);
		this.isEnabled = isEnabled;
	}
	
	public void Show()
	{
		if (isEnabled)
		{
			if (fpsTimer >= 5)
			{
				currentFrames = (int) (Simon.frameRate);
				fpsTimer = 0;
			}

			Simon.fill(255);

			Simon.textFont(ContentLoader.GetFont(1));

			Simon.text(ECalpha6Super.version, 475, 445);

			// Simon.text(fpsTimer,100,100); //debug
			Simon.text(currentFrames, 600, 475);

			++fpsTimer;
		}
	}

	public void Act()
	{
		isEnabled = !isEnabled;
		fpsTimer = 0;
	}

	public void ExtendedAct()
	{
		if (Simon.keyCode == KeyEvent.VK_F3)
		{
			Act();
		}
	}

	public boolean GetStatus()
	{
		return isEnabled;
	}

	public void SetStatus(final boolean value)
	{
		isEnabled = value;
	}
}
