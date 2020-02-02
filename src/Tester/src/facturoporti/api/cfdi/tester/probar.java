package facturoporti.api.cfdi.tester;

import facturoporti.api.cfdi.*;
import facturoporti.api.cfdi.entidades.*;
import facturoporti.api.cfdi.genericos.*;

import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class probar {

	private static final String DirectorioRecursos = "C:\\Alejandro\\JavaDllConsumirApiRest\\Tester\\";
	
	private static String UUID;
	private static String getUUID()
	{
		return UUID;
	}
	private static void setUUID(String value)
	{
		UUID = value;
	}
		
	public static void main(String[] args) throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {

		TimbrarDocumento();
		CancelarUUID();
		ConsultarEstatusUUID();
		ConsultarTimbresRestantes();
	}
	
	private static void TimbrarDocumento() throws FileNotFoundException, IOException, SAXException, ParserConfigurationException
	{
		Archivos manager = new Archivos();

		TimbreFiscalDigital Timbre = new TimbreFiscalDigital();
		CFDIPeticion Peticion = new CFDIPeticion();
		Utilerias utilerias = new Utilerias();
		String NombreArchivo = "";

		Peticion.setDatosGenerales(new DatosGeneralesCFDI());
		Peticion.getDatosGenerales().setUsuario("PruebasTimbrado"); // Este usuario se genera desde la pagina de https://cfdi.facturoporti.com.mx/ se debe de registrar para usar el productivo
		Peticion.getDatosGenerales().setPassword("@Notiene1"); // Es la contraseña del usuario cuando se registró
		Peticion.getDatosGenerales().setGeneraPDF("true");
		Peticion.getDatosGenerales().setEnviaEmail("false"); // Valores permitidos "true" : "false";
		Peticion.getDatosGenerales().setReceptorEmail("correodestino@midominio.com");

		//Logotipo (opcional) acepta una imagen jpeg o png en base 64 menor a 100 KB
		//Peticion.DatosGenerales.Logotipo = manager.ConvertirByteToBase64(manager.ConvertirStreamToByte(manager.Abrir("Cambiar la ruta de lectura o enviar la imagen en base 64")));

		enumTipoDocumento TipoDocumentoActual = enumTipoDocumento.Factura;

		switch (TipoDocumentoActual)
		{
			case Factura:
				Peticion.getDatosGenerales().setCFDI("Factura");
				Peticion.getDatosGenerales().setTipoCFDI("Ingreso");
				break;
			case NotaCargo:
				Peticion.getDatosGenerales().setCFDI("NotaCargo");
				Peticion.getDatosGenerales().setTipoCFDI("Ingreso");
				break;
			case NotaCredito:
				Peticion.getDatosGenerales().setCFDI("NotaCredito");
				Peticion.getDatosGenerales().setTipoCFDI("Egreso");
				break;
			case CartaPorte:
				Peticion.getDatosGenerales().setCFDI("CartaPorte");
				Peticion.getDatosGenerales().setTipoCFDI("Traslado");
				break;
			case Pago:
				Peticion.getDatosGenerales().setCFDI("Pago");
				Peticion.getDatosGenerales().setTipoCFDI("Pago");
				break;
			case ReciboNominaCFDI:
				Peticion.getDatosGenerales().setCFDI("ReciboNomina");
				Peticion.getDatosGenerales().setTipoCFDI("ReciboNomina");
				break;
		}

		Peticion.getDatosGenerales().setOpcionDecimales(String.valueOf((enumOpcionDecimales.Redondear.getValue()))); // Valores permitidos 1: Truncar (Operaciones exactas) 2: Redondear hacia arriba o hacia abajo las cantidades
		Peticion.getDatosGenerales().setNumeroDecimales("2"); // El valor predeterminado es 2 hasta un máximo de 6 decimales

		Peticion.setEncabezado(new EncabezadoCFDI());
		Peticion.getEncabezado().setEmisor(new EmisorCFDI());
		Peticion.getEncabezado().getEmisor().setRFC("AAA010101AAA"); // Para realizar pruebas solamente se puede usar este RFC AAA010101AAA
		Peticion.getEncabezado().getEmisor().setNombreRazonSocial("Mi nombre o el nombre de mi empresa");
		Peticion.getEncabezado().getEmisor().setRegimenFiscal("601"); // Se agrega la clave de acuerdo al catálogo del SAT http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls

		// El domicilio de emision es opcional pero se agrega por peticion del usuario
		DireccionCFDI direccion = new DireccionCFDI();

		direccion.setCalle("Avenida Reforma");
		direccion.setNumeroExterior("1234");
		direccion.setNumeroInterior("XA");
		direccion.setColonia("Roma Norte");
		direccion.setEstado("Ciudad de México");
		direccion.setMunicipio("Benito Juarez");
		direccion.setPais("México");
		direccion.setCodigoPostal("06470");

		Peticion.getEncabezado().getEmisor().setDireccion(new ArrayList<DireccionCFDI>());
		Peticion.getEncabezado().getEmisor().getDireccion().add(direccion);

		Peticion.getEncabezado().setReceptor(new ReceptorCFDI());
		Peticion.getEncabezado().getReceptor().setRFC("XEXX010101000");
		Peticion.getEncabezado().getReceptor().setNombreRazonSocial("Nombre del cliente");
		Peticion.getEncabezado().getReceptor().setUsoCFDI("G03"); // Se agrega la clave de acuerdo al catálogo del SAT http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls

		// El domicilio del receptor es opcional pero se agrega por peticion del usuario
		direccion = new DireccionCFDI();

		direccion.setCalle("Leo");
		direccion.setNumeroExterior("9876");
		direccion.setNumeroInterior("A-34");
		direccion.setColonia("San Rafael");
		direccion.setEstado("Morelos");
		direccion.setMunicipio("Cuernavaca");
		direccion.setPais("México");
		direccion.setCodigoPostal("62775");

		Peticion.getEncabezado().getReceptor().setDireccion(direccion);
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		Peticion.getEncabezado().setFecha(now.format(formatter)); // Se debe de enviar con el formato indicado yyyy-MM-ddTHH:mm:ss
		Peticion.getEncabezado().setSerie("A"); // Es el numero de serie es un valor opcional
		Peticion.getEncabezado().setFolio("12"); // Es el numero de folio es un valor opcional
		Peticion.getEncabezado().setMetodoPago("PUE"); // Se agrega la clave de acuerdo al catálogo del SAT http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
		Peticion.getEncabezado().setFormaPago("99"); // Se agrega la clave de acuerdo al catálogo del SAT http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
		Peticion.getEncabezado().setMoneda("MXN"); // Se agrega la clave de acuerdo al catálogo del SAT http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
		Peticion.getEncabezado().setLugarExpedicion("06470");
		Peticion.getEncabezado().setSubTotal("1500"); // Es la suma de los importes en bruto
		Peticion.getEncabezado().setTotal("1740"); // Es la suma de los importes + los impuestos trasladados - los impuestos retenidos

		Peticion.setConceptos(new ArrayList<ConceptoCFDI>());

		ConceptoCFDI concepto = new ConceptoCFDI();

		concepto.setCantidad("1");
		concepto.setCodigoUnidad("E48"); // Se agrega la clave de acuerdo al catálogo del SAT http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
		//concepto.Unidad = "Pieza"; // Este es un valor opcional 
		//concepto.Serie = ""; // Este es un valor opcional se agregan numero de series, partes, etc.
		concepto.setCodigoProducto("53112101"); // Se agrega la clave de acuerdo al catálogo del SAT http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
		concepto.setProducto("Zapatos de caballero marca patito");
		concepto.setPrecioUnitario("1000");
		concepto.setImporte("1000");

		concepto.setImpuestos(new ArrayList<ImpuestosCFDI>());
		ImpuestosCFDI impuesto = new ImpuestosCFDI();

		impuesto.setTipoImpuesto(String.valueOf((enumTipoImpuesto.Trasladado.getValue()))); // Tipo de impuesto se envía la clave 1 traslado 2 retenido
		impuesto.setImpuesto(String.valueOf((enumImpuesto.IVA.getValue())));
		impuesto.setFactor(String.valueOf((enumFactor.Tasa.getValue())));
		impuesto.setBase(concepto.getImporte());
		impuesto.setTasa("0.160000"); // Se debe de enviar con los 6 decimales la tasa para revisar las tasas actuales vea http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
		impuesto.setImpuestoImporte(utilerias.RegresaStringDecimalesXOpcion(Double.valueOf(concepto.getImporte()) * Double.valueOf("0.160000"), enumOpcionDecimales.Redondear.getValue(), (short)2));
		concepto.getImpuestos().add(impuesto);

		// En caso de llevar IEPS se llena esta seccion
		//if (TasaIEPS != null)
		//{
		//    impuesto.TipoImpuesto = ((int)enumTipoImpuesto.Trasladado).ToString(); // Tipo de impuesto se envía la clave 1 traslado 2 retenido
		//    impuesto.Impuesto = ((int)enumImpuesto.IEPS).ToString();
		//    impuesto.Factor = ((int)enumFactor.Tasa).ToString();
		//    impuesto.Base = concepto.Importe;
		//    impuesto.Tasa = "0.08000"; // en el ejemplo la tasa es de 8 porciento Se debe de enviar con los 6 decimales la tasa para revisar las tasas actuales vea http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
		//    impuesto.ImpuestoImporte = utilerias.RegresaStringDecimalesXOpcion(Convert.ToDecimal(concepto.Importe) * Convert.ToDecimal("0.080000"), (int)enumOpcionDecimales.Redondear, 2);
		//    concepto.Impuestos.Add(impuesto);
		//}

		//if (RetencionIVA != null)
		//{
		//    impuesto.TipoImpuesto = ((int)enumTipoImpuesto.Retenido).ToString(); // Tipo de impuesto se envía la clave 1 traslado 2 retenido
		//    impuesto.Impuesto = ((int)enumImpuesto.IVA).ToString();
		//    impuesto.Factor = ((int)enumFactor.Tasa).ToString();
		//    impuesto.Base = concepto.Importe;
		//    impuesto.Tasa = "0.106667"; // en el ejemplo la tasa es de 2/3 partes de IVA 10.66667 porciento Se debe de enviar con los 6 decimales la tasa para revisar las tasas actuales vea http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
		//    impuesto.ImpuestoImporte = utilerias.RegresaStringDecimalesXOpcion(Convert.ToDecimal(concepto.Importe) * Convert.ToDecimal("0.106667"), (int)enumOpcionDecimales.Redondear, 2);
		//    concepto.Impuestos.Add(impuesto);
		//}

		//if (RetencionISR != null)
		//{
		//    impuesto.TipoImpuesto = ((int)enumTipoImpuesto.Retenido).ToString(); // Tipo de impuesto se envía la clave 1 traslado 2 retenido
		//    impuesto.Impuesto = ((int)enumImpuesto.ISR).ToString();
		//    impuesto.Factor = ((int)enumFactor.Tasa).ToString();
		//    impuesto.Base = concepto.Importe;
		//    impuesto.Tasa = "0.10000"; // en el ejemplo la tasa es de 10 porciento Se debe de enviar con los 6 decimales la tasa para revisar las tasas actuales vea http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
		//    impuesto.ImpuestoImporte = utilerias.RegresaStringDecimalesXOpcion(Convert.ToDecimal(concepto.Importe) * Convert.ToDecimal("0.100000"), (int)enumOpcionDecimales.Redondear, 2);
		//    concepto.Impuestos.Add(impuesto);
		//}

		Peticion.getConceptos().add(concepto);

		concepto = new ConceptoCFDI();
		concepto.setCantidad("2");
		concepto.setCodigoUnidad("E48"); // Se agrega la clave de acuerdo al catálogo del SAT http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
	   //concepto.Unidad = "Pieza"; // Este es un valor opcional 
	   //concepto.Serie = ""; // Este es un valor opcional se agregan numero de series, partes, etc.
		concepto.setCodigoProducto("53112102"); // Se agrega la clave de acuerdo al catálogo del SAT http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
		concepto.setProducto("Zapatos de mujer  marca patito");
		concepto.setPrecioUnitario("250");
		concepto.setImporte("500");

		concepto.setImpuestos(new ArrayList<ImpuestosCFDI>());
		impuesto = new ImpuestosCFDI();

		impuesto.setTipoImpuesto(String.valueOf((enumTipoImpuesto.Trasladado.getValue()))); // Tipo de impuesto se envía la clave 1 traslado 2 retenido
		impuesto.setImpuesto(String.valueOf((enumImpuesto.IVA.getValue())));
		impuesto.setFactor(String.valueOf((enumFactor.Tasa.getValue())));
		impuesto.setBase(concepto.getImporte());
		impuesto.setTasa("0.160000"); // Se debe de enviar con los 6 decimales la tasa para revisar las tasas actuales vea http://omawww.sat.gob.mx/tramitesyservicios/Paginas/documentos/catCFDI.xls
		impuesto.setImpuestoImporte(utilerias.RegresaStringDecimalesXOpcion(Double.valueOf(concepto.getImporte()) * Double.valueOf("0.160000"), enumOpcionDecimales.Redondear.getValue(), (short)2));
		concepto.getImpuestos().add(impuesto);

		Peticion.getConceptos().add(concepto);

		ComprobanteDigital comprobante = new ComprobanteDigital();
		comprobante.setSandBox(true); // True = pruebas,  False= Productivo

		//Para el ejercicio se usan los certificados de prueba del SAT
		// Tambien se puede enviar un stream o arreglo de bytes
		String RutaCertificado = DirectorioRecursos + "Certificado\\AAA010101AAA.cer";
		String RutaLlavePrivada = DirectorioRecursos + "Certificado\\AAA010101AAA.key";
		String RutaTimbrados = DirectorioRecursos + "Timbrados\\";

		System.out.println("Inicio de Timbrado " + LocalDateTime.now());

		Archivos archivo = new 	Archivos();
		
		byte[] Certificado = archivo.ConvertirStreamToByte(archivo.Abrir(RutaCertificado));
		byte[] LlavePrivada = archivo.ConvertirStreamToByte(archivo.Abrir(RutaLlavePrivada));

		boolean resultado = comprobante.GeneraCFDI(Peticion, Certificado, LlavePrivada, "12345678a");

		if (resultado == true)
		{
			LocalDateTime fecha =  LocalDateTime.parse(Peticion.getEncabezado().getFecha());
	        formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

			NombreArchivo = Peticion.getEncabezado().getReceptor().getRFC() + AcronimoArchivo(TipoDocumentoActual) + (Peticion.getEncabezado().getSerie() == null ? "" : Peticion.getEncabezado().getSerie()) + Peticion.getEncabezado().getFolio() + "_" + now.format(formatter);
			manager.Guardar(manager.ConvertirBase64ToByte(comprobante.getTimbrado().getCFDITimbrado().getRespuesta().getCFDIXML()), RutaTimbrados + NombreArchivo + ".xml");
			manager.Guardar(manager.ConvertirBase64ToByte(comprobante.getTimbrado().getCFDITimbrado().getRespuesta().getPDF()), RutaTimbrados + NombreArchivo + ".pdf");

			Timbre = CargaTimbre((new Archivos()).ConvertirBase64ToString(comprobante.getTimbrado().getCFDITimbrado().getRespuesta().getTimbreXML()));
			
			System.out.println(comprobante.getMensaje() + " UUID " + getUUID());
			System.out.println(" Para ver los archivos ingrese a la carpeta " + RutaTimbrados);
		}
		else
		{
			System.out.println("");
			System.out.println(comprobante.getMensaje());
		}

		System.out.println("Fin de Timbrado");
		System.out.println("");
	}

	private static void CancelarUUID() throws FileNotFoundException, IOException
	{
		CancelarCFDIPeticion Peticion = new CancelarCFDIPeticion();

		Peticion.setUsuario("PruebasTimbrado"); // Este usuario se genera desde la pagina de https://cfdi.facturoporti.com.mx/ se debe de registrar para usar el productivo
		Peticion.setPassword("@Notiene1"); // Es la contraseña del usuario cuando se registró
		Peticion.setRFC("AAA010101AAA");

		Peticion.setUUIDs(new ArrayList<String>());
		Peticion.getUUIDs().add(getUUID()); // Aca se pueden agregar N folios fiscales

		ComprobanteDigital comprobante = new ComprobanteDigital();
		comprobante.setSandBox(true); // True = pruebas,  False= Productivo

		//Para el ejercicio se usan los certificados de prueba del SAT
		// Tambien se puede enviar un stream o arreglo de bytes
		String RutaCertificado = DirectorioRecursos + "Certificado\\AAA010101AAA.cer";
		String RutaLlavePrivada = DirectorioRecursos + "Certificado\\AAA010101AAA.key";

		System.out.println("Inicio de cancelación " + LocalDateTime.now());

		boolean resultado = comprobante.CancelarCFDI(Peticion, RutaCertificado, RutaLlavePrivada, "12345678a");

		if (resultado == true)
		{
			System.out.println(comprobante.getMensaje() + " Respuesta del Folio " + comprobante.getCancelaciones().getFoliosRespuesta().get(0).EstatusCancelacionSAT);
		}
		else
		{
			System.out.println("");
			System.out.println(comprobante.getMensaje());
		}

		System.out.println("Fin de la cancelación");
		System.out.println("");

	}

	private static void ConsultarEstatusUUID()
	{
		ConsultaEstatusPeticion Peticion = new ConsultaEstatusPeticion();

		Peticion.setUsuario("PruebasTimbrado"); // Este usuario se genera desde la pagina de https://cfdi.facturoporti.com.mx/ se debe de registrar para usar el productivo
		Peticion.setPassword("@Notiene1"); // Es la contraseña del usuario cuando se registró

		Peticion.setUUIDs(new ArrayList<String>());
		Peticion.getUUIDs().add(getUUID()); // Aca se pueden agregar N folios fiscales

		ComprobanteDigital comprobante = new ComprobanteDigital();
		comprobante.setSandBox(true); // True = pruebas,  False= Productivo

		System.out.println("Inicio de consulta " + LocalDateTime.now());

		boolean resultado = comprobante.ConsultaEstatusCFDI(Peticion);

		if (resultado == true)
		{
			System.out.println(comprobante.getMensaje() + " " + comprobante.getEstatusFolios().getFoliosRespuesta().get(0).EstatusCancelacionSAT);
		}
		else
		{
			System.out.println("");
			System.out.println(comprobante.getMensaje());
		}

		System.out.println("Fin de la consulta");
		System.out.println("");

	}

	private static void ConsultarTimbresRestantes()
	{		
		ConsultaTimbresRestantesPeticion Peticion = new ConsultaTimbresRestantesPeticion();
		Peticion.setUsuario("PruebasTimbrado"); // Este usuario se genera desde la pagina de https://cfdi.facturoporti.com.mx/ se debe de registrar para usar el productivo
		Peticion.setPassword("@Notiene1"); // Es la contraseña del usuario cuando se registró
		   
		ComprobanteDigital comprobante = new ComprobanteDigital();
		comprobante.setSandBox(true); // True = pruebas,  False= Productivo
		
		System.out.println("Inicio de consulta de timbres restantes " + LocalDateTime.now());
		System.out.println("");
 
		ConsultaTimbresRestantesRespuesta resultado = comprobante.ConsultaTimbresRestantes(Peticion);

		if (comprobante.getResultado() == true)
        {
			System.out.println(comprobante.getMensaje());
			System.out.println(" Fecha de Compra = " + resultado.getFechaCompra() + " Timbres Utilizados= " + resultado.getTimbresUtilizados() + " CreditosRestantes = " + resultado.getCreditosRestantes());
        }
        else
        {
        	System.out.println("");
        	System.out.println(comprobante.getMensaje());
        }

		System.out.println("Fin de la consulta de timbres restantes");
	}

	public static String AcronimoArchivo(enumTipoDocumento TipoDocumentoActual)
	{
		String nombre = "";

		switch (TipoDocumentoActual)
		{
			case Factura:
				nombre = "_FAC_";
				break;
			case NotaCargo:
				nombre = "_NCA_";
				break;
			case NotaCredito:
				nombre = "_NCE_";
				break;
			case ReciboHonorarios:
				nombre = "_RHO_";
				break;
			case ReciboArrendamiento:
				nombre = "_RARR_";
				break;
			case CartaPorte:
				nombre = "_CPT_";
				break;
			case ReciboNominaCFDI:
				nombre = "_RNOM_";
				break;
			case ReciboDonatario:
				nombre = "_RDON_";
				break;
			case Pago:
				nombre = "_PAGO_";
				break;
		}

		return nombre;
	}

	public static final TimbreFiscalDigital CargaTimbre(String xmlString) throws SAXException, IOException, ParserConfigurationException
	{
		String mensaje = "";
			
		TimbreFiscalDigital objeto = new TimbreFiscalDigital(); 
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		ByteArrayInputStream input = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
		Document doc = builder.parse(input);
		Element root = doc.getDocumentElement();
		
		objeto.setUUID(root.getAttribute("UUID"));
		setUUID(objeto.getUUID());
		objeto.setFechaTimbrado(root.getAttribute("FechaTimbrado"));
		objeto.setRfcProvCertif(root.getAttribute("RfcProvCertif"));
		objeto.setLeyenda(root.getAttribute("Leyenda"));
		objeto.setSelloCFD(root.getAttribute("SelloCFD"));
		objeto.setNoCertificadoSAT(root.getAttribute("NoCertificadoSAT"));
		objeto.setSelloSAT(root.getAttribute("SelloSAT"));
		
		return objeto;
	}

}
