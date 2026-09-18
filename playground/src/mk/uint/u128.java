package mk.uint;
public class u128
{
	private short m_a;
	private short m_b;
	private short m_c;
	private short m_d;
	private short m_e;
	private short m_f;
	private short m_g;
	private short m_h;
	private u128()
	{
		super();
	}
	private short get(int idx)
	{
		short r;
		assert idx >= 0;
		assert idx < 8;
		r = 0;
		switch(idx)
		{
			case 0: r = m_a; break;
			case 1: r = m_b; break;
			case 2: r = m_c; break;
			case 3: r = m_d; break;
			case 4: r = m_e; break;
			case 5: r = m_f; break;
			case 6: r = m_g; break;
			case 7: r = m_h; break;
		}
		return r;
	}
	private void set(int idx, short x)
	{
		assert idx >= 0;
		assert idx < 8;
		switch(idx)
		{
			case 0: m_a = x; break;
			case 1: m_b = x; break;
			case 2: m_c = x; break;
			case 3: m_d = x; break;
			case 4: m_e = x; break;
			case 5: m_f = x; break;
			case 6: m_g = x; break;
			case 7: m_h = x; break;
		}
	}
	private static u128 make_new()
	{
		u128 r;
		r = new u128();
		return r;
	}
	public static u128 make_random(java.util.Random random)
	{
		java.util.Random rnd;
		u128 r;
		int n;
		int i;
		short x;
		rnd = random;
		if(rnd == null)
		{
			rnd = new java.util.Random();
		}
		r = make_new();
		n = 8;
		for(i = 0; i != n; ++i)
		{
			x = u16.make_random_int(rnd);
			r.set(i, x);
		}
		return r;
	}
	public static u128 make_from_ints(short a, short b, short c, short d, short e, short f, short g, short h)
	{
		u128 r;
		r = make_new();
		r.set(0, a);
		r.set(1, b);
		r.set(2, c);
		r.set(3, d);
		r.set(4, e);
		r.set(5, f);
		r.set(6, g);
		r.set(7, h);
		return r;
	}
	public u128 assign(u128 x)
	{
		int n;
		int i;
		assert x != null;
		n = 8;
		for(i = 0; i != n; ++i)
		{
			set(i, x.get(i));
		}
		return this;
	}
	public u128 make_copy()
	{
		u128 r;
		r = make_new();
		r.assign(this);
		return r;
	}
	public static boolean eq(u128 a, u128 b)
	{
		boolean r;
		int n;
		int i;
		assert a != null;
		assert b != null;
		r = true;
		if(a != b)
		{
			n = 8;
			for(i = 0; i != n; ++i)
			{
				r = r & u16.eq(a.get(i), b.get(i));
			}
		}
		return r;
	}
	public boolean eq(u128 x)
	{
		boolean r;
		assert x != null;
		r = eq(this, x);
		return r;
	}
	@Override
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
				r = x instanceof u128;
			}
			if(r)
			{
				r = eq(((u128)(x)));
			}
		}
		return r;
	}
	public static void add(u128 r, u128 a, u128 b)
	{
		boolean cf;
		int n;
		int i;
		short sa;
		short sb;
		short sr;
		assert r != null;
		assert a != null;
		assert b != null;
		cf = false;
		n = 8;
		for(i = 0; i != n; ++i)
		{
			sa = a.get(i);
			sb = b.get(i);
			sr = u16.add(sa, sb, cf);
			cf = u16.would_overflow_add(sa, sb, cf);
			r.set(i, sr);
		}
	}
	public u128 add_mut(u128 x)
	{
		assert x != null;
		add(this, this, x);
		return this;
	}
	public u128 add_new(u128 x)
	{
		u128 neu;
		assert x != null;
		neu = make_copy();
		neu.add_mut(x);
		return neu;
	}
	public static void sub(u128 r, u128 a, u128 b)
	{
		boolean cf;
		int n;
		int i;
		short sa;
		short sb;
		short sr;
		assert r != null;
		assert a != null;
		assert b != null;
		cf = false;
		n = 8;
		for(i = 0; i != n; ++i)
		{
			sa = a.get(i);
			sb = b.get(i);
			sr = u16.sub(sa, sb, cf);
			cf = u16.would_overflow_sub(sa, sb, cf);
			r.set(i, sr);
		}
	}
	public u128 sub_mut(u128 x)
	{
		assert x != null;
		sub(this, this, x);
		return this;
	}
	public u128 sub_new(u128 x)
	{
		u128 neu;
		assert x != null;
		neu = make_copy();
		neu.sub_mut(x);
		return neu;
	}
	private static void mul_restrict(u128 r, u128 a, u128 b)
	{
		short sa;
		short sb;
		short ra;
		short rb;
		short ta;
		boolean cf;
		short tb;
		int n;
		int j;
		int i;
		assert r != null;
		assert a != null;
		assert b != null;
		assert r != a;
		assert r != b;
		sa = a.get(0); sb = b.get(0); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); r.set(0, ra); r.set(1, rb);
		sa = a.get(1); sb = b.get(0); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); ta = ra; r.set(2, rb);
		sa = r.get(1); sb = ta; ra = u16.add(sa, sb); cf = u16.would_overflow_add(sa, sb); r.set(1, ra);
		r.set(2, u16.add(r.get(2), ((short)(cf ? 1 : 0))));
		sa = a.get(0); sb = b.get(1); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); ta = ra; tb = rb;
		sa = r.get(1); sb = ta; ra = u16.add(sa, sb); cf = u16.would_overflow_add(sa, sb); r.set(1, ra);
		sa = r.get(2); sb = tb; ra = u16.add(sa, sb, cf); cf = u16.would_overflow_add(sa, sb, cf); r.set(2, ra);
		r.set(3, ((short)(cf ? 1 : 0)));
		n = 8;
		for(j = 2; j != n - 2; ++j)
		{
			sa = a.get(j); sb = b.get(0); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); ta = ra; tb = rb;
			sa = r.get(j); sb = ta; ra = u16.add(sa, sb); cf = u16.would_overflow_add(sa, sb); r.set(j, ra);
			sa = r.get(j + 1); sb = tb; ra = u16.add(sa, sb, cf); cf = u16.would_overflow_add(sa, sb, cf); r.set(j + 1, ra);
			r.set(j + 2, ((short)(cf ? 1 : 0)));
			for(i = 1; i != j + 1; ++i)
			{
				sa = a.get(j - i); sb = b.get(i); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); ta = ra; tb = rb;
				sa = r.get(j); sb = ta; ra = u16.add(sa, sb); cf = u16.would_overflow_add(sa, sb); r.set(j, ra);
				sa = r.get(j + 1); sb = tb; ra = u16.add(sa, sb, cf); cf = u16.would_overflow_add(sa, sb, cf); r.set(j + 1, ra);
				r.set(j + 2, u16.add(r.get(j + 2), ((short)(cf ? 1 : 0))));
			}
		}
		for(i = 0; i != j + 1; ++i)
		{
			sa = a.get(j - i); sb = b.get(i); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); ta = ra; tb = rb;
			sa = r.get(j); sb = ta; ra = u16.add(sa, sb); cf = u16.would_overflow_add(sa, sb); r.set(j, ra);
			sa = r.get(j + 1); sb = tb; ra = u16.add(sa, sb, cf); r.set(j + 1, ra);
		}
		++j;
		for(i = 0; i != j + 1; ++i)
		{
			sa = a.get(j - i); sb = b.get(i); ra = u16.mul_lo(sa, sb); ta = ra;
			sa = r.get(j); sb = ta; ra = u16.add(sa, sb); r.set(j, ra);
		}
	}
	private static void mul_alias(u128 r, u128 a, u128 b)
	{
		u128 rr;
		assert r != null;
		assert a != null;
		assert b != null;
		rr = make_new();
		mul_restrict(rr, a, b);
		r.assign(rr);
	}
	public static void mul(u128 r, u128 a, u128 b)
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
	public u128 mul_mut(u128 x)
	{
		assert x != null;
		mul(this, this, x);
		return this;
	}
	public u128 mul_new(u128 x)
	{
		u128 neu;
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
		short s;
		int nibble;
		java.lang.String r;
		buf = new char[128 / 8 * 2];
		idx = (128 / 8 * 2) - 1;
		n = 8;
		for(i = 0; i != n; ++i)
		{
			s = get(i);
			nibble = (s >> (0 * 4)) & 0xf; buf[idx] = constants.s_alphabet[nibble]; --idx;
			nibble = (s >> (1 * 4)) & 0xf; buf[idx] = constants.s_alphabet[nibble]; --idx;
			nibble = (s >> (2 * 4)) & 0xf; buf[idx] = constants.s_alphabet[nibble]; --idx;
			nibble = (s >> (3 * 4)) & 0xf; buf[idx] = constants.s_alphabet[nibble]; --idx;
		}
		assert idx == -1;
		r = new java.lang.String(buf);
		return r;
	}
	@Override
	public java.lang.String toString()
	{
		return "0x" + as_string_hex_full();
	}
}
