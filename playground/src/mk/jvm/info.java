package mk.jvm;
public class info
{
	public static void main(String[] args)
	{
		java.util.Properties p;
		java.util.Enumeration keys;
		java.lang.Object key;
		java.lang.Object val;
		p = java.lang.System.getProperties();
		keys = p.keys();
		while(keys.hasMoreElements())
		{
			key = keys.nextElement();
			val = p.get(key);
			java.lang.System.out.println("" + key.toString() + ":" + val.toString() + "");
		}
	}
}
