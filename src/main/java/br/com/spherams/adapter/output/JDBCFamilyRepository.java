package br.com.spherams.adapter.output;

import br.com.spherams.domain.port.output.FamilyRepository;
import br.com.spherams.infrastructure.persistence.common.JdbcRepositorySupport;
import br.com.spherams.infrastructure.persistence.mapper.FamilyCharacterMapper;
import br.com.spherams.infrastructure.persistence.mapper.FamilyEntlitlementMapper;
import br.com.spherams.infrastructure.persistence.model.FamilyCharacterEntity;
import br.com.spherams.infrastructure.persistence.model.FamilyEntitlementEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class JDBCFamilyRepository extends JdbcRepositorySupport implements FamilyRepository{
    private static final Logger log = LoggerFactory.getLogger(JDBCFamilyRepository.class);

    @Override
    public Optional<FamilyCharacterEntity> findById(int id) {
        final String query = "SELECT * FROM family_character WHERE id = ?";
        return queryOne(query, FamilyCharacterMapper.INSTANCE, id);
    }

    @Override
    public List<FamilyCharacterEntity> findAll() {
        final String query = "SELECT * FROM family_character";
        return queryMany(query,FamilyCharacterMapper.INSTANCE);
    }

    @Override
    public Optional<FamilyEntitlementEntity> findFamilyEntitlementByCharacterId(int id) {
        final String query = "SELECT * FROM family_entitlement WHERE character_id = ?";
        return queryOne(query, FamilyEntlitlementMapper.INSTANCE,id);
    }


}

