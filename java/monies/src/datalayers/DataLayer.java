package datalayers;

import entities.*;
import java.util.ArrayList;
import sql.Connector;
import java.sql.*;

/**
 * The DataLayer abstract class.
 *
 * @author alexhughes
 */
public abstract class DataLayer {

    protected Connector c;
    protected Entity e;
    protected ArrayList<Entity> entities;

    public DataLayer(Connector aConnector) {
        c = aConnector;
    }

    public DataLayer(Connector aConnector, Entity anEntity) {
        c = aConnector;
        e = anEntity;
    }

    public abstract Entity fetchEntity() throws SQLException;

    public abstract ArrayList<Entity> fetchEntities(String aSorting) throws SQLException;

    public abstract ResultSet fetchEntitiesR(String aSorting) throws SQLException;

    public abstract ArrayList<Entity> searchEntity() throws SQLException;

    public abstract ResultSet searchEntityR() throws SQLException;

    protected abstract String buildSearchQuery();

    public abstract int insertEntity() throws SQLException;

    public abstract void updateEntity() throws SQLException;

    public abstract void deleteEntity() throws SQLException;

    protected abstract ArrayList<Entity> resultSetToEntity(ResultSet aR) throws SQLException;

    protected void checkID(Entity anEntity) throws SQLException {
        if (anEntity instanceof Expense) {
            Expense e = (Expense) anEntity;
            if (e.getExpenseID() == Entity.NIL) {
                throw new SQLException();
            }
        }
    }
}
