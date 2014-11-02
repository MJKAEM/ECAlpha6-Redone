package ecalpha6super.entity;

public enum RunDirection
{
	Right(0, 0),
	Up(1, Math.PI / 2),
	Left(2, Math.PI),
	Down(3, 3 * Math.PI / 2),
	Custom(-1, Math.PI * 2);

	private int value;
	private double angle;

	private RunDirection(int value, double angle)
	{
		this.value = value;
		this.angle = angle;
	}

	public double GetAngle()
	{
		return this.angle;
	}

	public RunDirection GetDirectionFromValue(int value)
	{
		switch (value)
		{
		case -1:
			return RunDirection.Custom;
		case 0:
			return RunDirection.Right;
		case 1:
			return RunDirection.Up;
		case 2:
			return RunDirection.Left;
		case 3:
			return RunDirection.Down;
		}
		throw new IllegalArgumentException("The value must be between 0 - 4.");
	}

	public int GetValue()
	{
		return this.value;
	}
};
