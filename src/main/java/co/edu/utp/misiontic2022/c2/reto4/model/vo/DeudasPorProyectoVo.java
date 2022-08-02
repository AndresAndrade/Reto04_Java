package co.edu.utp.misiontic2022.c2.reto4.model.vo;

public class DeudasPorProyectoVo {

    private Integer idProyecto;
    private Double valor;

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "DeudasPorProyectoVo{" +
                "idProyecto=" + idProyecto +
                ", valor=" + valor +
                '}';
    }
}
