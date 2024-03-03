package com.bug.reporting.system.bugreportingsystem.bug.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bug")
@Getter
@Setter
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bugId;
    private String bugTitle;
    private String bugDescription;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    private String photo;
    private String video;


}
