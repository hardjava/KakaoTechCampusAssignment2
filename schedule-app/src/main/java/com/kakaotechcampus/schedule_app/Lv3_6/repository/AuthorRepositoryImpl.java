package com.kakaotechcampus.schedule_app.Lv3_6.repository;

import com.kakaotechcampus.schedule_app.Lv3_6.entity.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
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

        return new Author(key.longValue(), author.getEmail(), author.getName(), author.getCreatedAt(), author.getModifiedAt());
    }
}
