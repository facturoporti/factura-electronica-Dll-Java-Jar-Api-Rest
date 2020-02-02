package facturoporti.api.cfdi;


import java.util.*;

public class ConsultaEstatusPeticion
{
	private String Usuario;
	public final String getUsuario()
	{
		return Usuario;
	}
	public final void setUsuario(String value)
	{
		Usuario = value;
	}
	private String Password;
	public final String getPassword()
	{
		return Password;
	}
	public final void setPassword(String value)
	{
		Password = value;
	}
	private ArrayList<String> UUIDs;
	public final ArrayList<String> getUUIDs()
	{
		return UUIDs;
	}
	public final void setUUIDs(ArrayList<String> value)
	{
		UUIDs = value;
	}
}