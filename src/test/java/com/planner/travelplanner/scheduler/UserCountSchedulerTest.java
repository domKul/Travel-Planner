package com.planner.travelplanner.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserCountSchedulerTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserCountScheduler userCountScheduler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserCountFromDatabase_shouldReturnUserCountFromDatabase() {
        //Given
        int expectedUserCount = 10;
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(expectedUserCount);

        //When
        int actualUserCount = userCountScheduler.getUserCountFromDatabase();

        // Then
        assertEquals(expectedUserCount, actualUserCount);
    }

    @Test
    void saveUserCountToDatabase_shouldSaveUserCountToDatabase() {
        int userCount = 10;
        LocalDate date = LocalDate.now();
        userCountScheduler.saveUserCountToDatabase(userCount, date);
        verify(jdbcTemplate).update(anyString(), eq(userCount), eq(date));
    }
}
