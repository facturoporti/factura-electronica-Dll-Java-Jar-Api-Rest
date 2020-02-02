package facturoporti.api.cfdi;

import java.util.*;

public class CFDIPeticion
{
	private DatosGeneralesCFDI DatosGenerales;
	private EncabezadoCFDI Encabezado;
	private ArrayList<ConceptoCFDI> Conceptos;
	private ComplementoCFDI ComplementoCFDI;

	public final DatosGeneralesCFDI getDatosGenerales()
	{
		return this.DatosGenerales;
	}
	public final void setDatosGenerales(DatosGeneralesCFDI value)
	{
		this.DatosGenerales = value;
	}
	public final EncabezadoCFDI getEncabezado()
	{
		return this.Encabezado;
	}
	public final void setEncabezado(EncabezadoCFDI value)
	{
		this.Encabezado = value;
	}
	public final ArrayList<ConceptoCFDI> getConceptos()
	{
		return this.Conceptos;
	}
	public final void setConceptos(ArrayList<ConceptoCFDI> value)
	{
		this.Conceptos = value;
	}
	public final ComplementoCFDI getComplemento()
	{
		return this.ComplementoCFDI;
	}
	public final void setComplemento(ComplementoCFDI value)
	{
		this.ComplementoCFDI = value;
	}
}