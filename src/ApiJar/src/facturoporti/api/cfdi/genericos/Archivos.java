package facturoporti.api.cfdi.genericos;

import java.io.*;
import java.util.Base64;

/** 
 Clase que permite realizar operaciones basicas y comunes 
 del manejo de archivos como son: salvar, eliminar 
 abrir archivos a una ruta especifica
*/
public class Archivos
{
	private boolean Resultado;
	public final boolean getResultado()
	{
		return Resultado;
	}
	public final void setResultado(boolean value)
	{
		Resultado = value;
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

	/** 
	 Crea una instancia de Modulo
	*/
	public Archivos()
	{
	}

	/** 
	 Libera los recursos de la memoria
	*/
	public final void Dispose()
	{
	
	}

	/** 
	 Es el destructor de la clase
	*/
	protected void finalize() throws Throwable
	{
		this.Dispose();
	}

	/** 
	 Valida si existe un archivo en una ruta especifica
	 
	 @param ruta Es la ruta de los archivos 
	 @return 
	*/
	public final boolean Existe(String ruta)
	{
		return (new File(ruta)).isFile();
	}

	/** 
	 Eliminar un archivo de una ruta especifica 
	 
	 @param ruta Es la ruta de los archivos 
	 @return 
	*/
	public final boolean Eliminar(String ruta)
	{
		try
		{
			if ((new File(ruta)).isFile())
			{
				(new File(ruta)).delete();
			}

			setResultado(true);
		}
		catch (RuntimeException ex)
		{
			setMensaje("No se pudo eliminar el archivo ");
		}

		return getResultado();
	}

	/** 
	 Abrir un archivo de una ruta especifica en forma de Strea,
	 
	 @param ruta Es la ruta de los archivos 
	 @return 
	 * @throws FileNotFoundException 
	*/
	public final FileInputStream Abrir(String ruta) throws FileNotFoundException
	{
		FileInputStream resultado = null;

		try
		{
			 File initialFile = new File(ruta);
			 resultado = new FileInputStream(initialFile);
		}
		catch (RuntimeException ex)
		{
			setMensaje("No se pudo abrir el archivo");
		}
		return resultado;
	}

	/** 
	 Abrir un archivo y devolver un string con el contenido
	 
	 @param ruta Es la ruta de los archivos 
	 @return 
	 * @throws IOException 
	*/
	public final String AbrirModoTexto(String ruta) throws IOException
	{
		final StringBuilder cadena = new StringBuilder();
		
		try
		{				
			  InputStream inputStream = new FileInputStream(ruta);
			  //creating an InputStreamReader object
			  InputStreamReader isReader = new InputStreamReader(inputStream);
			  //Creating a BufferedReader object
			  BufferedReader reader = new BufferedReader(isReader);
			  String str;
			  while((str = reader.readLine())!= null){
				  cadena.append(str);
			  }

		}
		catch (RuntimeException ex)
		{
			setMensaje("No se pudo abrir el archivo");
		}

		return cadena.toString();
	}

	/** Guarda un archivo en una ubicacion que se especifica como parametro
	 La condicion es que debe de tener permisos el usuario de asp net para poderlo realizar
	 * @throws IOException 
	*/
	public final boolean Guardar(String contenido, String ruta) throws IOException
	{
		boolean resultado = false;

		try
		{
			    BufferedWriter writer = new BufferedWriter(new FileWriter(ruta));
			    writer.write(contenido);
			    writer.close();
			
			resultado = true;
		}
		catch (RuntimeException ex)
		{
			setMensaje("No se pudo guardar el archivo");
		}
		return resultado;
	}


	/** 
	 Guarda un archivo en una ubicacion que se especifica como parametro
	 La condicion es que debe de tener permisos el usuario de asp net para poderlo realizar
	 * @throws IOException 
	*/
	public final boolean Guardar(InputStream archivo, String ruta) throws IOException
	{
		boolean resultado = false;

		try
		{			
		    byte[] buffer = new byte[archivo.available()];
		    archivo.read(buffer);
		 		    
		    File destino = new File(ruta);
		    OutputStream outStream = new FileOutputStream(destino);
		    outStream.write(buffer);
		    outStream.close();
		    
		    resultado = true;
		}
		catch (RuntimeException ex)
		{
			setMensaje("No se pudo guardar el archivo");
		}
		return resultado;
	}


	/** 
	 Guarda un archivo en una ubicacion que se especifica como parametro
	 La condicion es que debe de tener permisos el usuario de asp net para poderlo realizar
	 * @throws IOException 
	*/
	public final boolean Guardar(byte[] archivo, String ruta) throws IOException
	{
		boolean resultado = false;

		try
		{
		    File destino = new File(ruta);
		    OutputStream outStream = new FileOutputStream(destino);
		    outStream.write(archivo);
		    outStream.close();
			resultado = true;
		}
		catch (RuntimeException ex)
		{
			setMensaje("No se pudo guardar el archivo");
		}
		return resultado;
	}

	/** 
	 Guarda un archivo en una ubicacion que se especifica como parametro
	 La condicion es que debe de tener permisos el usuario de asp net para poderlo realizar
	 * @throws IOException 
	*/
	public final byte[] ConvertirStreamToByte(InputStream archivo) throws IOException
	{
		byte[] archivoBytes = new byte[archivo.available()];

		try
		{
			archivo.read(archivoBytes);
		}
		catch (RuntimeException ex)
		{
			setMensaje("No se pudo convertir el objeto fuente al final, valide que tenga informacion el objeto fuente");
		}
		return archivoBytes;
	}

	public final byte[] ConvertirBase64ToByte(String file) throws IOException
	{
		byte[] archivoBytes = null;

		try
		{		   	
			archivoBytes = new sun.misc.BASE64Decoder().decodeBuffer(file);	    
		}
		catch (RuntimeException ex)
		{
			setMensaje("No se pudo convertir el objeto fuente al final, valide que tenga informacion el objeto fuente");
		}
		return archivoBytes;
	}

	public final String ConvertirBase64ToString(String valor)
	{
		String file = "";

		try
		{
			 Base64.Decoder dec = Base64.getDecoder();
			 file = new String(dec.decode(valor));
		}
		catch (RuntimeException ex)
		{
			setMensaje("No se pudo convertir el objeto fuente al final, valide que tenga informacion el objeto fuente");
		}
		return file;
	}

	public final String ConvertirByteToBase64(byte[] valor)
	{
		String file = "";

		try
		{
			Base64.Encoder enc = Base64.getEncoder();
			file = enc.encodeToString(valor);
		}
		catch (RuntimeException ex)
		{
			setMensaje("No se pudo convertir el objeto fuente al final, valide que tenga informacion el objeto fuente");
		}
		return file;
	}

	public final InputStream ConvertirByteToStream(byte[] valor)
	{
		InputStream file = null;

		try
		{
			 file= new ByteArrayInputStream(valor);	    
		}
		catch (RuntimeException ex)
		{
			setMensaje("No se pudo convertir el objeto fuente al final, valide que tenga informacion el objeto fuente");
		}
		return file;
	}
}