package dev.andrewjfei.xploreservice.dao;

import dev.andrewjfei.xploreservice.enumeration.LocationType;
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
import org.postgresql.geometric.PGpoint;

@Entity
@Table(name = "[location]")
@Getter
@Setter
public class LocationDao {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "[id]", columnDefinition = "UUID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "[name]", columnDefinition = "VARCHAR", nullable = false)
    private String name;

    @Column(name = "[description]", columnDefinition = "VARCHAR")
    private String description;

    @Column(name = "[coordinate]", columnDefinition = "POINT", nullable = false)
    private PGpoint coordinate;

    @Column(name = "[type]", columnDefinition = "VARCHAR", unique = true, nullable = false)
    private LocationType type;

    @Column(name = "[created]", columnDefinition = "DATETIME")
    private LocalDateTime created;

    @Column(name = "[last_modified]", columnDefinition = "DATETIME")
    private LocalDateTime lastModified;

    @Column(name = "[deleted]", columnDefinition = "DATETIME")
    private LocalDateTime deleted;

    public LocationDao() {

    }

    public LocationDao(String name, String description, PGpoint coordinate, LocationType type) {
        this.name = name;
        this.description = description;
        this.coordinate = coordinate;
        this.type = type;
        this.created = LocalDateTime.now();
    }
}
