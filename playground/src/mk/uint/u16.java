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
	public static long mul_lo(long a, long b)
	{
		long r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = a * b;
		assert r >= 0;
		assert r <= 0xfffe0001l;
		r &= constants.s_max_u16;
		assert r >= constants.s_min_u16;
		assert r <= constants.s_max_u16;
		return r;
	}
	public static long mul_hi(long a, long b)
	{
		long r;
		assert a >= constants.s_min_u16;
		assert a <= constants.s_max_u16;
		assert b >= constants.s_min_u16;
		assert b <= constants.s_max_u16;
		r = a * b;
		assert r >= 0;
		assert r <= 0xfffe0001l;
		r >>= 16;
		r &= constants.s_max_u16;
		assert r >= constants.s_min_u16;
		assert r <= constants.s_max_u16;
		return r;
	}
}
