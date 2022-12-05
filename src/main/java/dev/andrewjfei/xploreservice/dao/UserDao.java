package dev.andrewjfei.xploreservice.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "[user]")
@Getter
@Setter
public class UserDao {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "[id]", columnDefinition = "UUID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "[username]", columnDefinition = "VARCHAR", unique = true, nullable = false)
    private String username;

    @Column(name = "[first_name]", columnDefinition = "VARCHAR", nullable = false)
    private String firstName;

    @Column(name = "[last_name]", columnDefinition = "VARCHAR", nullable = false)
    private String lastName;

    @Column(name = "[email]", columnDefinition = "VARCHAR", unique = true, nullable = false)
    private String email;

    @Column(name = "[mobile]", columnDefinition = "VARCHAR", unique = true, nullable = false)
    private String mobile;

    @Column(name = "[password]", columnDefinition = "VARCHAR", nullable = false)
    private String password;

    @Column(name = "[is_verified]", columnDefinition = "BOOLEAN DEFAULT false", nullable = false)
    private boolean isVerified;

    @Column(name = "[created]", columnDefinition = "DATETIME")
    private LocalDateTime created;

    @Column(name = "[last_modified]", columnDefinition = "DATETIME")
    private LocalDateTime lastModified;

    @Column(name = "[deleted]", columnDefinition = "DATETIME")
    private LocalDateTime deleted;

    public UserDao() {

    }

    public UserDao(String username, String firstName, String lastName, String email, String mobile, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.created = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "UserDAO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                '}';
    }
}
