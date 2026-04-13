package com.example.backend.entity;

public class aaluno {
     
    package com.escola.entities;
        
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import lombok.AllArgsConstructor;
        import javax.persistence.*;
        import java.time.LocalDateTime;
        import java.util.List;
        
        @Entity
        @Table(name = "aluno")
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class Aluno {
            
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            
            @Column(name = "ra", unique = true, nullable = false, length = 20)
            private String ra;
            
            @Column(name = "nome", nullable = false, length = 100)
            private String nome;
            
            @Column(name = "email", unique = true, nullable = false, length = 100)
            private String email;
            
            @Column(name = "senha", nullable = false, length = 255)
            private String senha;
            
            @Column(name = "dados_pessoais_cript", nullable = false, columnDefinition = "TEXT")
            private String dadosPessoaisCript;
            
            @Column(name = "data_cadastro")
            private LocalDateTime dataCadastro;
            
            @Column(name = "ativo")
            private Boolean ativo = true;
            
            @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
            private List<Nota> notas;
            
            @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
            private List<Rematricula> rematriculas;
            
            @PrePersist
            protected void onCreate() {
                dataCadastro = LocalDateTime.now();
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

