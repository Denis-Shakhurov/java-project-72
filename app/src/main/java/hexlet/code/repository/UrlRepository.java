package hexlet.code.repository;

import hexlet.code.model.Url;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UrlRepository extends BaseRepository {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void save(Url url) throws SQLException {
        var sql = "INSERT INTO urls (name, created_at) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, url.getName());
            preparedStatement.setString(2, url.getCreatedAt().format(formatter));
            preparedStatement.executeUpdate();
            var generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                url.setId(generatedKey.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<Url> getEntities() throws SQLException {
        var sql = "SELECT * FROM urls";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            List<Url> urls = new ArrayList<>();
            while (resultSet.next()) {
                var name = resultSet.getString("name");
                var createdAt = resultSet.getString("created_at");
                var id = resultSet.getLong("id");
                var url = new Url(name, LocalDateTime.parse(createdAt, formatter));
                url.setId(id);
                urls.add(url);
            }
            return urls;
        }
    }

    public static Optional<Url> find(Long id) throws SQLException {
        var sql = "SELECT * FROM urls WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var createdAt = resultSet.getString("created_at");
                var url = new Url(name, LocalDateTime.parse(createdAt, formatter));
                url.setId(id);
                return Optional.of(url);
            }
        }
        return Optional.empty();
    }

    public static boolean existsByName(String name) throws SQLException {
        var sql = "SELECT * FROM urls WHERE name = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            var resultSet = stmt.executeQuery();
            List<Url> urls = new ArrayList<>();
            if (resultSet.next()) {
                var name1 = resultSet.getString("name");
                var createdAt = resultSet.getString("created_at");
                var id = resultSet.getLong("id");
                var url = new Url(name1, LocalDateTime.parse(createdAt, formatter));
                url.setId(id);
                urls.add(url);
            }
            return urls.isEmpty();
        }
    }
}
