package clases;

import java.io.File;
import java.util.ArrayList;

public class ColeccionDeDuenos {
	private ArrayList<Dueno> coleccionDuenos;
	

	public ColeccionDeDuenos() {
		coleccionDuenos = new ArrayList<Dueno>();
	}


	public ArrayList<Dueno> getColeccionDuenos() {
		return coleccionDuenos;
	}
	
	public void agregarDueno(Dueno u) {
		coleccionDuenos.add(u);
	}
	
	public Dueno getDueno(int numero) {
		return coleccionDuenos.get(numero);
	}
	
	public Dueno getDueno(String user) {
		for(int i=0; i<coleccionDuenos.size();i++) {
			if(coleccionDuenos.get(i).getUser().equals(user))
				return coleccionDuenos.get(i);
		}
		return null;
	}
	
	public static boolean existenUsuarios() {
		File archivo = new File("Duenos.json");
		if(archivo.exists())
			return true;
		else
			return false;	
	}
	
	public boolean existeUsuario(String user) {
		for(int i=0;i<coleccionDuenos.size();i++) {
			if(coleccionDuenos.get(i).getUser().equals(user))
				return true;
		}
		return false;
	}

	public Alojamiento buscarAlojamiento(String nombre) {
		for(int i=0;i<coleccionDuenos.size();i++) {
			for(int j=0;j<coleccionDuenos.get(i).getAlojamiento().size();j++) {
				if(coleccionDuenos.get(i).getAlojamiento().get(j).getNombre().equals(nombre)) {
					return coleccionDuenos.get(i).getAlojamiento().get(j);
				}
			}
		}
		return null;
	}
}
