package co.edu.utp.misiontic2022.c2.reto4.model.dao;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeudasPorProyectoDao {

    private final String queryDeudas = "SELECT p.ID_Proyecto, sum(c.Cantidad * m.Precio_Unidad) VALOR\n" +
            "FROM Proyecto p \n" +
            "JOIN Compra c ON p.ID_Proyecto = c.ID_Proyecto \n" +
            "JOIN MaterialConstruccion m ON c.ID_MaterialConstruccion = m.ID_MaterialConstruccion\n" +
            "WHERE c.Pagado = 'No'\n" +
            "GROUP BY p.ID_Proyecto HAVING VALOR > ?\n" +
            "ORDER BY VALOR DESC";

    public List<DeudasPorProyectoVo> buscarDeudasPorProyecto(Double limite) throws SQLException {
        List<DeudasPorProyectoVo> deudasPorProyecto = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryDeudas);
            pstm.setDouble(1, limite);
            rs = pstm.executeQuery();

            while (rs.next()) {
                DeudasPorProyectoVo deudaPorProyecto = new DeudasPorProyectoVo();
                deudaPorProyecto.setIdProyecto(rs.getInt(1));
                deudaPorProyecto.setValor(rs.getDouble(2));
                deudasPorProyecto.add(deudaPorProyecto);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return deudasPorProyecto;

    }

}
