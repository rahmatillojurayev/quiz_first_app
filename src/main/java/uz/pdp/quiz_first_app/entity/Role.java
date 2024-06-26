package uz.pdp.quiz_first_app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.quiz_first_app.entity.enums.RoleName;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }

}