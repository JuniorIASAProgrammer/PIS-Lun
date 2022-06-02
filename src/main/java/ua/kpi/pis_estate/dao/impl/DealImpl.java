package ua.kpi.pis_estate.dao.impl;

import ua.kpi.pis_estate.ConnectionPool.ConnectionPool;
import ua.kpi.pis_estate.dao.interfaces.DealDAO;
import ua.kpi.pis_estate.entities.Deal;
import ua.kpi.pis_estate.enums.DealStatusEnum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DealImpl implements DealDAO {
    public Connection connection = null;

    public DealImpl() {
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            connection = connectionPool.getConnection();
            System.out.println("Connected to MySQL");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Deal out(ResultSet resultSet) throws SQLException {
        Deal deal = new Deal();
        deal.setId(resultSet.getLong(1));
        deal.setEstateId(resultSet.getLong(2));
        deal.setOwnerId(resultSet.getLong(3));
        deal.setRealtorId(resultSet.getLong(4));
        deal.setTime(resultSet.getTimestamp(5));
        deal.setStatus(DealStatusEnum.valueOf(resultSet.getString(6)));
        System.out.println(deal);
        return deal;
    }

    public void findAll() throws SQLException {
        String query = "select * from deal";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                out(resultSet);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void findById(long id) {
        String query = "select * from deal where id = " + id + ";";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                out(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Deal> findByOwnerId(long id) {
        String query = "select * from deal where owner_id = " + id + ";";
        List<Deal> dealList = new ArrayList<Deal>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                out(resultSet);
                dealList.add(out(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealList;
    }

    public List<Deal> findByRealtorId(long id) {
        String query = "select * from deal where realtor_id = " + id + ";";
        List<Deal> dealList = new ArrayList<Deal>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                out(resultSet);
                dealList.add(out(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealList;
    }

    public void create(long estateId, long ownerId, long realtorId) {
        String query = String.format("insert into deal (id, estate_id, owner_id, realtor_id, time, status) values (null, %d, %d, %d, CURRENT_TIMESTAMP, '%s');",
                estateId, ownerId, realtorId, DealStatusEnum.CONCLUDE);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(long id) {
        String query = String.format("call change_deal_status(%d, '%s')", id, DealStatusEnum.COMPLETE);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancel(long id) {
        DealImpl dealDAO = new DealImpl();
        String query = String.format("call change_deal_status(%d, '%s')", id, DealStatusEnum.CANCEL);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            }
    }
}
