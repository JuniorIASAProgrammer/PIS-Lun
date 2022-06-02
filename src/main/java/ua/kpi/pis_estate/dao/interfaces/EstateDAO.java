package ua.kpi.pis_estate.dao.interfaces;

import ua.kpi.pis_estate.entities.Estate;
import ua.kpi.pis_estate.enums.DealTypeEnum;

import java.sql.SQLException;
import java.util.List;

public interface EstateDAO {
    List<Estate> findAll();
    Estate findById(long id);
    List<Estate> findByOwnerId(long id);
    List<Estate> findByDealType(DealTypeEnum deal);
    void create(DealTypeEnum dealType, long owner, long price);
    void updatePrice(Estate estate, long newValue);
    void updateDealType(Estate estate, DealTypeEnum newValue);
    void delete(Estate estate);
}
