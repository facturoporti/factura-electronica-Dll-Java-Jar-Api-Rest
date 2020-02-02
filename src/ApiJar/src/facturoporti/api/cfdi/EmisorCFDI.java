package facturoporti.api.cfdi;

import java.util.*;

public class EmisorCFDI
{
	private String RFC;
	public final String getRFC()
	{
		return RFC;
	}
	public final void setRFC(String value)
	{
		RFC = value;
	}
	private String NombreRazonSocial;
	public final String getNombreRazonSocial()
	{
		return NombreRazonSocial;
	}
	public final void setNombreRazonSocial(String value)
	{
		NombreRazonSocial = value;
	}
	private String RegimenFiscal;
	public final String getRegimenFiscal()
	{
		return RegimenFiscal;
	}
	public final void setRegimenFiscal(String value)
	{
		RegimenFiscal = value;
	}
	private ArrayList<DireccionCFDI> Direccion;
	public final ArrayList<DireccionCFDI> getDireccion()
	{
		return Direccion;
	}
	public final void setDireccion(ArrayList<DireccionCFDI> value)
	{
		Direccion = value;
	}
}