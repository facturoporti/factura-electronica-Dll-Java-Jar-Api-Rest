package facturoporti.api.cfdi.seguridad;

import java.io.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import facturoporti.api.cfdi.genericos.StringHelper;

public class Certificado
{
	public X509Certificate CertificadoX509;
	private String Contrasenia;
	public final String getContrasenia()
	{
		return Contrasenia;
	}
	public final void setContrasenia(String value)
	{
		Contrasenia = value;
	}

	public String Mensaje;
	public static String Modulo;
	public Properties Propiedades = new Properties();
	public String TipoCertificado;

	public Certificado()
	{
		this.inicializar();
	}

	public final void CargarCertificado(byte[] bufferCertificado) throws CertificateException
	{
		try
		{
			CertificateFactory fact = CertificateFactory.getInstance("X.509");
			InputStream datos = new ByteArrayInputStream(bufferCertificado); 
			this.CertificadoX509 = (X509Certificate)fact.generateCertificate(datos);			
		}
		catch (RuntimeException exception)
		{
			this.Mensaje = "Error al cargar el buffer del certificado. " + exception.getMessage();
			return;
		}
		this.cargarPropiedades();
	}
	
	private void cargarPropiedades()
	{
		if (this.CertificadoX509 == null)
		{
			this.Mensaje = "No ha cargado un certificado.";
		}
		else
		{
			try
			{
				this.Propiedades.Version = String.valueOf(this.CertificadoX509.getVersion());
				this.Propiedades.Sujeto = this.CertificadoX509.getSubjectDN().toString();
				this.Propiedades.Emisor = this.CertificadoX509.getIssuerDN().toString();
				this.Propiedades.ValidoDesde = this.CertificadoX509.getNotAfter().toString();
				this.Propiedades.ValidoHasta = this.CertificadoX509.getNotBefore().toString();
				this.Propiedades.RFC = StringHelper.substring(this.CertificadoX509.getSubjectDN().toString(), this.CertificadoX509.getSubjectDN().toString().indexOf("OID.2.5.4.45=") + 13, 13).trim();
				this.Propiedades.Fiel = this.CertificadoX509.getSubjectDN().toString().indexOf("OU=") < 0;
				this.Propiedades.NoSerie = this.SerialASCIItoHex(this.CertificadoX509.getSerialNumber().toString());
				int startIndex = this.CertificadoX509.getSubjectDN().toString().indexOf(" OID.2.5.4.41=") + 14;
				int index = this.CertificadoX509.getSubjectDN().toString().indexOf(", ", startIndex);
				this.Propiedades.RazonSocial = this.CertificadoX509.getSubjectDN().toString().substring(startIndex, index).trim();
			}
			catch (RuntimeException exception)
			{
				this.Mensaje = "Error al leer las propiedades del certificado. " + exception.getMessage();
			}
		}
	}

	private void inicializar()
	{
		this.Mensaje = "";
		this.TipoCertificado = "";
		Modulo = "";
		setContrasenia("");
		this.Propiedades.ValidoDesde = "";
		this.Propiedades.Version = "";
		this.Propiedades.Emisor = "";
		this.Propiedades.Sujeto = "";
		this.Propiedades.ValidoDesde = "";
		this.Propiedades.ValidoHasta = "";
		this.Propiedades.NoSerie = "";
		this.Propiedades.RFC = "";
		this.Propiedades.RazonSocial = "";
		this.Propiedades.Fiel = false;
	}

	private String SerialASCIItoHex(String serialAscci)
	{
		String str = "";
		for (int i = 0; i <= (serialAscci.length() - 1); i++)
		{
			if ((i % 2) == 1)
			{
				str = str + serialAscci.substring(i, i + 1);
			}
		}
		return str;
	}

	public final static class Properties
	{
		public String Version;
		public boolean Fiel;
		public String Emisor;
		public String Sujeto;
		public String ValidoDesde;
		public String ValidoHasta;
		public String NoSerie;
		public String RFC;
		public String RazonSocial;

		public Properties clone()
		{
			Properties varCopy = new Properties();

			varCopy.Version = this.Version;
			varCopy.Fiel = this.Fiel;
			varCopy.Emisor = this.Emisor;
			varCopy.Sujeto = this.Sujeto;
			varCopy.ValidoDesde = this.ValidoDesde;
			varCopy.ValidoHasta = this.ValidoHasta;
			varCopy.NoSerie = this.NoSerie;
			varCopy.RFC = this.RFC;
			varCopy.RazonSocial = this.RazonSocial;

			return varCopy;
		}
	}
}