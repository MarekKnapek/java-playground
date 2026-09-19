package test.mk.uint;
public class test_u16 extends base
{
	private void roud_trip_long()
	{
		long n;
		long i;
		long a;
		short b;
		long c;
		boolean gud;
		n = get_count();
		for(i = 0l; i != n; ++i)
		{
			a = mk.uint.u16.make_random_long(get_rnd());
			b = mk.uint.u16.long_to_int(a);
			c = mk.uint.u16.int_to_long(b);
			gud = c == a;
			test(gud);
		}
	}
	private void roud_trip_int()
	{
		long n;
		long i;
		short a;
		long b;
		short c;
		boolean gud;
		n = get_count();
		for(i = 0l; i != n; ++i)
		{
			a = mk.uint.u16.make_random_int(get_rnd());
			b = mk.uint.u16.int_to_long(a);
			c = mk.uint.u16.long_to_int(b);
			gud = c == a;
			test(gud);
		}
	}
	private void add_sub_long()
	{
		long n;
		long i;
		long a;
		long b;
		long c;
		long d;
		boolean gud;
		n = get_count();
		for(i = 0l; i != n; ++i)
		{
			a = mk.uint.u16.make_random_long(get_rnd());
			b = mk.uint.u16.make_random_long(get_rnd());
			c = mk.uint.u16.add(a, b);
			d = mk.uint.u16.sub(c, a);
			gud = mk.uint.u16.eq(d, b);
			test(gud);
		}
	}
	private void add_sub_int()
	{
		long n;
		long i;
		short a;
		short b;
		short c;
		short d;
		boolean gud;
		n = get_count();
		for(i = 0l; i != n; ++i)
		{
			a = mk.uint.u16.make_random_int(get_rnd());
			b = mk.uint.u16.make_random_int(get_rnd());
			c = mk.uint.u16.add(a, b);
			d = mk.uint.u16.sub(c, a);
			gud = mk.uint.u16.eq(d, b);
			test(gud);
		}
	}
	private void mul_lo_long()
	{
		long n;
		long i;
		long a;
		long b;
		java.math.BigInteger ba;
		java.math.BigInteger bb;
		java.math.BigInteger bc;
		java.math.BigInteger bd;
		java.math.BigInteger be;
		long c;
		long d;
		boolean gud;
		n = get_count();
		for(i = 0l; i != n; ++i)
		{
			a = mk.uint.u16.make_random_long(get_rnd());
			b = mk.uint.u16.make_random_long(get_rnd());
			ba = java.math.BigInteger.valueOf(a);
			bb = java.math.BigInteger.valueOf(b);
			bc = ba.multiply(bb);
			bd = java.math.BigInteger.valueOf(mk.uint.constants.s_max_u16);
			be = bc.and(bd);
			c = be.longValue();
			d = mk.uint.u16.mul_lo(a, b);
			gud = d == c;
			test(gud);
		}
	}
	private void mul_hi_long()
	{
		long n;
		long i;
		long a;
		long b;
		java.math.BigInteger ba;
		java.math.BigInteger bb;
		java.math.BigInteger bc;
		java.math.BigInteger bd;
		java.math.BigInteger be;
		java.math.BigInteger bf;
		long c;
		long d;
		boolean gud;
		n = get_count();
		for(i = 0l; i != n; ++i)
		{
			a = mk.uint.u16.make_random_long(get_rnd());
			b = mk.uint.u16.make_random_long(get_rnd());
			ba = java.math.BigInteger.valueOf(a);
			bb = java.math.BigInteger.valueOf(b);
			bc = ba.multiply(bb);
			bd = bc.shiftRight(16);
			be = java.math.BigInteger.valueOf(mk.uint.constants.s_max_u16);
			bf = bd.and(be);
			c = bf.longValue();
			d = mk.uint.u16.mul_hi(a, b);
			gud = d == c;
			test(gud);
		}
	}
	public void run_tests()
	{
		roud_trip_long();
		roud_trip_int();
		add_sub_long();
		add_sub_int();
		mul_lo_long();
		mul_hi_long();
	}
	public void run_program()
	{
		long before;
		long after;
		long diff;
		double seconds;
		long tests_count;
		before = java.lang.System.nanoTime();
		java.lang.System.out.println("Start.");
		run_tests();
		java.lang.System.out.println("Done.");
		after = java.lang.System.nanoTime();
		diff = after - before;
		seconds = ((double)(diff)) / (1.0 * 1000.0 * 1000.0 * 1000.0);
		tests_count = get_tests();
		java.lang.System.out.format(java.util.Locale.ROOT, "Performed %d tests, took %f seconds.%n", tests_count, seconds);
	}
	public test_u16(mk.test.itest itest)
	{
		super(itest);
	}
	public test_u16(base base)
	{
		super(base);
	}
	public static void main(String[] args)
	{
		mk.test.test itest;
		test_u16 test_u16;
		itest = new mk.test.test();
		test_u16 = new test_u16(itest);
		test_u16.run_program();
	}
}
