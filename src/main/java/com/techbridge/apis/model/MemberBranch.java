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
@Table(name="tbl_member_branch")
public class MemberBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mbr_id")
    private Long mbrId;
    @Column(name="mem_id")
    private Long memId;
    @Column(name="brn_id")
    private Long brnId;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;
}
