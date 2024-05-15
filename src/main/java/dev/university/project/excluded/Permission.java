package dev.university.project.excluded;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    TRANSACTION_READ("transaction:read"),
    TRANSACTION_WRITE("transaction:write"),


    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_UPDATE("admin:update"),

    MANAGER_READ("management:read"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),
    MANAGER_UPDATE("management:update");

    @Getter
    private final String permission;
}
