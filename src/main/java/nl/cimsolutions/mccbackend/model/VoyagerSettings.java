package nl.cimsolutions.mccbackend.model;

import javax.persistence.*;


public class VoyagerSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int speed;

}
