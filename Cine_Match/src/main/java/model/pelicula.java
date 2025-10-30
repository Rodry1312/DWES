package model;

public class pelicula {
	private int id;
	private String titulo;
	private String genero;
	private double puntuacionMedia;
	
	
	
	
	public pelicula(int id, String titulo, String genero, double puntuacionMedia) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.puntuacionMedia = puntuacionMedia;
	}


	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getTitulo() {
		return titulo;
	}




	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}




	public String getGenero() {
		return genero;
	}




	public void setGenero(String genero) {
		this.genero = genero;
	}




	public double getPuntuacionMedia() {
		return puntuacionMedia;
	}




	public void setPuntuacionMedia(double puntuacionMedia) {
		this.puntuacionMedia = puntuacionMedia;
	}


	@Override
	public String toString() {
		return "pelicula [id=" + id + ", titulo=" + titulo + ", genero=" + genero + ", puntuacionMedia="
				+ puntuacionMedia + "]";
	}
	
	
}
