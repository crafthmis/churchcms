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
@Table(name="tbl_message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="msg_id")
    private Long msgId;
    @Column(name="cmp_id")
    private Long cmpId;
    @Column(name="usr_id")
    private Long usrId;
    private String phone;
    private String msg;
    private String hashmessage;
    @Column(name="sent_status", columnDefinition = "bigint default 0")
    private int sentStatus;
    @Column(name="recv_status", columnDefinition = "bigint default 0")
    private int recvStatus;
    @Column(name="ack_status", columnDefinition = "bigint default 0")
    private int ackStatus;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;
}
