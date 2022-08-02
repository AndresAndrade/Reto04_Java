package co.edu.utp.misiontic2022.c2.reto4.model.dao;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComprasDeLiderDao {
    private final String queryComprasLider = "SELECT (l.Nombre || ' ' || l.Primer_Apellido || ' ' || l.Segundo_Apellido) LIDER, sum(c.Cantidad * m.Precio_Unidad) VALOR \n" +
            "FROM Lider l\n" +
            "JOIN Proyecto p ON l.ID_Lider = p.ID_Lider\n" +
            "JOIN Compra c ON p.ID_Proyecto = c.ID_Proyecto\n" +
            "JOIN MaterialConstruccion m ON c.ID_MaterialConstruccion = m.ID_MaterialConstruccion \n" +
            "GROUP BY LIDER\n" +
            "ORDER BY VALOR DESC\n" +
            "LIMIT 10";

    public List<ComprasDeLiderVo> buscarComprasDelLider() throws SQLException {
        List<ComprasDeLiderVo> comprasDeLider = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryComprasLider);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ComprasDeLiderVo compraDelLider = new ComprasDeLiderVo();
                compraDelLider.setLider(rs.getString(1));
                compraDelLider.setValor(rs.getDouble(2));
                comprasDeLider.add(compraDelLider);
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
        return comprasDeLider;

    }
    
}
