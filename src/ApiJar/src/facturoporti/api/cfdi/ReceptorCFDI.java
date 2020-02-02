package facturoporti.api.cfdi;

public class ReceptorCFDI
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
	private String UsoCFDI;
	public final String getUsoCFDI()
	{
		return UsoCFDI;
	}
	public final void setUsoCFDI(String value)
	{
		UsoCFDI = value;
	}
	private DireccionCFDI Direccion;
	public final DireccionCFDI getDireccion()
	{
		return Direccion;
	}
	public final void setDireccion(DireccionCFDI value)
	{
		Direccion = value;
	}
}