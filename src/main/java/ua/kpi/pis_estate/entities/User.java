package ua.kpi.pis_estate.entities;

import ua.kpi.pis_estate.enums.BanEnum;
import ua.kpi.pis_estate.enums.RoleEnum;

import java.util.Objects;

public class User {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private BanEnum ban;
    private RoleEnum role;

    public User() {
    }

    public User(String name, String surname, String email, String password, String phone, RoleEnum role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.ban = BanEnum.NO;
        this.role = role;
    }

    public User(String name, String surname, String email, String password, String phone, BanEnum ban, RoleEnum role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.ban = ban;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BanEnum getBan() {
        return ban;
    }

    public void setBan(BanEnum ban) {
        this.ban = ban;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name) && surname.equals(user.surname) && email.equals(user.email) && password.equals(user.password) && phone.equals(user.phone) && ban == user.ban && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, phone, ban, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", ban=" + ban +
                ", role=" + role +
                '}';
    }
}

