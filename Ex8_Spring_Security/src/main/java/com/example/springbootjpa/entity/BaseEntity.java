package com.example.springbootjpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
//    @NotEmpty(message = "Please provide a code")
    private String code;
    @Column(name = "name")
//    @NotNull(message = "Please provide a name")
    private String name;
    @Column(name = "date_create")
    @CreatedDate
    private Timestamp dateCreate;
    @Column(name = "date_update")
    @LastModifiedDate
    private Timestamp dateUpdate;

}
