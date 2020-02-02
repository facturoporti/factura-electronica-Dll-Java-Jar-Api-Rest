package facturoporti.api.cfdi;

public class Donativos
{
	private String Leyenda;
	public final String getLeyenda()
	{
		return Leyenda;
	}
	public final void setLeyenda(String value)
	{
		Leyenda = value;
	}
	private String FechaAutorizacion;
	public final String getFechaAutorizacion()
	{
		return FechaAutorizacion;
	}
	public final void setFechaAutorizacion(String value)
	{
		FechaAutorizacion = value;
	}
	private String NumeroAutorizacion;
	public final String getNumeroAutorizacion()
	{
		return NumeroAutorizacion;
	}
	public final void setNumeroAutorizacion(String value)
	{
		NumeroAutorizacion = value;
	}
}