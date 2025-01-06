package com.techbridge.apis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="tbl_dependant")
public class Dependant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dep_id")
    private Long depId;
    private String name;
    private String description;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;
}
