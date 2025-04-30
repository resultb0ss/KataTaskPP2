package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.exception.DaoException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final UserDao INSTANCE = new UserDaoJDBCImpl();

    private static final String CREATE_USERS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS users
            (
                id INTEGER NOT NULL AUTO_INCREMENT,
                name VARCHAR(50) NOT NULL,
                last_name VARCHAR(50) NOT NULL,
                age INTEGER NOT NULL,
                PRIMARY KEY (id)
            );""";

    private static final String DROP_USERS_TABLE_SQL = """ 
            DROP TABLE IF EXISTS users;
            """;

    private static final String SAVE_USER_SQL = """ 
            INSERT INTO users (name, last_name, age)
            VALUES (?, ?, ?);
            """;

    private static final String REMOVE_USER_BY_ID_SQL = """ 
            DELETE FROM users WHERE id = ?;
            """;

    private static final String GET_ALL_ALL_USERS_SQL = """ 
            SELECT id, name, last_name, age FROM users;
            """;

    private static final String CLEAN_USERS_TABLE_SQL = """ 
            TRUNCATE TABLE users;
            """;


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = ConnectionManager.get();
             Statement statement = connection.createStatement()) {

            statement.execute(CREATE_USERS_TABLE_SQL);

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

    }

    public void dropUsersTable() {

        try (Connection connection = ConnectionManager.get();
             Statement statement = connection.createStatement()) {

            statement.execute(DROP_USERS_TABLE_SQL);

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_SQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();


        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

    }

    public void removeUserById(long id) {

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID_SQL)) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();


        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ALL_USERS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }


        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return users;
    }

    public void cleanUsersTable() {

        try (Connection connection = ConnectionManager.get();
             Statement statement = connection.createStatement()) {

            statement.execute(CLEAN_USERS_TABLE_SQL);


        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
