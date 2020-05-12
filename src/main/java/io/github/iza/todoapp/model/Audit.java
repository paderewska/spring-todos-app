package io.github.iza.todoapp.model;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
class Audit {

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
