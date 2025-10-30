package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

import model.pelicula;
import model.usuarioPelicula;
import service.CineService;

@WebServlet("/cine")
public class CineController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CineService service = new CineService();

  
    private static final List<String> GENEROS = Arrays.asList("ACCIÓN", "COMEDIA", "DRAMA", "CIENCIA FICCIÓN", "TERROR");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("operacion");
        String alias = request.getParameter("alias");
        String genero = request.getParameter("genero");
        String valor = request.getParameter("valoracion");

        try {
            switch (op) {
                case "REGISTRAR": {
                    if (alias != null && genero != null && valor != null) {
                        usuarioPelicula up = new usuarioPelicula(0, alias, 0,
                                Double.parseDouble(valor), java.time.LocalDateTime.now());
                        service.registrarPreferencia(up);
                        request.getSession().setAttribute("flash", "Preferencia registrada correctamente.");
                        request.getSession().setAttribute("aliasActual", alias);
                        request.getSession().setAttribute("generoActual", genero);
                        request.getSession().setAttribute("valoracionActual", valor);
                    }
                    response.sendRedirect("cine?op=HOME");
                    break;
                }

                case "RECOMENDAR": {
                    Optional<pelicula> recomendada = service.recomendarPelicula(genero);
                    if (recomendada.isPresent()) {
                        request.getSession().setAttribute("recomendacion", recomendada.get());
                        request.getSession().setAttribute("flash", "Película recomendada encontrada.");
                    } else {
                        request.getSession().setAttribute("flash", "No se encontró ninguna película para ese género.");
                    }
                    response.sendRedirect("cine?op=HOME");
                    break;
                }

                case "CONSULTAR_HISTORIAL": {
                    request.getSession().setAttribute("aliasActual", alias);
                    response.sendRedirect("cine?op=CONSULTAR_HISTORIAL&alias=" + alias);
                    break;
                }

                case "BORRAR_HISTORIAL": {
                    int n = service.borrarHistorial(alias);
                    request.getSession().setAttribute("flash", n + " registros eliminados para " + alias);
                    response.sendRedirect("cine?op=HOME");
                    break;
                }

                case "TOP_PELICULAS": {
                    response.sendRedirect("cine?op=TOP_PELICULAS");
                    break;
                }

                default:
                    response.sendRedirect("cine?op=HOME");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("flash", "Error: " + e.getMessage());
            response.sendRedirect("cine?op=HOME");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        CineService service = new CineService();
        ArrayList <pelicula> peliculas = service.allPeliculas();
        if (op == null) op = "HOME";
        
        request.setAttribute("peliculas", peliculas);
        
       

        HttpSession sesion = request.getSession();

        try {
            switch (op) {
                case "HOME": {
                    request.setAttribute("generos", GENEROS);
                    request.setAttribute("flash", sesion.getAttribute("flash"));
                    request.setAttribute("aliasActual", sesion.getAttribute("aliasActual"));
                    request.setAttribute("generoActual", sesion.getAttribute("generoActual"));
                    request.setAttribute("valoracionActual", sesion.getAttribute("valoracionActual"));
                    request.setAttribute("recomendacion", sesion.getAttribute("recomendacion"));

                    String alias = (String) sesion.getAttribute("aliasActual");
                    if (alias != null) {
                        List<usuarioPelicula> ultimas = service.consultarHistorial(alias);
                        if (ultimas.size() > 5) ultimas = ultimas.subList(0, 5);
                        request.setAttribute("ultimas", ultimas);
                    }

                    // Limpiar flash tras leer
                    sesion.removeAttribute("flash");
                    getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
                    break;
                }

                case "CONSULTAR_HISTORIAL": {
                    String alias = request.getParameter("alias");
                    if (alias == null) alias = (String) sesion.getAttribute("aliasActual");
                    List<usuarioPelicula> historial = service.consultarHistorial(alias);

                    request.setAttribute("alias", alias);
                    request.setAttribute("historial", historial);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/historial.jsp").forward(request, response);
                    break;
                }

                case "BORRAR_HISTORIAL": {
                    String alias = request.getParameter("alias");
                    if (alias != null) {
                        int n = service.borrarHistorial(alias);
                        sesion.setAttribute("flash", n + " registros eliminados para " + alias);
                    }
                    response.sendRedirect("cine?op=HOME");
                    break;
                }

                case "TOP_PELICULAS": {
                    List<pelicula> top = service.topPeliculas(10);
                    request.setAttribute("topPeliculas", top);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/top.jsp").forward(request, response);
                    break;
                }

                default:
                    getServletContext().getRequestDispatcher("/WEB-INF/views/inicio.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sesion.setAttribute("flash", "Error en la operación: " + e.getMessage());
            response.sendRedirect("cine?op=HOME");
        }
    }
}
