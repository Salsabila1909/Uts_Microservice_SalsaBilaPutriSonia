package com.utscaseF.participant_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String email;
    private String nohp;
    private String registrasi_date;

    // Constructor tanpa parameter
    public Participant() {}

    // Constructor dengan parameter
    public Participant(String nama, String email, String nohp, String registrasi_date) {
        this.nama = nama;
        this.email = email;
        this.nohp = nohp;
        this.registrasi_date = registrasi_date;
    }

    // Getters dan Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getRegistrasi_date() {
        return registrasi_date;
    }

    public void setRegistrasi_date(String registrasi_date) {
        this.registrasi_date = registrasi_date;
    }
}
