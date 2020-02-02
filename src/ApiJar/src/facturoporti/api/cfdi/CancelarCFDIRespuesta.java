package facturoporti.api.cfdi;

import facturoporti.api.cfdi.entidades.*;
import java.util.*;

public class CancelarCFDIRespuesta extends WCFRespuesta
{
	private ArrayList<FoliosRespuesta> FoliosRespuesta;
	public final ArrayList<FoliosRespuesta> getFoliosRespuesta()
	{
		return this.FoliosRespuesta;
	}
	public final void setFoliosRespuesta(ArrayList<FoliosRespuesta> value)
	{
		this.FoliosRespuesta = value;
	}
}