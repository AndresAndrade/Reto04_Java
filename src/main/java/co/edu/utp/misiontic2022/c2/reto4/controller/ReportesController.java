package co.edu.utp.misiontic2022.c2.reto4.controller;

import co.edu.utp.misiontic2022.c2.reto4.model.dao.ComprasDeLiderDao;
import co.edu.utp.misiontic2022.c2.reto4.model.dao.DeudasPorProyectoDao;
import co.edu.utp.misiontic2022.c2.reto4.model.dao.ProyectoBancoDao;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;

import java.sql.SQLException;
import java.util.List;

public class ReportesController {
    private final ComprasDeLiderDao comprasDeLiderDao;
    private final DeudasPorProyectoDao deudasPorProyectoDao;
    private final ProyectoBancoDao proyectoBancoDao;

    public ReportesController() {
        this.comprasDeLiderDao = new ComprasDeLiderDao();
        this.deudasPorProyectoDao = new DeudasPorProyectoDao();
        this.proyectoBancoDao = new ProyectoBancoDao();
    }

    public List<ComprasDeLiderVo> buscarComprasDelLider() {
        try {
            return comprasDeLiderDao.buscarComprasDelLider();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DeudasPorProyectoVo> buscarDeudasPorProyecto(Double limite) {
        try {
            return deudasPorProyectoDao.buscarDeudasPorProyecto(limite);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProyectoBancoVo> buscarProyectosBanco(String nombreBanco) {
        try {
            return proyectoBancoDao.buscarProyectosBanco(nombreBanco);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

