package facturoporti.api.cfdi.genericos;

public enum enumOpcionDecimales
{
	Truncar(1),
	Redondear(2);

	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, enumOpcionDecimales> mappings;
	private static java.util.HashMap<Integer, enumOpcionDecimales> getMappings()
	{
		if (mappings == null)
		{
			synchronized (enumOpcionDecimales.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, enumOpcionDecimales>();
				}
			}
		}
		return mappings;
	}

	private enumOpcionDecimales(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static enumOpcionDecimales forValue(int value)
	{
		return getMappings().get(value);
	}
}