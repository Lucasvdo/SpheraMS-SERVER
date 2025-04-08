package br.com.spherams.infrastructure.persistence.model;

public class FamilyCharacterEntity {

    private Integer cid;
    private Integer familyId;
    private Integer seniorId;
    private Integer reputation;
    private Integer todaysrep;
    private Integer totalreputation;
    private Integer reptosenior;
    private String precepts;
    private Long lastresettime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public Integer getSeniorId() {
        return seniorId;
    }

    public void setSeniorId(Integer seniorId) {
        this.seniorId = seniorId;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public Integer getTotalreputation() {
        return totalreputation;
    }

    public void setTotalreputation(Integer totalreputation) {
        this.totalreputation = totalreputation;
    }

    public Integer getReptosenior() {
        return reptosenior;
    }

    public void setReptosenior(Integer reptosenior) {
        this.reptosenior = reptosenior;
    }

    public String getPrecepts() {
        return precepts;
    }

    public void setPrecepts(String precepts) {
        this.precepts = precepts;
    }

    public Long getLastresettime() {
        return lastresettime;
    }

    public void setLastresettime(Long lastresettime) {
        this.lastresettime = lastresettime;
    }

    public Integer getTodaysrep() {
        return todaysrep;
    }

    public void setTodaysrep(Integer todaysrep) {
        this.todaysrep = todaysrep;
    }

    @Override
    public String toString() {
        return "FamilyCharacterEntity{" +
                "cid=" + cid +
                ", familyId=" + familyId +
                ", seniorId=" + seniorId +
                ", reputation=" + reputation +
                ", todaysrep=" + todaysrep +
                ", totalreputation=" + totalreputation +
                ", reptosenior=" + reptosenior +
                ", precepts='" + precepts + '\'' +
                ", lastresettime=" + lastresettime +
                '}';
    }
}
