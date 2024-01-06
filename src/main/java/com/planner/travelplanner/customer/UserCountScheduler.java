package com.planner.travelplanner.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
class UserCountScheduler {

    private final List<Integer> userCounts;
    private final JdbcTemplate jdbcTemplate;

    UserCountScheduler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userCounts = new ArrayList<>();
    }

    @Scheduled(cron = "0 0 0 * * *")
    // @Scheduled(fixedDelay = 1000)
    void addUserCount() {
        int userCount = getUserCountFromDatabase();
        userCounts.add(userCount);
        saveUserCountToDatabase(userCount, LocalDate.now());
        System.out.println("Dodano informację o liczbie użytkowników: " + userCount + " - " + LocalDate.now());
    }

    int getUserCountFromDatabase() {
        String sql = "SELECT COUNT(*) FROM customers_list";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    void saveUserCountToDatabase(int userCount, LocalDate date) {
        String sql = "INSERT INTO user_count (user_count, date) VALUES (?, ?)";
        jdbcTemplate.update(sql, userCount, date);
    }

    List<Integer> getUserCounts() {
        return userCounts;
    }
}
