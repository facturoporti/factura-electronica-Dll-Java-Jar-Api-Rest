package facturoporti.api.cfdi.genericos;

public enum enumTazaIVA
{
	Excento(1),
	Cero(2),
	Fronterizo(3),
	General(4);

	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, enumTazaIVA> mappings;
	private static java.util.HashMap<Integer, enumTazaIVA> getMappings()
	{
		if (mappings == null)
		{
			synchronized (enumTazaIVA.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, enumTazaIVA>();
				}
			}
		}
		return mappings;
	}

	private enumTazaIVA(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static enumTazaIVA forValue(int value)
	{
		return getMappings().get(value);
	}
}