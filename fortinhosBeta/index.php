<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>Portal Fortinhos | Sistema Acadêmico</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:opsz,wght@14..32,300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: 'Inter', sans-serif;
            background: radial-gradient(circle at 10% 30%, #eef5fc, #dce6f0);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 1.5rem;
        }
        .app-wrapper {
            max-width: 1400px;
            width: 100%;
            background: rgba(255, 255, 255, 0.96);
            border-radius: 56px;
            box-shadow: 0 35px 70px -20px rgba(0, 32, 54, 0.4);
            overflow: hidden;
        }
        /* Tela de autenticação */
        .auth-screen {
            background: linear-gradient(125deg, #0a2f3f 0%, #175d7a 100%);
            padding: 3rem 2.5rem;
            display: flex;
            flex-wrap: wrap;
            gap: 2.5rem;
            justify-content: center;
            align-items: center;
        }
        .auth-brand {
            flex: 1;
            min-width: 260px;
            color: white;
        }
        .auth-brand h1 {
            font-size: 3.2rem;
            font-weight: 800;
            background: linear-gradient(135deg, #FFF9E6, #FFE5B4);
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
        }
        .auth-brand .highlight {
            border-left: 5px solid #f5b042;
            padding-left: 1rem;
            margin: 1rem 0;
        }
        .auth-card {
            flex: 0 1 450px;
            background: rgba(255,255,255,0.98);
            border-radius: 48px;
            padding: 2.2rem 2rem;
            box-shadow: 0 25px 45px -10px rgba(0,0,0,0.3);
        }
        .auth-tabs {
            display: flex;
            gap: 1rem;
            margin-bottom: 1.8rem;
            border-bottom: 2px solid #e2edf5;
        }
        .auth-tab {
            flex: 1;
            text-align: center;
            padding: 0.8rem;
            font-weight: 700;
            font-size: 1.1rem;
            cursor: pointer;
            background: transparent;
            border: none;
            color: #6b8ba0;
        }
        .auth-tab.active {
            color: #f5b042;
            border-bottom: 3px solid #f5b042;
        }
        .auth-form { display: none; }
        .auth-form.active-form { display: block; }
        .input-icon {
            position: relative;
            margin-bottom: 1.2rem;
        }
        .input-icon i {
            position: absolute;
            left: 18px;
            top: 50%;
            transform: translateY(-50%);
            color: #7c9eb3;
        }
        .input-icon input, .input-icon select {
            width: 100%;
            padding: 0.9rem 1rem 0.9rem 2.8rem;
            border-radius: 60px;
            border: 1.5px solid #e2edf5;
            font-size: 1rem;
        }
        .btn-auth {
            background: linear-gradient(95deg, #f5b042, #e6962c);
            border: none;
            width: 100%;
            padding: 0.9rem;
            border-radius: 60px;
            font-weight: 700;
            font-size: 1rem;
            color: #1c3b48;
            cursor: pointer;
            margin-top: 0.5rem;
        }
        .error-msg, .success-msg { font-size: 0.8rem; margin-top: 0.5rem; text-align: center; }
        .error-msg { color: #e74c3c; }
        .success-msg { color: #27ae60; }
        .info-text { font-size: 0.75rem; color: #7f8c8d; text-align: center; margin-top: 1rem; }
        /* Dashboard */
        .dashboard-container { display: none; animation: fadeSlide 0.4s ease; }
        .dashboard-container.active-dash { display: block; }
        @keyframes fadeSlide { from { opacity: 0; transform: translateY(12px);} to { opacity: 1; transform: translateY(0);} }
        .top-bar {
            background: linear-gradient(105deg, #0f3e4f, #1f6f8f);
            padding: 1rem 2.2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            color: white;
            gap: 1rem;
        }
        .logout-btn {
            background: #e6893a;
            border: none;
            padding: 0.5rem 1.4rem;
            border-radius: 40px;
            font-weight: 600;
            color: white;
            cursor: pointer;
        }
        .nav-tabs {
            display: flex;
            background: #f8fafd;
            padding: 0 2rem;
            gap: 0.8rem;
            border-bottom: 2px solid #dae8f2;
            flex-wrap: wrap;
        }
        .tab-btn {
            background: transparent;
            border: none;
            padding: 1rem 2rem;
            font-weight: 600;
            font-size: 1rem;
            color: #2c6b8a;
            cursor: pointer;
            border-radius: 30px 30px 0 0;
            display: flex;
            align-items: center;
            gap: 8px;
        }
        .tab-btn.active {
            background: white;
            color: #f5b042;
            border-bottom: 3px solid #f5b042;
        }
        .main-content { padding: 2rem 2.2rem; min-height: 520px; background: #ffffff; }
        .tab-pane { display: none; animation: fade 0.2s ease; }
        .tab-pane.active-pane { display: block; }
        .card-modern {
            background: #ffffff;
            border-radius: 32px;
            padding: 1.6rem;
            box-shadow: 0 12px 28px -8px rgba(0, 0, 0, 0.05);
            border: 1px solid #eef3fc;
            margin-bottom: 1.8rem;
        }
        .grid-2col {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 1.5rem;
        }
        .nota-table {
            width: 100%;
            border-collapse: collapse;
        }
        .nota-table th, .nota-table td {
            padding: 0.8rem 0.4rem;
            border-bottom: 1px solid #e2eff8;
            text-align: left;
        }
        .badge-aprov { color: #1f8a4c; background: #e0f3e6; display: inline-block; padding: 0.2rem 1rem; border-radius: 60px; }
        .badge-rec { color: #e68a2e; background: #fef0e2; border-radius: 60px; padding: 0.2rem 1rem; }
        .btn-grad {
            background: linear-gradient(95deg, #1f6e8c, #155a74);
            border: none;
            padding: 0.7rem 1.8rem;
            border-radius: 60px;
            color: white;
            font-weight: 600;
            cursor: pointer;
        }
        .event-item {
            background: #f7fbfe;
            border-radius: 60px;
            padding: 0.7rem 1.2rem;
            margin-bottom: 0.6rem;
            display: flex;
            justify-content: space-between;
            border-left: 6px solid #f5b042;
        }
        footer {
            background: #e9f0f6;
            text-align: center;
            padding: 1rem;
            font-size: 0.8rem;
            color: #2c6f8c;
        }
        @media (max-width: 700px) {
            .auth-screen { padding: 2rem; flex-direction: column; }
            .main-content { padding: 1rem; }
            .tab-btn { padding: 0.7rem 1rem; }
        }
    </style>
</head>
<body>
<div class="app-wrapper">
    <!-- Tela de Login/Cadastro -->
    <div id="authScreen" class="auth-screen">
        <div class="auth-brand">
            <h1><i class="fas fa-graduation-cap"></i> Portal Fortinhos</h1>
            <div class="highlight">
                <i class="fas fa-code-branch"></i> Arquitetura de Software v1.0<br>
                <span>Cadastro · Login · JWT simulado</span>
            </div>
        </div>
        <div class="auth-card">
            <div class="auth-tabs">
                <button class="auth-tab active" id="tabLoginBtn">🔐 Login</button>
                <button class="auth-tab" id="tabCadastroBtn">📝 Cadastro</button>
            </div>
            <!-- Login -->
            <div id="loginForm" class="auth-form active-form">
                <div class="input-icon">
                    <i class="fas fa-user-tag"></i>
                    <select id="loginPerfil">
                        <option value="aluno">🎓 Aluno</option>
                        <option value="professor">👨‍🏫 Professor</option>
                        <option value="secretaria">📋 Secretaria</option>
                    </select>
                </div>
                <div class="input-icon">
                    <i class="fas fa-id-card"></i>
                    <input type="text" id="loginEmail" placeholder="E-mail ou RA">
                </div>
                <div class="input-icon">
                    <i class="fas fa-key"></i>
                    <input type="password" id="loginSenha" placeholder="Senha">
                </div>
                <button class="btn-auth" id="btnLogin">Entrar</button>
                <div id="loginError" class="error-msg"></div>
                <div class="info-text">📌 Contas demo: aluno (joao@fortinhos.edu.br / 123456) | professor (professor@fortinhos.edu.br / 123456) | secretaria (secretaria@fortinhos.edu.br / 123456)</div>
            </div>
            <!-- Cadastro -->
            <div id="cadastroForm" class="auth-form">
                <div class="input-icon">
                    <i class="fas fa-user-tag"></i>
                    <select id="cadastroPerfil">
                        <option value="aluno">🎓 Aluno</option>
                        <option value="professor">👨‍🏫 Professor</option>
                        <option value="secretaria">📋 Secretaria</option>
                    </select>
                </div>
                <div class="input-icon">
                    <i class="fas fa-user"></i>
                    <input type="text" id="cadastroNome" placeholder="Nome completo">
                </div>
                <div class="input-icon">
                    <i class="fas fa-envelope"></i>
                    <input type="email" id="cadastroEmail" placeholder="E-mail">
                </div>
                <div class="input-icon">
                    <i class="fas fa-id-card"></i>
                    <input type="text" id="cadastroRa" placeholder="RA (para aluno) ou identificador">
                </div>
                <div class="input-icon">
                    <i class="fas fa-key"></i>
                    <input type="password" id="cadastroSenha" placeholder="Senha (mínimo 4)">
                </div>
                <button class="btn-auth" id="btnCadastrar">Cadastrar</button>
                <div id="cadastroMsg" class="success-msg"></div>
                <div id="cadastroError" class="error-msg"></div>
            </div>
        </div>
    </div>

    <!-- Dashboard -->
    <div id="dashboardContainer" class="dashboard-container">
        <div class="top-bar">
            <div class="logo-dash"><i class="fas fa-chalkboard-user"></i> Portal Fortinhos</div>
            <div class="user-badge" id="userGreeting"></div>
            <button id="logoutBtn" class="logout-btn">Sair</button>
        </div>
        <div class="nav-tabs" id="tabHeaders"></div>
        <div class="main-content" id="mainContent"></div>
        <footer>Arquitetura em camadas | RF001..RF006 + NF001..NF004 | Dados salvos localmente</footer>
    </div>
</div>

<script>
    // ========== DADOS INICIAIS (localStorage) ==========
    let usuarios = JSON.parse(localStorage.getItem("fortinhos_usuarios")) || [];
    if(usuarios.length === 0) {
        usuarios = [
            { id: "1", perfil: "aluno", nome: "João Silva", email: "joao@fortinhos.edu.br", senha: "123456", ra: "RA123456" },
            { id: "2", perfil: "professor", nome: "Prof. Ericka Campos", email: "professor@fortinhos.edu.br", senha: "123456", ra: "PROF001" },
            { id: "3", perfil: "secretaria", nome: "Secretaria Acadêmica", email: "secretaria@fortinhos.edu.br", senha: "123456", ra: "SEC001" }
        ];
        localStorage.setItem("fortinhos_usuarios", JSON.stringify(usuarios));
    }

    let notasAlunos = JSON.parse(localStorage.getItem("fortinhos_notas")) || {
        "João Silva": { "Lógica": { bim1: 8.5, bim2: 9.0 }, "Banco de Dados": { bim1: 7.0, bim2: 6.5 }, "Programação Web": { bim1: 5.5, bim2: 6.0 }, "Design UI": { bim1: 9.0, bim2: 8.5 } },
        "Maria Oliveira": { "Lógica": { bim1: 9.0, bim2: 8.5 }, "Banco de Dados": { bim1: 8.0, bim2: 7.5 }, "Programação Web": { bim1: 7.0, bim2: 8.0 }, "Design UI": { bim1: 10.0, bim2: 9.5 } },
        "Carlos Souza": { "Lógica": { bim1: 6.0, bim2: 5.5 }, "Banco de Dados": { bim1: 5.0, bim2: 4.5 }, "Programação Web": { bim1: 6.5, bim2: 5.0 }, "Design UI": { bim1: 7.0, bim2: 6.5 } }
    };
    let logsAlteracao = JSON.parse(localStorage.getItem("fortinhos_logs")) || [];
    let solicitacoesRematricula = JSON.parse(localStorage.getItem("fortinhos_rematriculas")) || [];
    let calendarioEventos = JSON.parse(localStorage.getItem("fortinhos_calendario")) || [
        { id: 1, titulo: "📚 Início das Aulas 2026.1", data: "2026-02-10" },
        { id: 2, titulo: "📝 Prova de Lógica", data: "2026-03-20" },
        { id: 3, titulo: "☀️ Feriado - Tiradentes", data: "2026-04-21" }
    ];

    function salvarDados() {
        localStorage.setItem("fortinhos_notas", JSON.stringify(notasAlunos));
        localStorage.setItem("fortinhos_logs", JSON.stringify(logsAlteracao));
        localStorage.setItem("fortinhos_rematriculas", JSON.stringify(solicitacoesRematricula));
        localStorage.setItem("fortinhos_calendario", JSON.stringify(calendarioEventos));
    }

    let currentSession = null;

    // ========== FUNÇÕES AUXILIARES ==========
    function calcularMediaESituacao(notaData) {
        let media = (notaData.bim1 + notaData.bim2)/2;
        if (media >= 7) return { media: media.toFixed(1), texto: "Aprovado", classe: "badge-aprov" };
        if (media >= 5) return { media: media.toFixed(1), texto: "Recuperação", classe: "badge-rec" };
        return { media: media.toFixed(1), texto: "Reprovado", classe: "badge-rec" };
    }

    function renderNotasAluno(alunoNome, containerId) {
        const container = document.getElementById(containerId);
        if(!container) return;
        let disciplinas = ["Lógica","Banco de Dados","Programação Web","Design UI"];
        let html = `<table class="nota-table"><thead><tr><th>Disciplina</th><th>1° Bim</th><th>2° Bim</th><th>Média</th><th>Situação</th></tr></thead><tbody>`;
        for(let disc of disciplinas) {
            let data = notasAlunos[alunoNome]?.[disc];
            if(data){
                let sit = calcularMediaESituacao(data);
                html += `<tr><td><i class="fas fa-book-open"></i> ${disc}</td><td>${data.bim1.toFixed(1)}</td><td>${data.bim2.toFixed(1)}</td><td><strong>${sit.media}</strong></td><td><span class="${sit.classe}">${sit.texto}</span></td></tr>`;
            }
        }
        html += `</tbody></table>`;
        container.innerHTML = html;
    }

    function renderCalendario(containerId) {
        const container = document.getElementById(containerId);
        if(!container) return;
        container.innerHTML = "";
        calendarioEventos.slice().sort((a,b)=>a.data.localeCompare(b.data)).forEach(ev => {
            let div = document.createElement("div");
            div.className = "event-item";
            div.innerHTML = `<span><i class="far fa-calendar-alt"></i> ${ev.titulo}</span><span>📅 ${ev.data}</span>`;
            container.appendChild(div);
        });
    }

    function atualizarStatusRematriculaAluno(alunoNome) {
        const div = document.getElementById("rematriculaStatusAluno");
        if(!div) return;
        let req = solicitacoesRematricula.find(r => r.alunoNome === alunoNome);
        if(req) {
            if(req.status === "pendente") div.innerHTML = `<i class="fas fa-hourglass-half"></i> Solicitação pendente. Aguardando secretaria.`;
            else div.innerHTML = `<i class="fas fa-check-circle" style="color:#2e7d64;"></i> Rematrícula aprovada para ${req.semestre}!`;
        } else div.innerHTML = `<i class="fas fa-file-signature"></i> Nenhuma solicitação ativa.`;
    }

    function renderLogsAlteracao() {
        const logDiv = document.getElementById("logAlteracoesPanel");
        if(!logDiv) return;
        if(logsAlteracao.length === 0) { logDiv.innerHTML = "<div class='card-modern'>Nenhuma alteração registrada.</div>"; return; }
        let html = `<div class='card-modern'><strong>📋 Últimas alterações (RF004)</strong><ul style="margin-top:0.8rem;">`;
        logsAlteracao.slice().reverse().slice(0,10).forEach(l => {
            html += `<li><i class="fas fa-pen"></i> ${l.data} | ${l.professor} alterou ${l.aluno} - ${l.disciplina}: ${l.valorAntigo} → ${l.valorNovo}</li>`;
        });
        html += `</ul></div>`;
        logDiv.innerHTML = html;
    }

    function adicionarLog(disciplina, aluno, bimestre, valorAntigo, valorNovo, professor) {
        logsAlteracao.push({ disciplina, aluno, bimestre, valorAntigo, valorNovo, professor, data: new Date().toLocaleString() });
        salvarDados();
        renderLogsAlteracao();
    }

    function lancarNota(disciplina, alunoNome, bimestre, novaNota, professor) {
        let alunoData = notasAlunos[alunoNome];
        if(!alunoData || !alunoData[disciplina]) return false;
        let campo = bimestre == 1 ? "bim1" : "bim2";
        let antigo = alunoData[disciplina][campo];
        if(antigo === novaNota) return false;
        alunoData[disciplina][campo] = parseFloat(novaNota);
        adicionarLog(disciplina, alunoNome, bimestre, antigo.toFixed(1), novaNota, professor);
        salvarDados();
        return true;
    }

    function renderSecretariaRematriculas() {
        const container = document.getElementById("listaRematriculasSecretaria");
        if(!container) return;
        let pendentes = solicitacoesRematricula.filter(r => r.status === "pendente");
        if(pendentes.length === 0) container.innerHTML = "<div class='card-modern'>✅ Nenhuma solicitação pendente.</div>";
        else {
            let html = `<div class='card-modern'><h4><i class="fas fa-tasks"></i> Solicitações pendentes</h4><ul>`;
            pendentes.forEach(r => {
                html += `<li><strong>${r.alunoNome}</strong> - solicitado em ${r.dataSolicitacao} <button class='btn-grad' style="margin-left:1rem; padding:0.2rem 1rem;" onclick='aprovarRematricula("${r.alunoNome}")'>Aprovar</button></li>`;
            });
            html += `</ul></div>`;
            container.innerHTML = html;
        }
    }

    window.aprovarRematricula = function(alunoNome) {
        let req = solicitacoesRematricula.find(r => r.alunoNome === alunoNome && r.status === "pendente");
        if(req) { req.status = "aprovada"; salvarDados(); renderSecretariaRematriculas(); if(currentSession?.perfil === "aluno" && currentSession.nome === alunoNome) atualizarStatusRematriculaAluno(currentSession.nome); alert(`Rematrícula de ${alunoNome} aprovada!`); }
    }

    function buildDashboard() {
        const tabContainer = document.getElementById("tabHeaders");
        const contentContainer = document.getElementById("mainContent");
        if(!currentSession) return;
        document.getElementById("userGreeting").innerHTML = `<i class="fas fa-user-circle"></i> ${currentSession.nome} (${currentSession.perfil === "aluno" ? "Aluno" : currentSession.perfil === "professor" ? "Professor" : "Secretaria"})`;
        
        let tabsHtml = "", panesHtml = "";
        if(currentSession.perfil === "aluno") {
            tabsHtml = `<button class="tab-btn active" data-tab="alunoNotas"><i class="fas fa-chart-line"></i> Minhas Notas</button>
                        <button class="tab-btn" data-tab="alunoCalendario"><i class="fas fa-calendar-week"></i> Calendário</button>
                        <button class="tab-btn" data-tab="alunoRematricula"><i class="fas fa-file-signature"></i> Rematrícula</button>`;
            panesHtml = `
                <div id="alunoNotas" class="tab-pane active-pane"><div class="card-modern"><h3>📊 Desempenho Acadêmico (RF003)</h3><div id="alunoNotasContainer"></div></div></div>
                <div id="alunoCalendario" class="tab-pane"><div class="card-modern"><h3>📅 Calendário Escolar (RF006)</h3><div id="calendarioLista"></div><button class="btn-grad" id="refreshCalBtn" style="margin-top:1rem;">Atualizar</button></div></div>
                <div id="alunoRematricula" class="tab-pane"><div class="card-modern"><h3>📎 Solicitação de Rematrícula (RF005)</h3><button class="btn-grad" id="solicitarRematAluno">Solicitar rematrícula + boleto simulado</button><div id="rematriculaStatusAluno" class="card-modern" style="margin-top:1rem;"></div></div></div>
            `;
        } else if(currentSession.perfil === "professor") {
            tabsHtml = `<button class="tab-btn active" data-tab="profNotas"><i class="fas fa-edit"></i> Lançar Notas</button>
                        <button class="tab-btn" data-tab="profLogs"><i class="fas fa-history"></i> Logs de Auditoria</button>`;
            panesHtml = `
                <div id="profNotas" class="tab-pane active-pane"><div class="card-modern"><h3>✏️ Lançar/Editar Notas (RF004)</h3><div class="grid-2col"><div><label>Disciplina</label><select id="disciplinaP" style="width:100%; padding:0.7rem; border-radius:60px;"><option>Lógica</option><option>Banco de Dados</option><option>Programação Web</option><option>Design UI</option></select></div><div><label>Aluno</label><select id="alunoP" style="width:100%; padding:0.7rem; border-radius:60px;"><option>João Silva</option><option>Maria Oliveira</option><option>Carlos Souza</option></select></div><div><label>Bimestre</label><select id="bimestreP" style="width:100%; padding:0.7rem; border-radius:60px;"><option value="1">1° Bimestre</option><option value="2">2° Bimestre</option></select></div><div><label>Nova Nota (0-10)</label><input type="number" id="novaNotaInput" step="0.1" min="0" max="10" style="width:100%; padding:0.7rem; border-radius:60px;"></div></div><button class="btn-grad" id="lancarNotaBtn" style="margin-top:1rem;">Salvar alteração</button></div></div>
                <div id="profLogs" class="tab-pane"><div id="logAlteracoesPanel"></div></div>
            `;
        } else if(currentSession.perfil === "secretaria") {
            tabsHtml = `<button class="tab-btn active" data-tab="secRemat"><i class="fas fa-clipboard-list"></i> Rematrículas</button>
                        <button class="tab-btn" data-tab="secCal"><i class="fas fa-plus-circle"></i> Calendário</button>
                        <button class="tab-btn" data-tab="secAudit"><i class="fas fa-search"></i> Auditoria</button>`;
            panesHtml = `
                <div id="secRemat" class="tab-pane active-pane"><div class="card-modern"><h3>✅ Aprovação de Rematrículas</h3><div id="listaRematriculasSecretaria"></div><button class="btn-grad" id="aprovarTodasBtn" style="margin-top:1rem;">Aprovar todas pendentes</button></div></div>
                <div id="secCal" class="tab-pane"><div class="card-modern"><h3>📅 Gerenciar Calendário (RF006)</h3><div class="grid-2col"><div><input type="text" id="novoEventoTitulo" placeholder="Título do evento" style="width:100%; padding:0.7rem; border-radius:60px;"></div><div><input type="date" id="novoEventoData" style="width:100%; padding:0.7rem; border-radius:60px;"></div></div><button class="btn-grad" id="adicionarEventoBtn" style="margin-top:1rem;">Adicionar evento</button><hr><div id="previewCalendario" style="margin-top:1rem;"></div></div></div>
                <div id="secAudit" class="tab-pane"><div class="card-modern"><h3>📋 Logs do Sistema (NF001/NF002)</h3><div id="logsAuditoriaSec" style="background:#f4f9fe; padding:1rem; border-radius:24px; max-height:250px; overflow:auto;"></div><button class="btn-grad" id="recarregarLogsBtn" style="margin-top:1rem;">Recarregar logs</button></div></div>
            `;
        }
        tabContainer.innerHTML = tabsHtml;
        contentContainer.innerHTML = panesHtml;
        
        // Eventos das abas
        document.querySelectorAll(".tab-btn").forEach(btn => {
            btn.addEventListener("click", () => {
                let tabId = btn.dataset.tab;
                document.querySelectorAll(".tab-pane").forEach(p => p.classList.remove("active-pane"));
                document.getElementById(tabId)?.classList.add("active-pane");
                document.querySelectorAll(".tab-btn").forEach(b => b.classList.remove("active"));
                btn.classList.add("active");
            });
        });
        
        if(currentSession.perfil === "aluno") {
            renderNotasAluno(currentSession.nome, "alunoNotasContainer");
            renderCalendario("calendarioLista");
            atualizarStatusRematriculaAluno(currentSession.nome);
            document.getElementById("solicitarRematAluno")?.addEventListener("click", () => {
                if(solicitacoesRematricula.find(r => r.alunoNome === currentSession.nome && r.status !== "aprovada")) { alert("Você já possui uma solicitação ativa."); return; }
                solicitacoesRematricula.push({ alunoNome: currentSession.nome, status: "pendente", semestre: "2026.1", dataSolicitacao: new Date().toLocaleDateString() });
                salvarDados();
                atualizarStatusRematriculaAluno(currentSession.nome);
                alert("✅ Solicitação de rematrícula enviada! Boleto simulado gerado.");
            });
            document.getElementById("refreshCalBtn")?.addEventListener("click", () => renderCalendario("calendarioLista"));
        }
        else if(currentSession.perfil === "professor") {
            renderLogsAlteracao();
            document.getElementById("lancarNotaBtn")?.addEventListener("click", () => {
                let disciplina = document.getElementById("disciplinaP").value;
                let aluno = document.getElementById("alunoP").value;
                let bim = parseInt(document.getElementById("bimestreP").value);
                let nota = parseFloat(document.getElementById("novaNotaInput").value);
                if(isNaN(nota) || nota<0 || nota>10) { alert("Nota inválida (0-10)"); return; }
                if(lancarNota(disciplina, aluno, bim, nota, currentSession.nome)) alert("Nota alterada com sucesso! Log registrado.");
                else alert("Nota igual à anterior. Nenhuma alteração.");
                document.getElementById("novaNotaInput").value = "";
                if(currentSession.perfil === "aluno") renderNotasAluno(currentSession.nome, "alunoNotasContainer");
            });
        }
        else if(currentSession.perfil === "secretaria") {
            renderSecretariaRematriculas();
            const previewCal = () => {
                let container = document.getElementById("previewCalendario");
                if(container) { container.innerHTML = ""; calendarioEventos.forEach(ev => { let d = document.createElement("div"); d.className = "event-item"; d.innerHTML = `<span>${ev.titulo}</span><span>${ev.data}</span>`; container.appendChild(d); }); }
            };
            previewCal();
            document.getElementById("adicionarEventoBtn")?.addEventListener("click", () => {
                let titulo = document.getElementById("novoEventoTitulo").value.trim();
                let data = document.getElementById("novoEventoData").value;
                if(!titulo || !data) { alert("Preencha título e data"); return; }
                calendarioEventos.push({ id: Date.now(), titulo, data });
                salvarDados();
                previewCal();
                alert("Evento adicionado ao calendário!");
                document.getElementById("novoEventoTitulo").value = "";
                document.getElementById("novoEventoData").value = "";
            });
            document.getElementById("aprovarTodasBtn")?.addEventListener("click", () => {
                solicitacoesRematricula.forEach(r => { if(r.status === "pendente") r.status = "aprovada"; });
                salvarDados();
                renderSecretariaRematriculas();
                if(currentSession.perfil === "aluno") atualizarStatusRematriculaAluno(currentSession.nome);
                alert("Todas as rematrículas foram aprovadas!");
            });
            document.getElementById("recarregarLogsBtn")?.addEventListener("click", () => {
                let logDiv = document.getElementById("logsAuditoriaSec");
                if(logsAlteracao.length === 0) logDiv.innerHTML = "Nenhum log de alteração de notas até o momento.";
                else { let txt = logsAlteracao.slice(-10).map(l => `📌 ${l.data} | ${l.professor} alterou ${l.aluno} - ${l.disciplina}`).join("<br>"); logDiv.innerHTML = txt; }
            });
            document.getElementById("recarregarLogsBtn")?.click();
        }
    }

    // ========== EVENTOS DE LOGIN / CADASTRO ==========
    const tabLogin = document.getElementById("tabLoginBtn");
    const tabCadastro = document.getElementById("tabCadastroBtn");
    const formLogin = document.getElementById("loginForm");
    const formCadastro = document.getElementById("cadastroForm");

    tabLogin.addEventListener("click", () => {
        tabLogin.classList.add("active");
        tabCadastro.classList.remove("active");
        formLogin.classList.add("active-form");
        formCadastro.classList.remove("active-form");
        document.getElementById("loginError").innerText = "";
        document.getElementById("cadastroError").innerText = "";
        document.getElementById("cadastroMsg").innerText = "";
    });
    tabCadastro.addEventListener("click", () => {
        tabCadastro.classList.add("active");
        tabLogin.classList.remove("active");
        formCadastro.classList.add("active-form");
        formLogin.classList.remove("active-form");
        document.getElementById("loginError").innerText = "";
        document.getElementById("cadastroError").innerText = "";
        document.getElementById("cadastroMsg").innerText = "";
    });

    document.getElementById("btnLogin").addEventListener("click", () => {
        const perfil = document.getElementById("loginPerfil").value;
        const email = document.getElementById("loginEmail").value.trim();
        const senha = document.getElementById("loginSenha").value;
        const user = usuarios.find(u => u.perfil === perfil && (u.email === email || u.ra === email) && u.senha === senha);
        if(user) {
            currentSession = { ...user };
            document.getElementById("authScreen").style.display = "none";
            document.getElementById("dashboardContainer").classList.add("active-dash");
            buildDashboard();
        } else {
            document.getElementById("loginError").innerText = "Credenciais inválidas! Use as contas demo.";
        }
    });

    document.getElementById("btnCadastrar").addEventListener("click", () => {
        const perfil = document.getElementById("cadastroPerfil").value;
        const nome = document.getElementById("cadastroNome").value.trim();
        const email = document.getElementById("cadastroEmail").value.trim();
        const ra = document.getElementById("cadastroRa").value.trim();
        const senha = document.getElementById("cadastroSenha").value;
        if(!nome || !email || !ra || !senha || senha.length < 4) {
            document.getElementById("cadastroError").innerText = "Preencha todos os campos (senha ≥ 4)";
            return;
        }
        if(usuarios.find(u => u.email === email)) {
            document.getElementById("cadastroError").innerText = "E-mail já cadastrado!";
            return;
        }
        const novoUsuario = { id: Date.now().toString(), perfil, nome, email, senha, ra };
        usuarios.push(novoUsuario);
        localStorage.setItem("fortinhos_usuarios", JSON.stringify(usuarios));
        document.getElementById("cadastroMsg").innerText = "Cadastro realizado! Faça login.";
        document.getElementById("cadastroError").innerText = "";
        document.getElementById("cadastroNome").value = "";
        document.getElementById("cadastroEmail").value = "";
        document.getElementById("cadastroRa").value = "";
        document.getElementById("cadastroSenha").value = "";
        tabLogin.click();
    });

    document.getElementById("logoutBtn").addEventListener("click", () => {
        currentSession = null;
        document.getElementById("dashboardContainer").classList.remove("active-dash");
        document.getElementById("authScreen").style.display = "flex";
        document.getElementById("loginEmail").value = "";
        document.getElementById("loginSenha").value = "";
        document.getElementById("loginError").innerText = "";
    });
</script>
</body>
</html>