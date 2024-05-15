package dev.university.project.excluded;

import dev.university.project.excluded.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static dev.university.project.excluded.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(Set.of(
            ADMIN_CREATE,
            ADMIN_DELETE,
            ADMIN_READ,
            ADMIN_UPDATE,
            MANAGER_CREATE,
            MANAGER_DELETE,
            MANAGER_READ,
            MANAGER_UPDATE)),
    MANAGER(Set.of(
            MANAGER_CREATE,
            MANAGER_DELETE,
            MANAGER_READ,
            MANAGER_UPDATE));

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
