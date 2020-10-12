package main.repositories;

import main.entities.Staff;
import main.entities.State;
import main.errors.CRUDError;

import java.math.BigInteger;
import java.sql.*;

/**
 * StaffRepository
 *
 * This class instance is used as a centralized bucket that provides
 * necessary methods for querying, storing, and updating staff's data
 * from the mysql database.
 *
 * @author Roshan Kharel
 */
public class StaffRepository {
    /**
     * Holds reference to database connection instance
     */
    private final Connection connection;

    /**
     * SQL Query for finding a single staff record
     */
    private final String FIND_QUERY = "SELECT * from staff WHERE id = ? LIMIT 1";

    /**
     * SQL Query for inserting a single staff record
     */
    private final String CREATE_QUERY = "INSERT INTO staff (first_name, middle_name, last_name, " +
                                                "address, city, `state`, telephone) VALUES (?, ?," +
                                                " ?, ?, ?, ?, ?)";

    /**
     * SQL Query for updating a single staff
     */
    private final String UPDATE_QUERY = "UPDATE staff SET first_name = ?, middle_name = ?, " +
                                                "last_name = ?, address = ?, city = ?, `state` = ?" +
                                                ", telephone = ? " +
                                                "WHERE id = ?";

    /**
     * default constructor
     *
     * @param connection A connection (session) with a MySQL database
     */
    public StaffRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * The method to get a single staff from the database based on
     * the supplied id number
     *
     * @param id id of a staff in database record
     */
    public Staff find(int id) throws SQLException {
        // Creates a PreparedStatement object for sending parameterized SQL
        // statements to the database.
        PreparedStatement statement = connection.prepareStatement(FIND_QUERY);

        // Sets the first parameter, i.e id, to the given int value
        statement.setInt(1, id);

        // Executes the SQL statement, return null if no record is found
        if (!statement.execute()) return null;

        // Record exists, fetch the first result
        ResultSet resultSet = statement.getResultSet();

        // convert result to a Staff class object
        Staff staff = transform(resultSet);

        // if transformations fails, there was an an error
        if (staff == null) {
            throw new CRUDError("Staff record could not be found.", FIND_QUERY);
        }

        // release database resources immediately instead of waiting for
        // this to happen when it is automatically closed.
        resultSet.close();
        statement.close();

        return staff;
    }

    /**
     * The method to insert a single staff record to the database
     *
     * @param staff A newly initialized staff object which does not have
     *              its ID property initialized
     * @return Staff newly inserted staff
     */
    public Staff create(Staff staff) throws SQLException, CRUDError {
        // Creates a PreparedStatement object for sending parameterized SQL
        // statements to the database. The flag `Statement.RETURN_GENERATED_KEYS` indicates
        // auto-generated (primary-key) keys should be returned
        PreparedStatement statement = connection.prepareStatement(CREATE_QUERY,
                Statement.RETURN_GENERATED_KEYS);
        bindData(statement, staff);

        // Executes the SQL statement, the return value is the number of affected rows
        // by this query
        int affectedRows = statement.executeUpdate();

        // No rows were affected, staff record insertion failed
        if (affectedRows == 0)
            throw new CRUDError("Staff record could not be created.", CREATE_QUERY);

        // get the primary key (id) of the last inserted record
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        int staffId = generatedKeys.getInt(1);

        // release database resources
        generatedKeys.close();
        statement.close();

        // return the inserted staff
        return find(staffId);
    }

    /**
     * The method updates a single staff record to the database
     * if a match is found in the database
     *
     * @param staff A staff object representing all the updated attributes
     * @return An updated staff
     */
    public Staff update(Staff staff) throws SQLException, CRUDError {
        // Creates a PreparedStatement object for sending parameterized SQL
        // statements to the database.
        PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        // bind necessary staff attributes to the prepared statement before query execution
        bindData(statement, staff);
        // bind last sql parameter to the id of the staff
        statement.setInt(8, staff.getId());

        // execute and hold the count of number of rows affected by this query
        int affectedRows = statement.executeUpdate();

        // no records were affected, this is an error
        if (affectedRows == 0)
            throw new CRUDError("Staff record could not be updated.", UPDATE_QUERY);

        // release database resources
        statement.close();

        // return the updated staff's record
        return find(staff.getId());
    }

    /**
     * An internal method to convert ResultSet received from database to
     * a Staff object
     *
     * @param resultSet A ResultSet received by querying the database
     * @return Staff object
     */
    Staff transform(ResultSet resultSet) throws SQLException {
        // check if result set is null and
        // call next method on the ResultSet to move the cursor forward
        // one row from its current position because ResultSet cursor is
        // initially positioned before the first row
        if (resultSet == null || !resultSet.next()) return null;

        // get the attributed of the staff from the ResultSet
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String middleName = resultSet.getString("middle_name");
        String address = resultSet.getString("address");
        String stateName = resultSet.getString("state");
        String city = resultSet.getString("city");
        BigInteger telephone = BigInteger.valueOf(resultSet.getLong("telephone"));

        // return the initialize Staff object with the attributes gathered from the database
        return new Staff(
                id,
                firstName,
                middleName,
                lastName,
                address,
                State.make(stateName),
                city,
                telephone
        );
    }

    /**
     * An internal method to bind staff attributes to the SQL query
     * (PreparedStatement) that is about to be executed
     *
     * @param statement PreparedStatement representing the SQL query
     * @param staff A staff object
     */
    void bindData(PreparedStatement statement, Staff staff) throws SQLException {
        // bind all attributes of the staff (except the id) with the PreparedStatement
        statement.setString(1, staff.getFirstName());
        statement.setString(2, staff.getMiddleName());
        statement.setString(3, staff.getLastName());
        statement.setString(4, staff.getAddress());
        statement.setString(5, staff.getCity());
        statement.setString(6, staff.getState().toString());
        statement.setString(7, staff.getTelephone().toString());
    }
}
