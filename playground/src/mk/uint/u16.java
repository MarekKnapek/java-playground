package mk.uint;
public class u16
{
	public static long int_to_long(short x)
	{
		long r;
		if(x >= 0)
		{
			r = x;
		}
		else
		{
			r = constants.s_max_u16;
			r += x;
			r += 1;
		}
		assert r >= constants.s_min_u16;
		assert r <= constants.s_max_u16;
		return r;
	}
	public static short long_to_int(long x)
	{
		short r;
		long t;
		assert x >= constants.s_min_u16;
		assert x <= constants.s_max_u16;
		if(x <= constants.s_max_s16)
		{
			assert x >= constants.s_min_s16;
			assert x <= constants.s_max_s16;
			r = ((short)(x));
		}
		else
		{
			t = x;
			t -= 1;
			t -= constants.s_max_u16;
			assert t >= constants.s_min_s16;
			assert t <= constants.s_max_s16;
			r = ((short)(t));
		}
		return r;
	}
	public static short make_random_int(java.util.Random random)
	{
		java.util.Random rnd;
		int mx;
		int tsi;
		short r;
		rnd = random;
		if(rnd == null)
		{
			rnd = new java.util.Random();
		}
		mx = ((int)(constants.s_max_u16));
		mx += 1;
		tsi = rnd.nextInt(mx);
		assert tsi >= constants.s_min_u16;
		assert tsi <= constants.s_max_u16;
		tsi -= ((int)(constants.s_max_s16));
		tsi -= 1;
		assert tsi >= constants.s_min_s16;
		assert tsi <= constants.s_max_s16;
		r = ((short)(tsi));
		assert r == tsi;
		return r;
	}
	public static long make_random_long(java.util.Random random)
	{
		return int_to_long(make_random_int(random));
	}
	public static boolean eq(long a, long b)
	{
		boolean r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = a == b;
		return r;
	}
	public static boolean eq(short a, short b)
	{
		return eq(int_to_long(a), int_to_long(b));
	}
	public static long add(long a, long b)
	{
		long r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = a + b;
		assert r >= 0;
		assert r <= 0x1fffel;
		r &= constants.s_max_u16;
		assert r >= constants.s_min_u16;
		assert r <= constants.s_max_u16;
		return r;
	}
	public static short add(short a, short b)
	{
		return long_to_int(add(int_to_long(a), int_to_long(b)));
	}
	public static long add(long a, long b, boolean cf)
	{
		long r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = a + b + (cf ? 1l : 0l);
		assert r >= 0;
		assert r <= 0x1fffel;
		r &= constants.s_max_u16;
		assert r >= constants.s_min_u16;
		assert r <= constants.s_max_u16;
		return r;
	}
	public static short add(short a, short b, boolean cf)
	{
		return long_to_int(add(int_to_long(a), int_to_long(b), cf));
	}
	public static boolean would_overflow_add(long a, long b)
	{
		boolean r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = (a + b) > constants.s_max_u16;
		return r;
	}
	public static boolean would_overflow_add(short a, short b)
	{
		return u16.would_overflow_add(int_to_long(a), int_to_long(b));
	}
	public static boolean would_overflow_add(long a, long b, boolean cf)
	{
		boolean r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = (a + b + (cf ? 1l : 0l)) > constants.s_max_u16;
		return r;
	}
	public static boolean would_overflow_add(short a, short b, boolean cf)
	{
		return u16.would_overflow_add(int_to_long(a), int_to_long(b), cf);
	}
	public static long sub(long a, long b)
	{
		long r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = (a + (constants.s_max_u16 + 1)) - b;
		assert r >= 1l;
		assert r <= 0x1ffffl;
		r &= constants.s_max_u16;
		assert r >= constants.s_min_u16;
		assert r <= constants.s_max_u16;
		return r;
	}
	public static short sub(short a, short b)
	{
		return long_to_int(sub(int_to_long(a), int_to_long(b)));
	}
	public static long sub(long a, long b, boolean cf)
	{
		long r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = (a + (constants.s_max_u16 + 1)) - b - (cf ? 1l : 0l);
		assert r >= 1l;
		assert r <= 0x1ffffl;
		r &= constants.s_max_u16;
		assert r >= constants.s_min_u16;
		assert r <= constants.s_max_u16;
		return r;
	}
	public static short sub(short a, short b, boolean cf)
	{
		return long_to_int(sub(int_to_long(a), int_to_long(b), cf));
	}
	public static boolean would_overflow_sub(long a, long b)
	{
		boolean r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = a < b;
		return r;
	}
	public static boolean would_overflow_sub(short a, short b)
	{
		return u16.would_overflow_sub(int_to_long(a), int_to_long(b));
	}
	public static boolean would_overflow_sub(long a, long b, boolean cf)
	{
		boolean r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		if(cf)
		{
			r = a <= b;
		}
		else
		{
			r = a < b;
		}
		return r;
	}
	public static boolean would_overflow_sub(short a, short b, boolean cf)
	{
		return u16.would_overflow_sub(int_to_long(a), int_to_long(b), cf);
	}
	public static long mul_lo(long a, long b)
	{
		long r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = a * b;
		assert r >= 0l;
		assert r <= 0xfffe0001l;
		r &= constants.s_max_u16;
		assert r >= constants.s_min_u16;
		assert r <= constants.s_max_u16;
		return r;
	}
	public static short mul_lo(short a, short b)
	{
		return long_to_int(mul_lo(int_to_long(a), int_to_long(b)));
	}
	public static long mul_hi(long a, long b)
	{
		long r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = a * b;
		assert r >= 0l;
		assert r <= 0xfffe0001l;
		r >>= 16;
		r &= constants.s_max_u16;
		assert r >= constants.s_min_u16;
		assert r <= constants.s_max_u16;
		return r;
	}
	public static short mul_hi(short a, short b)
	{
		return long_to_int(mul_hi(int_to_long(a), int_to_long(b)));
	}
}
