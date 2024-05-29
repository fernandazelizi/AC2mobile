package com.example.ac2_mobile.model;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ac2_mobile.R;
import com.example.ac2_mobile.network.ApiCepService;
import com.example.ac2_mobile.network.RetrofitClient;
import com.example.ac2_mobile.util.Endereco;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CadastrarAluno extends AppCompatActivity {

    private EditText input_ra, input_nome, input_cep, input_logradouro, input_complemento, input_bairro, input_cidade, input_Uf;
    private Button button_cep, buttonSalvar;

    private ApiCepService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_aluno);

        input_ra = findViewById(R.id.input_ra);
        input_nome = findViewById(R.id.input_nome);
        input_cep = findViewById(R.id.input_cep);
        input_logradouro = findViewById(R.id.input_logradouro);
        input_complemento = findViewById(R.id.input_complemento);
        input_bairro = findViewById(R.id.input_bairro);
        input_cidade = findViewById(R.id.input_cidade);
        input_Uf = findViewById(R.id.input_Uf);
        button_cep = findViewById(R.id.button_cep);
        buttonSalvar = findViewById(R.id.buttonSalvar);

        // api do cep
        Retrofit retrofit = RetrofitClient.getClient("https://viacep.com.br/");
        apiService = retrofit.create(ApiCepService.class);

        button_cep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cep = input_cep.getText().toString().trim();
                if (!cep.isEmpty()) {
                    buscarEnderecoPorCep(cep);
                } else {
                    Toast.makeText(CadastrarAluno.this, "Por favor, insira um CEP válido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void buscarEnderecoPorCep(String cep) {
        Call<Endereco> call = apiService.getEndereco(cep);
        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Endereco endereco = response.body();
                    preencherCamposEndereco(endereco);
                } else {
                    Toast.makeText(CadastrarAluno.this, "CEP não encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Toast.makeText(CadastrarAluno.this, "Erro ao buscar endereço", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void preencherCamposEndereco(Endereco endereco) {
        input_logradouro.setText(endereco.getLogradouro());
        input_complemento.setText(endereco.getComplemento());
        input_bairro.setText(endereco.getBairro());
        input_cidade.setText(endereco.getLocalidade());
        input_Uf.setText(endereco.getUf());
    }
}