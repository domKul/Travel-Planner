package com.planner.travelplanner.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserCountScheduler {
    private final List<Integer> userCounts;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public UserCountScheduler() {
        this.userCounts = new ArrayList<>();
    }


    @Scheduled(cron = "0 0 0 * * *")
    // @Scheduled(fixedDelay = 1000)
    public void addUserCount() {
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

    public List<Integer> getUserCounts() {
        return userCounts;
    }
}
