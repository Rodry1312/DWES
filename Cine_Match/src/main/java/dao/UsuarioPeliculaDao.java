package dao;



import conexion.conexion;
import model.usuarioPelicula;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;





public class UsuarioPeliculaDao {


public void insert(usuarioPelicula up) {
String sql = "INSERT INTO usuario_pelicula(alias,pelicula_id,valoracion) VALUES(?,?,?)";
try (Connection conn = conexion.getConexion();
PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setString(1, up.getAlias());
ps.setInt(2, up.getPeliculaId());
ps.setDouble(3, up.getValoracion());
ps.executeUpdate();
try (ResultSet keys = ps.getGeneratedKeys()) {
if (keys.next()) up.setId(keys.getLong(1));
}
} catch (SQLException e) 
{ e.printStackTrace(); }
catch (Exception e1) {
	
	e1.printStackTrace();
}
}


public ArrayList<usuarioPelicula> findByAlias(String alias) {
String sql = "SELECT id, alias, pelicula_id, valoracion, fecha_registro FROM usuario_pelicula WHERE alias = ? ORDER BY fecha_registro DESC";
ArrayList<usuarioPelicula> lista = new ArrayList<>();
try (Connection conn = conexion.getConexion();
PreparedStatement ps = conn.prepareStatement(sql)) {
ps.setString(1, alias);
try (ResultSet rs = ps.executeQuery()) {
while (rs.next()) {
usuarioPelicula up = new usuarioPelicula();
up.setId(rs.getLong("id"));
up.setAlias(rs.getString("alias"));
up.setPeliculaId(rs.getInt("pelicula_id"));
up.setValoracion(rs.getDouble("valoracion"));
Timestamp ts = rs.getTimestamp("fecha_registro");
if (ts != null) up.setFechaRegistro(ts.toLocalDateTime());
lista.add(up);
}
}
} catch (SQLException e) { e.printStackTrace(); } 
catch (Exception e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
return lista;
}


public int deleteByAlias(String alias) {
String sql = "DELETE FROM usuario_pelicula WHERE alias = ?";
try (Connection conn = conexion.getConexion();
PreparedStatement ps = conn.prepareStatement(sql)) {
ps.setString(1, alias);
return ps.executeUpdate();
} catch (SQLException e)
{ e.printStackTrace(); }
catch (Exception e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
return 0;
}
}