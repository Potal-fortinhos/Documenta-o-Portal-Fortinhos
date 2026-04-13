package com.example.backend.entity;

public class validacao {
     package com.escola.entities;
    
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.AllArgsConstructor;
    import javax.persistence.*;
    import java.time.LocalDateTime;
    
    @Entity
    @Table(name = "validacao_dados")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ValidacaoDados {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(name = "tabela", nullable = false, length = 50)
        private String tabela;
        
        @Column(name = "campo", nullable = false, length = 50)
        private String campo;
        
        @Column(name = "valor_referencia", nullable = false, length = 255)
        private String valorReferencia;
        
        @Column(name = "descricao", length = 255)
        private String descricao;
        
        @Enumerated(EnumType.STRING)
        @Column(name = "tipo_validacao", nullable = false)
        private TipoValidacao tipoValidacao;
        
        @Column(name = "ativo")
        private Boolean ativo = true;
        
        @Column(name = "mensagem_erro", length = 255)
        private String mensagemErro;
        
        @Column(name = "created_at")
        private LocalDateTime createdAt;
        
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;
        
        public enum TipoValidacao {
            existencia, formato, faixa, unico, relacionamento
        }
        
        @PrePersist
        protected void onCreate() {
            createdAt = LocalDateTime.now();
            if (ativo == null) {
                ativo = true;
            }
        }
        
        @PreUpdate
        protected void onUpdate() {
            updatedAt = LocalDateTime.now();
        }
    }
    
}


import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

