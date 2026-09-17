package mk.uint;
public class u128 extends java.lang.Object
{
	public int m_a;
	public int m_b;
	public int m_c;
	public int m_d;
	private static final long s_maxu32 = 0x00000000ffffffffl;
	private int get(int idx)
	{
		assert idx >= 0;
		assert idx < 4;
		int r = 0;
		switch(idx)
		{
			case 0: r = m_a; break;
			case 1: r = m_b; break;
			case 2: r = m_c; break;
			case 3: r = m_d; break;
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
			case 2: m_c = x; break;
			case 3: m_d = x; break;
		}
	}
	private static boolean equals(u128 a, u128 b)
	{
		assert a != null;
		assert b != null;
		boolean r;
		boolean eq;
		int n;
		int i;
		int int_a;
		int int_b;
		if(a == b)
		{
			r = true;
		}
		else
		{
			eq = true;
			n = 4;
			for(i = 0; i != n; ++i)
			{
				int_a = a.get(i);
				int_b = b.get(i);
				eq = int_a == int_b;
				if(!eq)
				{
					break;
				}
			}
			r = eq;
		}
		return r;
	}
	private static boolean equals(u128 a, java.lang.Object other)
	{
		assert a != null;
		java.lang.Object self;
		boolean r;
		u128 b;
		self = a;
		if(self == other)
		{
			r = true;
		}
		else
		{
			if(other instanceof u128)
			{
				b = (u128)other;
				r = equals(a, b);
			}
			else
			{
				r = false;
			}
		}
		return r;
	}
	private static void add(u128 r, u128 a, u128 b)
	{
		boolean carry;
		int n;
		int i;
		int int_a;
		int int_b;
		long lng_a;
		long lng_b;
		long lng_c;
		int sint;
		carry = false;
		n = 4;
		for(i = 0; i != n; ++i)
		{
			int_a = a.get(i);
			int_b = b.get(i);
			lng_a = u32.int_to_long(int_a);
			lng_b = u32.int_to_long(int_b);
			lng_c = lng_a + lng_b + (carry ? 1l : 0l);
			carry = lng_c > s_maxu32;
			lng_c &= s_maxu32;
			sint = u32.long_to_int(lng_c);
			r.set(i, sint);
		}
	}
	private static void sub(u128 r, u128 a, u128 b)
	{
		boolean carry;
		int n;
		int i;
		int int_a;
		int int_b;
		long lng_a;
		long lng_b;
		long lng_c;
		int sint;
		carry = false;
		n = 4;
		for(i = 0; i != n; ++i)
		{
			int_a = a.get(i);
			int_b = b.get(i);
			lng_a = u32.int_to_long(int_a);
			lng_b = u32.int_to_long(int_b);
			lng_c = lng_a - lng_b - (carry ? 1l : 0l);
			carry = lng_c < 0l;
			lng_c += s_maxu32;
			lng_c += 1;
			lng_c &= s_maxu32;
			sint = u32.long_to_int(lng_c);
			r.set(i, sint);
		}
	}
	private static void mul(u128 r, u128 a, u128 b)
	{
		assert r != null;
		assert a != null;
		assert b != null;
		assert r != a;
		assert r != b;
	}
	private java.lang.String as_string()
	{
		return "";
	}
	public static u128 make_random(java.util.Random rnd)
	{
		u128 r;
		int n;
		int i;
		int x;
		r = new u128();
		if(rnd == null)
		{
			rnd = new java.util.Random();
		}
		n = 4;
		for(i = 0; i != n; ++i)
		{
			x = rnd.nextInt();
			r.set(i, x);
		}
		return r;
	}
	public void add_mut(u128 x)
	{
		add(this, this, x);
	}
	public void sub_mut(u128 x)
	{
		sub(this, this, x);
	}
	public u128 add_new(u128 x)
	{
		u128 r = new u128();
		add(r, this, x);
		return r;
	}
	public u128 sub_new(u128 x)
	{
		u128 r = new u128();
		sub(r, this, x);
		return r;
	}
	@Override
	public java.lang.String toString()
	{
		return as_string();
	}
	@Override
	public boolean equals(java.lang.Object other)
	{
		return equals(this, other);
	}
}
