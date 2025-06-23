package modelo.producto;

public class AlimentoMascota extends Producto {
    private String especieObjetivo;
    private String etapaVida;
    private String infoNutricional;

    public AlimentoMascota(int idProducto, String nombre, double precio, int stock, String especieObjetivo, String etapaVida, String infoNutricional) {
        super(idProducto, nombre, precio, stock, "AlimentoMascota");
        this.especieObjetivo = especieObjetivo;
        this.etapaVida = etapaVida;
        this.infoNutricional = infoNutricional;
    }


    public String getEspecieObjetivo() {
        return especieObjetivo;
    }
    public void setEspecieObjetivo(String especieObjetivo) {
        this.especieObjetivo = especieObjetivo;
    }
    public String getEtapaVida() {
        return etapaVida;
    }
    public void setEtapaVida(String etapaVida) {
        this.etapaVida = etapaVida;
    }
    public String getInfoNutricional() {
        return infoNutricional;
    }
    public void setInfoNutricional(String infoNutricional) {
        this.infoNutricional = infoNutricional;
    }

    @Override
    public String toDataString() {
        return super.toDataString() + ";AlimentoMascota;" + especieObjetivo + ";" + etapaVida + ";" + infoNutricional;
    }

}
