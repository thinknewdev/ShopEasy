package com.thinknew.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.thinknew.shoppinglist.data.Item;


public class CreateItemActivity extends ActionBarActivity {

    public static final String KEY_ITEM = "KEY_ITEM";
    public static final String KEY_EDIT_ITEM = "KEY_EDIT_ITEM";
    public static final String KEY_EDIT_ID = "KEY_EDIT_ID";

    private boolean inEditMode;

    private Spinner typeSpinner;
    private EditText editItemName;
    private EditText editItemPrice;

    private Item itemToEdit;
    private int editId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        typeSpinner = (Spinner) findViewById(R.id.spinnerItemType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        editItemName = (EditText) findViewById(R.id.editItemName);
        editItemPrice = (EditText) findViewById(R.id.editItemPrice);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(KEY_EDIT_ITEM)) {
            inEditMode = true;
            itemToEdit = (Item) getIntent().getSerializableExtra(KEY_EDIT_ITEM);
            editId = getIntent().getIntExtra(KEY_EDIT_ID, -1);

            typeSpinner.setSelection(itemToEdit.getType().getValue());
            editItemName.setText(itemToEdit.getName());
            editItemPrice.setText(Double.toString(itemToEdit.getPriceEstimate()));
        }

        Button saveButton = (Button) findViewById(R.id.btnSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inEditMode) {
                    updatePlace();
                } else {
                    saveItem();
                }

            }
        });
    }

    private void updatePlace() {
        itemToEdit.setName(editItemName.getText().toString());
        itemToEdit.setPriceEstimate(Double.parseDouble(editItemPrice.getText().toString()));
        itemToEdit.setType(Item.ItemType.fromInt(typeSpinner.getSelectedItemPosition()));

        Intent intentResult = new Intent();
        intentResult.putExtra(KEY_EDIT_ID, editId);
        intentResult.putExtra(KEY_ITEM, itemToEdit);
        setResult(RESULT_OK, intentResult);
        finish();
    }

    private void saveItem(){
        Item item = new Item(editItemName.getText().toString(),
                Item.ItemType.fromInt(typeSpinner.getSelectedItemPosition()),
                Double.parseDouble(editItemPrice.getText().toString()),
                1);
        setResult(RESULT_OK, new Intent().putExtra(KEY_ITEM,
                item));

        finish();
    }
}
