package br.com.spherams.infrastructure.persistence.model;

public class FamilyEntitlementEntity {

  private long id;
  private long charid;
  private long entitlementid;
  private long timestamp;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCharid() {
    return charid;
  }

  public void setCharid(long charid) {
    this.charid = charid;
  }


  public long getEntitlementid() {
    return entitlementid;
  }

  public void setEntitlementid(long entitlementid) {
    this.entitlementid = entitlementid;
  }


  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

}
