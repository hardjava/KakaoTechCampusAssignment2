package com.kakaotechcampus.schedule_app.Lv3_6.repository;

import com.kakaotechcampus.schedule_app.Lv3_6.entity.Author;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository{
    private final JdbcTemplate jdbcTemplate;

    public AuthorRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Author createAuthor(Author author) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        LocalDateTime now = LocalDateTime.now();

        jdbcInsert
                .withTableName("author")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", author.getEmail());
        parameters.put("name", author.getName());
        parameters.put("created_at", now);
        parameters.put("modified_at", now);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new Author(key.longValue(), author.getEmail(), author.getName(), now, now);
    }

    @Override
    public Author findAuthorByIdOrElseThrow(Long authorId) {
        String sql = "SELECT * FROM author WHERE id = ?";
        List<Author> result = jdbcTemplate.query(sql, authorRowMapper(), authorId);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author Does Not Found"));
    }

    private RowMapper<Author> authorRowMapper(){
        return new RowMapper<Author>() {
            @Override
            public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Author(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime()
                );
            }
        };
    }
}
