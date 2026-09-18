package test.mk.uint;
public class test_all extends base
{
	private void u16()
	{
		test_u16 test_u16;
		test_u16 = new test_u16(this);
		test_u16.run_tests();
	}
	private void u32()
	{
		test_u32 test_u32;
		test_u32 = new test_u32(this);
		test_u32.run_tests();
	}
	public void run_tests()
	{
		u16();
		u32();
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
	public test_all(mk.test.itest itest)
	{
		super(itest);
	}
	public test_all(base base)
	{
		super(base);
	}
	public static void main(String[] args)
	{
		mk.test.test itest;
		test_all test_all;
		itest = new mk.test.test();
		test_all = new test_all(itest);
		test_all.run_program();
	}
}
