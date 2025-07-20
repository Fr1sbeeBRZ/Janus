package fr1sbee.dev.janus.web.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
abstract class AuditableEntity<U> {
    @CreatedBy
    @Column(name = "creation_user", nullable = false)
    private String creationUser;

    @LastModifiedBy
    @Column(name = "last_modification_user")
    private String lastModificationUser;

    @CreatedDate
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @LastModifiedDate
    @Column(name = "last_modification_date")
    private LocalDate lastModificationDate;
}
