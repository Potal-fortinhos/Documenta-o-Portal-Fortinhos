package com.example.backend.entity;

public class nota {
    
     package com.escola.entities;
    
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.AllArgsConstructor;
    import javax.persistence.*;
    import java.math.BigDecimal;
    import java.time.LocalDateTime;
    import java.util.List;
    
    @Entity
    @Table(name = "nota")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Nota {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @ManyToOne
        @JoinColumn(name = "aluno_id", nullable = false)
        private Aluno aluno;
        
        @ManyToOne
        @JoinColumn(name = "disciplina_id", nullable = false)
        private TurmaDisciplina disciplina;
        
        @Column(name = "bimestre", nullable = false)
        private Integer bimestre;
        
        @Column(name = "valor", nullable = false, precision = 5, scale = 2)
        private BigDecimal valor;
        
        @Column(name = "data_lancamento", nullable = false)
        private LocalDateTime dataLancamento;
        
        @ManyToOne
        @JoinColumn(name = "lancado_por", nullable = false)
        private Professor lancadoPor;
        
        @Column(name = "observacao", columnDefinition = "TEXT")
        private String observacao;
        
        @OneToMany(mappedBy = "nota", cascade = CascadeType.ALL)
        private List<LogAlteracaoNota> logsAlteracao;
        
        @PrePersist
        protected void onCreate() {
            dataLancamento = LocalDateTime.now();
        }
}


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

