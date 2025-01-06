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
@Table(name="tbl_group_social_media_link")
public class GroupSocialMediaLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gsm_id")
    private Long gsmId;
    @Column(name="grp_id")
    private Long grpId;
    @Column(name="smd_id")
    private Long smdId;
    private String name;
    private String link;
    private String key;
    private String token;
    @Column(name="is_active")
    private Long isActive;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;
}
