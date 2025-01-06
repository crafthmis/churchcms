package com.techbridge.apis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="tbl_sermon")
public class Sermon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="srm_id")
    private Long srmId;
    @Column(name="brn_id")
    private Long brnId;
    private String title;
    private String description;
    @Column(name="sermon_date")
    private LocalDate sermonDate;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;
}
