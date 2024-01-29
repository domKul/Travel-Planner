package travelplanner.enums;

public enum IdType {

    EMPTY_ID(0L);

    private final Long id;

    IdType(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
