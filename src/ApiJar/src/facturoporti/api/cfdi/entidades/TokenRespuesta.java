package facturoporti.api.cfdi.entidades;

public class TokenRespuesta extends RespuestaBase
{
	private String Token;
	public final String getToken()
	{
		return Token;
	}
	public final void setToken(String value)
	{
		Token = value;
	}
	private String CorreoUsuario;
	public final String getCorreoUsuario()
	{
		return CorreoUsuario;
	}
	public final void setCorreoUsuario(String value)
	{
		CorreoUsuario = value;
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
	private String IdEmpresaPadre;
	public final String getIdEmpresaPadre()
	{
		return IdEmpresaPadre;
	}
	public final void setIdEmpresaPadre(String value)
	{
		IdEmpresaPadre = value;
	}
}