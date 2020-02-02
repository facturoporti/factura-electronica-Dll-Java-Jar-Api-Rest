package facturoporti.api.cfdi;

import java.util.*;
import java.math.*;

public class Pagos
{
	private String FechaPago;
	public final String getFechaPago()
	{
		return FechaPago;
	}
	public final void setFechaPago(String value)
	{
		FechaPago = value;
	}

	private String FormaPago;
	public final String getFormaPago()
	{
		return FormaPago;
	}
	public final void setFormaPago(String value)
	{
		FormaPago = value;
	}

	private String Moneda;
	public final String getMoneda()
	{
		return Moneda;
	}
	public final void setMoneda(String value)
	{
		Moneda = value;
	}

	private BigDecimal TipoCambio = null;
	public final BigDecimal getTipoCambio()
	{
		return TipoCambio;
	}
	public final void setTipoCambio(BigDecimal value)
	{
		TipoCambio = value;
	}

	private String BancoExtranjero;
	public final String getBancoExtranjero()
	{
		return BancoExtranjero;
	}
	public final void setBancoExtranjero(String value)
	{
		BancoExtranjero = value;
	}

	private String NumeroOperacion;
	public final String getNumeroOperacion()
	{
		return NumeroOperacion;
	}
	public final void setNumeroOperacion(String value)
	{
		NumeroOperacion = value;
	}

	private String RFCCtaOrdenante;
	public final String getRFCCtaOrdenante()
	{
		return RFCCtaOrdenante;
	}
	public final void setRFCCtaOrdenante(String value)
	{
		RFCCtaOrdenante = value;
	}

	private String CuentaOrdenante;
	public final String getCuentaOrdenante()
	{
		return CuentaOrdenante;
	}
	public final void setCuentaOrdenante(String value)
	{
		CuentaOrdenante = value;
	}

	private String RFCCtaBeneficiario;
	public final String getRFCCtaBeneficiario()
	{
		return RFCCtaBeneficiario;
	}
	public final void setRFCCtaBeneficiario(String value)
	{
		RFCCtaBeneficiario = value;
	}

	private String CuentaBeneficiario;
	public final String getCuentaBeneficiario()
	{
		return CuentaBeneficiario;
	}
	public final void setCuentaBeneficiario(String value)
	{
		CuentaBeneficiario = value;
	}

	private ArrayList<DocumentoRelacionadosPagos> DocumentosRelacionados;
	public final ArrayList<DocumentoRelacionadosPagos> getDocumentosRelacionados()
	{
		return DocumentosRelacionados;
	}
	public final void setDocumentosRelacionados(ArrayList<DocumentoRelacionadosPagos> value)
	{
		DocumentosRelacionados = value;
	}

	private String Adicional_1;
	public final String getAdicional_1()
	{
		return Adicional_1;
	}
	public final void setAdicional_1(String value)
	{
		Adicional_1 = value;
	}

	private String Adicional_2;
	public final String getAdicional_2()
	{
		return Adicional_2;
	}
	public final void setAdicional_2(String value)
	{
		Adicional_2 = value;
	}

	private String Adicional_3;
	public final String getAdicional_3()
	{
		return Adicional_3;
	}
	public final void setAdicional_3(String value)
	{
		Adicional_3 = value;
	}

	private String Adicional_4;
	public final String getAdicional_4()
	{
		return Adicional_4;
	}
	public final void setAdicional_4(String value)
	{
		Adicional_4 = value;
	}

	private String Adicional_5;
	public final String getAdicional_5()
	{
		return Adicional_5;
	}
	public final void setAdicional_5(String value)
	{
		Adicional_5 = value;
	}

	private String Adicional_6;
	public final String getAdicional_6()
	{
		return Adicional_6;
	}
	public final void setAdicional_6(String value)
	{
		Adicional_6 = value;
	}

	private String Adicional_7;
	public final String getAdicional_7()
	{
		return Adicional_7;
	}
	public final void setAdicional_7(String value)
	{
		Adicional_7 = value;
	}

	private String Adicional_8;
	public final String getAdicional_8()
	{
		return Adicional_8;
	}
	public final void setAdicional_8(String value)
	{
		Adicional_8 = value;
	}

	private String Adicional_9;
	public final String getAdicional_9()
	{
		return Adicional_9;
	}
	public final void setAdicional_9(String value)
	{
		Adicional_9 = value;
	}

	private String Adicional_10;
	public final String getAdicional_10()
	{
		return Adicional_10;
	}
	public final void setAdicional_10(String value)
	{
		Adicional_10 = value;
	}
}