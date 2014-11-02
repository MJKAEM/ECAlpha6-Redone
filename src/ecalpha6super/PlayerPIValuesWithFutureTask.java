package ecalpha6super;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class PlayerPIValuesWithFutureTask
{
	public static final double[] ALL_PI_VALUES = new double[90];
	public static double[] ALL_COS_VALUES = new double[90];
	public static double[] ALL_SIN_VALUES = new double[90];

	public static final void SetAllPIValues() throws InterruptedException,
			ExecutionException
	{
		if (ALL_PI_VALUES[0] != 0)
		{
			throw new RuntimeException("You cannot call this method twice.");
		}

		for (int i = ALL_PI_VALUES.length - 1; i >= 0; --i)
		{
			ALL_PI_VALUES[i] = Math.toRadians(i);
		}

		ExecutorService executor = Executors.newFixedThreadPool(1);

		FutureTask<double[]> CosTask = new FutureTask<double[]>(
				new Callable<double[]>()
				{
					public double[] call()
					{
						return GetCosValues();
					}
				});

		executor.execute(CosTask);

		FutureTask<double[]> SinTask = new FutureTask<double[]>(
				new Callable<double[]>()
				{
					public double[] call()
					{
						return GetSinValues();
					}
				});

		executor.execute(SinTask);

		while (!(CosTask.isDone() && SinTask.isDone()))
		{
			Thread.sleep(10);
		}

		ALL_COS_VALUES = CosTask.get();
		ALL_SIN_VALUES = SinTask.get();

		/*
		 * for (int i = ALL_PI_VALUES.length - 1; i >= 0; --i) {
		 * ALL_PI_VALUES[i] = Math.toRadians(i); }
		 * 
		 * for (int i = ALL_COS_VALUES.length - 1; i >= 0; --i) {
		 * ALL_COS_VALUES[i] = Math.cos(ALL_PI_VALUES[i]); }
		 * 
		 * for (int i = ALL_SIN_VALUES.length - 1; i >= 0; --i) {
		 * ALL_SIN_VALUES[i] = Math.sin(ALL_PI_VALUES[i]); }
		 */
	}

	private static double[] GetCosValues()
	{
		double[] tempCosValues = new double[90];

		for (int i = tempCosValues.length - 1; i >= 0; --i)
		{
			tempCosValues[i] = Math.cos(ALL_PI_VALUES[i]);
		}

		return tempCosValues;
	}

	private static double[] GetSinValues()
	{
		double[] tempSinValues = new double[90];

		for (int i = tempSinValues.length - 1; i >= 0; --i)
		{
			tempSinValues[i] = Math.sin(ALL_PI_VALUES[i]);
		}

		return tempSinValues;
	}
}
