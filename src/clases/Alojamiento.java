package clases;

import java.io.File;
import java.util.ArrayList;

public class Alojamiento {
	
	private String nombre;
	private int tipoDeAlojamiento; //0 hotel, 1 posada, 2 apartamento
	private String direccion;
	private String telefono;
	private int rif;
	private int codigoPostal;
	private String estado;
	
	private boolean verificado;
	private boolean solicitoVerificacion;	
	private ArrayList<String> imagenes;
	int cHabitaciones;
	private ColeccionHabitaciones habitaciones;
	private String promocion;
	
	private ArrayList<Calificacion> calificaciones;
	private ArrayList<Fecha> fechas; //en esta lista solo estaran las fechas que estan ocupadas (no disp)
	private ArrayList<SolicitudActiva> solicitudesActivas;
	
	
	public boolean verificarFechaOcupada(Fecha f) { //verifica si una fecha en concreto esta ocupada
		for (Fecha x:this.fechas) {
			if (x.iguales(f.getDia(),f.getMes(),f.getAnio())) {	
				return true;
			}
		}
		return false;
	}
	
	public void eliminarFecha(String dia, String mes, String anio) {
		
		for (int i=0; i<this.fechas.size(); i++) {
			if (fechas.get(i).iguales(dia, mes, anio)) {
				this.fechas.remove(i);
			}
		}
	}
	
	public ArrayList<Fecha> getFechas() {
		return fechas;
	}

	public void setFechas(ArrayList<Fecha> fechas) {
		this.fechas = fechas;
	}

	public void setCalificaciones(ArrayList<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public int getcHabitaciones() {
		return cHabitaciones;
	}

	

	public String getPromocion() {
		return promocion;
	}



	public void setPromocion(String promocion) {
		this.promocion = promocion;
	}



	public void setcHabitaciones(int cHabitaciones) {
		this.cHabitaciones = cHabitaciones;
	}
	
	//para saber cuantas habitaciones tiene un alojamiento se recorre la coleccion

	public Alojamiento(String nombre,int tAlojamiento, String direccion, String telefono, int cHab,
			ColeccionHabitaciones habitaciones, String promo) {
		this.nombre = nombre;
		this.tipoDeAlojamiento = tAlojamiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cHabitaciones = cHab;
		this.habitaciones = habitaciones;
		this.imagenes = new ArrayList<String>();
		this.promocion = promo;
		this.calificaciones = new ArrayList<Calificacion>();
		this.fechas = new ArrayList<Fecha>();
		this.solicitudesActivas = new ArrayList<SolicitudActiva>();

	}

	public ArrayList<SolicitudActiva> getSolicitudesActivas() {
		return solicitudesActivas;
	}

	public void setSolicitudesActivas(ArrayList<SolicitudActiva> solicitudesActivas) {
		this.solicitudesActivas = solicitudesActivas;
	}
	
	public void eliminarReserva(String nombre) {
		for (int i = 0; i<(this.solicitudesActivas.size()); i++) {
		//for (SolicitudActiva s: this.solicitudesActivas) {	
			if (this.solicitudesActivas.get(i).getNombre().equals(nombre))
				this.solicitudesActivas.remove(i);
		}
	}

	public Alojamiento() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public int getTipoDeAlojamiento() {
		return tipoDeAlojamiento;
	}
	
	public String getTipoDeAlojamientoString() { //0 hotel, 1 posada, 2 apartamento
		if (this.tipoDeAlojamiento == 0)
			return "Hotel";		
		if (this.tipoDeAlojamiento == 1)
			return "Posada";
		if (this.tipoDeAlojamiento == 2)
			return "Apartamento";
					
		return null;
	}
	

	public void setTipoDeAlojamiento(int tipoDeAlojamiento) {
		this.tipoDeAlojamiento = tipoDeAlojamiento;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ColeccionHabitaciones getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(ColeccionHabitaciones habitaciones) {
		this.habitaciones = habitaciones;
	}
	
	public static boolean existeTemporal() {
		File archivo = new File("Temporal.json");
		if(archivo.exists())
			return true;
		else
			return false;	
	}
	
	public static boolean existeTemporal2() {
		File archivo = new File("Temporal2.json");
		if(archivo.exists())
			return true;
		else
			return false;	
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}
	

	public boolean isSolicitoVerificacion() {
		return solicitoVerificacion;
	}

	public void setSolicitoVerificacion(boolean solicitoVerificacion) {
		this.solicitoVerificacion = solicitoVerificacion;
	}

	public ArrayList<String> getImagenes() {
		return imagenes;
	}

	public void setImagenes(ArrayList<String> imagenes) {
		this.imagenes = imagenes;
	}

	public int getRif() {
		return rif;
	}

	public void setRif(int rif) {
		this.rif = rif;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
