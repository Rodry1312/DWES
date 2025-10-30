package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import conexion.conexion;
import model.pelicula;

public class PelicuaDao {
	
	

	public PelicuaDao() {
		super();
	}


	public ArrayList<pelicula> getAllPeliculas(){
		conexion conexion=new conexion();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		ArrayList<pelicula> listadoPeliculas = new ArrayList<pelicula>();
		
	
	
			try {
				con= conexion.getConexion();
			
				String query ="Select * from pelicula";
				
				ps= con.prepareStatement(query);
				
				rs=ps.executeQuery(query);
						
			    while (rs.next()) {
			        listadoPeliculas.add(map(rs));
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

				return listadoPeliculas;
		
	}
	

			public ArrayList<pelicula> findByGenero(String genero) throws Exception {
			String sql = "SELECT id, titulo, genero, puntuacion_media FROM pelicula WHERE genero = ?";
			ArrayList<pelicula> lista = new ArrayList<>();
			try (Connection conn= conexion.getConexion();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, genero);
			try (ResultSet rs = ps.executeQuery()) {
			while (rs.next()) lista.add(map(rs));
			}
			} catch (SQLException e) { e.printStackTrace(); }
			return lista;
			}


			public Optional<pelicula> findBestByGenero(String genero)  {
			String sql = "SELECT id, titulo, genero, puntuacion_media FROM pelicula WHERE genero = ? ORDER BY puntuacion_media DESC LIMIT 1";
			try (Connection conn = conexion.getConexion();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, genero);
			try (ResultSet rs = ps.executeQuery()) {
			if (rs.next()) return Optional.of(map(rs));
			}
			} catch (SQLException e) { e.printStackTrace(); } 
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return Optional.empty();
			}


			public ArrayList<pelicula> findTopRated(int limit)  {
			String sql = "SELECT id, titulo, genero, puntuacion_media FROM pelicula ORDER BY puntuacion_media DESC LIMIT ?";
			ArrayList<pelicula> lista = new ArrayList<>();
			try (Connection conn = conexion.getConexion();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, limit);
			try (ResultSet rs = ps.executeQuery()) {
			while (rs.next()) lista.add(map(rs));
			}
			} catch (SQLException e) 
			{ e.printStackTrace(); }
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return lista;
			}


			private pelicula map(ResultSet rs) throws SQLException {
			return new pelicula(rs.getInt("id"), rs.getString("titulo"), rs.getString("genero"), rs.getDouble("puntuacion_media"));
			}
			}

