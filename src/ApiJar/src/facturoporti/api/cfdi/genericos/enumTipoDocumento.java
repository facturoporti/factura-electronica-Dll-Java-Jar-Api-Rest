package facturoporti.api.cfdi.genericos;

//Enumerador con el tipo de documento
public enum enumTipoDocumento
{
	Factura(1),
	NotaCargo(2),
	NotaCredito(3),
	ReciboHonorarios(4),
	ReciboArrendamiento(5),
	CartaPorte(6),
	ReciboNominaCFDI(7),
	ReciboDonatario(8),
	Pago(9);

	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, enumTipoDocumento> mappings;
	private static java.util.HashMap<Integer, enumTipoDocumento> getMappings()
	{
		if (mappings == null)
		{
			synchronized (enumTipoDocumento.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, enumTipoDocumento>();
				}
			}
		}
		return mappings;
	}

	private enumTipoDocumento(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static enumTipoDocumento forValue(int value)
	{
		return getMappings().get(value);
	}
}