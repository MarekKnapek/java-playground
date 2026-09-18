package test.mk.uint;
public class common
{
	private java.util.Random m_rnd;
	private long m_count;
	public common()
	{
		super();
		m_rnd = new java.util.Random();
		m_count = 1l * 1000l * 1000l;
	}
	public long get_count()
	{
		return m_count;
	}
	public java.util.Random get_rnd()
	{
		return m_rnd;
	}
}
