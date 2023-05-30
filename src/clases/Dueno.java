package clases;

import java.util.ArrayList;

public class Dueno extends Usuario {
	
	private ArrayList<Alojamiento> alojamiento;

	public Dueno(String user, String clave) {
		super(user,clave);
		alojamiento = new ArrayList<Alojamiento>();
	}

	public ArrayList<Alojamiento> getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento.add(alojamiento);
	}
	

}
