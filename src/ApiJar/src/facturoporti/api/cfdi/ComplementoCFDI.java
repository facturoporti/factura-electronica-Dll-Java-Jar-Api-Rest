package facturoporti.api.cfdi;

import java.util.*;

public class ComplementoCFDI
{
	private ArrayList<Pagos> Pagos;
	public final ArrayList<Pagos> getPagos()
	{
		return Pagos;
	}
	public final void setPagos(ArrayList<Pagos> value)
	{
		Pagos = value;
	}
	private Donativos Donativos;
	public final Donativos getDonativos()
	{
		return Donativos;
	}
	public final void setDonativos(Donativos value)
	{
		Donativos = value;
	}
}