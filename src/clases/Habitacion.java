package clases;

public class Habitacion {
	
	private String tipo;
	private int cPersonas;
	private int precio;

	public String getTipo() {
		return tipo;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getcPersonas() {
		return cPersonas;
	}

	public void setcPersonas(int cPersonas) {
		this.cPersonas = cPersonas;
	}
	
	public Habitacion(String tipo, int cPersonas, int precio) {
		this.tipo = tipo;
		this.cPersonas = cPersonas;
		this.precio = precio;
	}
	public String toString() {
        return tipo ;
    }

}
