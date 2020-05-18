package ru.geekbrains.bottomdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyBottomSheetDialogFragment dialogFragment =
                        MyBottomSheetDialogFragment.newInstance();
                dialogFragment.show(getSupportFragmentManager(),
                        "dialog_fragment");
            }
        });

    }
}
