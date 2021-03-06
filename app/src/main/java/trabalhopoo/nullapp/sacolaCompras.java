package trabalhopoo.nullapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class sacolaCompras extends AppCompatActivity {

    BancoDados db = new BancoDados(this);
    TextView t,t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sacola_activity);

        t = (TextView) findViewById(R.id.textView4);
        t1 = (TextView) findViewById(R.id.textView3);

        int soma = 0;
        int qtd = 0;
        try{
            ArrayList<Produto> produtos = db.listaTodosProdutosSacola();

            for(Produto p : produtos){
                soma += + p.getPreco_unitario();
                qtd++;
            }

            t.setText(String.valueOf(soma));
            t1.setText(String.valueOf(qtd));
        }catch (Exception e){
//            Toast.makeText(sacolaCompras.this,"OBA",Toast.LENGTH_LONG).show();
        }

    }

    public void visualizarItens(View view){
        startActivity(new Intent(getBaseContext(),visualizarSacola.class));
    }

    public void finalizarCompra(View view){
        ArrayList<Produto> p = db.listaTodosProdutosSacola();
        if(p.size()==0){
            Toast.makeText(sacolaCompras.this,"Voce nao possui produtos na sacola",Toast.LENGTH_LONG).show();
        }else {
            startActivity(new Intent(getBaseContext(), CadastraCartao.class));
        }
    }

    public void limparSacola(View view){

        ArrayList<Produto> produtos = db.listaTodosProdutosSacola();

        ArrayList<Produto> produtosCadatrados = db.listaTodosProdutos();

        if(produtos.size()==0){
            Toast.makeText(sacolaCompras.this,"Voce nao possui produtos na sacola",Toast.LENGTH_LONG).show();

        }else {

            for (Produto t : produtos) {
                db.deletarProdutoSacola(t);
            }

            //funcao top para limpar a sacola e adicionar os produtos
            for (int i = 0; i < produtos.size(); i++) {
                int count = 0;
                for (int j = 0; j < produtosCadatrados.size(); j++) {
                    produtosCadatrados = db.listaTodosProdutos();
                    if (produtos.get(i).getCpf().equals(produtosCadatrados.get(j).getCpf()) && produtos.get(i).getNome().equals(produtosCadatrados.get(j).getNome())) {
                        int qntTotal = produtos.get(i).getQuantidade() + produtosCadatrados.get(j).getQuantidade();
                        produtos.get(i).setQuantidade(qntTotal);
                        db.alterarProduto(produtos.get(i));
                    } else {
                        count++;
                    }
                }
                if (count == produtosCadatrados.size()) {
                    db.addProduto(produtos.get(i));
                }
            }

            Toast.makeText(sacolaCompras.this, "Produtos Removidos", Toast.LENGTH_LONG).show();
            finish();

        }
    }
}
