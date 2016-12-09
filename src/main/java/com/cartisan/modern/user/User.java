package com.cartisan.modern.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    private String userName;

    @NonNull
    private String password;
}
