package ru.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;

import static ru.geekbrains.calculator.R.string.error;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b0;
    private Button b_equal;
    private Button b_multi;
    private Button b_divide;
    private Button b_add;
    private Button b_sub;
    private Button b_clear;
    private Button b_dot;
    private Button b_para1;
    private Button b_para2;
    private TextView tInput;
    private TextView tOutput;
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';
    private final char EQU = '=';
    private final char EXTRA = '@';
    private final char MODULUS = '%';
    private char ACTION;
    private double val1 = Double.NaN;
    private double val2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewSetup();

        b1.setOnClickListener((View view) -> {
            ifErrorOnOutput();
            exceedLength();
            tInput.setText(String.format("%s1", tInput.getText().toString()));
        });

        b2.setOnClickListener((View view) -> {
            ifErrorOnOutput();
            exceedLength();
            tInput.setText(String.format("%s2", tInput.getText().toString()));
        });

        b3.setOnClickListener((View view) -> {
            ifErrorOnOutput();
            exceedLength();
            tInput.setText(String.format("%s3", tInput.getText().toString()));
        });

        b4.setOnClickListener((View view) -> {
            ifErrorOnOutput();
            exceedLength();
            tInput.setText(String.format("%s4", tInput.getText().toString()));
        });

        b5.setOnClickListener((View view) -> {
            ifErrorOnOutput();
            exceedLength();
            tInput.setText(String.format("%s5", tInput.getText().toString()));
        });

        b6.setOnClickListener((View view) -> {
            ifErrorOnOutput();
            exceedLength();
            tInput.setText(String.format("%s6", tInput.getText().toString()));
        });

        b7.setOnClickListener((View view) -> {
            ifErrorOnOutput();
            exceedLength();
            tInput.setText(String.format("%s7", tInput.getText().toString()));
        });

        b8.setOnClickListener((View view) -> {
            ifErrorOnOutput();
            exceedLength();
            tInput.setText(String.format("%s8", tInput.getText().toString()));
        });

        b9.setOnClickListener((View view) -> {
            ifErrorOnOutput();
            exceedLength();
            tInput.setText(String.format("%s9", tInput.getText().toString()));
        });

        b0.setOnClickListener((View view) -> {
            ifErrorOnOutput();
            exceedLength();
            tInput.setText(String.format("%s0", tInput.getText().toString()));
        });

        b_dot.setOnClickListener((View view) -> {
            exceedLength();
            tInput.setText(String.format("%s.", tInput.getText().toString()));
        });

        b_para1.setOnClickListener((View view) -> {
            if (tInput.getText().length() > 0) {
                ACTION = MODULUS;
                operation();
                if (!ifReallyDecimal()) {
                    tOutput.setText(MessageFormat.format("{0}%", val1));
                } else {
                    tOutput.setText(MessageFormat.format("{0}%", (int) val1));
                }
                tInput.setText(null);
            } else {
                tOutput.setText(error);
            }
        });

        b_add.setOnClickListener((View view) -> {
            if (tInput.getText().length() > 0) {
                ACTION = ADDITION;
                operation();
                if (!ifReallyDecimal()) {
                    tOutput.setText(MessageFormat.format("{0}+", val1));
                } else {
                    tOutput.setText(MessageFormat.format("{0}+", (int) val1));
                }
                tInput.setText(null);
            } else {
                tOutput.setText(error);
            }
        });

        b_sub.setOnClickListener((View view) -> {
            if (tInput.getText().length() > 0) {
                ACTION = SUBTRACTION;
                operation();
                if (tInput.getText().length() > 0)
                    if (!ifReallyDecimal()) {
                        tOutput.setText(MessageFormat.format("{0}-", val1));
                    } else {
                        tOutput.setText(MessageFormat.format("{0}-", (int) val1));
                    }
                tInput.setText(null);
            } else {
                tOutput.setText(error);
            }
        });

        b_multi.setOnClickListener(view -> {
            if (tInput.getText().length() > 0) {
                ACTION = MULTIPLICATION;
                operation();
                if (!ifReallyDecimal()) {
                    tOutput.setText(MessageFormat.format("{0}×", val1));
                } else {
                    tOutput.setText(MessageFormat.format("{0}×", (int) val1));
                }
                tInput.setText(null);
            } else {
                tOutput.setText(error);
            }
        });

        b_divide.setOnClickListener((View view) -> {
            if (tInput.getText().length() > 0) {
                ACTION = DIVISION;
                operation();
                if (ifReallyDecimal()) {
                    tOutput.setText(MessageFormat.format("{0}/", (int) val1));
                } else {
                    tOutput.setText(MessageFormat.format("{0}/", val1));
                }
                tInput.setText(null);
            } else {
                tOutput.setText(error);
            }
        });

        b_para2.setOnClickListener((View view) -> {
            if (!tOutput.getText().toString().isEmpty() || !tInput.getText().toString().isEmpty()) {
                val1 = Double.parseDouble(tInput.getText().toString());
                ACTION = EXTRA;
                tOutput.setText(MessageFormat.format("-{0}", tInput.getText().toString()));
                tInput.setText("");
            } else {
                tOutput.setText(error);
            }
        });

        b_equal.setOnClickListener((View view) -> {
            if (tInput.getText().length() > 0) {
                operation();
                ACTION = EQU;
                if (!ifReallyDecimal()) {
                    tOutput.setText(/*t2.getText().toString() + String.valueOf(val2) +
                    "=" + */String.valueOf(val1));
                } else {
                    tOutput.setText(/*t2.getText().toString() + String.valueOf(val2) +
                    "=" + */String.valueOf((int) val1));
                }
                tInput.setText(null);
            } else {
                tOutput.setText(error);
            }
        });

        b_clear.setOnClickListener((View view) -> {
            if (tInput.getText().length() > 0) {
                CharSequence name = tInput.getText().toString();
                tInput.setText(name.subSequence(0, name.length() - 1));
            } else {
                val1 = Double.NaN;
                val2 = Double.NaN;
                tInput.setText("");
                tOutput.setText("");
            }
        });

        b_clear.setOnLongClickListener(view -> {
            val1 = Double.NaN;
            val2 = Double.NaN;
            tInput.setText("");
            tOutput.setText("");
            return true;
        });
    }

    private void viewSetup() {
        b1 = findViewById(R.id.button_1);
        b2 = findViewById(R.id.button_2);
        b3 = findViewById(R.id.button_3);
        b4 = findViewById(R.id.button_4);
        b5 = findViewById(R.id.button_5);
        b6 = findViewById(R.id.button_6);
        b7 = findViewById(R.id.button_7);
        b8 = findViewById(R.id.button_8);
        b9 = findViewById(R.id.button_9);
        b0 = findViewById(R.id.button_0);
        b_equal = findViewById(R.id.button_equal);
        b_multi = findViewById(R.id.button_multi);
        b_divide = findViewById(R.id.button_divide);
        b_add = findViewById(R.id.button_add);
        b_sub = findViewById(R.id.button_sub);
        b_clear = findViewById(R.id.button_clear);
        b_dot = findViewById(R.id.button_dot);
        b_para1 = findViewById(R.id.button_para1);
        b_para2 = findViewById(R.id.button_para2);
        tInput = findViewById(R.id.input);
        tOutput = findViewById(R.id.output);
    }

    private void operation() {
        if (!Double.isNaN(val1)) {
            switch (tOutput.getText().toString()) {
            }
            val2 = Double.parseDouble(tInput.getText().toString());

            switch (ACTION) {
                case ADDITION:
                    val1 = val1 + val2;
                    break;
                case SUBTRACTION:
                    val1 = val1 - val2;
                    break;
                case MULTIPLICATION:
                    val1 = val1 * val2;
                    break;
                case DIVISION:
                    val1 = val1 / val2;
                    break;
                case EXTRA:
                    val1 = (-1) * val1;
                    break;
                case MODULUS:
                    val1 = val1 % val2;
                    break;
                case EQU:
                    break;
            }
        } else {
            val1 = Double.parseDouble(tInput.getText().toString());
        }
    }

    // Удалить сообщение об ошибке, которое там написано
    private void ifErrorOnOutput() {
        tOutput.getText().toString();
    }

    // Независимо от того, является ли значение двойным или нет
    private boolean ifReallyDecimal() {
        return val1 == (int) val1;
    }

    // Сделать текст мельче, если много цифр
    private void exceedLength() {
        if (tInput.getText().toString().length() > 10) {
            tInput.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }
    }
}