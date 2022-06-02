package ua.kpi.pis_estate;

import ua.kpi.pis_estate.ConnectionPool.ConnectionPool;
import ua.kpi.pis_estate.dao.impl.DealImpl;
import ua.kpi.pis_estate.dao.impl.EstateImpl;
import ua.kpi.pis_estate.dao.impl.OfferImpl;
import ua.kpi.pis_estate.dao.impl.UserImpl;
import ua.kpi.pis_estate.enums.DealTypeEnum;
import ua.kpi.pis_estate.enums.RoleEnum;

import java.sql.SQLException;

public class PisEstateApplication {

    private void connect() throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.createConnectionPool();
    }

    public static void main(String[] args) throws SQLException {
        PisEstateApplication app = new PisEstateApplication();
        app.connect();

        UserImpl userDAO = new UserImpl();
//
//        System.out.println();
//        userDAO.create("Alex", "Stone", "a_stone@example.com", "smthToWrite", "+0965555555", RoleEnum.REALTOR);
//        System.out.println();
//
        System.out.println("Find all users");
        userDAO.findAll();
        System.out.println();
//
//        System.out.println("Find user by id");
//        userDAO.findById(1);
//        System.out.println();
//
//        System.out.println("Update user by id");
//        userDAO.update(1, "name", "Jack");
//        System.out.println();
//
//        System.out.println("Unban user by id");
//        userDAO.unblockById(1);
//        System.out.println();
//
//        System.out.println("Ban user by id");
//        userDAO.blockById(1);
//        System.out.println();
//
//        System.out.println("Delete user by id");
//        userDAO.delete(2);
//        System.out.println();
//
//        userDAO.closeConnection();


//        Estate
        EstateImpl estateDAO = new EstateImpl();
//
//        System.out.println();
//        estateDAO.create(DealTypeEnum.RENT, 3, 50000);
//        System.out.println();
//
        System.out.println("Find all estate");
        estateDAO.findAll();
        System.out.println();
//
//        System.out.println("Find estate of user");
//        estateDAO.findByOwnerId(1);
//        System.out.println();
//
//        System.out.println("Find estate of deal type");
//        estateDAO.findByDealType(DealEnum.RENT);
//        System.out.println();
//
//        System.out.println("Find estate of deal type");
//        estateDAO.updatePrice(1, 75000);
//        System.out.println();
//
//        System.out.println("Find estate of deal type");
//        estateDAO.update(1, DealEnum.SELL, 500000);
//        System.out.println();

        OfferImpl offerDAO = new OfferImpl();

//        System.out.println("Create estate");
//        offerDAO.create(6, 3);
//        System.out.println();

        System.out.println("Find all offer");
        offerDAO.findAll();
        System.out.println();


        DealImpl dealDAO = new DealImpl();

//        System.out.println("Create deal");
//        dealDAO.create(6, 3, 4);
//        System.out.println();
//
        System.out.println("All deal");
        dealDAO.findAll();
        System.out.println();

        System.out.println("All deal");
        dealDAO.close(2);
        System.out.println();

        System.out.println("All deal");
        dealDAO.findAll();
        System.out.println();


    }

}
