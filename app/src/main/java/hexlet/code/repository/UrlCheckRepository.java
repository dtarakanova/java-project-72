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

public class UrlCheckRepository extends BaseRepository {
    public static void saveUrlCheck(UrlCheck newCheck) throws SQLException {
        String sql = "INSERT INTO url_checks(url_id, status_code, title, h1, description, created_at)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        Timestamp dateandtime = new Timestamp(System.currentTimeMillis());
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, newCheck.getUrlId());
            preparedStatement.setInt(2, newCheck.getStatusCode());
            preparedStatement.setString(3, newCheck.getTitle());
            preparedStatement.setString(4, newCheck.getH1());
            preparedStatement.setString(5, newCheck.getDescription());
            preparedStatement.setTimestamp(6, dateandtime);
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
                long urlId = resultSet.getLong("url_id");
                int statusCode = resultSet.getInt("status_code");
                String title = resultSet.getString("title");
                String h1 = resultSet.getString("h1");
                String description = resultSet.getString("description");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                UrlCheck urlCheck = new UrlCheck(urlId, statusCode, title, h1, description, createdAt);
                result.put(urlId, urlCheck);
            }
            return result;
        }
    }
}
