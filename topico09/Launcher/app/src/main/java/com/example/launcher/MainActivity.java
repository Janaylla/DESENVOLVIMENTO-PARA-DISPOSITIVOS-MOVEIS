package com.example.launcher;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.Manifest;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView backgroundImageView;
    private EditText searchEditText;
    private Button selectImageButton;
    private static final int REQUEST_PERMISSION = 1; // You can choose any integer value
    private static final String KEY_BACKGROUND = "background_image_path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Se a permissão não foi concedida, solicite-a ao usuário
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
        }

        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listview_apps);
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> packageInfoList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
/* Cria um Intent com a ação ACTION_MAIN, que é usada para filtrar aplicativos
que tenham uma atividade principal. O segundo argumento null indica que não há
um componente específico a ser direcionado neste momento.*/
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        ArrayAdapterApplicationInfo appAdapter = new ArrayAdapterApplicationInfo(this,R.layout.item_lista, packageInfoList);
        listView.setAdapter(appAdapter);
        backgroundImageView = findViewById(R.id.background_image);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ApplicationInfo applicationInfo = (ApplicationInfo) parent.getItemAtPosition(position);
                String packageName = applicationInfo.packageName;
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

                Intent intent = packageManager.getLaunchIntentForPackage(packageName);
                if (intent != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Não foi possível abrir o aplicativo", Toast.LENGTH_SHORT).show();
                }
            }
        });
        setBackgroundImage();
        selectImageButton = findViewById(R.id.select_image_button);
        selectImageButton.setOnClickListener(v -> {
            // Implemente a lógica para abrir a galeria ou a câmera e permitir que o usuário selecione uma imagem.
            // Depois, armazene o caminho da imagem como explicado anteriormente.
            openImageSelection();
        });
        searchEditText = findViewById(R.id.search_edit_text);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void setBackgroundImage() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        // Substitua "MyAppPrefs" pelo nome do arquivo de preferências compartilhadas do seu aplicativo.

        // Recupere o caminho da imagem de plano de fundo, ou uma string vazia se não estiver definido
        String backgroundImagePath = sharedPreferences.getString(KEY_BACKGROUND, "");

        // Verifique se o usuário fez alguma escolha de plano de fundo
        if (backgroundImagePath != null && !backgroundImagePath.isEmpty()) {
            Log.i("backgroundImagePath", backgroundImagePath.toString());
            // Verifique se o arquivo de imagem realmente existe
            File backgroundFile = new File(backgroundImagePath);
            if (backgroundFile.exists()) {
                Bitmap backgroundBitmap = BitmapFactory.decodeFile(backgroundImagePath);
                // Carregue e defina a imagem de fundo a partir da escolha do usuário
                backgroundImageView.setImageBitmap(backgroundBitmap);
            }
        }
    }
    private final ActivityResultLauncher<Intent> selectImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri selectedImageUri = data.getData();
                        String imagePath = getPathFromUri(selectedImageUri);
                        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(KEY_BACKGROUND, imagePath);
                        editor.apply();
                        setBackgroundImage();
                    }
                }
            }
    );
    private void openImageSelection() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        selectImageLauncher.launch(intent);
    }
    private String getPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String imagePath = cursor.getString(columnIndex);
            cursor.close();
            return imagePath;
        }
        return uri.getPath(); // Se a consulta do cursor falhar, tente obter o caminho a partir do URI diretamente.
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}