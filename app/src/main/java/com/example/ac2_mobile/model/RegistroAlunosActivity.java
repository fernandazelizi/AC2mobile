import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ac2_mobile.R;
import com.example.ac2_mobile.adapter.AlunoAdapter;
import com.example.ac2_mobile.model.Aluno;
import com.example.ac2_mobile.service.AlunoService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroAlunosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlunoAdapter alunoAdapter;
    private AlunoService alunoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_alunos);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        recyclerView = findViewById(R.id.recyclerViewAlunos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6643e49b6c6a65658708a213.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        alunoService = retrofit.create(AlunoService.class);
        mostrarAlunos();
    }

    private void mostrarAlunos() {
        Call<List<Aluno>> call = alunoService.getAlunos();
        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Aluno> alunos = response.body();
                    alunoAdapter = new AlunoAdapter(alunos);
                    recyclerView.setAdapter(alunoAdapter);
                } else {
                    Toast.makeText(RegistroAlunosActivity.this, "Erro ao carregar alunos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                Toast.makeText(RegistroAlunosActivity.this, "Erro ao carregar alunos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}