package mk.uint;
public class u64
{
	private int m_a;
	private int m_b;
	private int get(int idx)
	{
		assert idx >= 0;
		assert idx < 4;
		int r = 0;
		switch(idx)
		{
			case 0: r = m_a; break;
			case 1: r = m_b; break;
		}
		return r;
	}
	private void set(int idx, int x)
	{
		assert idx >= 0;
		assert idx < 4;
		switch(idx)
		{
			case 0: m_a = x; break;
			case 1: m_b = x; break;
		}
	}
	public static u64 make_random(java.util.Random rnd)
	{
		u64 r;
		java.util.Random rrr;
		int n;
		int i;
		int x;
		r = new u64();
		assert r != null;
		rrr = rnd;
		if(rrr == null)
		{
			rrr = new java.util.Random();
		}
		assert rrr != null;
		n = 2;
		for(i = 0; i != n; ++i)
		{
			x = rnd.nextInt();
			r.set(i, x);
		}
		return r;
	}
	public static u64 make_from_ints(int a, int b)
	{
		u64 r;
		r = new u64();
		assert r != null;
		r.set(0, a);
		r.set(1, b);
		return r;
	}
}
