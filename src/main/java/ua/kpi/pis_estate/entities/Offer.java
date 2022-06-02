package ua.kpi.pis_estate.entities;

import java.sql.Timestamp;

public class Offer {
    private long id;
    private long estate_id;
    private long realtor_id;
    private Timestamp time;

    public Offer() {
    }

    public Offer(long estate_id, long realtor_id, Timestamp timestamp) {
        this.estate_id = estate_id;
        this.realtor_id = realtor_id;
        this.time = timestamp;
    }

    public Offer(long id, long estate_id, long realtor_id, Timestamp timestamp) {
        this.id = id;
        this.estate_id = estate_id;
        this.realtor_id = realtor_id;
        this.time = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEstateId() {
        return estate_id;
    }

    public void setEstateId(long estate_id) {
        this.estate_id = estate_id;
    }

    public long getRealtorId() {
        return realtor_id;
    }

    public void setRealtorId(long realtor_id) {
        this.realtor_id = realtor_id;
    }

    public Timestamp getDate() {
        return time;
    }

    public void setDate(Timestamp timestamp) {
        this.time = timestamp;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", estate_id=" + estate_id +
                ", realtor_id=" + realtor_id +
                ", date=" + time +
                '}';
    }
}
