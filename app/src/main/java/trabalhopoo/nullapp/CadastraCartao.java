package trabalhopoo.nullapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Utility.ExpressaoRegular;

public class CadastraCartao  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_cadastracartao);

        Button BtCadastrar;

        BtCadastrar = (Button) findViewById(R.id.BCadastrar);

        BtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etnomecartao,etnumerocartao,etdatacartao,etcodigocartao;
                String NomeCartao,NumeroCartao,DataCartao,CodCartao;

                etnomecartao = (EditText) findViewById(R.id.etnomecartao);
                etnumerocartao = (EditText) findViewById(R.id.etnumerocartao);
                etdatacartao = (EditText) findViewById(R.id.etdatacartao);
                etcodigocartao = (EditText) findViewById(R.id.etcodigocartao);

                NomeCartao = etnomecartao.getText().toString();
                NumeroCartao = etnumerocartao.getText().toString();
                DataCartao = etdatacartao.getText().toString();
                CodCartao = etcodigocartao.getText().toString();


                ExpressaoRegular VerificaS = new ExpressaoRegular();

                if(VerificaS.verificaNome(NomeCartao) == true){
                    if(VerificaS.verificanumeroCartao(NumeroCartao) == true){
                        if(VerificaS.verificaDataCartao(DataCartao) == true){
                            if(VerificaS.verificaCodCartao(CodCartao) == true){
                                //VINCULAR COM O BANCO-------------------------------------------------------------------------------------------------------

                                Alerta("Seu Cartão foi cadastrado com sucesso!");
                                finish();
                            }else{
                                etcodigocartao.setText("");
                                Alerta("Codigo do Cartão Invalido! \n\t Deve ter 3 digitos!");
                            }
                        }else{
                            etdatacartao.setText("");
                            Alerta("Data do Cartão Invalida! \n\t Ex: ( MM/AA )");
                        }
                    }else{
                        etnumerocartao.setText("");
                        Alerta("O numero do Cartão é Invalido! \n\t Deve ter 16 digitos!");
                    }
                }else{
                    etnomecartao.setText("");
                    Alerta("Nome Invalido! \n\t Deve ter no minimo 3 letras!");
                }
            }
        });


    }

    private void Alerta(String a){
        Toast.makeText(this,a,Toast.LENGTH_LONG).show();
    }
}
