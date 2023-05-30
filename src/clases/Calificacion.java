package clases;

import java.io.IOException;

public class Calificacion {
	
	private Usuario usuario;
	private String comentario;
	private int calificacion;
	
	public Calificacion(Usuario usuario, String comentario, int calificacion) {
		this.usuario = usuario;
		this.comentario = comentario;
		this.calificacion = calificacion;
	}
	
	public static void agregarCalificacion(Alojamiento alojamiento,Usuario usuario, String comentario, int calificacion) throws IOException {
		JsonEngine gson = new JsonEngine();
		ColeccionDeDuenos duenos = new ColeccionDeDuenos();
		duenos = gson.cargarDuenos();
		Calificacion nuevaCalif = new Calificacion(usuario,comentario,calificacion);
		
		for(int i=0;i<duenos.getColeccionDuenos().size();i++) {
			for(int j=0;j<duenos.getColeccionDuenos().get(i).getAlojamiento().size();j++) {
				if(duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).getNombre().equals(alojamiento.getNombre())) {
					duenos.getColeccionDuenos().get(i).getAlojamiento().get(j).getCalificaciones().add(nuevaCalif);
				}
			}
		}
		
		gson.guardarDuenos(duenos);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	

}
