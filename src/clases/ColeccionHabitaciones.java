package clases;

import java.io.File;
import java.util.ArrayList;

public class ColeccionHabitaciones {
	
	private ArrayList<Habitacion> coleccionHabitacion;
	
	public ColeccionHabitaciones() {
		coleccionHabitacion = new ArrayList<Habitacion>();
	}

	public ArrayList<Habitacion> getColeccionHabitacion() {
		return coleccionHabitacion;
	}

	public void setColeccionHabitacion(ArrayList<Habitacion> coleccionHabitacion) {
		this.coleccionHabitacion = coleccionHabitacion;
	}
	
	public void agregarHabitacion(String tipo, int cPersonas, int precio) {
		this.coleccionHabitacion.add(new Habitacion(tipo, cPersonas, precio));
	}
	
	public static boolean existenHabitaciones() {
		File archivo = new File("Habitacion.json");
		if(archivo.exists())
			return true;
		else
			return false;	
	}


}
