package modelo;

import java.sql.Connection;

public interface ConnInyectable {
    public void setConn(Connection conn);
    public void consultaInicial();
}
