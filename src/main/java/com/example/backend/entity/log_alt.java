package com.example.backend.entity;

public class log_alt {
    
    package com.escola.entities;
    
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.AllArgsConstructor;
    import javax.persistence.*;
    import java.math.BigDecimal;
    import java.time.LocalDateTime;
    
    @Entity
    @Table(name = "log_alteracao_nota")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class LogAlteracaoNota {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @ManyToOne
        @JoinColumn(name = "nota_id", nullable = false)
        private Nota nota;
        
        @Column(name = "data_alteracao", nullable = false)
        private LocalDateTime dataAlteracao;
        
        @ManyToOne
        @JoinColumn(name = "professor_id", nullable = false)
        private Professor professor;
        
        @Column(name = "valor_antigo", nullable = false, precision = 5, scale = 2)
        private BigDecimal valorAntigo;
        
        @Column(name = "valor_novo", nullable = false, precision = 5, scale = 2)
        private BigDecimal valorNovo;
        
        @Column(name = "motivo", length = 255)
        private String motivo;
        
        @PrePersist
        protected void onCreate() {
            dataAlteracao = LocalDateTime.now();
        }
}


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

