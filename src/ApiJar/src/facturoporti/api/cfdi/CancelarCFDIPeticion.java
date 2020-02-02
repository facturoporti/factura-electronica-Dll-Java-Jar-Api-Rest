package facturoporti.api.cfdi;

import java.util.*;

public class CancelarCFDIPeticion
{
	private String Usuario;
	public final String getUsuario()
	{
		return Usuario;
	}
	public final void setUsuario(String value)
	{
		Usuario = value;
	}
	private String Password;
	public final String getPassword()
	{
		return Password;
	}
	public final void setPassword(String value)
	{
		Password = value;
	}
	/** 
	 RFC del emisor 
	*/
	private String RFC;
	public final String getRFC()
	{
		return RFC;
	}
	public final void setRFC(String value)
	{
		RFC = value;
	}
	/** 
	 Este valor se genera automáticamente no asignar
	*/
	private String Certificado;
	public final String getCertificado()
	{
		return Certificado;
	}
	public final void setCertificado(String value)
	{
		Certificado = value;
	}
	
	private String LlavePrivada;
	public final String getLlavePrivada()
	{
		return LlavePrivada;
	}
	public final void setLlavePrivada(String value)
	{
		LlavePrivada = value;
	}
	
	/** 
	 Este valor se asigna autoáticamente no asignar
	*/
	private String CertificadoPassword;
	public final String getCertificadoPassword()
	{
		return CertificadoPassword;
	}
	public final void setCertificadoPassword(String value)
	{
		CertificadoPassword = value;
	}
	
	private ArrayList<String> UUIDs;
	public final ArrayList<String> getUUIDs()
	{
		return UUIDs;
	}
	public final void setUUIDs(ArrayList<String> value)
	{
		UUIDs = value;
	}

}