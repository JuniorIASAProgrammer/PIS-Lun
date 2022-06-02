package ua.kpi.pis_estate.dao.interfaces;

import ua.kpi.pis_estate.entities.Estate;
import ua.kpi.pis_estate.entities.Offer;
import ua.kpi.pis_estate.entities.User;

import java.util.List;

public interface OfferDAO {
    List<Offer> findAll();
    Offer findById(long id);
    List<Offer> findByEstateId(long estateId);
    List<Offer> findByRealtorId(long realtorId);
    void create(Estate estate, User realtor);
    void delete(Offer offer);
}
