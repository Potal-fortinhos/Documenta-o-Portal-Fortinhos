package com.example.backend.entity;

public class turma {
    
    package com.escola.entities;
    
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.AllArgsConstructor;
    import javax.persistence.*;
    import java.util.List;
    
    @Entity
    @Table(name = "turma_disciplina")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class TurmaDisciplina {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(name = "nome", nullable = false, length = 100)
        private String nome;
        
        @Column(name = "carga_horaria", nullable = false)
        private Integer cargaHoraria;
        
        @ManyToOne
        @JoinColumn(name = "professor_id", nullable = false)
        private Professor professor;
        
        @Column(name = "semestre", length = 10)
        private String semestre;
        
        @Column(name = "ano")
        private Integer ano;
        
        @Column(name = "ativo")
        private Boolean ativo = true;
        
        @OneToMany(mappedBy = "disciplina")
        private List<Nota> notas;
        
        @OneToMany(mappedBy = "turma")
        private List<CalendarioEvento> eventos;
        
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

