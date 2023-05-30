package clases;

import java.io.File;
import java.util.ArrayList;


public class ColeccionUsuariosNormales {
	
	private ArrayList<Usuario> coleccionUsuariosNormales;
	

	public ColeccionUsuariosNormales() {
		coleccionUsuariosNormales = new ArrayList<Usuario>();
	}


	public ArrayList<Usuario> getColeccionUsuarios() {
		return coleccionUsuariosNormales;
	}
	
	public void agregarUsuario(Usuario u) {
		coleccionUsuariosNormales.add(u);
	}
	
	public Usuario getUsuario(int numero) {
		return coleccionUsuariosNormales.get(numero);
	}
	
	public Usuario getUsuario(String user) {
		for(int i=0;i<coleccionUsuariosNormales.size();i++) {
			if(coleccionUsuariosNormales.get(i).getUser().equals(user)) {
				return coleccionUsuariosNormales.get(i);
			}
		}
		return null;
	}
	
	public static boolean existenUsuarios() {
		File archivo = new File("UsuariosNormales.json");
		if(archivo.exists())
			return true;
		else
			return false;	
	}
	
	public boolean existeUsuario(String user) {
		for(int i=0;i<coleccionUsuariosNormales.size();i++) {
			if(coleccionUsuariosNormales.get(i).getUser().toUpperCase().equals(user.toUpperCase()))
				return true;
		}
		return false;
	}

}
