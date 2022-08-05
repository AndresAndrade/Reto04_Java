package co.edu.utp.misiontic2022.c2.reto4.model.dao;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoBancoDao {

    private final String queryProyectoBanco = "SELECT p.ID_Proyecto ID, p.Constructora, p.Ciudad, p.Clasificacion, t.Estrato, " +
                                                "l.Nombre || ' ' || l.Primer_Apellido || ' ' || Segundo_Apellido LIDER\n" +
                                                "FROM Proyecto p \n" +
                                                "JOIN Tipo t ON p.ID_Tipo = t.ID_Tipo \n" +
                                                "JOIN Lider l ON p.ID_Lider = l.ID_Lider \n" +
                                                "WHERE Banco_Vinculado = ? \n" +
                                                "ORDER BY  Fecha_Inicio DESC, Ciudad, Constructora";

    public List<ProyectoBancoVo> buscarProyectosBanco(String nombreBanco) throws SQLException {
        List<ProyectoBancoVo> listaComprasDeLider = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryProyectoBanco);
            pstm.setString(1, nombreBanco);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ProyectoBancoVo proyectoBancoVo = new ProyectoBancoVo();
                proyectoBancoVo.setIdProyecto(rs.getInt(1));
                proyectoBancoVo.setConstructora(rs.getString(2));
                proyectoBancoVo.setCiudad(rs.getString(3));
                proyectoBancoVo.setClasificacion(rs.getString(4));
                proyectoBancoVo.setEstrato(rs.getInt(5));
                proyectoBancoVo.setLider(rs.getString(6));
                listaComprasDeLider.add(proyectoBancoVo);
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
        return listaComprasDeLider;
    }

}
