package com.example.backend.entity;

public class rematricula {
    
     package com.escola.entities;
    
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.AllArgsConstructor;
    import javax.persistence.*;
    import java.time.LocalDateTime;
    
    @Entity
    @Table(name = "rematricula")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Rematricula {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @ManyToOne
        @JoinColumn(name = "aluno_id", nullable = false)
        private Aluno aluno;
        
        @Column(name = "semestre", nullable = false, length = 10)
        private String semestre;
        
        @Column(name = "data_solicitacao", nullable = false)
        private LocalDateTime dataSolicitacao;
        
        @Column(name = "data_processamento")
        private LocalDateTime dataProcessamento;
    olumn(name = "boleto_simulado", columnDefinition = "TEXT")
        private String boletoSimulado;
        
        @Column(name = "observacao", columnDefinition = "TEXT")
        private String observacao;
        
        @ManyToOne
        @JoinColumn(name = "processado_por")
        private Professor processadoPor;
        
        @PrePersist
        protected void onCreate() {
            dataSolicitacao = LocalDateTime.now();
            if (status == null) {
                status = StatusRematricula.pendente;
            }
        }
        
        public enum StatusRematricula {
            pendente, aprovado, rejeitado, concluido
        }    
        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false)
        private StatusRematricula status = StatusRematricula.pendente;
        
        @C
}


import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

