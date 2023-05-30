package clases;

public class Clave {
	
	public static String encriptarClave(String clave) {
		String claveEncriptada = "";
		int aux = 0;
		int numeroCaracter;
		while(aux<clave.length()) {
			numeroCaracter = clave.charAt(aux) + 1;
			claveEncriptada = claveEncriptada + Integer.toString(numeroCaracter) + " ";
			aux++;
		}
		return claveEncriptada;
	}
	
	public static String desencriptarClave(String claveEncriptada) {
		int aux = 0;
		String clave = "";
		int numeroCaracter;
		String caracter = "";
		char letra;
		while(aux<claveEncriptada.length()) {
			while(claveEncriptada.charAt(aux)!=' ') {
				caracter = caracter + Character.toString(claveEncriptada.charAt(aux)); 
				aux++;
			}
			aux++;
			numeroCaracter = Integer.parseInt(caracter) - 1;
			letra = (char) numeroCaracter;
			clave = clave + Character.toString(letra);
			caracter = "";
		}
		return clave;
	}
	
	public static boolean verificarClave(String clave) {
		if(clave.length() < 6)
			return false;
		else
			return true;
		
	}

}
