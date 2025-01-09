package com.techbridge.apis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="tbl_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mem_id")
    private Long memId;
    @Column(name="bdt_id")
    private Long bdtId;
    @Column(name="dep_id")
    private Long depId;
    private LocalDate dob;
    @Column(name="is_primary")
    private Long isPrimary;
    @Column(name="is_dependant")
    private Long isDependant;
    @Column(name="has_dependant")
    private Long hasDependant;
    @Column(name="is_fullmember")
    private Long isFullmember;
    @Column(name="is_baptized")
    private Long isBaptized;
    @Column(name="is_active")
    private Long isActive;
    @Column(name="photo_link")
    private String photoLink;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;
}
