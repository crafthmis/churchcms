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
@Table(name="tbl_sermon_link")
public class SermonLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sml_id")
    private Long sml_id;
    @Column(name="srm_id")
    private Long srm_id;
    private String link;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;
}
