package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.HuellaCarbonoService;

@WebServlet("/huella")
public class HuellaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final HuellaCarbonoService service = new HuellaCarbonoService();

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // Todo el procesamiento está aquí
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Aqui recoge los datos del formulario
            String transporte = request.getParameter("transporte");
            String operacion = request.getParameter("operacion");
            String kmStr = request.getParameter("km");
            String diasStr = request.getParameter("dias");

            if (transporte != null && operacion != null && kmStr != null && diasStr != null) {
                double kmDiarios = Double.parseDouble(kmStr);
                int dias = Integer.parseInt(diasStr);

                if (kmDiarios <= 0 || dias < 1 || dias > 7)
                    throw new IllegalArgumentException("Datos fuera de rango");

                // Calcular la huella
                double kg = service.calcularSemanal(transporte, kmDiarios, dias);
                request.setAttribute("kg", kg);
                request.setAttribute("op", operacion);

               
                switch (operacion) {
                    case "CLASIFICAR_IMPACTO":
                        request.setAttribute("impacto", service.clasificarImpacto(kg));
                        break;
                    case "PROPONER_COMPENSACION":
                        request.setAttribute("comp", service.proponerCompensacion(kg));
                        break;
                }
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
        }

        // Muestra los resultados 
        request.getRequestDispatcher("/WEB-INF/views/huella.jsp").forward(request, response);
    }
}
