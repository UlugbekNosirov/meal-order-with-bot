package uz.elmurodov.meal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.elmurodov.meal.domain.base.Auditable;
import uz.elmurodov.meal.enums.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auth_user", indexes = {
        @Index(name = "auth_user_username_key",
                columnList = "username", unique = true
        )
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser extends Auditable {

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Lob
    @Column(name = "password")
    private String password;

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name = "chat_id")
    private String chatId;

    @Enumerated(EnumType.STRING)
    private AuthRole role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private Language language;

}