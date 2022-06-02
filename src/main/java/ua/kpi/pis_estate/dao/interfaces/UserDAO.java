package ua.kpi.pis_estate.dao.interfaces;

import ua.kpi.pis_estate.entities.User;
import ua.kpi.pis_estate.enums.RoleEnum;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> findAll() throws SQLException;
    User findById(long id);
    User findByEmail(String email);
    void create(User user);
    void update(User user, String field, String newValue);
    User block(User user);
    User unblock(User user);
    void delete(User user);
}
