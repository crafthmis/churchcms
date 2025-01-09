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
@Table(name="tbl_campaign")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cmp_id")
    private Long cmpId;
    private String name;
    private String description;
    private String message;
    @Column(name="dispatch_date")
    private Date dispatchDate;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;
}
