package com.planner.travelplanner.sender;

public record EmailDto(
        String to,
        String title,
        String content
) {
}