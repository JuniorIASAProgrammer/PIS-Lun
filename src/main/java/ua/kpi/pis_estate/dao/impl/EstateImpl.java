package ua.kpi.pis_estate.dao.impl;

import ua.kpi.pis_estate.ConnectionPool.ConnectionPool;
import ua.kpi.pis_estate.dao.interfaces.EstateDAO;
import ua.kpi.pis_estate.entities.Estate;
import ua.kpi.pis_estate.enums.DealTypeEnum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstateImpl implements EstateDAO {
    public Connection connection = null;

    public EstateImpl() {
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            connection = connectionPool.getConnection();
            System.out.println("Connected to MySQL");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private List<Estate> getRequest(String req){
        ResultSet resultSet;
        List<Estate> estateList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(req);
            while (resultSet.next()){
                Estate estate = out(resultSet);
                estateList.add(estate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estateList;
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

    public Estate out(ResultSet resultSet) throws SQLException {
        Estate estate = new Estate();
        estate.setId(resultSet.getLong(1));
        estate.setDealType(DealTypeEnum.valueOf(resultSet.getString(2)));
        estate.setOwnerId(resultSet.getLong(3));
        estate.setPrice(resultSet.getLong(4));
        System.out.println(estate);
        return estate;
    }

    public List<Estate> findAll() {
        String query = "select * from estate";
        List<Estate> estateList = getRequest(query);
        return estateList;
    }

    public Estate findById(long id) {
        String query = "select * from estate where id = " + id + ";";
        List<Estate> estateList = getRequest(query);
        return estateList.get(1);
    }

    public List<Estate> findByOwnerId(long id) {
        String query = "select * from estate where owner_id = " + id + ";";
        List<Estate> estateList = getRequest(query);
        return estateList;
    }

    public List<Estate> findByDealType(DealTypeEnum deal) {
        String query = "select * from estate where deal_type = '" + deal + "';";
        List<Estate> estateList = getRequest(query);
        return estateList;
    }

    public void create(DealTypeEnum dealType, long owner, long price){
        String query = String.format("insert into estate (id, deal_type, owner_id, price) values (null, '%s', %d, %d);", dealType, owner, price);
        postRequest(query);
    }

    public void updatePrice(Estate estate, long newValue) {
        long id = estate.getId();
        String query = String.format("call change_price(%d, %d)", id, newValue);
        postRequest(query);
    }

    public void updateDealType(Estate estate, DealTypeEnum newValue) {
        long id = estate.getId();
        String query = String.format("call change_deal_type(%d, '%s')", id, newValue);
        postRequest(query);
    }

    public void delete(Estate estate) {
        long id = estate.getId();
        String query = String.format("delete from estate WHERE id = %d;", id);
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
