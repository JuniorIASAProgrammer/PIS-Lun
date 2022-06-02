package ua.kpi.pis_estate.entities;

import ua.kpi.pis_estate.enums.DealTypeEnum;

public class Estate {
    private long id;
    private DealTypeEnum deal_type;
    private long owner_id ;
    private long price;

    public Estate() {
    }

    public Estate(long id, DealTypeEnum deal_type, long owner_id, long price) {
        this.id = id;
        this.deal_type = deal_type;
        this.owner_id = owner_id;
        this.price = price;
    }

    public Estate(DealTypeEnum deal_type, long owner_id, long price) {
        this.deal_type = deal_type;
        this.owner_id = owner_id;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DealTypeEnum getDealType() {
        return deal_type;
    }

    public void setDealType(DealTypeEnum deal_type) {
        this.deal_type = deal_type;
    }

    public long getOwnerId() {
        return owner_id;
    }

    public void setOwnerId(long owner_id) {
        this.owner_id = owner_id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Estate{" +
                "id=" + id +
                ", deal_type=" + deal_type +
                ", owner_id=" + owner_id +
                ", price=" + price +
                '}';
    }
}
