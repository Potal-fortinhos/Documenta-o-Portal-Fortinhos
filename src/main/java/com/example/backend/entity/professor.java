package com.example.backend.entity;

public class escola {

    package com.escola.entities;
    
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.AllArgsConstructor;
    import javax.persistence.*;
    import java.time.LocalDate;
    import java.util.List;
    
    @Entity
    @Table(name = "professor")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Professor {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(name = "nome", nullable = false, length = 100)
        private String nome;
        
        @Column(name = "email", unique = true, nullable = false, length = 100)
        private String email;
        
        @Column(name = "senha", nullable = false, length = 255)
        private String senha;
        
        @Column(name = "especialidade", length = 100)
        private String especialidade;
        
        @Column(name = "data_contratacao")
        private LocalDate dataContratacao;
        
        @Column(name = "ativo")
        private Boolean ativo = true;
        
        @OneToMany(mappedBy = "professor")
        private List<TurmaDisciplina> disciplinas;
        
        @OneToMany(mappedBy = "lancadoPor")
        private List<Nota> notasLancadas;
        
        @OneToMany(mappedBy = "professor")
        private List<LogAlteracaoNota> alteracoesNotas;
        
        @OneToMany(mappedBy = "processadoPor")
        private List<Rematricula> rematriculasProcessadas;
        
        @PrePersist
        protected void onCreate() {
            if (ativo == null) {
                ativo = true;
            }
        }
    }


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

