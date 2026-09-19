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
	private void u64()
	{
		test_u64 test_u64;
		test_u64 = new test_u64(this);
		test_u64.run_tests();
	}
	private void u128()
	{
		test_u128 test_u128;
		test_u128 = new test_u128(this);
		test_u128.run_tests();
	}
	private void u256()
	{
		test_u256 test_u256;
		test_u256 = new test_u256(this);
		test_u256.run_tests();
	}
	public void run_tests()
	{
		u16();
		u32();
		u64();
		u128();
		u256();
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
		java.lang.System.out.format(java.util.Locale.ROOT, "Performed %d tests, took %f seconds.%n", tests_count, seconds);
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
