package br.com.spherams.domain.port.output;

import br.com.spherams.infrastructure.persistence.model.CharactersEntity;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository {
    Optional<CharactersEntity> findById(int id);
    List<CharactersEntity> findAll();
}
