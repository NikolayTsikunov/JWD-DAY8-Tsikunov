package by.tsikunov.day6.model.util;

public class IdGenerator {
    private static final long MIN_ID = 1;
    private static final long MAX_ID = 1000;

    private static long id;

    private IdGenerator() {}

    public static long generateId() {
        id++;
        if(id > MAX_ID) {
            id = MIN_ID;
        }
        return id;
    }
}
