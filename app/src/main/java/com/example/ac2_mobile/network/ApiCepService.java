package com.example.ac2_mobile.network;



import com.example.ac2_mobile.model.Aluno;
import com.example.ac2_mobile.util.Endereco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiCepService {



    @POST("aluno")
    Call<Void> registrarAluno(@Body Aluno aluno);

    @GET("aluno")
    Call<List<Aluno>> mostrarAlunos();

    @GET("{cep}/json/")
    Call<Endereco> getEndereco(@Path("cep") String cep);
}


