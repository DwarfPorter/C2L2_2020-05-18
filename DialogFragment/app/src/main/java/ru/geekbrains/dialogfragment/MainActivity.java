package ru.geekbrains.dialogfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentDialogResult {

    private DialogCustomFragment dlgCustom;
    private DialogBuilderFragment dlgBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dlgCustom = new DialogCustomFragment();
        dlgBuilder = new DialogBuilderFragment();

        findViewById(R.id.dialogBuilder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgBuilder.show(getSupportFragmentManager(), "dialogBuilder");
            }
        });

        findViewById(R.id.dialogCustom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgCustom.show(getSupportFragmentManager(), "dialogCustom");
            }
        });
    }

    @Override
    public void onDialogResult(String result) {
        Toast.makeText(this, "Выбрано " + result, Toast.LENGTH_SHORT).show();
    }
}
