package com.example.lab2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Spinner spSelector;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.spSelector = findViewById(R.id.spSelector);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.selectionOptions, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spSelector.setAdapter(adapter);
        spSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        TextView countView = findViewById(R.id.countView);
        Button CountBtn = findViewById(R.id.btnCount);
        EditText edUserInput = findViewById(R.id.edUserInputText);
        CountBtn.setOnClickListener(v -> {
            String selectedOption = (String) spSelector.getSelectedItem();
            String inputText = edUserInput.getText().toString();
            int count = Functions.countText(MainActivity.this, selectedOption, inputText);
            if (count > 0)
                countView.setText("Count: " + count);
        });
    }
}