package com.sparta.trellowiththreeipeople.user.entity;

import com.sparta.trellowiththreeipeople.user.dto.request.CreateUserRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE users SET deleted_at=CURRENT_TIMESTAMP where id=?")
@Where(clause = "deleted_at IS NULL")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;

    public User(CreateUserRequestDto createUserRequestDto, String encryptpassword) {
        this.username = createUserRequestDto.getUsername();
        this.password = encryptpassword;
        this.role = UserRoleEnum.USER;
    }

    public void update(String encryptpassword) {
        this.password = Objects.nonNull(encryptpassword) ? encryptpassword : this.password;
    }
}
