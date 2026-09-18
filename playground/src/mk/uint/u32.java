package mk.uint;
public class u32
{
	public static long int_to_long(int x)
	{
		long r;
		if(x >= 0)
		{
			r = x;
		}
		else
		{
			r = constants.s_max_u32;
			r += x;
			r += 1;
		}
		assert r >= constants.s_min_u32;
		assert r <= constants.s_max_u32;
		return r;
	}
	public static int long_to_int(long x)
	{
		int r;
		long t;
		assert x >= constants.s_min_u32;
		assert x <= constants.s_max_u32;
		if(x <= constants.s_max_s32)
		{
			assert x >= constants.s_min_s32;
			assert x <= constants.s_max_s32;
			r = ((int)(x));
		}
		else
		{
			t = x;
			t -= 1;
			t -= constants.s_max_u32;
			assert t >= constants.s_min_s32;
			assert t <= constants.s_max_s32;
			r = ((int)(t));
		}
		return r;
	}
	public static int make_random_int(java.util.Random random)
	{
		java.util.Random rnd;
		int r;
		rnd = random;
		if(rnd == null)
		{
			rnd = new java.util.Random();
		}
		r = rnd.nextInt();
		assert r >= constants.s_min_s32;
		assert r <= constants.s_max_s32;
		return r;
	}
	public static long make_random_long(java.util.Random random)
	{
		return int_to_long(make_random_int(random));
	}
	public static boolean eq(long a, long b)
	{
		boolean r;
		assert a >= constants.s_min_u32;
		assert a <= constants.s_max_u32;
		assert b >= constants.s_min_u32;
		assert b <= constants.s_max_u32;
		r = a == b;
		return r;
	}
	public static boolean eq(int a, int b)
	{
		return eq(int_to_long(a), int_to_long(b));
	}
	public static long get_lo(long x)
	{
		long r;
		assert x >= constants.s_min_u32;
		assert x <= constants.s_max_u32;
		r = (x >> (0 * 16)) & 0xffffl;
		assert r >= constants.s_min_u16;
		assert r <= constants.s_max_u16;
		return r;
	}
	public static int get_lo(int x)
	{
		return long_to_int(get_lo(int_to_long(x)));
	}
	public static long get_hi(long x)
	{
		long r;
		assert x >= constants.s_min_u32;
		assert x <= constants.s_max_u32;
		r = (x >> (1 * 16)) & 0xffffl;
		assert r >= constants.s_min_u16;
		assert r <= constants.s_max_u16;
		return r;
	}
	public static int get_hi(int x)
	{
		return long_to_int(get_hi(int_to_long(x)));
	}
	public static long combine_hi_lo_long(long x_hi, long x_lo)
	{
		long r;
		assert x_hi >= constants.s_min_u16;
		assert x_hi <= constants.s_max_u16;
		assert x_lo >= constants.s_min_u16;
		assert x_lo <= constants.s_max_u16;
		r =
			(x_hi << (1 * 16)) |
			(x_lo << (0 * 16)) |
		0;
		assert r >= constants.s_min_u32;
		assert r <= constants.s_max_u32;
		return r;
	}
	public static int combine_hi_lo_int(long x_hi, long x_lo)
	{
		return long_to_int(combine_hi_lo_long(x_hi, x_lo));
	}
	public static long add(long a, long b)
	{
		long r;
		assert a >= constants.s_min_u32;
		assert a <= constants.s_max_u32;
		assert b >= constants.s_min_u32;
		assert b <= constants.s_max_u32;
		r = a + b;
		assert r >= 0;
		assert r <= 0x1fffffffel;
		r &= constants.s_max_u32;
		assert r >= constants.s_min_u32;
		assert r <= constants.s_max_u32;
		return r;
	}
	public static int add(int a, int b)
	{
		return long_to_int(add(int_to_long(a), int_to_long(b)));
	}
	public static long sub(long a, long b)
	{
		long r;
		assert a >= constants.s_min_u32;
		assert a <= constants.s_max_u32;
		assert b >= constants.s_min_u32;
		assert b <= constants.s_max_u32;
		r = (a + (constants.s_max_u32 + 1)) - b;
		assert r >= 1l;
		assert r <= 0x1ffffffffl;
		r &= constants.s_max_u32;
		assert r >= constants.s_min_u32;
		assert r <= constants.s_max_u32;
		return r;
	}
	public static int sub(int a, int b)
	{
		return long_to_int(sub(int_to_long(a), int_to_long(b)));
	}
	public static long and(long a, long b)
	{
		long r;
		assert a >= constants.s_min_u32;
		assert a <= constants.s_max_u32;
		assert b >= constants.s_min_u32;
		assert b <= constants.s_max_u32;
		r = a & b;
		assert r >= constants.s_min_u32;
		assert r <= constants.s_max_u32;
		return r;
	}
	public static long shr(long x, int bits)
	{
		long r;
		assert x >= constants.s_min_u32;
		assert x <= constants.s_max_u32;
		assert bits >= 1;
		assert bits <= 32 - 1;
		r = x >> bits;
		assert r >= constants.s_min_u32;
		assert r <= constants.s_max_u32;
		return r;
	}
	public static long mul_lo(long a, long b)
	{
		long a_lo;
		long a_hi;
		long b_lo;
		long b_hi;
		long c_lo;
		long c_hi;
		long ta;
		long tb;
		long r;
		assert a >= constants.s_min_u32;
		assert a <= constants.s_max_u32;
		assert b >= constants.s_min_u32;
		assert b <= constants.s_max_u32;
		a_lo = get_lo(a);
		a_hi = get_hi(a);
		b_lo = get_lo(b);
		b_hi = get_hi(b);
		c_lo = u16.mul_lo(a_lo, b_lo);
		c_hi = u16.mul_hi(a_lo, b_lo);
		ta = u16.mul_lo(a_lo, b_hi);
		tb = u16.mul_lo(a_hi, b_lo);
		c_hi = u16.add(c_hi, ta);
		c_hi = u16.add(c_hi, tb);
		r = combine_hi_lo_long(c_hi, c_lo);
		assert r >= constants.s_min_u32;
		assert r <= constants.s_max_u32;
		return r;
	}
	public static int mul_lo(int a, int b)
	{
		long al;
		long bl;
		long rl;
		int r;
		al = int_to_long(a);
		bl = int_to_long(b);
		rl = mul_lo(al, bl);
		r = long_to_int(rl);
		return r;
	}
	public static long mul_hi(long a, long b)
	{
		long alo;
		long ahi;
		long blo;
		long bhi;
		long ablo;
		long abmi;
		long bami;
		long abhi;
		long r;
		long ta;
		assert a >= constants.s_min_u32;
		assert a <= constants.s_max_u32;
		assert b >= constants.s_min_u32;
		assert b <= constants.s_max_u32;
		alo = get_lo(a);
		ahi = get_hi(a);
		blo = get_lo(b);
		bhi = get_hi(b);
		ablo = mul_lo(alo, blo);
		abmi = mul_lo(alo, bhi);
		bami = mul_lo(ahi, blo);
		abhi = mul_lo(ahi, bhi);
		r = 0l;
		r = add(r, abhi);
		r = add(r, get_hi(abmi));
		r = add(r, get_hi(bami));
		ta = 0l;
		ta = add(ta, get_lo(abmi));
		ta = add(ta, get_lo(bami));
		ta = add(ta, get_hi(ablo));
		r = add(r, get_hi(ta));
		assert r >= constants.s_min_u32;
		assert r <= constants.s_max_u32;
		return r;
	}
	public static int mul_hi(int a, int b)
	{
		long al;
		long bl;
		long rl;
		int r;
		al = int_to_long(a);
		bl = int_to_long(b);
		rl = mul_hi(al, bl);
		r = long_to_int(rl);
		return r;
	}
}
