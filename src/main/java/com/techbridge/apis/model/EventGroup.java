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
@Table(name="tbl_event_group")
public class EventGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="evm_id")
    private Long evmId;
    @Column(name="evn_id")
    private Long evnId;
    @Column(name="mem_id")
    private Long memId;
    @Column(name="is_deleted")
    private Long isDeleted;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;

}
