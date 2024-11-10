package com.example.apphitunggaji;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNama;
    private CheckBox checkBoxMenikah;
    private RadioButton radioGolongan1, radioGolongan2;
    private TextView textViewNama, textViewGaji, textViewTunjangan, textViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        editTextNama = findViewById(R.id.nama);
        checkBoxMenikah = findViewById(R.id.menikah);
        radioGolongan1 = findViewById(R.id.radioButton2);
        radioGolongan2 = findViewById(R.id.radioButton3);
        textViewNama = findViewById(R.id.namas);
        textViewGaji = findViewById(R.id.gaji);
        textViewTunjangan = findViewById(R.id.tunjangan);
        textViewTotal = findViewById(R.id.total);
        Button buttonHitung = findViewById(R.id.button);

        // Set button click listener
        buttonHitung.setOnClickListener(v -> calculateSalary());
    }

    private void calculateSalary() {
        // Get input values
        String nama = editTextNama.getText().toString();
        boolean isMenikah = checkBoxMenikah.isChecked();
        boolean isGolongan1 = radioGolongan1.isChecked();
        boolean isGolongan2 = radioGolongan2.isChecked();

        // Validate input
        if (nama.isEmpty()) {
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isGolongan1 && !isGolongan2) {
            Toast.makeText(this, "Pilih golongan", Toast.LENGTH_SHORT).show();
            return;
        }

        // Define base salary and allowance
        int gajiPokok;
        int tunjangan = 0;

        if (isGolongan1) {
            gajiPokok = 5000000; // Example salary for Golongan1
        } else { // isGolongan2
            gajiPokok = 7000000; // Example salary for Golongan2
        }

        if (isMenikah) {
            tunjangan = 1000000; // Example allowance for married status
        }

        // Calculate total salary
        int totalGaji = gajiPokok + tunjangan;

        // Set results in TextViews
        textViewNama.setText(nama);
        textViewGaji.setText("Rp " + gajiPokok);
        textViewTunjangan.setText("Rp " + tunjangan);
        textViewTotal.setText("Rp " + totalGaji);
    }
}
