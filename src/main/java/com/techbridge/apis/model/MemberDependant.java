package com.techbridge.apis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="tbl_member_dependant")
public class MemberDependant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mdp_id")
    private Long mdpId;
    @Column(name="fml_id")
    private Long fmlId;
    @Column(name="mem_id")
    private Long memId;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;
}
