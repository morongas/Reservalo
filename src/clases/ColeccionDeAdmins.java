package clases;

import java.util.ArrayList;

public class ColeccionDeAdmins {
	private ArrayList<Administrador> coleccionAdmins;
	

	public ColeccionDeAdmins() {
		coleccionAdmins = new ArrayList<Administrador>();
	}


	public ArrayList<Administrador> getAdmins() {
		return coleccionAdmins;
	}
		
	public Administrador getAdmin(int numero) {
		return coleccionAdmins.get(numero);
	}
	
	public Administrador getAdmin(String user) {
		for(int i=0;i<coleccionAdmins.size();i++) {
			if(coleccionAdmins.get(i).getUser().equals(user)) {
				return coleccionAdmins.get(i);
			}
		}
		return null;
	}
	
	public boolean existeAdmin(String user) {
		for(int i=0;i<coleccionAdmins.size();i++) {
			if(coleccionAdmins.get(i).getUser().equals(user)) {
				return true;
			}
		}
		return false;
	}
}
