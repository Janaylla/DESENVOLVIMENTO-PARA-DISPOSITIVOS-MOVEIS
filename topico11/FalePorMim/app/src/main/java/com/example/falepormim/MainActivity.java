package com.example.falepormim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.falepormim.controller.FalaController;
import com.example.falepormim.model.Fala;
import com.example.falepormim.view.ArrayAdapterFalas;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener  {

    private TextToSpeech tts;
    private Button btnSpeak;
    private Button btnClear;
    private EditText txtSpeak;
    private ArrayAdapterFalas appAdapter;

    private FalaController falaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSpeak = (Button) findViewById(R.id.btnSpeak);
        btnClear = (Button) findViewById(R.id.btnClear);
        txtSpeak = (EditText) findViewById(R.id.txtSpeak);
        ListView listView = findViewById(R.id.listFala);

        // Inicializa o TextToSpeech
        tts = new TextToSpeech(this, this);

        // Configura o botão para falar um texto quando clicado
        btnSpeak.setOnClickListener(this::speakOut);
        btnClear.setOnClickListener(this::clear);

        falaController = new FalaController(this);
        ArrayList<Fala> falas = falaController.getListFalas();

        appAdapter = new ArrayAdapterFalas(
                this,
                R.layout.item_lista_fala,
                falas);

        listView.setAdapter(appAdapter);

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int langResult = tts.setLanguage(new Locale("pt", "BR"));
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                exibirAlerta("Error", "Língua não suportada ou dados faltando");
            } else {
                btnSpeak.setEnabled(true);
            }
        } else {
            exibirAlerta("Error", "Falha na inicialização do TextToSpeech");
        }
    }

    private void clear(View v) {
        falaController.excluirTudo();
        appAdapter.clear();
        appAdapter.notifyDataSetChanged();
    }
    private void speakOut(View v) {
        String text = txtSpeak.getText().toString();
        Fala fala = new Fala(text);
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        falaController.cadastrarNovaFala(fala);
        appAdapter.add(fala);
        appAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    public void exibirAlerta(String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);

        // Botão de OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ação a ser executada quando o usuário tocar em OK
                dialog.dismiss(); // Fechar o diálogo
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}