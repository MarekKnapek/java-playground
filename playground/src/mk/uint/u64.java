package mk.uint;
public class u64
{
	static final char[] s_alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	private short m_a;
	private short m_b;
	private short m_c;
	private short m_d;
	private u64()
	{
		super();
	}
	private short get(int idx)
	{
		short r;
		assert idx >= 0;
		assert idx < 4;
		r = 0;
		switch(idx)
		{
			case 0: r = m_a; break;
			case 1: r = m_b; break;
			case 2: r = m_c; break;
			case 3: r = m_d; break;
		}
		return r;
	}
	private void set(int idx, short x)
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
	public u64 assign(u64 x)
	{
		int n;
		int i;
		assert x != null;
		n = 4;
		for(i = 0; i != n; ++i)
		{
			set(i, x.get(i));
		}
		return this;
	}
	public static u64 make_random(java.util.Random random)
	{
		java.util.Random rnd;
		u64 r;
		int n;
		int i;
		short x;
		rnd = random;
		if(rnd == null)
		{
			rnd = new java.util.Random();
		}
		r = new u64();
		n = 4;
		for(i = 0; i != n; ++i)
		{
			x = u16.make_random_int(rnd);
			r.set(i, x);
		}
		return r;
	}
	public static u64 make_from_ints(short a, short b, short c, short d)
	{
		u64 r;
		r = new u64();
		r.set(0, a);
		r.set(1, b);
		r.set(2, c);
		r.set(3, d);
		return r;
	}
	public u64 make_copy()
	{
		u64 r;
		r = new u64();
		r.assign(this);
		return r;
	}
	public static boolean eq(u64 a, u64 b)
	{
		boolean r;
		assert a != null;
		assert b != null;
		r = true;
		if(a != b)
		{
			r = r & u16.eq(a.get(0), b.get(0));
			r = r & u16.eq(a.get(1), b.get(1));
			r = r & u16.eq(a.get(2), b.get(2));
			r = r & u16.eq(a.get(3), b.get(3));
		}
		return r;
	}
	public boolean eq(u64 x)
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
				r = x instanceof u64;
			}
			if(r)
			{
				r = eq(((u64)(x)));
			}
		}
		return r;
	}
	public static void add(u64 r, u64 a, u64 b)
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
		n = 4;
		for(i = 0; i != n; ++i)
		{
			sa = a.get(i);
			sb = b.get(i);
			sr = u16.add(sa, sb, cf);
			cf = u16.would_overflow_add(sa, sb, cf);
			r.set(i, sr);
		}
	}
	public u64 add_mut(u64 x)
	{
		assert x != null;
		add(this, this, x);
		return this;
	}
	public u64 add_new(u64 x)
	{
		u64 neu;
		assert x != null;
		neu = make_copy();
		neu.add_mut(x);
		return neu;
	}
	public static void sub(u64 r, u64 a, u64 b)
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
		n = 4;
		for(i = 0; i != n; ++i)
		{
			sa = a.get(i);
			sb = b.get(i);
			sr = u16.sub(sa, sb, cf);
			cf = u16.would_overflow_sub(sa, sb, cf);
			r.set(i, sr);
		}
	}
	public u64 sub_mut(u64 x)
	{
		assert x != null;
		sub(this, this, x);
		return this;
	}
	public u64 sub_new(u64 x)
	{
		u64 neu;
		assert x != null;
		neu = make_copy();
		neu.sub_mut(x);
		return neu;
	}
	private static void mul_restrict(u64 r, u64 a, u64 b)
	{
		short sa;
		short sb;
		short ra;
		short rb;
		short ta;
		boolean cf;
		short tb;
		assert r != null;
		assert a != null;
		assert b != null;
		assert r != a;
		assert r != b;
		sa = a.get(0); sb = b.get(0); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); r.set(0, ra); r.set(1, rb);
		sa = a.get(1); sb = b.get(0); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); ta = ra; r.set(2, rb);
		sa = r.get(1); sb = ta; ra = u16.add(sa, sb); cf = u16.would_overflow_add(sa, sb); r.set(1, ra);
		if(cf){ r.set(2, u16.add(r.get(2), ((short)(1)))); }
		sa = a.get(0); sb = b.get(1); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); ta = ra; tb = rb;
		sa = r.get(1); sb = ta; ra = u16.add(sa, sb); cf = u16.would_overflow_add(sa, sb); r.set(1, ra);
		sa = r.get(2); sb = tb; ra = u16.add(sa, sb, cf); cf = u16.would_overflow_add(sa, sb, cf); r.set(2, ra);
		if(cf){ r.set(3, ((short)(1))); }else{ r.set(3, ((short)(0))); }
		sa = a.get(2); sb = b.get(0); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); ta = ra; tb = rb;
		sa = r.get(2); sb = ta; ra = u16.add(sa, sb); cf = u16.would_overflow_add(sa, sb); r.set(2, ra);
		sa = r.get(3); sb = tb; ra = u16.add(sa, sb, cf); r.set(3, ra);
		sa = a.get(1); sb = b.get(1); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); ta = ra; tb = rb;
		sa = r.get(2); sb = ta; ra = u16.add(sa, sb); cf = u16.would_overflow_add(sa, sb); r.set(2, ra);
		sa = r.get(3); sb = tb; ra = u16.add(sa, sb, cf); r.set(3, ra);
		sa = a.get(0); sb = b.get(2); ra = u16.mul_lo(sa, sb); rb = u16.mul_hi(sa, sb); ta = ra; tb = rb;
		sa = r.get(2); sb = ta; ra = u16.add(sa, sb); cf = u16.would_overflow_add(sa, sb); r.set(2, ra);
		sa = r.get(3); sb = tb; ra = u16.add(sa, sb, cf); r.set(3, ra);
		sa = a.get(3); sb = b.get(0); ra = u16.mul_lo(sa, sb); ta = ra;
		sa = r.get(3); sb = ta; ra = u16.add(sa, sb); r.set(3, ra);
		sa = a.get(2); sb = b.get(1); ra = u16.mul_lo(sa, sb); ta = ra;
		sa = r.get(3); sb = ta; ra = u16.add(sa, sb); r.set(3, ra);
		sa = a.get(1); sb = b.get(2); ra = u16.mul_lo(sa, sb); ta = ra;
		sa = r.get(3); sb = ta; ra = u16.add(sa, sb); r.set(3, ra);
		sa = a.get(0); sb = b.get(3); ra = u16.mul_lo(sa, sb); ta = ra;
		sa = r.get(3); sb = ta; ra = u16.add(sa, sb); r.set(3, ra);
	}
	private static void mul_alias(u64 r, u64 a, u64 b)
	{
		u64 rr;
		assert r != null;
		assert a != null;
		assert b != null;
		rr = new u64();
		mul_restrict(rr, a, b);
		r.assign(rr);
	}
	public static void mul(u64 r, u64 a, u64 b)
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
	public u64 mul_mut(u64 x)
	{
		assert x != null;
		mul(this, this, x);
		return this;
	}
	public u64 mul_new(u64 x)
	{
		u64 neu;
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
		buf = new char[64 / 8 * 2];
		idx = (64 / 8 * 2) - 1;
		n = 4;
		for(i = 0; i != n; ++i)
		{
			s = get(i);
			nibble = (s >> (0 * 4)) & 0xf; buf[idx] = s_alphabet[nibble]; --idx;
			nibble = (s >> (1 * 4)) & 0xf; buf[idx] = s_alphabet[nibble]; --idx;
			nibble = (s >> (2 * 4)) & 0xf; buf[idx] = s_alphabet[nibble]; --idx;
			nibble = (s >> (3 * 4)) & 0xf; buf[idx] = s_alphabet[nibble]; --idx;
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
