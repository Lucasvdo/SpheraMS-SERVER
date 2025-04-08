package br.com.spherams.infrastructure.persistence.mapper;


import br.com.spherams.infrastructure.persistence.common.RowMapper;
import br.com.spherams.infrastructure.persistence.model.FamilyCharacterEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyCharacterMapper implements RowMapper<FamilyCharacterEntity> {

    public static final FamilyCharacterMapper INSTANCE = new FamilyCharacterMapper();

    private FamilyCharacterMapper() {
    }

    @Override
    public FamilyCharacterEntity map(ResultSet rs) throws SQLException {
       FamilyCharacterEntity fc = new FamilyCharacterEntity();
        fc.setCid(rs.getInt("cid"));
        fc.setFamilyId(rs.getInt("familyid"));
        fc.setSeniorId(rs.getInt("seniorid"));
        fc.setReputation(rs.getInt("reputation"));
        fc.setTodaysrep(rs.getInt("todaysrep"));
        fc.setReptosenior(rs.getInt("reptosenior"));
        fc.setPrecepts(rs.getString("precepts"));
        fc.setLastresettime(rs.getLong("lastresettime"));
        return fc;
    }

}
