package com.planner.travelplanner.mapper;

public enum IdType {

     EMPTY_ID(0L);

    private final long id;

    IdType(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
