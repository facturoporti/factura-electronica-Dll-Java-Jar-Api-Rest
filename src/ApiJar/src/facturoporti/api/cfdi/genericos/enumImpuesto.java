package facturoporti.api.cfdi.genericos;

public enum enumImpuesto
{
	ISR(1),
	IVA(2),
	IEPS(3);

	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, enumImpuesto> mappings;
	private static java.util.HashMap<Integer, enumImpuesto> getMappings()
	{
		if (mappings == null)
		{
			synchronized (enumImpuesto.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, enumImpuesto>();
				}
			}
		}
		return mappings;
	}

	private enumImpuesto(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static enumImpuesto forValue(int value)
	{
		return getMappings().get(value);
	}
}