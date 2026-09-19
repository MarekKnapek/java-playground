package test.mk.uint;
public class base implements mk.test.itest
{
	private common m_common;
	private mk.test.itest m_itest;
	public base(mk.test.itest itest)
	{
		super();
		m_common = new common();
		m_itest = itest;
	}
	public base(base other)
	{
		super();
		m_common = other.m_common;
		m_itest = other.m_itest;
	}
	public long get_count()
	{
		return m_common.get_count();
	}
	public java.util.Random get_rnd()
	{
		return m_common.get_rnd();
	}
	/*@Override*/
	public void test(boolean test)
	{
		m_itest.test(test);
	}
	/*@Override*/
	public long get_tests()
	{
		return m_itest.get_tests();
	}
};
