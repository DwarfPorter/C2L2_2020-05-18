package ru.geekbrains.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener clickAlertDialog1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.exclamation)
                    .setMessage(R.string.press_button)
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setCancelable(false)
                    .setPositiveButton(R.string.button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Кнопка ок", Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            Toast.makeText(MainActivity.this, "Диалог открыт", Toast.LENGTH_SHORT).show();
        }
    };
    private View.OnClickListener clickAlertDialog3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.exclamation)
                    .setMessage("2 * 2 = 4?")
                    .setCancelable(true)
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Нет!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNeutralButton(R.string.dunno, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Не знаю!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Да!", Toast.LENGTH_SHORT).show();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.alertDialog1).setOnClickListener(clickAlertDialog1);
        findViewById(R.id.alertDialog3).setOnClickListener(clickAlertDialog3);
    }
}
