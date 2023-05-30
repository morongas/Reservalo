package clases;

public class Administrador extends Usuario{
	
	private int codigo;

	
	public Administrador(String user, String clave) {
		super(user,clave);
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
