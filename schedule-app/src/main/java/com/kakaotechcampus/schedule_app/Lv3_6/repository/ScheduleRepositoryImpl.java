package com.kakaotechcampus.schedule_app.Lv3_6.repository;

import com.kakaotechcampus.schedule_app.Lv3_6.entity.Schedule;
import com.kakaotechcampus.schedule_app.Lv3_6.entity.ScheduleWithAuthor;
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

    public ScheduleRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        LocalDateTime now = LocalDateTime.now();

        jdbcInsert
                .withTableName("schedule")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("author_id", schedule.getAuthorId());
        parameters.put("contents", schedule.getContents());
        parameters.put("password", schedule.getPassword());
        parameters.put("created_at", now);
        parameters.put("modified_at", now);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new Schedule(key.longValue(), schedule.getAuthorId(), schedule.getContents(), schedule.getPassword(), now, now);
    }

    @Override
    public List<ScheduleWithAuthor> findAll(Long authorId, LocalDate modifiedAt) {
        String sql = "SELECT s.author_id, a.name, a.email, s.contents, s.created_at, s.modified_at " +
                        "FROM schedule s " +
                        "JOIN author a ON s.author_id = a.id " +
                        "WHERE 1=1";

        List<Object> params = new ArrayList<>();

        if (authorId != null) {
            sql += " AND s.author_id = ?";
            params.add(authorId);
        }

        if (modifiedAt != null) {
            sql += " AND DATE(s.modified_at) = ?";
            params.add(Date.valueOf(modifiedAt));
        }

        sql += " ORDER BY s.modified_at DESC";

        return jdbcTemplate.query(sql, scheduleWithAuthorRowMapper(), params.toArray());
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        List<Schedule> result = jdbcTemplate.query(sql, scheduleRowMapper(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule Does Not Found [ID: " + id + "]"));
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) {
        String sql = "UPDATE schedule SET contents = ?, modified_at = ? WHERE id = ?";
        schedule.setModifiedAt(LocalDateTime.now());

        jdbcTemplate.update(sql,
                schedule.getContents(),
                schedule.getModifiedAt(),
                schedule.getId());

        return schedule;
    }

    @Override
    public void deleteSchedule(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Schedule> scheduleRowMapper(){
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getLong("author_id"),
                        rs.getString("contents"),
                        rs.getString("password"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime()
                );
            }
        };
    }


    private RowMapper<ScheduleWithAuthor> scheduleWithAuthorRowMapper(){
        return new RowMapper<ScheduleWithAuthor>() {
            @Override
            public ScheduleWithAuthor mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleWithAuthor(
                        rs.getLong("author_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("contents"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime()
                );
            }
        };
    }
}
