package ua.kpi.pis_estate.dao.impl;

import ua.kpi.pis_estate.ConnectionPool.ConnectionPool;
import ua.kpi.pis_estate.dao.interfaces.UserDAO;
import ua.kpi.pis_estate.entities.Estate;
import ua.kpi.pis_estate.entities.User;
import ua.kpi.pis_estate.enums.BanEnum;
import ua.kpi.pis_estate.enums.RoleEnum;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDAO {
    public Connection connection = null;

    public UserImpl() {
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            connection = connectionPool.getConnection();
            System.out.println("Connected to MySQL");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private List<User> getRequest(String req){
        ResultSet resultSet;
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(req);
            while (resultSet.next()){
                User user = out(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private void postRequest(String req){
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(req);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User out(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(1));
        user.setName(resultSet.getString(2));
        user.setSurname(resultSet.getString(3));
        user.setEmail(resultSet.getString(4));
        user.setPassword(resultSet.getString(5));
        user.setPhone(resultSet.getString(6));
        user.setBan(BanEnum.valueOf(resultSet.getString(7)));
        user.setRole(RoleEnum.values()[(resultSet.getInt(8))]);
        System.out.println(user);
        return user;
    }

    public List<User> findAll() {
        String query = "select * from user";
        List<User> userList = getRequest(query);
        return userList;
    }

    public User findById(long id) {
        String query = "select * from user where id = " + id + ";";
        List<User> userList = getRequest(query);
        return userList.get(1);
    }

    public User findByEmail(String email){
        String query = "select * from user where email = " + email + ";";
        List<User> userList = getRequest(query);
        return userList.get(1);
    }

    public void create(User user){
        String name = user.getName();
        String surname = user.getSurname();
        String email = user.getEmail();
        String password = user.getPassword();
        String phone = user.getPhone();
        RoleEnum role = user.getRole();
        String query = "insert into user (id, name, surname, email, password, phone, ban, role) " +
                "values (null, '" + name + "', '" + surname + "', '" + email + "', '" + password + "', '" + phone + "', '"+ BanEnum.NO + "', " + role.ordinal() + ");";
        postRequest(query);
    }

    public void update(User user, String field, String newValue){
        long userId = user.getId();
        String query = "call change_" + field + "(" + userId + ", '" + newValue + "');";
        postRequest(query);
        findById(userId);
    }

    public User block(User user){
        long id = user.getId();
        String query = "update user set ban = " + "'YES'" + " where id = " + id + ";";
        postRequest(query);
        return findById(id);
    }

    public User unblock(User user){
        long id = user.getId();
        String query = "update user set ban = " + "'NO'" + " where id = " + id + ";";
        postRequest(query);
        return findById(id);
    }

    public void delete(User user){
        long id = user.getId();
        String query = String.format("delete from user where id = %d;", id);
        postRequest(query);
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException error){
            System.err.println(error.getMessage());
        }
    }
}
