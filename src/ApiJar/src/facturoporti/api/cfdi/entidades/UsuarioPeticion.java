package facturoporti.api.cfdi.entidades;

public class UsuarioPeticion
{
	public UsuarioPeticion()
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

	private String IdEmpresa;
	public final String getIdEmpresa()
	{
		return IdEmpresa;
	}
	public final void setIdEmpresa(String value)
	{
		IdEmpresa = value;
	}
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
	private String RFC;
	public final String getRFC()
	{
		return RFC;
	}
	public final void setRFC(String value)
	{
		RFC = value;
	}
	private String Correo;
	public final String getCorreo()
	{
		return Correo;
	}
	public final void setCorreo(String value)
	{
		Correo = value;
	}
	private String MR;
	public final String getMR()
	{
		return MR;
	}
	public final void setMR(String value)
	{
		MR = value;
	}
	private String NuevoPassword;
	public final String getNuevoPassword()
	{
		return NuevoPassword;
	}
	public final void setNuevoPassword(String value)
	{
		NuevoPassword = value;
	}
	private int IdAplicacion;
	public final int getIdAplicacion()
	{
		return IdAplicacion;
	}
	public final void setIdAplicacion(int value)
	{
		IdAplicacion = value;
	}
	private int IdPerfil;
	public final int getIdPerfil()
	{
		return IdPerfil;
	}
	public final void setIdPerfil(int value)
	{
		IdPerfil = value;
	}
}