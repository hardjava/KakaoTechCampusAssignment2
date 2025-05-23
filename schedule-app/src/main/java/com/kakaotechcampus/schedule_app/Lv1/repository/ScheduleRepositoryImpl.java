package com.kakaotechcampus.schedule_app.Lv1.repository;

import com.kakaotechcampus.schedule_app.Lv1.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        LocalDateTime now = LocalDateTime.now();

        jdbcInsert
                .withTableName("schedule")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", schedule.getUsername());
        parameters.put("contents", schedule.getContents());
        parameters.put("password", schedule.getPassword());
        parameters.put("created_at", now);
        parameters.put("modified_at", now);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new Schedule(key.longValue(), schedule.getUsername(), schedule.getContents(), now, now);
    }

    @Override
    public List<Schedule> findAll(String username, LocalDate modifiedAt) {
        String sql = "SELECT * FROM schedule WHERE 1=1";
        List<Object> params = new ArrayList<>();

        if (username != null){
            sql += " AND username = ?";
            params.add(username);
        }

        if (modifiedAt != null){
            sql += " AND DATE(modified_at) = ?" ;
            params.add(Date.valueOf(modifiedAt));
        }

        sql += " ORDER BY modified_at DESC";

        return jdbcTemplate.query(sql, scheduleRowMapper(), params.toArray());
    }

    @Override
    public Schedule findByIdOrElseThrow(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        List<Schedule> result = jdbcTemplate.query(sql, scheduleRowMapper(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule Does Not Found [ID: " + id + "]"));
    }

    @Override
    public Schedule update(Schedule schedule) {
        schedule.setModifiedAt(LocalDateTime.now());
        String sql = "UPDATE schedule SET username = ?, contents =?, modified_at = ? WHERE id = ?";

        jdbcTemplate.update(
                sql,
                schedule.getUsername(),
                schedule.getContents(),
                schedule.getModifiedAt(),
                schedule.getId()
                );

        return schedule;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("contents"),
                        rs.getString("password"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime()
                );
            }
        };
    }
}
