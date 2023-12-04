package ru.ilya.lab2_spring.model.enums;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.ilya.lab2_spring.model.Permission;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum Role {
    USER(1, Set.of(Permission.USER_WRITE, Permission.USER_WATCH)),
    SELLER(20, Set.of(Permission.SELLER_WRITE, Permission.SELLER_WATCH,
            Permission.USER_WRITE, Permission.USER_WATCH)),
    ADMIN(10, Set.of(Permission.ADMIN_WRITE, Permission.ADMIN_WATCH,
            Permission.SELLER_WRITE, Permission.SELLER_WATCH,
            Permission.USER_WRITE, Permission.USER_WATCH));

    final int code;
    final Set<Permission> permissions;

    Role(int code, Set<Permission> permissions) {
        this.code = code;
        this.permissions = permissions;
    }

    public static Role fromCode(int code) {
        for (Role role : values()) {
            if (role.code == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("No such role with code: " + code);
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
