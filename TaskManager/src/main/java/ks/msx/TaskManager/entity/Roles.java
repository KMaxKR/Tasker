package ks.msx.TaskManager.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ks.msx.TaskManager.entity.Permissions.*;

@RequiredArgsConstructor
public enum Roles {

    USER(
            Set.of(READ, WRITE, UPDATE, DELETE)
    );

    @Getter
    private final Set<Permissions> permissionsSet;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = new ArrayList<>(getPermissionsSet()
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .toList()
        );
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
