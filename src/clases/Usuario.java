package clases;


public class Usuario {
	private String user;
	private String clave;
	
	public Usuario(String user,String clave) {
		this.user = user;
		this.clave = clave;
	}
	
	
	
	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}

	
	public String getClave() {
		return clave;
	}

	
	public void setClave(String clave) {
		this.clave = clave;
	}
}