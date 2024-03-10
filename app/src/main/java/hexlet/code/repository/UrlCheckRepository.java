package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class UrlCheckRepository extends BaseRepository {
    public static void saveUrlCheck(UrlCheck newCheck) throws SQLException {
        String sql = "INSERT INTO url_checks(status_code, created_at) VALUES(?,?)";
        Timestamp dateandtime = new Timestamp(System.currentTimeMillis());
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, newCheck.getUrlId());
            preparedStatement.setInt(2, newCheck.getStatusCode());
            preparedStatement.setTimestamp(3, dateandtime);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                newCheck.setId(resultSet.getLong(1));
                newCheck.setCreatedAt(dateandtime);
            } else {
                throw new SQLException("Ошибка при сохранении");
            }
        }
    }
}
