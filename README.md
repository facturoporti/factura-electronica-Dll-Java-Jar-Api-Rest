<div align="center">

![banner](GitHub.png)

# Proyecto en Java (jar) para Timbrar y Cancelar Facturas Electrónicas de cualquier tipo

![C# badge](subtitulo-badge.png)

</div>

**Jar hecho en Java** que permite generar cualquier tipo de comprobante digital **Ingreso, Egreso, Traslado, Nomina, Pago, etc.(Xml y PDF)** llenando los datos correspondientes fácilmente podrás generar las facturas digitales en cuestión de minutos. 

**Es ideal y compatible para integrarla en cualquier tipo de proyecto ya sean aplicaciones web, escritorio, moviles, etc.** usa el Jar  directamente en el proyecto o agrega el código fuente desde el proyecto y modifícalo de acuerdo a tus necesidades. 

Estos proyectos te pérmiten timbrar, cancelar, obtener estatus del CFDI ante el SAT, consultar los timbres restantes entre otras cosas. 

No necesitas saber ninguna regla del SAT el Jar generará el XML de acuerdo al anexo 20, además se encarga de todo  facilitando el proceso de integración de tu sistema y/o aplicación, servicio, para cumplir con lo que solicita el SAT.

El archivo es muy liviano y rápido, utiliza llamadas Rest Api que permitirá generar tus CFDIs y enviar los por correo todo al mismo tiempo. 

## Requerimientos

Se recomienda usar cualquier IDE de desarrollo para Java. El que se uso para desarrollar esta herramienta es Eclipse Jee 2019-03. 

El jar tiene diferentes requerimientos que estan en la carpeta de recursos lib (Referenced Libraries)

## Timbrar

Para mandar una peticion para timbrar se debe de generar un objeto de tipo **CFDIPeticion**, este a su vez tiene otros objetos que deben de llenarse: 

- DatosGeneralesCFDI
- EncabezadoCFDI
- ConceptoCFDI

```java

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

	private static final String DirectorioRecursos = "C:\\Alejandro\\JavaDllConsumirApiRest\\src\\Tester\\";
	
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
 
```

## Probar Timbrado CFDI

Si usas el proyecto con el código de ejemplo **Ejecuta o depura la aplicación** automáticamente se generará el Xml y PDF con los datos que ingresaste (es una aplicación de consola que te sirve para depurar la información que envias y recibes). En caso de que lo integres a tu solución recuerda tener las referencias correspondientes a los requerimientos especificados anterioremente. 

Revisa el objeto de respuesta CFDITimbradoRespuesta en el encontraras tanto el XML, PDF, timbre fiscal, estatus, errores, etc. **Todo codigo de error diferente de "000" indica algún tipo de error ** que se debe de revisar y corregir.

Los atributos **CFDIXML, TimbreXML y PDF estan en Base64** se deberán de convertir a texto para obtener el XML y/o timbre del CFDI, en el caso del PDF lo podrán guardar o convertir de manera binaria para obtener la representación impresa. Esto ya se hace en el proyecto de ejemplo.

## Cancelar

El jar permite mandar uno o varios Folios Fiscales para cancelar al mismo tiempo solo se debe de enviar el usuario, contraseña, rfc del emisor y certificado digital. 

```java
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
```

## Probar Cancelación de CFDI

Si usas el proyecto con el código de ejemplo **Ejecuta o depura la aplicación** automáticamente se enviará la cancelación con los datos que ingresaste (es una aplicación de consola que te sirve para depurar la información que envias y recibes). En caso de que lo integres a tu solución recuerda tener las referencias correspondientes a los requerimientos especificados anterioremente. 

## Consultar Estatus de la cancelación

El proyecto de ejemplo permite consultar uno o varios Folios Fiscales para revisar el estatus de la cancelación; de acuerdo a las normas dictadas por el SAT el CFDI dependiendo de las reglas que apliquen puede cancelarse de inmediato o se deberá de esperar un máximo de 72 horas para que el usuario receptor del CFDI acepte o rechace la cancelación del CFDI.

En ese lapso de 72 horas de manera automática se podrá actualizar el estatus del CFDI por lo que se requiere periodicamente consultar el servicio para validar el estatus actual del CFDI. No se recomienda hacerlo con una periodicidad menor a 1 hora ya que el SAT tarda de igual manera de 1 a 72 horas en ver reflejado el cambio de estatus. Asi que se sugiere que manden a llamar este servicio con un lapso mayor a 1 hora cada vez, antes de ese tiempo será en vano consultar el estatus del CFDI.

```java
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
```

## Consultar Timbres Restantes 

Para poder emitir o cancelar cualquier tipo de CFDI es necesario que tenga un paquete de timbres del Servicio Rest, si estas usando el modo de pruebas con el usuario que está en el ejemplo, el tiene un paquete de timbres que puedes usar siempre. 

Cuando pases el ambiente productivo deberás de contar con una cuenta válida de acceso a FacturoPorTi [Registrate Aquí](https://cfdi.facturoporti.com.mx/Usuario/Registrar "Registrate Aquí") y ademas un paquete de timbres para el Servicio Rest. La lista de precios actualizada de timbres la puedes consultar directamente e: 

**[Lista de Precios](https://www.facturoporti.com.mx/lista-de-precios/ "Lista de Precios")**

Como tenemos varios paquetes con características distintas en cuanto a uso y precio se debe de ingresar a la **tabla de Timbres Servicio Rest Api** allí encontraras los términos y uso de cada paquete.

```csharp
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
```
## Documentación Adicional

Si deseas conocer mas opciones de como generar cualquier tipo de CFDI lee la documentación con el **diccionario de datos** que contiene todos los atributos y su descripción de los valores que se permiten http://software.facturoporti.com.mx/TaaS/Diccionario/Rest-Api-V-2.3.7.xlsx

## ¿Tienes dudas? 

En caso de que necesites ayuda o tengas dudas contáctanos a soporte@facturoporti.com.mx o ingresa a [FacturoPorTi](https://www.facturoporti.com.mx/timbrado/ "Contáctanos")  para ayudarte con tus necesidades.

## Contribuir

1. Fork el repositorio 

2. Clona el repositorio

    git clone git@github.com:yourUserName/factura-electronica-Dll-Java-Jar-Api-Rest.git

3. Crea una rama 
```
    git checkout desarrollo
    git pull al original desarrollo
    # Podrás escoger el nombre de tu rama
    git checkout -b <feature/my_branch>
```
4. Haz los cambios necesarios y commit para carga los
```
    git add .
    git commit -m "mis cambios"
```
5. Envía los cambios a GitHub
```
    git push origin <feature/my_branch>
```

***-

## License

Desarrollado en México por [FacturoPorTi](https://www.FacturoPorTi.com.mx). Licencia de uso [Ver mas](https://github.com/facturoporti/factura-electronica-Dll-Java-Jar-Api-Rest/blob/master/Licencia).
****
