package ru.ilya.lab2_spring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
//import ru.ilya.lab2_spring.converter.RoleConverter;
import ru.ilya.lab2_spring.model.enums.Role;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends BaseEntity {
//    @Convert(converter = RoleConverter.class)
    @Enumerated(value = EnumType.STRING)
    @Column
    private Role role;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "role", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> users;
}
