package co.edu.utp.misiontic2022.c2.reto4.view;

import co.edu.utp.misiontic2022.c2.reto4.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;

import java.util.List;

public class ReportesView {

    private static final ReportesController controller = new ReportesController();

    private String repitaCaracter(Character caracter, Integer veces) {
        String respuesta = "";
        for (int i = 0; i < veces; i++) {
            respuesta += caracter;
        }
        return respuesta;
    }

    public void proyectosFinanciadosPorBanco(String banco) {
        System.out.println(repitaCaracter('=', 36) + " LISTADO DE PROYECTOS POR BANCO "
                + repitaCaracter('=', 37));
        if (banco != null && !banco.isBlank()) {
            System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s",
                    "ID", "CONSTRUCTORA", "CIUDAD", "CLASIFICACION", "ESTRATO", "LIDER"));
            System.out.println(repitaCaracter('-', 105));

            List<ProyectoBancoVo> listaProyectos = controller.buscarProyectosBanco(banco);
            for (ProyectoBancoVo proyecto : listaProyectos) {
                System.out.println(proyecto);
            }
        }
    }

    public void totalAdeudadoPorProyectosSuperioresALimite(Double limiteInferior) {
        System.out.println(repitaCaracter('=', 1) + " TOTAL DEUDAS POR PROYECTO "
                + repitaCaracter('=', 1));
        if (limiteInferior != null) {
            System.out.println(String.format("%3s %15s", "ID", "VALOR "));
            System.out.println(repitaCaracter('-', 29));

            List<DeudasPorProyectoVo> listaDeudas = controller.buscarDeudasPorProyecto(limiteInferior);
            for (DeudasPorProyectoVo proyecto : listaDeudas) {
                System.out.println(proyecto);;
            }
        }
    }

    public void lideresQueMasGastan() {
        System.out.println(repitaCaracter('=', 6) + " 10 LIDERES MAS COMPRADORES "
                + repitaCaracter('=', 7));
        System.out.println(String.format("%-25s %15s", "LIDER", "VALOR "));
        System.out.println(repitaCaracter('-', 41));

        List<ComprasDeLiderVo> listaCompras = controller.buscarComprasDelLider();
        for (ComprasDeLiderVo proyecto : listaCompras) {
            System.out.println(proyecto);;
        }
    }
}
