package facturoporti.api.cfdi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

public class WebServiceFacturoPorTi
{

	private boolean EsSandBox;
	public final boolean getEsSandBox()
	{
		return EsSandBox;
	}
	public final void setEsSandBox(boolean value)
	{
		EsSandBox = value;
	}
	private String Url;
	
	private String Token;
	public final String getToken()
	{
		return Token;
	}
	public final void setToken(String value)
	{
		Token = value;
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
	private String Codigo;
	public final String getCodigo()
	{
		return Codigo;
	}
	public final void setCodigo(String value)
	{
		Codigo = value;
	}

	private boolean ErrorConexion;
	public final boolean getErrorConexion()
	{
		return ErrorConexion;
	}
	public final void setErrorConexion(boolean value)
	{
		ErrorConexion = value;
	}

	public WebServiceFacturoPorTi(boolean _esSandBox)
	{
		setEsSandBox(_esSandBox);
	}

	public final GeneraCFDIApiRespuesta Timbrar(CFDIPeticion peticion)  
	{
 	    Url = "http://wcfpruebas.facturoporti.com.mx/Timbrado/Servicios.svc/ApiTimbrarCFDI";
	   
		if (getEsSandBox() == false)
		{
			Url = "http://wcf.facturoporti.com.mx/Timbrado/Servicios.svc/ApiTimbrarCFDI";
		}
		else
		{
			Url = "http://wcfpruebas.facturoporti.com.mx/Timbrado/Servicios.svc/ApiTimbrarCFDI";
		}

		//Url = "http://localhost:52860/Servicios.svc/ApiTimbrarCFDI";
		
		Gson gson = new Gson();
		String json = gson.toJson(peticion);
		
		 Client client = ClientBuilder.newClient();
			 
		 WebTarget target = client.target(Url);		
		 String response = target.request().post(Entity.entity(json, MediaType.APPLICATION_JSON), String.class);
		
		 GeneraCFDIApiRespuesta salida = gson.fromJson(response, GeneraCFDIApiRespuesta.class);
		
		 return salida;
	}
	
	public final CancelarCFDIRespuesta Cancelar(CancelarCFDIPeticion peticion)  
	{
 	    Url = "http://wcfpruebas.facturoporti.com.mx/Timbrado/Servicios.svc/ApiCancelarCFDI";
	   
		if (getEsSandBox() == false)
		{
			Url = "http://wcf.facturoporti.com.mx/Timbrado/Servicios.svc/ApiCancelarCFDI";
		}
		else
		{
			Url = "http://wcfpruebas.facturoporti.com.mx/Timbrado/Servicios.svc/ApiCancelarCFDI";
		}
		
		//Url = "http://localhost:52860/Servicios.svc/ApiCancelarCFDI";

		Gson gson = new Gson();
		String json = gson.toJson(peticion);
		
		 Client client = ClientBuilder.newClient();
			 
		 WebTarget target = client.target(Url);		
		 String response = target.request().post(Entity.entity(json, MediaType.APPLICATION_JSON), String.class);
		
		 CancelarCFDIRespuesta salida = gson.fromJson(response, CancelarCFDIRespuesta.class);

		 return salida;
	}
	
	public final ConsultaEstatusRespuesta ConsultarEstatus(ConsultaEstatusPeticion peticion)  
	{
 	    Url = "http://wcfpruebas.facturoporti.com.mx/Timbrado/Servicios.svc/ApiConsultaEstatusCFDI";
	   
		if (getEsSandBox() == false)
		{
			Url = "http://wcf.facturoporti.com.mx/Timbrado/Servicios.svc/ApiConsultaEstatusCFDI";
		}
		else
		{
			Url = "http://wcfpruebas.facturoporti.com.mx/Timbrado/Servicios.svc/ApiConsultaEstatusCFDI";
		}
		
		//Url = "http://localhost:52860/Servicios.svc/ApiConsultaEstatusCFDI";

		Gson gson = new Gson();
		String json = gson.toJson(peticion);
		
		 Client client = ClientBuilder.newClient();
			 
		 WebTarget target = client.target(Url);		
		 String response = target.request().post(Entity.entity(json, MediaType.APPLICATION_JSON), String.class);
		
		 ConsultaEstatusRespuesta salida = gson.fromJson(response, ConsultaEstatusRespuesta.class);

		 return salida;
	}
	
	public final ConsultaTimbresRestantesRespuesta ConsultaTimbresRestantes(ConsultaTimbresRestantesPeticion peticion)  
	{
 	    Url = "http://wcfpruebas.facturoporti.com.mx/Timbrado/Servicios.svc/ApiConsultaTimbresRestantes";
	   
		if (getEsSandBox() == false)
		{
			Url = "http://wcf.facturoporti.com.mx/Timbrado/Servicios.svc/ApiConsultaTimbresRestantes";
		}
		else
		{
			Url = "http://wcfpruebas.facturoporti.com.mx/Timbrado/Servicios.svc/ApiConsultaTimbresRestantes";
		}

		//Url = "http://localhost:52860/Servicios.svc/ApiConsultaTimbresRestantes";
				
		Gson gson = new Gson();
		String json = gson.toJson(peticion);
		
		 Client client = ClientBuilder.newClient();
			 
		 WebTarget target = client.target(Url);		
		 String response = target.request().post(Entity.entity(json, MediaType.APPLICATION_JSON), String.class);
		
		 ConsultaTimbresRestantesRespuesta salida = gson.fromJson(response, ConsultaTimbresRestantesRespuesta.class);

		return salida;
		
	}
}