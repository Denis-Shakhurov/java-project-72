package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlCheckRepository extends BaseRepository {

    public static void save(UrlCheck urlCheck) throws SQLException {
        var sql = "INSERT INTO url_checks (status_code, title, h1, description, url_id,  created_at)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, urlCheck.getStatusCode());
            stmt.setString(2, urlCheck.getTitle());
            stmt.setString(3, urlCheck.getH1());
            stmt.setString(4, urlCheck.getDescription());
            stmt.setLong(5, urlCheck.getUrlId());
            stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
            var generatedKey = stmt.getGeneratedKeys();
            if (generatedKey.next()) {
                urlCheck.setId(generatedKey.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<UrlCheck> getEntitiesByUrlId(Long urlId) throws SQLException {
        var sql = "SELECT * FROM url_checks WHERE url_id = ? ORDER BY created_at";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, urlId);
            var resultSet = stmt.executeQuery();
            List<UrlCheck> urlChecks = new ArrayList<>();
            while (resultSet.next()) {

                var statusCode = resultSet.getInt("status_code");
                var h1 = resultSet.getString("h1");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at");
                var id = resultSet.getLong("id");
                var urlCheck = new UrlCheck(statusCode, title, h1, description, urlId);
                urlCheck.setId(id);
                urlCheck.setCreatedAt(createdAt);
                urlChecks.add(urlCheck);
            }
            return urlChecks;
        }
    }

    public static Map<Long, UrlCheck> getLastChecks() throws SQLException {
        var sql = "SELECT DISTINCT ON (url_id) * FROM url_checks ORDER BY url_id DESC, id DESC";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();;
            Map<Long, UrlCheck> urlChecks = new HashMap<>();
            while (resultSet.next()) {

                var statusCode = resultSet.getInt("status_code");
                var h1 = resultSet.getString("h1");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at");
                var urlId = resultSet.getLong("url_id");
                var id = resultSet.getLong("id");
                var urlCheck = new UrlCheck(statusCode, title, h1, description, urlId);
                urlCheck.setId(id);
                urlCheck.setCreatedAt(createdAt);
                urlChecks.put(urlId, urlCheck);
            }
            return urlChecks;
        }
    }
}
