package com.echo.client.config.databaseConfig;

import com.echo.client.enums.DataBaseType;
import org.springframework.util.Assert;

public class DataBaseContextHolder {

    private static final ThreadLocal<DataBaseType> CONTEXT
            = new ThreadLocal<>();

    public static void set(DataBaseType database) {
        Assert.notNull(database, "DataBaseType cannot be null");
        CONTEXT.set(database);
    }

    public static DataBaseType getDatabase() {
        return CONTEXT.get();
    }

    @SuppressWarnings("unused")
    public static void clear() {
        CONTEXT.remove();
    }
}
