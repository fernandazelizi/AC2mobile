package com.example.ac2_mobile.service;

import com.example.ac2_mobile.model.Aluno;
import java.util.ArrayList;
import java.util.List;

public class AlunoService {
    private List<Aluno> alunos = new ArrayList<>();


    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    // Listar todos os alunos
    public List<Aluno> listarAlunos() {
        // Aqui você adicionaria a lógica para buscar os dados do MockAPI
        return new ArrayList<>(alunos);
    }
}