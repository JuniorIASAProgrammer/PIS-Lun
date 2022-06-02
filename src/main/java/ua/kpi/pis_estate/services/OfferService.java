package ua.kpi.pis_estate.services;

import ua.kpi.pis_estate.dao.impl.OfferImpl;
import ua.kpi.pis_estate.entities.Estate;
import ua.kpi.pis_estate.entities.Offer;
import ua.kpi.pis_estate.entities.User;

import java.util.List;

public class OfferService {
    private OfferImpl offerDAO;

    public OfferService(){
        offerDAO = new OfferImpl();
    }

    public List<Offer> getAllOffers(){
        return offerDAO.findAll();
    }

    public List<Offer> getByEstate(Estate estate){
        return offerDAO.findByEstateId(estate.getId());
    }

    public List<Offer> getByRealtor(User user){
        return offerDAO.findByRealtorId(user.getId());
    }

    public void createOffer(Estate estate, User user){
        offerDAO.create(estate, user);
    }

    public void deleteOffer(Offer offer){
        offerDAO.delete(offer);
    }

    public void closeConnection(){
        offerDAO.closeConnection();
    }
}
