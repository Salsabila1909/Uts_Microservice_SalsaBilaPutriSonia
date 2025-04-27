package com.utscaseF.course_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "course")
public class Course {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nama;
   private String deskripsi;
   private String pengajar;
   private String kategori;


   // Constructor tanpa parameter
   public Course() {}


   // Constructor dengan parameter
   public Course(String nama, String deskripsi, String pengajar,String kategori) {
       this.nama = nama;
       this.deskripsi = deskripsi;
       this.pengajar = pengajar;
       this.kategori = kategori;
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



   public String getDeskripsi() {
       return deskripsi;
   }

   public void setDeskripsi(String deskripsi) {
       this.deskripsi = deskripsi;
   }

   public String getPengajar() {
    return pengajar;
}


public void setPengajar(String pengajar) {
    this.pengajar = pengajar;
}

   public String getKategori() {
    return kategori;
}


public void setKategori(String kategori) {
    this.kategori = kategori;
}
}

