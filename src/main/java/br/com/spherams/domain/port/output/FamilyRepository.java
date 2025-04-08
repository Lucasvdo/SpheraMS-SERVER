package br.com.spherams.domain.port.output;

import br.com.spherams.client.FamilyEntitlement;
import br.com.spherams.infrastructure.persistence.model.FamilyCharacterEntity;
import br.com.spherams.infrastructure.persistence.model.FamilyEntitlementEntity;

import java.util.List;
import java.util.Optional;

public interface FamilyRepository {
    Optional<FamilyCharacterEntity> findById(int id);
    List<FamilyCharacterEntity> findAll();
    Optional<FamilyEntitlementEntity> findFamilyEntitlementByCharacterId(int id);
}
