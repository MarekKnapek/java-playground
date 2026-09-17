package mk.jvm;
public class info
{
	public static void main(String[] args)
	{
		java.lang.String[] keys_descriptions;
		int n;
		int i;
		java.lang.String key;
		java.lang.String description;
		java.lang.String value;
		keys_descriptions = new String[]
		{
			"java.version", "Java Runtime Environment version",
			"java.vendor", "Java Runtime Environment vendor",
			"java.vendor.url", "Java vendor URL",
			"java.home", "Java installation directory",
			"java.vm.specification.version", "Java Virtual Machine specification version",
			"java.vm.specification.vendor", "Java Virtual Machine specification vendor",
			"java.vm.specification.name", "Java Virtual Machine specification name",
			"java.vm.version", "Java Virtual Machine implementation version",
			"java.vm.vendor", "Java Virtual Machine implementation vendor",
			"java.vm.name", "Java Virtual Machine implementation name",
			"java.specification.version", "Java Runtime Environment specification version",
			"java.specification.vendor", "Java Runtime Environment specification vendor",
			"java.specification.name", "Java Runtime Environment specification name",
			"java.class.version", "Java class format version number",
			"java.class.path", "Java class path",
			"java.library.path", "List of paths to search when loading libraries",
			"java.io.tmpdir", "Default temp file path",
			"java.compiler", "Name of JIT compiler to use",
			"java.ext.dirs", "Path of extension directory or directories",
			"os.name", "Operating system name",
			"os.arch", "Operating system architecture",
			"os.version", "Operating system version",
			"file.separator", "File separator",
			"path.separator", "Path separator",
			"line.separator", "Line separator",
			"user.name", "User's account name",
			"user.home", "User's home directory",
			"user.dir", "User's current working directory",
		};
		assert keys_descriptions.length % 2 == 0;
		n = keys_descriptions.length / 2;
		for(i = 0; i != n; ++i)
		{
			key = keys_descriptions[i * 2 + 0];
			description = keys_descriptions[i * 2 + 1];
			value = System.getProperty(key, "");
			System.out.format(java.util.Locale.ROOT, "%s\t%s%n", key, value);
		}
	}
}
