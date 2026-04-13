package com.example.backend.entity;

public class calendario_nota {
   
    package com.escola.entities;
    
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.AllArgsConstructor;
    import javax.persistence.*;
    import java.time.LocalDate;
    
    @Entity
    @Table(name = "calendario_evento")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class CalendarioEvento {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(name = "titulo", nullable = false, length = 200)
        private String titulo;
        
        @Column(name = "descricao", columnDefinition = "TEXT")
        private String descricao;
        
        @Column(name = "data", nullable = false)
        private LocalDate data;
        
        @Column(name = "data_fim")
        private LocalDate dataFim;
        
        @Enumerated(EnumType.STRING)
        @Column(name = "tipo", nullable = false)
        private TipoEvento tipo;
        
        @ManyToOne
        @JoinColumn(name = "turma_id")
        private TurmaDisciplina turma;
        
        public enum TipoEvento {
            prova, feriado, reuniao, evento, matricula
        }
}


import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

