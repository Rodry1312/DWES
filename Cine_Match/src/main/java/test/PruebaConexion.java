package test;

import java.sql.Connection;

import conexion.conexion;

public class PruebaConexion {

	public static void main(String[] args) {
		 try {
	            conexion conexion = new conexion();
	            Connection con = conexion.getConexion();

	            if (con != null) {
	                System.out.println(" Conexión establecida correctamente a la base de datos Oracle.");
	                con.close();
	            } else {
	                System.out.println(" No se pudo conectar a la base de datos.");
	            }
	        } catch (Exception e) {
	            System.out.println(" Error: " + e.getMessage());
	            e.printStackTrace();
	        
	     }
	}
}
