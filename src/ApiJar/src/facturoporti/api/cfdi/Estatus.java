package facturoporti.api.cfdi;

public class Estatus
{
	private String Codigo;
	public final String getCodigo()
	{
		return this.Codigo;
	}
	public final void setCodigo(String value)
	{
		this.Codigo = value;
	}

	private String Descripcion;
	public final String getDescripcion()
	{
		return this.Descripcion;
	}
	public final void setDescripcion(String value)
	{
		this.Descripcion = value;
	}

	private String InformacionTecnica;
	public final String getInformacionTecnica()
	{
		return this.InformacionTecnica;
	}
	public final void setInformacionTecnica(String value)
	{
		this.InformacionTecnica = value;
	}

	/** 
	 Fecha  y hora de la respuesta 
	*/
	private String Fecha;
	public final String getFecha()
	{
		return this.Fecha;
	}
	public final void setFecha(String value)
	{
		this.Fecha = value;
	}

	/** 
	 Fecha  y hora de la respuesta 
	*/
	private boolean DetieneEjecucionProveedor;
	public final boolean getDetieneEjecucionProveedor()
	{
		return this.DetieneEjecucionProveedor;
	}
	public final void setDetieneEjecucionProveedor(boolean value)
	{
		this.DetieneEjecucionProveedor = value;
	}
}