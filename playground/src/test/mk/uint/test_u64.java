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
	public void run_tests()
	{
		add_sub();
	}
	public void run_program()
	{
		long before;
		long after;
		long diff;
		double seconds;
		long tests_count;
		before = java.lang.System.nanoTime();
		System.out.println("Start.");
		run_tests();
		System.out.println("Done.");
		after = java.lang.System.nanoTime();
		diff = after - before;
		seconds = ((double)(diff)) / (1.0 * 1000.0 * 1000.0 * 1000.0);
		tests_count = get_tests();
		System.out.format(java.util.Locale.ROOT, "Performed %d tests, took %f seconds.%n", tests_count, seconds);
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
