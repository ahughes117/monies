package datalayers;

import entities.Entity;
import entities.Expense;
import java.sql.*;
import java.util.ArrayList;
import sql.Connector;

/**
 * The Expense Entity Data Layer Class
 *
 * @author ahughes
 */
public class ExpenseDL extends DataLayer {

    public ExpenseDL(Connector aConnector) {
        super(aConnector);
    }

    public ExpenseDL(Connector aConnector, Entity anEntity) {
        super(aConnector, anEntity);
    }

    @Override
    public Entity fetchEntity() throws SQLException {
        Expense expense = (Expense) e;

        checkID(expense);

        String query = ""
                + "SELECT * "
                + "FROM expense "
                + "WHERE expenseID = ? ";

        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, expense.getExpenseID());

        ResultSet expenseR = ps.executeQuery();

        entities = resultSetToEntity(expenseR);

        if (entities.size() > 0) {
            e = entities.get(0);
        } else {
            throw new SQLException();
        }

        return e;
    }

    /**
     * Fetches the items according to a given interval
     *
     * @param anInterval
     * @return
     * @throws SQLException
     */
    public ArrayList<Entity> fetchIntervalItems(String anInterval) throws SQLException {
        entities = new ArrayList();

        String whereS = "";
        if (anInterval.equals("day")) {
            whereS = "DATE(Date) = CURDATE() ";
        } else if (anInterval.equals("week")) {
            whereS = "WEEKOFYEAR(Date) = WEEKOFYEAR(NOW()) AND Date > CURRENT_TIMESTAMP - INTERVAL 2 WEEK ";
        } else if (anInterval.equals("month")) {
            whereS = "MONTHNAME(Date) = MONTHNAME(NOW()) AND Date > CURRENT_TIMESTAMP - INTERVAL 1 MONTH ";
        } else if (anInterval.equals("all")) {
            whereS = "1=1 ";
        }

        String query = ""
                + "SELECT * "
                + "FROM expense "
                + "WHERE " + whereS
                + "ORDER BY Date ASC ";

        ResultSet entityR = c.sendQuery(query);
        entities = resultSetToEntity(entityR);

        return entities;
    }

    @Override
    public ArrayList<Entity> fetchEntities(String aSorting) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet fetchEntitiesR(String aSorting) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Entity> searchEntity() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet searchEntityR() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String buildSearchQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertEntity() throws SQLException {
        int id = Entity.NIL;
        Expense expense = (Expense) e;

        String query = ""
                + "INSERT INTO expense (Amount, Description, Date, DateCreated) VALUES "
                + "(?, ?, ?, CURRENT_TIMESTAMP) ";

        PreparedStatement ps = c.prepareStatement(query);

        ps.setDouble(1, expense.getAmount());
        ps.setString(2, expense.getDescription());
        ps.setDate(3, expense.getDate());

        ps.executeUpdate();

        ResultSet keyR = ps.getGeneratedKeys();
        while (keyR.next()) {
            id = keyR.getInt(1);
        }

        return id;
    }

    @Override
    public void updateEntity() throws SQLException {
        Expense expense = (Expense) e;
        checkID(expense);

        String query = ""
                + "UPDATE expense SET "
                + "Amount = ?, "
                + "Description = ?, "
                + "Date = ? "
                + "WHERE expenseID = ? ";

        PreparedStatement ps = c.prepareStatement(query);

        ps.setDouble(1, expense.getAmount());
        ps.setString(2, expense.getDescription());
        ps.setDate(3, expense.getDate());
        ps.setInt(4, expense.getExpenseID());

        ps.executeUpdate();
    }

    @Override
    public void deleteEntity() throws SQLException {
        Expense expense = (Expense) e;
        checkID(expense);

        String query = ""
                + "DELETE "
                + "FROM expense "
                + "WHERE expenseID = ? ";

        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, expense.getExpenseID());

        ps.executeUpdate();
    }

    @Override
    protected ArrayList<Entity> resultSetToEntity(ResultSet aR) throws SQLException {
        ArrayList<Entity> entityL = new ArrayList();
        Expense expense;

        while (aR.next()) {
            expense = new Expense(
                    aR.getInt("expenseID"),
                    aR.getDouble("Amount"),
                    aR.getString("Description"),
                    aR.getDate("Date"),
                    aR.getTimestamp("DateCreated"),
                    aR.getTimestamp("_dateModified"));
            entityL.add(expense);
        }
        return entityL;
    }

    @Override
    protected void checkID(Entity anEntity) throws SQLException {
        super.checkID(anEntity);
    }

}
