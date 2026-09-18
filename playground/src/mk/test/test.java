package mk.test;
public class test implements itest
{
	private long m_tests;
	public test()
	{
		super();
		m_tests = 0;
	}
	@Override
	public void test(boolean test)
	{
		++m_tests;
		if(!test)
		{
			 java.lang.Runtime.getRuntime().exit(1);
		}
	}
	@Override
	public long get_tests()
	{
		return m_tests;
	}
}
