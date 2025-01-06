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
@Table(name="tbl_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="evn_id")
    private Long evnId;
    @Column(name="brn_id")
    private Long brn_id;
    @Column(name="ety_id")
    private Long etyId;
    @Column(name="is_pledgeable")
    private Long is_pledgeable;
    private String name;
    private String description;
    @Column(name="from_date")
    private Date fromDate;
    @Column(name="to_date")
    private Date toDate;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;
}
