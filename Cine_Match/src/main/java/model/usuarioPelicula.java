package model;

import java.time.LocalDateTime;

public class usuarioPelicula {
	private long id;
	private String alias;
	private int peliculaId;
	private double valoracion;
	private LocalDateTime fechaRegistro;
	
	
	public usuarioPelicula(long id, String alias, int peliculaId, double valoracion, LocalDateTime fechaRegistro) {
		super();
		this.id = id;
		this.alias = alias;
		this.peliculaId = peliculaId;
		this.valoracion = valoracion;
		this.fechaRegistro = fechaRegistro;
	}


	public usuarioPelicula() {
	
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public int getPeliculaId() {
		return peliculaId;
	}


	public void setPeliculaId(int peliculaId) {
		this.peliculaId = peliculaId;
	}


	public double getValoracion() {
		return valoracion;
	}


	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}


	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
}
