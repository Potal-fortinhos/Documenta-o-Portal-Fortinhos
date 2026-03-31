
public class teste {
    
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
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusRematricula status = StatusRematricula.pendente;
    
    @Column(name = "boleto_simulado", columnDefinition = "TEXT")
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
}

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

    package com.escola.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;
    
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    
    @Column(name = "token", length = 255)
    private String token;
    
    @Column(name = "data_login")
    private LocalDateTime dataLogin;
    
    @Column(name = "data_logout")
    private LocalDateTime dataLogout;
    
    @Column(name = "ip_address", length = 45)
    private String ipAddress;
    
    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;
    
    @Column(name = "sucesso")
    private Boolean sucesso = true;
    
    public enum TipoUsuario {
        aluno, professor, admin
    }
    
    @PrePersist
    protected void onCreate() {
        dataLogin = LocalDateTime.now();
        if (sucesso == null) {
            sucesso = true;
        }
    }
}
   
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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

