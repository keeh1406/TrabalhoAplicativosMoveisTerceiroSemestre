package com.example.opet.aplicativomaiscadastros.ActivityCadastrar;
/**
 * Created by opet on 16/05/2018.
 */

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;
        import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarFornecedores;
        import com.example.opet.aplicativomaiscadastros.DAO.FornecedorDAO;
        import com.example.opet.aplicativomaiscadastros.Model.Fornecedor;
        import com.example.opet.aplicativomaiscadastros.R;
        import java.text.ParseException;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityCadastrarFornecedor extends Activity {
    private EditText editNomeFornecedor;
    private EditText editCNPJFornecedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_fornecedor);

        editNomeFornecedor = (EditText) findViewById(R.id.editNomeFornecedor);
        editCNPJFornecedor = (EditText) findViewById(R.id.editCNPJFornecedor);

    }

    public void salvarFornecedor(View v) throws ParseException {
        FornecedorDAO fornecedorDAO = new FornecedorDAO(this);
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNomeFornecedor(editNomeFornecedor.getText().toString());
        fornecedor.setCNPJ(Long.parseLong(editCNPJFornecedor.getText().toString()));

        long resultado = fornecedorDAO.insereDado(fornecedor);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarFornecedores = new Intent(ActivityCadastrarFornecedor.this,ActivityListarFornecedores.class);
            startActivity(listarFornecedores);
            finish();
        }
        else{
            exibirMensagem("Erro ao cadastrar o item.");
        }
    }

    private void exibirMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}



