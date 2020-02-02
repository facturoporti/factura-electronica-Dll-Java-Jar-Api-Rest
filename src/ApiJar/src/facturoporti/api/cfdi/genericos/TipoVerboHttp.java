package facturoporti.api.cfdi.genericos;

public enum TipoVerboHttp
{
	Post(1),
	Get(2),
	Put(3),
	Delete(4),
	Patch(5),
	Head(6),
	Connect(7),
	Options(8),
	Trace(9),
	Custom(10);

	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, TipoVerboHttp> mappings;
	private static java.util.HashMap<Integer, TipoVerboHttp> getMappings()
	{
		if (mappings == null)
		{
			synchronized (TipoVerboHttp.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, TipoVerboHttp>();
				}
			}
		}
		return mappings;
	}

	private TipoVerboHttp(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static TipoVerboHttp forValue(int value)
	{
		return getMappings().get(value);
	}
}