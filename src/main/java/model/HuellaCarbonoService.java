package model;

public class HuellaCarbonoService {

    public double calcularSemanal(String transporte, double kmDiarios, int diasSemanales) {
        double factor;
        switch (transporte.toUpperCase()) {
            case "COCHE": factor = 0.21; break;
            case "AUTOBUS": factor = 0.10; break;
            case "TREN": factor = 0.04; break;
            case "BICI":
            case "PIE": factor = 0.0; break;
            default: throw new IllegalArgumentException("Transporte no válido");
        }
        return factor * kmDiarios * diasSemanales;
    }

    public String clasificarImpacto(double kgSem) {
        if (kgSem <= 5) return "Baja";
        else if (kgSem <= 15) return "Media";
        else return "Alta";
    }

    public String proponerCompensacion(double kgSem) {
        double arboles = kgSem / 0.40;
        double kmBici = kgSem / 0.21;
        return String.format(
            "Para compensar %.2f kg: ~%.1f árboles (anuales) o %.1f km en bici esta semana.",
            kgSem, arboles, kmBici
        );
    }
}

