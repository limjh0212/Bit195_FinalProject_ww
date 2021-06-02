package com.bit.ww.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {
    @ApiModelProperty(hidden = true)
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regdate;

    @ApiModelProperty(hidden = true)
    @LastModifiedDate
    @Column
    private LocalDateTime editdate;
}
