package mk;
public class utils
{
	private static final int k_nano_time_not_known = 0;
	private static final int k_nano_time_present = 1;
	private static final int k_nano_time_missing = 2;
	private static int s_nano_time_status = k_nano_time_not_known;
	public static int min(int a, int b)
	{
		return b < a ? b : a;
	}
	public static int max(int a, int b)
	{
		return b < a ? a : b;
	}
	public static long nano_time()
	{
		long r;
		r = 0;
		switch(s_nano_time_status)
		{
			case k_nano_time_not_known:
			{
				try
				{
					r = java.lang.System.nanoTime();
					s_nano_time_status = k_nano_time_present;
				}
				catch(java.lang.NoSuchMethodError ex)
				{
					s_nano_time_status = k_nano_time_missing;
					r = nano_time();
				}
			}
			break;
			case k_nano_time_present:
			{
				r = java.lang.System.nanoTime();
			}
			break;
			case k_nano_time_missing:
			{
				r = java.lang.System.currentTimeMillis();
				r *= 1000l;
				r *= 1000l;
			}
			break;
		}
		return r;
	}
}
