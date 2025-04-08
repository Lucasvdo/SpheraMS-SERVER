package br.com.spherams.infrastructure.persistence.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface JdbcRepository<T, ID> {
    Optional<T> findById(Connection connection, ID id) throws SQLException;
    List<T> findAll(Connection connection) throws SQLException;
    void save(Connection connection, T entity) throws SQLException;
    void delete(Connection connection, ID id) throws SQLException;
}