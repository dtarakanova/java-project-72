package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UrlCheckRepository extends BaseRepository {
    public static void saveUrlCheck(UrlCheck newCheck) throws SQLException {
        String sql = "INSERT INTO url_checks(url_id, status_code, created_at)"
                + "VALUES (?, ?, ?)";
        Timestamp dateandtime = new Timestamp(System.currentTimeMillis());
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, newCheck.getUrlId());
            preparedStatement.setInt(2, newCheck.getStatusCode());
            preparedStatement.setTimestamp(3, dateandtime);
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newCheck.setId(generatedKeys.getLong(1));
                newCheck.setCreatedAt(dateandtime);
            } else {
                throw new SQLException("Ошибка при сохранении");
            }

        }
    }

    public static Map<Long, UrlCheck> findLatestChecks() throws SQLException {
        String sql = "SELECT DISTINCT ON (url_id) * from url_checks order by url_id DESC, id DESC";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            var result = new HashMap<Long, UrlCheck>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long urlId = resultSet.getLong("url_id");
                int statusCode = resultSet.getInt("status_code");
                //String title = resultSet.getString("title");
                //String h1 = resultSet.getString("h1");
                //String description = resultSet.getString("description");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                UrlCheck urlCheck = new UrlCheck(urlId, statusCode, createdAt);
                //urlCheck.setId(id);
                //urlCheck.setUrlId(urlId);
                //urlCheck.setCreatedAt(createdAt);
                result.put(urlId, urlCheck);
            }
            return result;
        }
    }

    public static Optional<UrlCheck> latestChecksById(Long id) throws SQLException {
        String sql = "SELECT * FROM url_checks WHERE url_id = ? ORDER BY created_at DESC LIMIT 1";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Long idN = resultSet.getLong("id");
                Long urlId = resultSet.getLong("url_id");
                int statusCode = resultSet.getInt("status_code");
                //String h1 = resultSet.getString("h1");
                //String title = resultSet.getString("title");
                //String description = resultSet.getString("description");
                Timestamp createdAt = resultSet.getTimestamp("created_at");

                UrlCheck newUrlCheck = new UrlCheck(urlId, statusCode, createdAt);
                newUrlCheck.setId(idN);
                return Optional.of(newUrlCheck);
            }
            return Optional.empty();
        }
    }
}
