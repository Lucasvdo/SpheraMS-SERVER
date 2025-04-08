package br.com.spherams.infrastructure.persistence.mapper;

import br.com.spherams.infrastructure.persistence.common.RowMapper;
import br.com.spherams.infrastructure.persistence.model.FamilyEntitlementEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyEntlitlementMapper implements RowMapper<FamilyEntitlementEntity> {

    public static final FamilyEntlitlementMapper INSTANCE = new FamilyEntlitlementMapper();

    private FamilyEntlitlementMapper() {
    }

    @Override
    public FamilyEntitlementEntity map(ResultSet rs) throws SQLException {
        FamilyEntitlementEntity entitlement = new FamilyEntitlementEntity();
        entitlement.setId(rs.getLong("id"));
        entitlement.setCharid(rs.getLong("charid"));
        entitlement.setEntitlementid(rs.getLong("entitlementid"));
        entitlement.setTimestamp(rs.getLong("timestamp"));
        return entitlement;
    }
}
