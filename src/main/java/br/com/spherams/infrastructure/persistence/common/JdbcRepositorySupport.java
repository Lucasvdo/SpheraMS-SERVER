package br.com.spherams.infrastructure.persistence.common;

import br.com.spherams.tools.DatabaseConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class JdbcRepositorySupport {

    protected <T> Optional<T> queryOne(String sql, RowMapper<T> mapper, Object... params) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = prepareStatement(conn, sql, params);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return Optional.ofNullable(mapper.map(rs));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao executar queryOne", e);
        }
    }

    protected <T> List<T> queryMany(String sql, RowMapper<T> mapper, Object... params) {
        List<T> result = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = prepareStatement(conn, sql, params);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                result.add(mapper.map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao executar queryMany", e);
        }
        return result;
    }

    protected int executeUpdate(String sql, Object... params) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = prepareStatement(conn, sql, params)) {

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao executar update", e);
        }
    }

    private PreparedStatement prepareStatement(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]); // parâmetros começam em 1
        }
        return stmt;
    }

}
