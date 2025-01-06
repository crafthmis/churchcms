package com.techbridge.apis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="tbl_biodata")
public class Biodata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bdt_id")
    private Long bdtId;
    @Column(name="gnd_id")
    private Long gndId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="middle_name")
    private String middleName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="id_number")
    private String idNumber;
    private String phone1;
    private String phone2;
    private LocalDate dob;

    @Transient
    private Integer age;
    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name="last_update")
    private Date lastUpdate;
    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }
}
