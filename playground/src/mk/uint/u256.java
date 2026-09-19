package mk.uint;
public class u256
{
	private final int[] m_digits;
	private u256()
	{
		super();
		m_digits = new int[256 / 32];
	}
	private static u256 make_new()
	{
		u256 r;
		r = new u256();
		return r;
	}
	public static u256 make_from_digits(int[] digits)
	{
		u256 r;
		int n;
		int i;
		assert digits.length == 256 / 32;
		r = make_new();
		n = 256 / 32;
		for(i = 0; i != n; ++i)
		{
			r.m_digits[i] = digits[i];
		}
		return r;
	}
	public static u256 make_random(java.util.Random random)
	{
		java.util.Random rnd;
		u256 r;
		int n;
		int i;
		int x;
		rnd = random;
		if(rnd == null)
		{
			rnd = new java.util.Random();
		}
		r = make_new();
		n = 256 / 32;
		for(i = 0; i != n; ++i)
		{
			x = rnd.nextInt();
			r.m_digits[i] = x;
		}
		return r;
	}
	public u256 assign(u256 x)
	{
		int n;
		int i;
		assert x != null;
		n = 256 / 32;
		for(i = 0; i != n; ++i)
		{
			m_digits[i] = x.m_digits[i];
		}
		return this;
	}
	public u256 make_copy()
	{
		u256 r;
		r = make_new();
		r.assign(this);
		return r;
	}
	public static boolean eq(u256 a, u256 b)
	{
		boolean r;
		int n;
		int i;
		assert a != null;
		assert b != null;
		r = true;
		if(a != b)
		{
			n = 256 / 32;
			for(i = 0; i != n; ++i)
			{
				r = r & (a.m_digits[i] == b.m_digits[i]);
			}
		}
		return r;
	}
	public boolean eq(u256 x)
	{
		boolean r;
		assert x != null;
		r = eq(this, x);
		return r;
	}
	/*@Override*/
	public boolean equals(java.lang.Object x)
	{
		boolean r;
		r = this == x;
		if(!r)
		{
			r = true;
			if(r)
			{
				r = x != null;
			}
			if(r)
			{
				r = x instanceof u256;
			}
			if(r)
			{
				r = eq(((u256)(x)));
			}
		}
		return r;
	}
	public static void add(u256 r, u256 a, u256 b)
	{
		long cf;
		int n;
		int i;
		long la;
		long lb;
		long lr;
		assert r != null;
		assert a != null;
		assert b != null;
		cf = 0;
		n = 256 / 32;
		for(i = 0; i != n; ++i)
		{
			la = a.m_digits[i] & constants.s_max_u32;
			lb = b.m_digits[i] & constants.s_max_u32;
			lr = la + lb + cf;
			cf = lr >>> 32;
			r.m_digits[i] = ((int)(lr));
		}
	}
	public u256 add_mut(u256 x)
	{
		assert x != null;
		add(this, this, x);
		return this;
	}
	public u256 add_new(u256 x)
	{
		u256 neu;
		assert x != null;
		neu = make_copy();
		neu.add_mut(x);
		return neu;
	}
	public static void sub(u256 r, u256 a, u256 b)
	{
		long cf;
		int n;
		int i;
		long la;
		long lb;
		long lr;
		assert r != null;
		assert a != null;
		assert b != null;
		cf = 0;
		n = 256 / 32;
		for(i = 0; i != n; ++i)
		{
			la = a.m_digits[i] & constants.s_max_u32;
			lb = b.m_digits[i] & constants.s_max_u32;
			lr = la - lb + cf;
			cf = lr >> 32;
			r.m_digits[i] = ((int)(lr));
		}
	}
	public u256 sub_mut(u256 x)
	{
		assert x != null;
		sub(this, this, x);
		return this;
	}
	public u256 sub_new(u256 x)
	{
		u256 neu;
		assert x != null;
		neu = make_copy();
		neu.sub_mut(x);
		return neu;
	}
	private static void mul_restrict(u256 r, u256 a, u256 b)
	{
		long cf;
		int n;
		int i;
		long la;
		long lb;
		long lc;
		int m;
		int j;
		long lr;
		assert r != null;
		assert a != null;
		assert b != null;
		assert r != a;
		assert r != b;
		cf = 0;
		n = 256 / 32;
		for(i = 0; i != n; ++i)
		{
			la = a.m_digits[i] & constants.s_max_u32;
			lb = b.m_digits[0] & constants.s_max_u32;
			lc = la * lb + cf;
			r.m_digits[i] = ((int)(lc));
			cf = lc >>> 32;
		}
		for(i = 1; i != n; ++i)
		{
			cf = 0;
			m = n - i;
			for(j = 0; j != m; ++j)
			{
				la = a.m_digits[j] & constants.s_max_u32;
				lb = b.m_digits[i] & constants.s_max_u32;
				lr = r.m_digits[i + j] & constants.s_max_u32;
				lc = la * lb + lr + cf;
				r.m_digits[i + j] = ((int)(lc));
				cf = lc >>> 32;
			}
		}
	}
	private static void mul_alias(u256 r, u256 a, u256 b)
	{
		u256 rr;
		assert r != null;
		assert a != null;
		assert b != null;
		rr = make_new();
		mul_restrict(rr, a, b);
		r.assign(rr);
	}
	public static void mul(u256 r, u256 a, u256 b)
	{
		assert r != null;
		assert a != null;
		assert b != null;
		if(r == a || r == b)
		{
			mul_alias(r, a, b);
		}
		else
		{
			mul_restrict(r, a, b);
		}
	}
	public u256 mul_mut(u256 x)
	{
		assert x != null;
		mul(this, this, x);
		return this;
	}
	public u256 mul_new(u256 x)
	{
		u256 neu;
		assert x != null;
		neu = make_copy();
		neu.mul_mut(x);
		return neu;
	}
	public java.lang.String as_string_hex_full()
	{
		char buf[];
		int idx;
		int n;
		int i;
		long x;
		int nibble;
		java.lang.String r;
		buf = new char[256 / 8 * 2];
		idx = (256 / 8 * 2) - 1;
		n = 256 / 32;
		for(i = 0; i != n; ++i)
		{
			x = m_digits[i] & constants.s_max_u32;
			nibble = ((int)((x >> (0 * 4)) & 0xf)); buf[idx] = constants.s_alphabet[nibble]; --idx;
			nibble = ((int)((x >> (1 * 4)) & 0xf)); buf[idx] = constants.s_alphabet[nibble]; --idx;
			nibble = ((int)((x >> (2 * 4)) & 0xf)); buf[idx] = constants.s_alphabet[nibble]; --idx;
			nibble = ((int)((x >> (3 * 4)) & 0xf)); buf[idx] = constants.s_alphabet[nibble]; --idx;
			nibble = ((int)((x >> (4 * 4)) & 0xf)); buf[idx] = constants.s_alphabet[nibble]; --idx;
			nibble = ((int)((x >> (5 * 4)) & 0xf)); buf[idx] = constants.s_alphabet[nibble]; --idx;
			nibble = ((int)((x >> (6 * 4)) & 0xf)); buf[idx] = constants.s_alphabet[nibble]; --idx;
			nibble = ((int)((x >> (7 * 4)) & 0xf)); buf[idx] = constants.s_alphabet[nibble]; --idx;
		}
		assert idx == -1;
		r = new java.lang.String(buf);
		return r;
	}
	/*@Override*/
	public java.lang.String toString()
	{
		return "0x" + as_string_hex_full();
	}
}
