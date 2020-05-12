package io.github.iza.todoapp.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
abstract class BaseAuditableEntity {

    private LocalDateTime createOn;
    private LocalDateTime updateOn;

    @PrePersist
    void prePersist() {
        createOn = LocalDateTime.now();
    }

    @PreUpdate
    void preMerge() {
        updateOn = LocalDateTime.now();
    }

}
