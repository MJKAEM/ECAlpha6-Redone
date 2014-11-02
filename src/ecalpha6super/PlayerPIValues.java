package ecalpha6super;

public final class PlayerPIValues
{
	public static final double[] ALL_PI_VALUES = new double[90];
	public static final double[] ALL_COS_VALUES = new double[90];
	public static final double[] ALL_SIN_VALUES = new double[90];

	public static final void SetAllPIValues()
	{
		if (ALL_PI_VALUES[0] != 0)
		{
			throw new RuntimeException("You cannot call this method twice.");
		}

		for (int i = ALL_PI_VALUES.length - 1; i >= 0; --i)
		{
			ALL_PI_VALUES[i] = Math.toRadians(i);
		}

		for (int i = ALL_COS_VALUES.length - 1; i >= 0; --i)
		{
			ALL_COS_VALUES[i] = Math.cos(ALL_PI_VALUES[i]);
		}

		for (int i = ALL_SIN_VALUES.length - 1; i >= 0; --i)
		{
			ALL_SIN_VALUES[i] = Math.sin(ALL_PI_VALUES[i]);
		}

	}
}
