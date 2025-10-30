package TestConexion;

import java.sql.Connection;

import java.sql.SQLException;

import conexion.conexion;



public class TestConexion {

	public static void main(String[] args) throws Exception {
	
		conexion conexion = new conexion();
		
		Connection con = null;
		
		try {
			
			con=(Connection) conexion.getConexion();
			System.out.println("Conexion abierta correctamente");
			con.close();
			System.out.println("Conexion cerrada correctamente");
			
		} catch (SQLException e) {
			
			System.out.println("Tratamiento del error");
			System.out.println(e.toString());
			
		}

	}}
