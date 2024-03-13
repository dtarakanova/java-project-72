package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

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
}
