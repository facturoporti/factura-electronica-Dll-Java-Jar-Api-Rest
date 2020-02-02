package facturoporti.api.cfdi.genericos;

public enum enumFactor
{
	Tasa(1),
	Cuota(2),
	Exento(3);

	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, enumFactor> mappings;
	private static java.util.HashMap<Integer, enumFactor> getMappings()
	{
		if (mappings == null)
		{
			synchronized (enumFactor.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, enumFactor>();
				}
			}
		}
		return mappings;
	}

	private enumFactor(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static enumFactor forValue(int value)
	{
		return getMappings().get(value);
	}
}