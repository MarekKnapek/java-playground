package test.mk.uint;
public class test_u64 extends base
{
	private void add_sub()
	{
		long n;
		long i;
		mk.uint.u64 a;
		mk.uint.u64 b;
		mk.uint.u64 c;
		mk.uint.u64 d;
		boolean gud;
		n = get_count();
		for(i = 0l; i != n; ++i)
		{
			a = mk.uint.u64.make_random(get_rnd());
			b = mk.uint.u64.make_random(get_rnd());
			c = a.add_new(b);
			d = c.sub_new(a);
			gud = d.eq(b);
			test(gud);
		}
	}
	private void add()
	{
		long n;
		long i;
		mk.uint.u64 a;
		mk.uint.u64 b;
		mk.uint.u64 c;
		java.math.BigInteger ba;
		java.math.BigInteger bb;
		java.math.BigInteger bc;
		java.math.BigInteger bd;
		boolean gud;
		n = get_count();
		for(i = 0l; i != n; ++i)
		{
			a = mk.uint.u64.make_random(get_rnd());
			b = mk.uint.u64.make_random(get_rnd());
			c = a.add_new(b);
			ba = new java.math.BigInteger(a.as_string_hex_full(), 16);
			bb = new java.math.BigInteger(b.as_string_hex_full(), 16);
			bc = new java.math.BigInteger(c.as_string_hex_full(), 16);
			bd = ba.add(bb).and(java.math.BigInteger.valueOf(1).shiftLeft(64).subtract(java.math.BigInteger.valueOf(1)));
			gud = bc.equals(bd);
			test(gud);
		}
	}
	private void sub()
	{
		long n;
		long i;
		mk.uint.u64 a;
		mk.uint.u64 b;
		mk.uint.u64 c;
		java.math.BigInteger ba;
		java.math.BigInteger bb;
		java.math.BigInteger bc;
		java.math.BigInteger bd;
		boolean gud;
		n = get_count();
		for(i = 0l; i != n; ++i)
		{
			a = mk.uint.u64.make_random(get_rnd());
			b = mk.uint.u64.make_random(get_rnd());
			c = a.sub_new(b);
			ba = new java.math.BigInteger(a.as_string_hex_full(), 16);
			bb = new java.math.BigInteger(b.as_string_hex_full(), 16);
			bc = new java.math.BigInteger(c.as_string_hex_full(), 16);
			bd = ba.subtract(bb).add(java.math.BigInteger.valueOf(1).shiftLeft(64)).and(java.math.BigInteger.valueOf(1).shiftLeft(64).subtract(java.math.BigInteger.valueOf(1)));
			gud = bc.equals(bd);
			test(gud);
		}
	}
	private void mul()
	{
		long n;
		long i;
		mk.uint.u64 a;
		mk.uint.u64 b;
		mk.uint.u64 c;
		java.math.BigInteger ba;
		java.math.BigInteger bb;
		java.math.BigInteger bc;
		java.math.BigInteger bd;
		boolean gud;
		n = get_count();
		for(i = 0l; i != n; ++i)
		{
			a = mk.uint.u64.make_random(get_rnd());
			b = mk.uint.u64.make_random(get_rnd());
			c = a.mul_new(b);
			ba = new java.math.BigInteger(a.as_string_hex_full(), 16);
			bb = new java.math.BigInteger(b.as_string_hex_full(), 16);
			bc = new java.math.BigInteger(c.as_string_hex_full(), 16);
			bd = ba.multiply(bb).and(java.math.BigInteger.valueOf(1).shiftLeft(64).subtract(java.math.BigInteger.valueOf(1)));
			gud = bc.equals(bd);
			test(gud);
		}
	}
	public void run_tests()
	{
		add_sub();
		add();
		sub();
		mul();
	}
	public void run_program()
	{
		long before;
		long after;
		long diff;
		double seconds;
		long tests_count;
		before = mk.utils.nano_time();
		java.lang.System.out.println("Start.");
		run_tests();
		java.lang.System.out.println("Done.");
		after = mk.utils.nano_time();
		diff = after - before;
		seconds = ((double)(diff)) / (1.0 * 1000.0 * 1000.0 * 1000.0);
		tests_count = get_tests();
		java.lang.System.out.println("Performed " + tests_count + " tests, took " + seconds + " seconds.");
	}
	public test_u64(mk.test.itest itest)
	{
		super(itest);
	}
	public test_u64(base base)
	{
		super(base);
	}
	public static void main(String[] args)
	{
		mk.test.test itest;
		test_u64 test_u64;
		itest = new mk.test.test();
		test_u64 = new test_u64(itest);
		test_u64.run_program();
	}
}
