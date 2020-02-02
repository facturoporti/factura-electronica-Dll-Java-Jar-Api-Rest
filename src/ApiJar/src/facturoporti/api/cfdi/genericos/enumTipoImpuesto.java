package facturoporti.api.cfdi.genericos;

public enum enumTipoImpuesto
{
	Trasladado(1),
	Retenido(2);

	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, enumTipoImpuesto> mappings;
	private static java.util.HashMap<Integer, enumTipoImpuesto> getMappings()
	{
		if (mappings == null)
		{
			synchronized (enumTipoImpuesto.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, enumTipoImpuesto>();
				}
			}
		}
		return mappings;
	}

	private enumTipoImpuesto(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static enumTipoImpuesto forValue(int value)
	{
		return getMappings().get(value);
	}
}