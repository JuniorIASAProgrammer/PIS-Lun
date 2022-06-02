package ua.kpi.pis_estate.entities;

import ua.kpi.pis_estate.enums.DealStatusEnum;

import java.sql.Timestamp;

public class Deal {

    private long id;
    private long estate_id;
    private long owner_id;
    private long realtor_id;
    private Timestamp time;
    private DealStatusEnum status;

    public Deal() {
    }

    public Deal(long id, long estate_id, long owner_id, long realtor_id, Timestamp time, DealStatusEnum status) {
        this.id = id;
        this.estate_id = estate_id;
        this.owner_id = owner_id;
        this.realtor_id = realtor_id;
        this.time = time;
        this.status = status;
    }

    public Deal(long estate_id, long owner_id, long realtor_id, Timestamp time, DealStatusEnum status) {
        this.estate_id = estate_id;
        this.owner_id = owner_id;
        this.realtor_id = realtor_id;
        this.time = time;
        this.status = status;
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

    public long getOwnerId() {
        return owner_id;
    }

    public void setOwnerId(long owner_id) {
        this.owner_id = owner_id;
    }

    public long getRealtorId() {
        return realtor_id;
    }

    public void setRealtorId(long realtor_id) {
        this.realtor_id = realtor_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public DealStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DealStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", estate_id=" + estate_id +
                ", owner_id=" + owner_id +
                ", realtor_id=" + realtor_id +
                ", time=" + time +
                ", status=" + status +
                '}';
    }
}
