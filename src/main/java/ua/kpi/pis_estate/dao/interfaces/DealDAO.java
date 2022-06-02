package ua.kpi.pis_estate.dao.interfaces;

import ua.kpi.pis_estate.entities.Deal;
import ua.kpi.pis_estate.enums.DealStatusEnum;

import java.sql.SQLException;
import java.util.List;

public interface DealDAO {
    void findAll() throws SQLException;
    void findById(long id);
    List<Deal> findByOwnerId(long id);
    List<Deal> findByRealtorId(long id);
    void create(long estateId, long ownerId, long realtorId);
    void close(long id);
    void cancel(long id);
}
