package test.mk.uint;
public class benchmark
{
	private static void run_test_they(java.util.Random random)
	{
		mk.uint.u256 a;
		mk.uint.u256 b;
		mk.uint.u256 c;
		java.math.BigInteger ba;
		java.math.BigInteger bb;
		java.math.BigInteger bc;
		java.math.BigInteger mask;
		long n;
		long i;
		a = mk.uint.u256.make_random(random);
		b = mk.uint.u256.make_random(random);
		c = mk.uint.u256.make_random(random);
		ba = new java.math.BigInteger(a.as_string_hex_full(), 16);
		bb = new java.math.BigInteger(b.as_string_hex_full(), 16);
		bc = new java.math.BigInteger(c.as_string_hex_full(), 16);
		mask = java.math.BigInteger.ONE.shiftLeft(256).subtract(java.math.BigInteger.ONE);
		n = 1l * 1000l * 1000l;
		for(i = 0; i != n; ++i)
		{
			ba = ba.multiply(bb).and(mask);
			ba = ba.add(bc).and(mask);
		}
		java.lang.System.out.println(ba.toString(16));
	}
	private static void run_test_my(java.util.Random random)
	{
		mk.uint.u256 a;
		mk.uint.u256 b;
		mk.uint.u256 c;
		long n;
		long i;
		a = mk.uint.u256.make_random(random);
		b = mk.uint.u256.make_random(random);
		c = mk.uint.u256.make_random(random);
		n = 1l * 1000l * 1000l;
		for(i = 0; i != n; ++i)
		{
			a.mul_mut(b);
			a.add_mut(c);
		}
		java.lang.System.out.println(a.as_string_hex_full());
	}
	private static void run_program_my()
	{
		long before;
		java.util.Random random;
		long after;
		long diff;
		double seconds;
		before = mk.utils.nano_time();
		java.lang.System.out.println("Start.");
		random = new java.util.Random(42);
		run_test_my(random);
		java.lang.System.out.println("Done.");
		after = mk.utils.nano_time();
		diff = after - before;
		seconds = ((double)(diff)) / (1.0 * 1000.0 * 1000.0 * 1000.0);
		java.lang.System.out.format(java.util.Locale.ROOT, "Took %f seconds.%n", seconds);
	}
	private static void run_program_they()
	{
		long before;
		java.util.Random random;
		long after;
		long diff;
		double seconds;
		before = mk.utils.nano_time();
		java.lang.System.out.println("Start.");
		random = new java.util.Random(42);
		run_test_they(random);
		java.lang.System.out.println("Done.");
		after = mk.utils.nano_time();
		diff = after - before;
		seconds = ((double)(diff)) / (1.0 * 1000.0 * 1000.0 * 1000.0);
		java.lang.System.out.format(java.util.Locale.ROOT, "Took %f seconds.%n", seconds);
	}
	public static void main(java.lang.String[] args)
	{
		run_program_my();
		run_program_they();
		run_program_my();
		run_program_they();
	}
}
