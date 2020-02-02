package facturoporti.api.cfdi;

public class ImpuestosCFDI
{
	private String TipoImpuesto;
	public final String getTipoImpuesto()
	{
		return TipoImpuesto;
	}
	public final void setTipoImpuesto(String value)
	{
		TipoImpuesto = value;
	}
	private String Impuesto;
	public final String getImpuesto()
	{
		return Impuesto;
	}
	public final void setImpuesto(String value)
	{
		Impuesto = value;
	}
	private String Factor;
	public final String getFactor()
	{
		return Factor;
	}
	public final void setFactor(String value)
	{
		Factor = value;
	}
	private String Base;
	public final String getBase()
	{
		return Base;
	}
	public final void setBase(String value)
	{
		Base = value;
	}
	private String Tasa;
	public final String getTasa()
	{
		return Tasa;
	}
	public final void setTasa(String value)
	{
		Tasa = value;
	}
	private String ImpuestoImporte;
	public final String getImpuestoImporte()
	{
		return ImpuestoImporte;
	}
	public final void setImpuestoImporte(String value)
	{
		ImpuestoImporte = value;
	}
}