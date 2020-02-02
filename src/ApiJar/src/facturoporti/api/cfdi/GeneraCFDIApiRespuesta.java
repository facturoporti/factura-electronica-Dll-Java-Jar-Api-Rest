package facturoporti.api.cfdi;

public class GeneraCFDIApiRespuesta extends WCFRespuesta
{
	private CFDITimbradoRespuesta CFDITimbrado;
	public final CFDITimbradoRespuesta getCFDITimbrado()
	{
		return CFDITimbrado;
	}
	public final void setCFDITimbrado(CFDITimbradoRespuesta value)
	{
		CFDITimbrado = value;
	}
}