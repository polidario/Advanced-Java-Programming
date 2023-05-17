package org.bbpolidario.services.exceptions;

import java.sql.SQLException;

public class DatamodelSearchException extends Throwable {
    public void initCause(SQLException e) {
    }
}
