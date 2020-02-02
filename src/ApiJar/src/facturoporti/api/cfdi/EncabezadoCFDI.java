package facturoporti.api.cfdi;

public class EncabezadoCFDI
{
	private EmisorCFDI Emisor;
	public final EmisorCFDI getEmisor()
	{
		return Emisor;
	}
	public final void setEmisor(EmisorCFDI value)
	{
		Emisor = value;
	}
	private ReceptorCFDI Receptor;
	public final ReceptorCFDI getReceptor()
	{
		return Receptor;
	}
	public final void setReceptor(ReceptorCFDI value)
	{
		Receptor = value;
	}
	private String Fecha;
	public final String getFecha()
	{
		return Fecha;
	}
	public final void setFecha(String value)
	{
		Fecha = value;
	}
	private String Serie;
	public final String getSerie()
	{
		return Serie;
	}
	public final void setSerie(String value)
	{
		Serie = value;
	}
	private String Folio;
	public final String getFolio()
	{
		return Folio;
	}
	public final void setFolio(String value)
	{
		Folio = value;
	}
	private String MetodoPago;
	public final String getMetodoPago()
	{
		return MetodoPago;
	}
	public final void setMetodoPago(String value)
	{
		MetodoPago = value;
	}
	private String FormaPago;
	public final String getFormaPago()
	{
		return FormaPago;
	}
	public final void setFormaPago(String value)
	{
		FormaPago = value;
	}
	private String CondicionesPago;
	public final String getCondicionesPago()
	{
		return CondicionesPago;
	}
	public final void setCondicionesPago(String value)
	{
		CondicionesPago = value;
	}
	private String Moneda;
	public final String getMoneda()
	{
		return Moneda;
	}
	public final void setMoneda(String value)
	{
		Moneda = value;
	}
	private String TipoCambio;
	public final String getTipoCambio()
	{
		return TipoCambio;
	}
	public final void setTipoCambio(String value)
	{
		TipoCambio = value;
	}
	private String LugarExpedicion;
	public final String getLugarExpedicion()
	{
		return LugarExpedicion;
	}
	public final void setLugarExpedicion(String value)
	{
		LugarExpedicion = value;
	}
	private String SubTotal;
	public final String getSubTotal()
	{
		return SubTotal;
	}
	public final void setSubTotal(String value)
	{
		SubTotal = value;
	}
	private String Total;
	public final String getTotal()
	{
		return Total;
	}
	public final void setTotal(String value)
	{
		Total = value;
	}
	private String Descuento;
	public final String getDescuento()
	{
		return Descuento;
	}
	public final void setDescuento(String value)
	{
		Descuento = value;
	}
	private String CFDIsRelacionados;
	public final String getCFDIsRelacionados()
	{
		return CFDIsRelacionados;
	}
	public final void setCFDIsRelacionados(String value)
	{
		CFDIsRelacionados = value;
	}
	private String TipoRelacion;
	public final String getTipoRelacion()
	{
		return TipoRelacion;
	}
	public final void setTipoRelacion(String value)
	{
		TipoRelacion = value;
	}

	private String FechaEntrega;
	public final String getFechaEntrega()
	{
		return FechaEntrega;
	}
	public final void setFechaEntrega(String value)
	{
		FechaEntrega = value;
	}
	private String Remitente;
	public final String getRemitente()
	{
		return Remitente;
	}
	public final void setRemitente(String value)
	{
		Remitente = value;
	}
	private String RemitenteDomicilio;
	public final String getRemitenteDomicilio()
	{
		return RemitenteDomicilio;
	}
	public final void setRemitenteDomicilio(String value)
	{
		RemitenteDomicilio = value;
	}
	private String RemitenteLugarRecoger;
	public final String getRemitenteLugarRecoger()
	{
		return RemitenteLugarRecoger;
	}
	public final void setRemitenteLugarRecoger(String value)
	{
		RemitenteLugarRecoger = value;
	}
	private String Destinatario;
	public final String getDestinatario()
	{
		return Destinatario;
	}
	public final void setDestinatario(String value)
	{
		Destinatario = value;
	}
	private String DestinatarioDomicilio;
	public final String getDestinatarioDomicilio()
	{
		return DestinatarioDomicilio;
	}
	public final void setDestinatarioDomicilio(String value)
	{
		DestinatarioDomicilio = value;
	}
	private String DestinatarioLugarEntrega;
	public final String getDestinatarioLugarEntrega()
	{
		return DestinatarioLugarEntrega;
	}
	public final void setDestinatarioLugarEntrega(String value)
	{
		DestinatarioLugarEntrega = value;
	}
	private String DescripcionMercancia;
	public final String getDescripcionMercancia()
	{
		return DescripcionMercancia;
	}
	public final void setDescripcionMercancia(String value)
	{
		DescripcionMercancia = value;
	}
	private String Peso;
	public final String getPeso()
	{
		return Peso;
	}
	public final void setPeso(String value)
	{
		Peso = value;
	}
	private String MetrosCubicos;
	public final String getMetrosCubicos()
	{
		return MetrosCubicos;
	}
	public final void setMetrosCubicos(String value)
	{
		MetrosCubicos = value;
	}
	private String Litros;
	public final String getLitros()
	{
		return Litros;
	}
	public final void setLitros(String value)
	{
		Litros = value;
	}
	private String MaterialPeligroso;
	public final String getMaterialPeligroso()
	{
		return MaterialPeligroso;
	}
	public final void setMaterialPeligroso(String value)
	{
		MaterialPeligroso = value;
	}
	private String ValorDeclarado;
	public final String getValorDeclarado()
	{
		return ValorDeclarado;
	}
	public final void setValorDeclarado(String value)
	{
		ValorDeclarado = value;
	}
	private String Indemnizacion;
	public final String getIndemnizacion()
	{
		return Indemnizacion;
	}
	public final void setIndemnizacion(String value)
	{
		Indemnizacion = value;
	}
	private String Conductor;
	public final String getConductor()
	{
		return Conductor;
	}
	public final void setConductor(String value)
	{
		Conductor = value;
	}
	private String Vehiculo;
	public final String getVehiculo()
	{
		return Vehiculo;
	}
	public final void setVehiculo(String value)
	{
		Vehiculo = value;
	}
	private String Placas;
	public final String getPlacas()
	{
		return Placas;
	}
	public final void setPlacas(String value)
	{
		Placas = value;
	}
	private String Kilometros;
	public final String getKilometros()
	{
		return Kilometros;
	}
	public final void setKilometros(String value)
	{
		Kilometros = value;
	}
	private String Observaciones;
	public final String getObservaciones()
	{
		return Observaciones;
	}
	public final void setObservaciones(String value)
	{
		Observaciones = value;
	}

	private String CampoAdicional_1;
	public final String getCampoAdicional_1()
	{
		return CampoAdicional_1;
	}
	public final void setCampoAdicional_1(String value)
	{
		CampoAdicional_1 = value;
	}
	private String CampoAdicional_2;
	public final String getCampoAdicional_2()
	{
		return CampoAdicional_2;
	}
	public final void setCampoAdicional_2(String value)
	{
		CampoAdicional_2 = value;
	}
	private String CampoAdicional_3;
	public final String getCampoAdicional_3()
	{
		return CampoAdicional_3;
	}
	public final void setCampoAdicional_3(String value)
	{
		CampoAdicional_3 = value;
	}
	private String CampoAdicional_4;
	public final String getCampoAdicional_4()
	{
		return CampoAdicional_4;
	}
	public final void setCampoAdicional_4(String value)
	{
		CampoAdicional_4 = value;
	}
	private String CampoAdicional_5;
	public final String getCampoAdicional_5()
	{
		return CampoAdicional_5;
	}
	public final void setCampoAdicional_5(String value)
	{
		CampoAdicional_5 = value;
	}
	private String CampoAdicional_6;
	public final String getCampoAdicional_6()
	{
		return CampoAdicional_6;
	}
	public final void setCampoAdicional_6(String value)
	{
		CampoAdicional_6 = value;
	}
	private String CampoAdicional_7;
	public final String getCampoAdicional_7()
	{
		return CampoAdicional_7;
	}
	public final void setCampoAdicional_7(String value)
	{
		CampoAdicional_7 = value;
	}
	private String CampoAdicional_8;
	public final String getCampoAdicional_8()
	{
		return CampoAdicional_8;
	}
	public final void setCampoAdicional_8(String value)
	{
		CampoAdicional_8 = value;
	}
	private String CampoAdicional_9;
	public final String getCampoAdicional_9()
	{
		return CampoAdicional_9;
	}
	public final void setCampoAdicional_9(String value)
	{
		CampoAdicional_9 = value;
	}
	private String CampoAdicional_10;
	public final String getCampoAdicional_10()
	{
		return CampoAdicional_10;
	}
	public final void setCampoAdicional_10(String value)
	{
		CampoAdicional_10 = value;
	}
	private String CampoAdicional_11;
	public final String getCampoAdicional_11()
	{
		return CampoAdicional_11;
	}
	public final void setCampoAdicional_11(String value)
	{
		CampoAdicional_11 = value;
	}
	private String CampoAdicional_12;
	public final String getCampoAdicional_12()
	{
		return CampoAdicional_12;
	}
	public final void setCampoAdicional_12(String value)
	{
		CampoAdicional_12 = value;
	}
	private String CampoAdicional_13;
	public final String getCampoAdicional_13()
	{
		return CampoAdicional_13;
	}
	public final void setCampoAdicional_13(String value)
	{
		CampoAdicional_13 = value;
	}
	private String CampoAdicional_14;
	public final String getCampoAdicional_14()
	{
		return CampoAdicional_14;
	}
	public final void setCampoAdicional_14(String value)
	{
		CampoAdicional_14 = value;
	}
	private String CampoAdicional_15;
	public final String getCampoAdicional_15()
	{
		return CampoAdicional_15;
	}
	public final void setCampoAdicional_15(String value)
	{
		CampoAdicional_15 = value;
	}
	private String CampoAdicional_16;
	public final String getCampoAdicional_16()
	{
		return CampoAdicional_16;
	}
	public final void setCampoAdicional_16(String value)
	{
		CampoAdicional_16 = value;
	}
	private String CampoAdicional_17;
	public final String getCampoAdicional_17()
	{
		return CampoAdicional_17;
	}
	public final void setCampoAdicional_17(String value)
	{
		CampoAdicional_17 = value;
	}
	private String CampoAdicional_18;
	public final String getCampoAdicional_18()
	{
		return CampoAdicional_18;
	}
	public final void setCampoAdicional_18(String value)
	{
		CampoAdicional_18 = value;
	}
	private String CampoAdicional_19;
	public final String getCampoAdicional_19()
	{
		return CampoAdicional_19;
	}
	public final void setCampoAdicional_19(String value)
	{
		CampoAdicional_19 = value;
	}
	private String CampoAdicional_20;
	public final String getCampoAdicional_20()
	{
		return CampoAdicional_20;
	}
	public final void setCampoAdicional_20(String value)
	{
		CampoAdicional_20 = value;
	}
}