package clases;

public class Fecha {
	
	private String dia;
	private String mes;
	private String anio;
	
	public Fecha(String dia, String mes, String anio) {
		this.dia=dia;
		this.mes=mes;
		this.anio=anio;
	}
	
	public boolean iguales(String dia, String mes, String anio) {
		
		if (this.dia.equals(dia) && this.mes.equals(mes) && this.anio.equals(anio)) {
			return true;
		} else {return false;}
		
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}
	

	public void setAnio(String anio) {
		this.anio = anio;
	}

}
