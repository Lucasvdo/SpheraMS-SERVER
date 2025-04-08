package br.com.spherams.adapter.output;

import br.com.spherams.domain.port.output.CharacterRepository;
import br.com.spherams.infrastructure.persistence.common.JdbcRepositorySupport;
import br.com.spherams.infrastructure.persistence.mapper.CharactersMapper;
import br.com.spherams.infrastructure.persistence.model.CharactersEntity;

import java.util.List;
import java.util.Optional;

public class JDBCCharacterRepository extends JdbcRepositorySupport implements CharacterRepository {

    @Override
    public Optional<CharactersEntity> findById(int id) {
        final String sql = "SELECT * FROM characters WHERE id = ?";
        return this.queryOne(sql, CharactersMapper.INSTANCE,id);
    }

    @Override
    public List<CharactersEntity> findAll() {
        final String sql = "SELECT * FROM characters";
        return this.queryMany(sql, CharactersMapper.INSTANCE);
    }
}
