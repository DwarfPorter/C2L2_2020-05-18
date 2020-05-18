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
    private View.OnClickListener clickAlertDialogList = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] items = getResources().getStringArray(R.array.choose);
            // Создаём билдер и передаём контекст приложения
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // В билдере указываем заголовок окна (можно указывать как ресурс, так
            // и строку)
            builder.setTitle(R.string.exclamation)
                    // Добавим список элементов
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int item) {
                            Toast.makeText(MainActivity.this, String.format("Выбран пункт %d", item + 1), Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }
    };

    private int chosen = -1;    // Здесь будет храниться выбранный пункт
    private View.OnClickListener clickAlertDialogListSingle = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final String[] items = getResources().getStringArray(R.array.choose);
            // Создаём билдер и передаём контекст приложения
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // В билдере указываем заголовок окна (можно указывать как ресурс, так
            // и строку)
            builder.setTitle(R.string.exclamation)
                    // Добавляем список элементов; chosen - выбранный элемент,
                    // если = -1, то ни один не выбран
                    .setSingleChoiceItems(items, chosen, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int item) {
                            chosen = item; // Обновляем выбранный элемент
                        }
                    })
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "Отмена!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (chosen == -1) {
                                Toast.makeText(MainActivity.this, "Ок, пункт не выбран!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(MainActivity.this, String.format("Ок, выбран '%s'!", items[chosen]), Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };
    private View.OnClickListener clickAlertDialogListMulti = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final String[] items = getResources().getStringArray(R.array.choose);
            final boolean[] chosen = {false, true, false};
            // Создаём билдер и передаём контекст приложения
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // В билдере указываем заголовок окна (можно указывать как ресурс, так
            // и строку)
            builder.setTitle(R.string.exclamation)
                    // Добавляем список элементов; булев chosen - массив
                    // с выбранными элементами
                    .setMultiChoiceItems(items, chosen, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            chosen[i] = b; // При переключении обновляем ячейку
                            // в массиве
                        }
                    })
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "Отмена!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Собираем выбранные элементы в строку
                            StringBuilder sb = new StringBuilder("Ок, выбрано: ");
                            for (int index = 0; index < chosen.length; index++) {
                                if (chosen[index]) {
                                    sb.append(items[index]);
                                    sb.append("; ");
                                }
                            }
                            Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
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
        findViewById(R.id.alertDialogList).setOnClickListener(clickAlertDialogList);
        findViewById(R.id.alertDialogListSingle).setOnClickListener(clickAlertDialogListSingle);
        findViewById(R.id.alertDialogListMulti).setOnClickListener(clickAlertDialogListMulti);

    }
}
