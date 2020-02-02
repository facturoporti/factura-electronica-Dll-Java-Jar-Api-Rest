package facturoporti.api.cfdi;

public class ConsultaTimbresRestantesRespuesta extends WCFRespuesta
{
	private String FechaCompra = null;
	public final String getFechaCompra()
	{
		return FechaCompra;
	}
	public final void setFechaCompra(String value)
	{
		this.FechaCompra = value;
	}

	private int TimbresUtilizados;
	public final int getTimbresUtilizados()
	{
		return TimbresUtilizados;
	}
	public final void setTimbresUtilizados(int value)
	{
		this.TimbresUtilizados = value;
	}

	private int CreditosRestantes;

	public final int getCreditosRestantes()
	{
		return CreditosRestantes;
	}
	public final void setCreditosRestantes(int value)
	{
		this.CreditosRestantes = value;
	}
}