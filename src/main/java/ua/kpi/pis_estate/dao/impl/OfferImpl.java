package ua.kpi.pis_estate.dao.impl;

import ua.kpi.pis_estate.ConnectionPool.ConnectionPool;
import ua.kpi.pis_estate.dao.interfaces.OfferDAO;
import ua.kpi.pis_estate.entities.Estate;
import ua.kpi.pis_estate.entities.Offer;
import ua.kpi.pis_estate.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferImpl implements OfferDAO {
    public Connection connection = null;

    public OfferImpl() {
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            connection = connectionPool.getConnection();
            System.out.println("Connected to MySQL");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Offer out(ResultSet resultSet) throws SQLException {
        Offer offer = new Offer();
        offer.setId(resultSet.getLong(1));
        offer.setEstateId(resultSet.getLong(2));
        offer.setRealtorId(resultSet.getLong(3));
        offer.setDate(resultSet.getTimestamp(4));
        System.out.println(offer);
        return offer;
    }

    private List<Offer> getRequest(String req){
        ResultSet resultSet;
        List<Offer> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(req);
            while (resultSet.next()){
                Offer offer = out(resultSet);
                userList.add(offer);
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

    public List<Offer> findAll() {
        String query = "select * from offer";
        List<Offer> offerList = getRequest(query);
        return offerList;
    }

    public Offer findById(long id) {
        String query = "select * from offer where id = " + id + ";";
        List <Offer> offerList = getRequest(query);
        return offerList.get(1);
    }

    public List<Offer> findByEstateId(long estateId) {
        String query = "select * from offer where estate_id = " + estateId + ";";
        List<Offer> offerList = getRequest(query);
        return offerList;
    }

    public List<Offer> findByRealtorId(long realtorId) {
        String query = "select * from offer where realtor_id = " + realtorId + ";";
        List<Offer> offerList = getRequest(query);
        return offerList;
    }

    public void create(Estate estate, User realtor) {
        long estateId = estate.getId();
        long realtorId = realtor.getId();
        String query = String.format("insert into offer (id, estate_id, realtor_id, time) values (null, %d, %d, CURRENT_TIMESTAMP);", estateId, realtorId);
        postRequest(query);
    }

    public void delete(Offer offer) {
        long id = offer.getId();
        String query = String.format("delete from offer WHERE id = %d;", id);
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
