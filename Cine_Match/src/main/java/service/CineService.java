package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dao.PelicuaDao;
import dao.UsuarioPeliculaDao;
import model.pelicula;
import model.usuarioPelicula;

public class CineService {

    private PelicuaDao peliculaDao = new PelicuaDao();
    private UsuarioPeliculaDao usuarioPeliculaDao = new UsuarioPeliculaDao();

    // Registro de preferencia de usuario
    public void registrarPreferencia(usuarioPelicula up) {
        usuarioPeliculaDao.insert(up);
    }

    // Obtener mejor película del género
    public Optional<pelicula> recomendarPelicula(String genero) {
        return peliculaDao.findBestByGenero(genero);
    }
    
    public ArrayList<pelicula> allPeliculas(){
    	
    	PelicuaDao dao = new PelicuaDao();
    	
    	
    	return dao.getAllPeliculas();
    }

    // Consultar historial del usuario
    public List<usuarioPelicula> consultarHistorial(String alias) {
        return usuarioPeliculaDao.findByAlias(alias);
    }

    // Borrar historial del usuario
    public int borrarHistorial(String alias) {
        return usuarioPeliculaDao.deleteByAlias(alias);
    }

    // Top global
    public List<pelicula> topPeliculas(int limite) {
        return peliculaDao.findTopRated(limite);
    }

    // Listar películas por género (para referencias si lo necesitás)
    public List<pelicula> listarPorGenero(String genero) throws Exception {
        return peliculaDao.findByGenero(genero);
    }
}
