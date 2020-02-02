package facturoporti.api.cfdi;

public class WCFRespuesta
{
	/** 
	 Objeto que contiene el detalle del estatus de la respuesta 
	 en caso de error se envía el mensaje correspondiente y el codigo de respuesta
	*/
	private Estatus Estatus;
	public final Estatus getEstatus()
	{
		return this.Estatus;
	}
	public final void setEstatus(Estatus value)
	{
		this.Estatus = value;
	}
}