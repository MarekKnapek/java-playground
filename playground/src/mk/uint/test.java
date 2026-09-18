package mk.uint;
public class test
{
	private java.util.Random m_rnd;
	private long m_count;
	private test()
	{
		m_rnd = new java.util.Random();
		m_count = 1l * 1000l * 1000l;
	}
	private void test_uint16_roud_trip()
	{
		long n;
		long i;
		short a;
		long b;
		int c;
		n = m_count;
		for(i = 0l; i != n; ++i)
		{
			a = ((short)(m_rnd.nextInt(((int)(Short.MAX_VALUE)) + 1)));
			b = mk.uint.u16.int_to_long(a);
			c = mk.uint.u16.long_to_int(b);
			assert c == a;
		}
	}
	private void test_uint128_roud_trip()
	{
		long n;
		long i;
		int a;
		long b;
		int c;
		n = m_count;
		for(i = 0l; i != n; ++i)
		{
			a = m_rnd.nextInt();
			b = mk.uint.u32.int_to_long(a);
			c = mk.uint.u32.long_to_int(b);
			assert c == a;
		}
	}
	private void test_uint128_add_sub()
	{
		long n;
		long i;
		u128 a;
		u128 b;
		u128 c;
		u128 d;
		boolean gud;
		n = m_count;
		for(i = 0l; i != n; ++i)
		{
			a = u128.make_random(m_rnd);
			b = u128.make_random(m_rnd);
			c = a.add_new(b);
			d = c.sub_new(b);
			gud = d.equals(a);
			assert gud;
		}
	}
	private void test_uint32_mul_lo_a()
	{
		long n;
		long i;
		int a;
		int b;
		int c;
		int d;
		boolean gud;
		n = m_count;
		for(i = 0l; i != n; ++i)
		{
			a = m_rnd.nextInt();
			b = m_rnd.nextInt();
			c = a * b;
			d = mk.uint.u32.mul_lo(a, b);
			gud = d == c;
			assert gud;
		}
	}
	private void test_uint32_mul_lo_b()
	{
		long n;
		long i;
		int a;
		int b;
		java.math.BigInteger ba;
		java.math.BigInteger bb;
		java.math.BigInteger bc;
		java.math.BigInteger bd;
		java.math.BigInteger be;
		int c;
		int d;
		boolean gud;
		n = m_count;
		for(i = 0l; i != n; ++i)
		{
			a = m_rnd.nextInt();
			b = m_rnd.nextInt();
			ba = java.math.BigInteger.valueOf(mk.uint.u32.int_to_long(a));
			bb = java.math.BigInteger.valueOf(mk.uint.u32.int_to_long(b));
			bc = ba.multiply(bb);
			bd = java.math.BigInteger.valueOf(0xffffffffl);
			be = bc.and(bd);
			c = mk.uint.u32.long_to_int(be.longValue());
			d = mk.uint.u32.mul_lo(a, b);
			gud = d == c;
			assert gud;
		}
	}
	private void test_uint32_mul_hi()
	{
		long n;
		long i;
		int a;
		int b;
		java.math.BigInteger ba;
		java.math.BigInteger bb;
		java.math.BigInteger bc;
		java.math.BigInteger bd;
		int c;
		int d;
		boolean gud;
		n = m_count;
		for(i = 0l; i != n; ++i)
		{
			a = m_rnd.nextInt();
			b = m_rnd.nextInt();
			ba = java.math.BigInteger.valueOf(mk.uint.u32.int_to_long(a));
			bb = java.math.BigInteger.valueOf(mk.uint.u32.int_to_long(b));
			bc = ba.multiply(bb);
			bd = bc.shiftRight(32);
			c = mk.uint.u32.long_to_int(bd.longValue());
			d = mk.uint.u32.mul_hi(a, b);
			gud = d == c;
			assert gud;
		}
	}
	private void run_tests()
	{
		test_uint16_roud_trip();
		test_uint128_roud_trip();
		test_uint128_add_sub();
		test_uint32_mul_lo_a();
		test_uint32_mul_lo_b();
		test_uint32_mul_hi();
	}
	private void run_program()
	{
		long before;
		long after;
		long diff;
		double seconds;
		before = java.lang.System.nanoTime();
		System.out.println("Start.");
		run_tests();
		System.out.println("Done.");
		after = java.lang.System.nanoTime();
		diff = after - before;
		seconds = ((double)(diff)) / (1.0 * 1000.0 * 1000.0 * 1000.0);
		System.out.format(java.util.Locale.ROOT, "Took %f seconds.%n", seconds);
	}
	public static void main(String[] args)
	{
		test tst = new test();
		tst.run_program();
	}
}
