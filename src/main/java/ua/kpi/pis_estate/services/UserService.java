package ua.kpi.pis_estate.services;

import ua.kpi.pis_estate.dao.impl.DealImpl;
import ua.kpi.pis_estate.dao.impl.EstateImpl;
import ua.kpi.pis_estate.dao.impl.OfferImpl;
import ua.kpi.pis_estate.dao.impl.UserImpl;
import ua.kpi.pis_estate.entities.Estate;
import ua.kpi.pis_estate.entities.Offer;
import ua.kpi.pis_estate.entities.User;
import ua.kpi.pis_estate.enums.RoleEnum;

import java.util.List;

public class UserService {
    private UserImpl userDAO;
    private EstateImpl estateDAO;
    private OfferImpl offerDAO;
    private DealImpl dealDAO;

    public UserService(){
        userDAO = new UserImpl();
        estateDAO = new EstateImpl();
        offerDAO = new OfferImpl();
        dealDAO = new DealImpl();
    }

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public User getUserById(long id){
        return userDAO.findById(id);
    }

    public User getUserByEmail(String email){
        return userDAO.findByEmail(email);
    }

    public long createUser(String name, String surname, String email, String password, String phone, RoleEnum role){
        userDAO.create(new User(name, surname, email, password, phone, role));
        return userDAO.findByEmail(email).getId();
    }

    public User updateName(User user, String newValue){
        userDAO.update(user, "name", newValue);
        return userDAO.findById(user.getId());
    }

    public User updateSurname(User user, String newValue){
        userDAO.update(user, "surname", newValue);
        return userDAO.findById(user.getId());
    }

    public User updatePassword(User user, String newValue){
        userDAO.update(user, "password", newValue);
        return userDAO.findById(user.getId());
    }

    public User updatePhone(User user, String newValue){
        userDAO.update(user, "phone", newValue);
        return userDAO.findById(user.getId());
    }

    public User blockUser(User user){
        return userDAO.unblock(user);
    };

    public User unblockUser(User user){
        return userDAO.block(user);
    }

    public void deleteUser(User user){
        if (user.getRole() == RoleEnum.CLIENT) {
            List<Estate> estateList = estateDAO.findByOwnerId(user.getId());
            for (Estate estate : estateList){
                estateDAO.delete(estate);
                List<Offer> offerList = offerDAO.findByEstateId(estate.getId());
                for (Offer offer : offerList){
                    offerDAO.delete(offer);
                }
            }
        }
        else if (user.getRole() == RoleEnum.REALTOR) {
            List<Offer> offerList = offerDAO.findByRealtorId(user.getId());
            for (Offer offer : offerList){
                offerDAO.delete(offer);
            }
        }
        userDAO.delete(user);
    }

    public void closeConnection(){
        userDAO.closeConnection();
    }
}
