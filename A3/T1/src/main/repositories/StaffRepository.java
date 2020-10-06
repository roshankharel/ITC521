package main.repositories;

import main.entities.Staff;
import main.entities.State;
import main.errors.CRUDError;

import java.math.BigInteger;
import java.sql.*;

public class StaffRepository extends Repository {
    private final Connection connection;

    private final String FIND_QUERY = "SELECT * from staff WHERE id = ? LIMIT 1";
    private final String CREATE_QUERY = "INSERT INTO staff (first_name, middle_name, last_name, " +
                                                "address, city, `state`, telephone) VALUES (?, ?," +
                                                " ?, ?, ?, ?, ?)";
    private final String UPDATE_QUERY = "UPDATE staff SET first_name = ?, middle_name = ?, " +
                                                "last_name = ?, address = ?, city = ?, `state` = ?" +
                                                ", telephone = ? " +
                                                "WHERE id = ?";
    private final String DELETE_QUERY = "DELETE FROM staff WHERE id = ?";

    public StaffRepository(Connection connection) {
        this.connection = connection;
    }

    public Staff find(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
        statement.setInt(1, id);

        if (!statement.execute()) return null;

        ResultSet resultSet = statement.getResultSet();

        Staff staff = transform(resultSet);

        if (staff == null) {
            throw new CRUDError("Staff record could not be found.", FIND_QUERY);
        }

        resultSet.close();
        statement.close();

        return staff;
    }

    public Staff create(Staff staff) throws SQLException, CRUDError {
        PreparedStatement statement = connection.prepareStatement(CREATE_QUERY,
                Statement.RETURN_GENERATED_KEYS);
        bindData(statement, staff);

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0)
            throw new CRUDError("Staff record could not be created.", CREATE_QUERY);


        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        int staffId = generatedKeys.getInt(1);

        generatedKeys.close();
        statement.close();

        return find(staffId);
    }

    public Staff update(Staff staff) throws SQLException, CRUDError {
        PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        bindData(statement, staff);
        statement.setInt(8, staff.getId());

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0)
            throw new CRUDError("Staff record could not be updated.", UPDATE_QUERY);

        statement.close();

        return find(staff.getId());
    }

    Staff transform(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet == null);
        if (resultSet == null || !resultSet.next()) return null;

        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String middleName = resultSet.getString("middle_name");
        String address = resultSet.getString("address");
        String stateName = resultSet.getString("state");
        String city = resultSet.getString("city");
        BigInteger telephone = BigInteger.valueOf(resultSet.getLong("telephone"));

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

    void bindData(PreparedStatement statement, Staff staff) throws SQLException {
        statement.setString(1, staff.getFirstName());
        statement.setString(2, staff.getMiddleName());
        statement.setString(3, staff.getLastName());
        statement.setString(4, staff.getAddress());
        statement.setString(5, staff.getCity());
        statement.setString(6, staff.getState().toString());
        statement.setString(7, staff.getTelephone().toString());
    }
}
