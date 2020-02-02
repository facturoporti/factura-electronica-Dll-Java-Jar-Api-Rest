package facturoporti.api.cfdi.genericos;

import java.util.*;
import java.util.regex.Pattern;
import java.io.StringReader;
import java.math.*;
import java.util.List;  

import javax.xml.bind.JAXBContext;  
import javax.xml.bind.JAXBException;  
import javax.xml.bind.Unmarshaller;  

public class Utilerias
{
	private String Mensaje;
	public final String getMensaje()
	{
		return Mensaje;
	}
	public final void setMensaje(String value)
	{
		Mensaje = value;
	}
	public Utilerias()
	{
	}

	public final boolean ValidaCorreos(String cadena)
	{
		boolean validos = true;
		int indice = -1;
		String caracterSeparador = ";";

		indice = cadena.indexOf(caracterSeparador);
		if (indice > 0)
		{
			ArrayList<String> lista = (ArrayList<String>)Arrays.asList(cadena.trim().split(caracterSeparador));

			for (int contador = 0; contador < lista.size(); contador++)
			{
				if (!StringHelper.isNullOrEmpty(lista.get(contador)))
				{
					validos = ValidaEmail(lista.get(contador).trim());
					if (validos == false)
					{
						setMensaje("El correo " + lista.get(contador).trim() + " no es valido, corrija y vuelva a intentarlo.");
						break;
					}
				}
				else
				{
					setMensaje("Ingrese el correo correctamente.");
					break;
				}
			}

			return validos;
		}

		caracterSeparador = ",";
		indice = cadena.indexOf(caracterSeparador);
		if (indice > 0)
		{
			ArrayList<String> lista = (ArrayList<String>)Arrays.asList(cadena.trim().split(caracterSeparador));

			for (int contador = 0; contador < lista.size(); contador++)
			{
				if (!StringHelper.isNullOrEmpty(lista.get(contador)))
				{
					validos = ValidaEmail(lista.get(contador).trim());
					if (validos == false)
					{
						setMensaje("El correo " + lista.get(contador).trim() + " no es valido, corrija y vuelva a intentarlo.");
						break;
					}
				}
				else
				{
					setMensaje("Ingrese el correo correctamente.");
					break;
				}
			}

			return validos;
		}

		validos = ValidaEmail(cadena.trim());
		if (validos == false)
		{
			setMensaje("El correo " + cadena.trim() + " no es valido, corrija y vuelva a intentarlo.");
		}

		return validos;
	}

	public final double RegresaDecimalesXOpcion(double valor, int OpcionDecimales, short NumeroDecimales)
	{
		double tmpValor = 0;

		if (OpcionDecimales == 1) // Truncar los resultados
		{
			double formula = Math.pow(10, NumeroDecimales);
			double tmpValDecimal = valor * 100;
			tmpValor = (tmpValDecimal / formula);
		}
		else // Redondear al numero de decimales hacia arriba
		{
			tmpValor =  (new BigDecimal(String.valueOf(valor)).setScale(NumeroDecimales, BigDecimal.ROUND_HALF_UP)).doubleValue();
		}

		return tmpValor;
	}
	public final String RegresaStringDecimalesXOpcion(double valor, int OpcionDecimales, short NumeroDecimales)
	{
		return String.valueOf(RegresaDecimalesXOpcion(valor, OpcionDecimales, NumeroDecimales));
	}

	public final boolean ValidaRFC(String valor)
	{
		boolean resultado = false;
		String expresion = "";

		expresion = "[A-Za-z,�,�,&]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Za-z,0-9]?[A-Za-z,0-9]?[0-9,A-Za-z]?";

		if (StringHelper.isNullOrEmpty(valor))
		{
			valor = "";
		}

		resultado = Pattern.matches(expresion, valor);
	
		return resultado;
	}

	public final boolean ValidaEmail(String valor)
	{
		boolean resultado = false;
		String expresion = "";

		expresion = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";

		if (StringHelper.isNullOrEmpty(valor))
		{
			valor = "";
		}

		resultado = Pattern.matches(expresion, valor);
		
		return resultado;
	}
	public final boolean ValidaCURP(String valor)
	{
		boolean resultado = false;

		String expresion = "";

		expresion = "[A-Z][A,E,I,O,U,X][A-Z]{2}[0-9]{2}[0-1][0-9][0-3][0-9][M,H][A-Z]{2}[B,C,D,F,G,H,J,K,L,M,N,Ñ,P,Q,R,S,T,V,W,X,Y,Z]{3}[0-9,A-Z][0-9]"; // Version del SAT

		if (StringHelper.isNullOrEmpty(valor))
		{
			valor = "";
		}

		resultado = Pattern.matches(expresion, valor);
		
		return resultado;
	}

	public final boolean ValidaRegistroPatronal(String valor)
	{
		boolean resultado = false;
		String expresion = "";

		expresion = "([A-Z]|[a-z]|[0-9]|Ñ|ñ|!|&quot;|%|&|'|´|-|:|;|>|=|<|@|_|,|\\{|\\}|`|~|á|é|í|ó|ú|�?|É|�?|Ó|Ú|ü|Ü){1,20}";

		if (StringHelper.isNullOrEmpty(valor))
		{
			valor = "";
		}

		resultado = Pattern.matches(expresion, valor);
		
		return resultado;
	}

	public final boolean ValidaNumeroSeguroSocial(String valor)
	{
		boolean resultado = false;
		String expresion = "";

		expresion = "[0-9]{1,15}";

		if (StringHelper.isNullOrEmpty(valor))
		{
			valor = "";
		}

		resultado = Pattern.matches(expresion, valor);
		
		return resultado;
	}
}