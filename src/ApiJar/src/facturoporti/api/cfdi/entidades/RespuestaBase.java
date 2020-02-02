package facturoporti.api.cfdi.entidades;

public class RespuestaBase
{
	public RespuestaBase()
	{
	}

	/** 
	 Libera los recursos de la memoria
	*/
	public final void Dispose()
	{
		
	}

	/** 
	 Es el destructor de la clase
	*/
	protected void finalize() throws Throwable
	{
		this.Dispose();
	}

	private String Codigo;
	public final String getCodigo()
	{
		return Codigo;
	}
	public final void setCodigo(String value)
	{
		Codigo = value;
	}
	private String Mensaje;
	public final String getMensaje()
	{
		return Mensaje;
	}
	public final void setMensaje(String value)
	{
		Mensaje = value;
	}
}