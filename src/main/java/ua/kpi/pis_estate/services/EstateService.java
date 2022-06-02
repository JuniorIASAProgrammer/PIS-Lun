package ua.kpi.pis_estate.services;

import ua.kpi.pis_estate.dao.impl.EstateImpl;
import ua.kpi.pis_estate.dao.impl.OfferImpl;
import ua.kpi.pis_estate.entities.Estate;
import ua.kpi.pis_estate.entities.Offer;
import ua.kpi.pis_estate.entities.User;
import ua.kpi.pis_estate.enums.DealTypeEnum;

import java.util.List;


public class EstateService {
    private EstateImpl estateDAO;
    private OfferImpl offerDAO;

    public List<Estate> getAllEstates(){
        return estateDAO.findAll();
    }

    public List<Estate> getEstateByOwner(User user){
        long id = user.getId();
        return estateDAO.findByOwnerId(id);
    }

    public List<Estate> getEstateByDealType(DealTypeEnum dealType){
        return estateDAO.findByDealType(dealType);
    }

    public void createEstate(DealTypeEnum dealType, long owner, long price){
        estateDAO.create(dealType, owner, price);
    }

    public Estate changePrice(Estate estate, long newPrice){
        estateDAO.updatePrice(estate, newPrice);
        return estateDAO.findById(estate.getId());
    }

    public Estate changeDealType(Estate estate, DealTypeEnum newType){
        estateDAO.updateDealType(estate, newType);
        return estateDAO.findById(estate.getId());
    }

    public void deleteEstate(Estate estate){
        estateDAO.delete(estate);
        List<Offer> offerList = offerDAO.findByEstateId(estate.getId());
        for (Offer offer : offerList){
            offerDAO.delete(offer);
        }
    }

    public void closeConnection(){
        estateDAO.closeConnection();
    }
}
