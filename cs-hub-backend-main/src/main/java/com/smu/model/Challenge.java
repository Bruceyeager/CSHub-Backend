package com.smu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity; // added import for @Entity for reason described below -EH
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity // added this cuz it was screaming at me that Challenge is not managed -EH
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String shortDescription;
    private String longDescription;
    private String requiredExpertise;
    private String preferredExpertise;
    private double budget;
    private String location;
    private Long organizationId; //this will connect us to Team one's entity when they are done


}
