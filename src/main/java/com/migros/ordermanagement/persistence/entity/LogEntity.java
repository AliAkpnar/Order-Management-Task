package com.migros.ordermanagement.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "log")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String logId;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String message;
    @Column
    private String className;
    @Column
    private int logCode;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
