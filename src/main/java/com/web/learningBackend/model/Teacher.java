package com.web.learningBackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


    @Entity
    @Table(name = "teacher")
    @Data
    public class Teacher {  
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int teacherID;
        @Column(nullable = false)
        private String Email;
        @Column(nullable = false)
        private String Subjectcode;
        @Column(nullable = false)
        private String Subjectname;
        @Column(nullable = false)
        private String Subjectdescription;
        @Column(nullable = false)
        private String Link;
        @Column(nullable = false)
        public void setteacherid(int teacherID) {
        }

}

