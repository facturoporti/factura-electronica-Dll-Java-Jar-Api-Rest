package facturoporti.api.cfdi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import  facturoporti.api.cfdi.genericos.*;

public class ComprobanteDigital
{
	public ComprobanteDigital()
	{
	}

	/** 
	 Variable que indica si la operacion fue correcta
	*/
	private boolean Resultado;
	public final boolean getResultado()
	{
		return Resultado;
	}
	public final void setResultado(boolean value)
	{
		Resultado = value;
	}
	/** 
	 Almacena los mensajes del sistema
	*/
	private String Mensaje;
	public final String getMensaje()
	{
		return Mensaje;
	}
	public final void setMensaje(String value)
	{
		Mensaje = value;
	}
	/** 
	 Es el objeto del timbre generado junto con el XML y PDF
	*/
	private GeneraCFDIApiRespuesta Timbrado;
	public final GeneraCFDIApiRespuesta getTimbrado()
	{
		return Timbrado;
	}
	public final void setTimbrado(GeneraCFDIApiRespuesta value)
	{
		Timbrado = value;
	}

	private CancelarCFDIRespuesta Cancelaciones;
	public final CancelarCFDIRespuesta getCancelaciones()
	{
		return Cancelaciones;
	}
	public final void setCancelaciones(CancelarCFDIRespuesta value)
	{
		Cancelaciones = value;
	}

	private ConsultaEstatusRespuesta EstatusFolios;
	public final ConsultaEstatusRespuesta getEstatusFolios()
	{
		return EstatusFolios;
	}
	public final void setEstatusFolios(ConsultaEstatusRespuesta value)
	{
		EstatusFolios = value;
	}

	/** 
	 Indica si es SandBox el ambitente True = Pruebas , False = Productivo
	*/
	private boolean SandBox;
	public final boolean getSandBox()
	{
		return SandBox;
	}
	public final void setSandBox(boolean value)
	{
		SandBox = value;
	}

	private Archivos AdministradorArchivos;
	private Archivos getAdministradorArchivos()
	{
		return AdministradorArchivos;
	}
	private void setAdministradorArchivos(Archivos value)
	{
		AdministradorArchivos = value;
	}

	/** 
	 Genera el CFDI a partir de los datos ingresados
	 
	 @param Peticion
	 @param RutaCertificado
	 @param RutaLlavePrivada
	 @param ContraseñaCertificado
	 @return 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	*/
	public final boolean GeneraCFDI(CFDIPeticion Peticion, String RutaCertificado, String RutaLlavePrivada, String ContraseniaCertificado) throws FileNotFoundException, IOException
	{		
		byte[] Certificado = getAdministradorArchivos().ConvertirStreamToByte(getAdministradorArchivos().Abrir(RutaCertificado));
		byte[] LlavePrivada = getAdministradorArchivos().ConvertirStreamToByte(getAdministradorArchivos().Abrir(RutaLlavePrivada));

		return GeneraCFDI(Peticion, Certificado, LlavePrivada, ContraseniaCertificado);
	}

	/** 
	 Genera el CFDI a partir de los datos ingresados
	 
	 @param Peticion
	 @param Certificado
	 @param LlavePrivada
	 @param ContraseñaCertificado
	 @return 
	 * @throws IOException 
	*/
	public final boolean GeneraCFDI(CFDIPeticion Peticion, InputStream Certificado, InputStream LlavePrivada, String ContraseniaCertificado) throws IOException
	{
		setAdministradorArchivos(new Archivos());

		byte[] arregloCertificado = getAdministradorArchivos().ConvertirStreamToByte(Certificado);
		byte[] arregloLlavePrivada = getAdministradorArchivos().ConvertirStreamToByte(LlavePrivada);

		return GeneraCFDI(Peticion, arregloCertificado, arregloLlavePrivada, ContraseniaCertificado);
	}

	/** 
	 Genera el CFDI a partir de los datos ingresados
	 
	 @param Peticion
	 @param Certificado
	 @param LlavePrivada
	 @param ContraseñaCertificado
	 @return 
	*/
	public final boolean GeneraCFDI(CFDIPeticion Peticion, byte[] Certificado, byte[] LlavePrivada, String ContraseniaCertificado)
	{
		setMensaje("");
		setResultado(false);
		setAdministradorArchivos(new Archivos());
		Utilerias utilerias = new Utilerias();

		Peticion.getDatosGenerales().setVersion("3.3");
		Peticion.getDatosGenerales().setSellaCFDI("true");
		Peticion.getDatosGenerales().setTimbraCFDI("true");

		if (StringHelper.isNullOrEmpty(Peticion.getDatosGenerales().getOpcionDecimales()))
		{
			Peticion.getDatosGenerales().setOpcionDecimales("2"); // Valores permitidos 1: Truncar (Operaciones exactas) 2: Redondear hacia arriba o hacia abajo las cantidades
		}

		if (StringHelper.isNullOrEmpty(Peticion.getDatosGenerales().getNumeroDecimales()))
		{
			Peticion.getDatosGenerales().setNumeroDecimales("2");
		}

		if (Peticion.getEncabezado().getEmisor() != null)
		{
			setResultado(utilerias.ValidaRFC(Peticion.getEncabezado().getEmisor().getRFC()));
			if (getResultado() == false)
			{
				setMensaje("El RFC del emisor es incorrecto");
				return false;
			}
		}
		else
		{
			setMensaje("El RFC del emisor es incorrecto");
			return false;
		}

		if (Peticion.getEncabezado().getReceptor() != null)
		{
			setResultado(utilerias.ValidaRFC(Peticion.getEncabezado().getReceptor().getRFC()));
			if (getResultado() == false)
			{
				setMensaje("El RFC del receptor es incorrecto");
				return false;
			}
		}
		else
		{
			setMensaje("El RFC del receptor es incorrecto");
			return false;
		}

		if (!StringHelper.isNullOrEmpty(Peticion.getDatosGenerales().getReceptorEmail()))
		{
			setResultado(utilerias.ValidaCorreos(Peticion.getDatosGenerales().getReceptorEmail()));
			if (getResultado() == false)
			{
				setMensaje("El email del destinatario es incorrecto");
				return false;
			}
		}

		if (!StringHelper.isNullOrEmpty(Peticion.getDatosGenerales().getReceptorEmailCC()))
		{
			setResultado(utilerias.ValidaCorreos(Peticion.getDatosGenerales().getReceptorEmailCC()));
			if (getResultado() == false)
			{
				setMensaje("El email de la copia es incorrecto");
				return false;
			}
		}

		if (!StringHelper.isNullOrEmpty(Peticion.getDatosGenerales().getReceptorEmailCCO()))
		{
			setResultado(utilerias.ValidaCorreos(Peticion.getDatosGenerales().getReceptorEmailCCO()));
			if (getResultado() == false)
			{
				setMensaje("El email de la copia oculta es incorrecto");
				return false;
			}
		}

		Peticion.getDatosGenerales().setCSD(getAdministradorArchivos().ConvertirByteToBase64(Certificado));
		Peticion.getDatosGenerales().setLlavePrivada(getAdministradorArchivos().ConvertirByteToBase64(LlavePrivada));		
		Peticion.getDatosGenerales().setCSDPassword(ContraseniaCertificado);
				
		setResultado(false);
		WebServiceFacturoPorTi Api = new WebServiceFacturoPorTi(getSandBox());
		setTimbrado(Api.Timbrar(Peticion));

		if (getTimbrado() != null)
		{
			if (getTimbrado().getEstatus().getCodigo().equals("000"))
			{
				setResultado(true);
				setMensaje("CFDI generado correctamente");
			}
			else
			{
				setMensaje(getTimbrado().getEstatus().getDescripcion() + System.lineSeparator() + System.lineSeparator() + getTimbrado().getEstatus().getInformacionTecnica());
			}
		}
		else
		{
			setMensaje("No se pudo realizar el timbrado si el problema persiste comuniquese a soporte@facturoporti.com.mx");
		}

		return getResultado();
	}

	/** 
	 Se genera la cancelacion de los UUID por medio del certificado
	 
	 @param Peticion
	 @param RutaCertificado
	 @param RutaLlavePrivada
	 @param ContraseñaCertificado
	 @return 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	*/
	public final boolean CancelarCFDI(CancelarCFDIPeticion Peticion, String RutaCertificado, String RutaLlavePrivada, String ContraseniaCertificado) throws FileNotFoundException, IOException
	{
		setAdministradorArchivos(new Archivos());

		byte[] Certificado = getAdministradorArchivos().ConvertirStreamToByte(getAdministradorArchivos().Abrir(RutaCertificado));
		byte[] LlavePrivada = getAdministradorArchivos().ConvertirStreamToByte(getAdministradorArchivos().Abrir(RutaLlavePrivada));

		return CancelarCFDI(Peticion, Certificado, LlavePrivada, ContraseniaCertificado);
	}

	/** 
	 Se genera la cancelacion de los UUID por medio del certificado
	 
	 @param Peticion
	 @param Certificado
	 @param LlavePrivada
	 @param ContraseñaCertificado
	 @return 
	 * @throws IOException 
	*/
//C# TO JAVA CONVERTER TODO TASK: C# to Java Converter cannot determine whether this System.IO.Stream is input or output:
	public final boolean CancelarCFDI(CancelarCFDIPeticion Peticion, InputStream Certificado, InputStream LlavePrivada, String ContraseniaCertificado) throws IOException
	{
		setAdministradorArchivos(new Archivos());
		byte[] arregloCertificado = getAdministradorArchivos().ConvertirStreamToByte(Certificado);
		byte[] arregloLlavePrivada = getAdministradorArchivos().ConvertirStreamToByte(LlavePrivada);

		return CancelarCFDI(Peticion, arregloCertificado, arregloLlavePrivada, ContraseniaCertificado);
	}

	public final boolean CancelarCFDI(CancelarCFDIPeticion Peticion, byte[] Certificado, byte[] LlavePrivada, String ContraseniaCertificado)
	{
		setMensaje("");
		setResultado(false);
		setAdministradorArchivos(new Archivos());
		Utilerias utilerias = new Utilerias();
		setResultado(utilerias.ValidaRFC(Peticion.getRFC()));
		if (getResultado() == false)
		{
			setMensaje("Ingrese el RFC correctamente");
			return false;
		}
		
		Peticion.setCertificado(getAdministradorArchivos().ConvertirByteToBase64(Certificado));
		Peticion.setLlavePrivada(getAdministradorArchivos().ConvertirByteToBase64(LlavePrivada));		
		Peticion.setCertificadoPassword(ContraseniaCertificado);

		setResultado(false);
		WebServiceFacturoPorTi Api = new WebServiceFacturoPorTi(getSandBox());
		setCancelaciones(Api.Cancelar(Peticion));

		if (getCancelaciones() != null)
		{
			if (getCancelaciones().getEstatus().getCodigo().equals("000"))
			{
				setResultado(true);
				setMensaje(getCancelaciones().getEstatus().getDescripcion());
			}
			else
			{
				setMensaje(getCancelaciones().getEstatus().getDescripcion() + System.lineSeparator() + System.lineSeparator() + getCancelaciones().getEstatus().getInformacionTecnica());
			}
		}
		else
		{
			setMensaje("No se pudo realizar la cancelación si el problema persiste comuniquese a soporte@facturoporti.com.mx");
		}

		return getResultado();
	}

	public final boolean ConsultaEstatusCFDI(ConsultaEstatusPeticion Peticion)
	{
		setMensaje("");
		setResultado(false);

		WebServiceFacturoPorTi Api = new WebServiceFacturoPorTi(getSandBox());
		setEstatusFolios(Api.ConsultarEstatus(Peticion));

		if (getEstatusFolios() != null)
		{
			if (getEstatusFolios().getEstatus().getCodigo().equals("000"))
			{
				setResultado(true);
				setMensaje(getEstatusFolios().getEstatus().getDescripcion());
			}
			else
			{
				setMensaje(getEstatusFolios().getEstatus().getDescripcion() + System.lineSeparator() + System.lineSeparator() + getEstatusFolios().getEstatus().getInformacionTecnica());
			}
		}
		else
		{
			setMensaje("No se pudo realizar la consulta del estatus de CFDI's si el problema persiste comuniquese a soporte@facturoporti.com.mx");
		}

		return getResultado();
	}

	public final ConsultaTimbresRestantesRespuesta ConsultaTimbresRestantes(ConsultaTimbresRestantesPeticion Peticion)
	{
		setMensaje("");
		setResultado(false);

		WebServiceFacturoPorTi Api = new WebServiceFacturoPorTi(getSandBox());
		ConsultaTimbresRestantesRespuesta respuesta = Api.ConsultaTimbresRestantes(Peticion);

		if (respuesta != null)
		{
			if (respuesta.getEstatus().getCodigo().equals("000"))
			{
				setResultado(true);
				setMensaje(respuesta.getEstatus().getDescripcion());
			}
			else
			{
				setMensaje(respuesta.getEstatus().getDescripcion() + System.lineSeparator() + System.lineSeparator() + respuesta.getEstatus().getInformacionTecnica());
			}
		}
		else
		{
			setMensaje("No se pudo realizar la consulta de los timbres restantes si el problema persiste comuniquese a soporte@facturoporti.com.mx");
		}
		
		return respuesta;
	}

}