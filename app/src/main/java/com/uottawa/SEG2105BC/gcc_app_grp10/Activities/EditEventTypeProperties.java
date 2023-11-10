package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Space;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Admin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class EditEventTypeProperties extends AppCompatActivity {

    Admin admin;
    String eventTypeName;
    HashMap<Integer, Integer> fields;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event_type_properties);

        Intent intent = getIntent();
        eventTypeName = intent.getStringExtra("eventTypeName");

        fields = new HashMap<>();

        admin = new Admin("admin", "admin1", "admin@admin.com", "admin");
    }

    public void onAddIndividualFieldButton(View view) {
        LinearLayout linearLayout = findViewById(R.id.fieldsLinearLayout);

        EditText newField = new EditText(this);
        newField.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        int newFieldID = View.generateViewId();
        System.out.println(newFieldID);
        newField.setId(newFieldID);
        newField.setHint(eventTypeName);
        linearLayout.addView(newField);

        // Create a RadioGroup
        RadioGroup radioGroup = new RadioGroup(this);
        Integer radioGroupID = View.generateViewId();
        radioGroup.setId(radioGroupID);
        radioGroup.setLayoutParams(new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,
                RadioGroup.LayoutParams.WRAP_CONTENT));
        radioGroup.setOrientation(RadioGroup.HORIZONTAL);

        RadioButton stringButton = new RadioButton(this);
        stringButton.setId(View.generateViewId());
        stringButton.setText("String");
        radioGroup.addView(stringButton);

        RadioButton integerButton = new RadioButton(this);
        integerButton.setId(View.generateViewId());
        integerButton.setText("Integer");
        radioGroup.addView(integerButton);

        RadioButton decimalButton = new RadioButton(this);
        decimalButton.setId(View.generateViewId());
        decimalButton.setText("Decimal");
        radioGroup.addView(decimalButton);

        linearLayout.addView(radioGroup);

        fields.put(newFieldID, radioGroupID);

        Space space = new Space(this);
        space.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                24));
        linearLayout.addView(space);

    }

    public void onEditFieldsButton(View view) {
        HashMap<String, String> properties = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : fields.entrySet()) {
            Integer fieldNameID = entry.getKey();
            Integer fieldTypeID = entry.getValue();

            EditText currentFieldName = findViewById(fieldNameID);
            RadioGroup currentFieldType = findViewById(fieldTypeID);

            int selectedRadioButtonId = currentFieldType.getCheckedRadioButtonId();

            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

            String selectedText;

            try {
                selectedText = selectedRadioButton.getText().toString();
            }
            catch (Exception e) {
                Snackbar.make(findViewById(android.R.id.content), Objects.requireNonNull(e.getMessage()), Toast.LENGTH_LONG).show();
                return;
            }

            System.out.println(selectedRadioButtonId);

            if (currentFieldName.getText().toString().equals("")) {
                Snackbar.make(findViewById(android.R.id.content), "You cannot have an empty field", Toast.LENGTH_LONG).show();
                return;
            } else {
                if (properties.keySet().contains(currentFieldName.getText().toString())) {
                    Snackbar.make(findViewById(android.R.id.content), "You cannot give a duplicate field name", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    properties.put(currentFieldName.getText().toString(), selectedText);
                }
            }
        }

        admin.createEventType(eventTypeName, properties);

        finish();
    }
}