package facturoporti.api.cfdi.entidades;

public class TimbreFiscalDigital 
{
	public TimbreFiscalDigital()
	{
		
	}
	
	private String Version;
	
	private String UUID;

	private String FechaTimbrado;

	private String RfcProvCertif;

	private String Leyenda;

	private String SelloCFD;

	private String NoCertificadoSAT;

	private String SelloSAT;

	/** <comentarios/>
	*/
	
	public final String getVersion()
	{
		return this.Version;
	}
	public final void setVersion(String value)
	{
		this.Version = value;
	}

	/** <comentarios/>
	*/
	
	
	public final String getUUID()
	{
		return this.UUID;
	}
	

	public final void setUUID(String value)
	{
		this.UUID = value;
	}

	/** <comentarios/>
	*/
	
	public final String getFechaTimbrado()
	{
		return this.FechaTimbrado;
	}
	
	public final void setFechaTimbrado(String value)
	{
		this.FechaTimbrado = value;
	}

	/** <comentarios/>
	*/
	public final String getRfcProvCertif()
	{
		return this.RfcProvCertif;
	}
 
	public final void setRfcProvCertif(String value)
	{
		this.RfcProvCertif = value;
	}

	/** <comentarios/>
	*/
	public final String getLeyenda()
	{
		return this.Leyenda;
	}
	public final void setLeyenda(String value)
	{
		this.Leyenda = value;
	}

	/** <comentarios/>
	*/
	public final String getSelloCFD()
	{
		return this.SelloCFD;
	}
	public final void setSelloCFD(String value)
	{
		this.SelloCFD = value;
	}

	/** <comentarios/>
	*/

	public final String getNoCertificadoSAT()
	{
		return this.NoCertificadoSAT;
	}
	public final void setNoCertificadoSAT(String value)
	{
		this.NoCertificadoSAT = value;
	}

	/** <comentarios/>
	*/

	public final String getSelloSAT()
	{
		return this.SelloSAT;
	}
	public final void setSelloSAT(String value)
	{
		this.SelloSAT = value;
	}
}