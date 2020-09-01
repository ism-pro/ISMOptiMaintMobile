package org.ism.ismoptimaintmobile.ui.stk.inventory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.zxing.integration.android.IntentIntegrator;

import org.ism.ismoptimaintmobile.MainActivity;
import org.ism.ismoptimaintmobile.R;
import org.ism.ismoptimaintmobile.scan.CaptureAct;

import java.util.List;

public class StkInventoryFragment extends Fragment implements View.OnClickListener{

    private View root;

    public static StkInventoryViewModel stkInventoryViewModel;


    private TextView scanned;
    private Button scanBtn;
    private TableLayout tbInventory;


    /**
     * OnCreateView manage creation state.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Get infiltrate fragment stock inventory
        root = inflater.inflate(R.layout.fragment_stk_inventory, container, false);
        // Get scanned view
        scanned = root.findViewById(R.id.stkScanned);
        // Get table scan inventory
        tbInventory = root.findViewById(R.id.tbInventory);
        // Scan button
        scanBtn = root.findViewById(R.id.stkScanBtn);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });

        // Manage view model
        if(stkInventoryViewModel ==null) {
            stkInventoryViewModel =
                    ViewModelProviders.of(this).get(StkInventoryViewModel.class);
        }
        stkInventoryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                scanned.setText(s);
            }
        });
        stkInventoryViewModel.getInventories().observe(getViewLifecycleOwner(), new Observer<List<StkInventoryData>>() {
            @Override
            public void onChanged(List<StkInventoryData> stkInventoryData) {
                updateTable();
            }

        });


        return root;
    }

    /**
     * Process action over screen
     * @param v
     */
    @Override
    public void onClick(View v) {
        scanCode();
    }



    /**
     * scanCode allow to perform call to camera and process the reading of barecode.
     */
    private void scanCode() {
        MainActivity main = (MainActivity) getParentFragment().getActivity();
        IntentIntegrator integrator = new IntentIntegrator(main);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanner le code barre...");
        integrator.initiateScan();
    }

    public void out(String msg){
        Log.e("ASK", msg);
    }

    private void updateTable() {
        out("UPDATE TABLE CALL !");
        if(stkInventoryViewModel.getInventories()!=null){
            out("Exist somes mutable !!");
            List<StkInventoryData> inventories = stkInventoryViewModel.getInventories().getValue();
            if(inventories!=null){
                out("Inventory is not null");
                if(inventories.size()>=1){
                    TableRow tableRow = getAsTableRow(inventories.get(inventories.size()-1));
                    add(tableRow);
                }else{
                    out("Table size is bellow 1");
                }
            }else{
                out("List inventory is empty !!!!!");
            }
        }else{
            out("MUTABLE DOES NOTE EXIST !!!!!!!!");
        }
    }



    public TableRow getAsTableRow(StkInventoryData stkInventoryData){
        int leftRowMargin=0, topRowMargin=0, rightRowMargin=0, bottomRowMargin = 0;
        int textSize = 16, smallTextSize = 8, mediumTextSize = 12;



        // data columns button add
        final Button colBtnAdd = new Button(this.getContext());
        colBtnAdd.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        colBtnAdd.setGravity(Gravity.LEFT);
        colBtnAdd.setPadding(5, 15, 0, 15);
        if (stkInventoryData.getId() == -1) {
            colBtnAdd.setText("+");
            colBtnAdd.setBackgroundColor(Color.parseColor("#f0f0f0"));
            colBtnAdd.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        }else{
            colBtnAdd.setBackgroundColor(Color.parseColor("#f8f8f8"));
            colBtnAdd.setText("+");
            colBtnAdd.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        // data columns Id
        final TextView colId = new TextView(this.getContext());
        colId.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        colId.setGravity(Gravity.LEFT);
        colId.setPadding(5, 15, 0, 15);
        if (stkInventoryData.getId() == -1) {
            colId.setText("Inv.#");
            colId.setBackgroundColor(Color.parseColor("#f0f0f0"));
            colId.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        }else{
            colId.setBackgroundColor(Color.parseColor("#f8f8f8"));
            colId.setText(String.valueOf(stkInventoryData.getId()));
            colId.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        // data columns article
        final TextView colArticle = new TextView(this.getContext());
        colArticle.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        colArticle.setGravity(Gravity.LEFT);
        colArticle.setPadding(5, 15, 0, 15);
        if (stkInventoryData.getId() == -1) {
            colArticle.setText("Inv.#");
            colArticle.setBackgroundColor(Color.parseColor("#f0f0f0"));
            colArticle.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        }else{
            colArticle.setBackgroundColor(Color.parseColor("#f8f8f8"));
            colArticle.setText(String.valueOf(stkInventoryData.getArticle()));
            colArticle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        // data columns Magasin
        final TextView colMagasin = new TextView(this.getContext());
        colMagasin.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        colMagasin.setGravity(Gravity.LEFT);
        colMagasin.setPadding(5, 15, 0, 15);
        if (stkInventoryData.getId() == -1) {
            colMagasin.setText("Inv.#");
            colMagasin.setBackgroundColor(Color.parseColor("#f0f0f0"));
            colMagasin.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        }else{
            colMagasin.setBackgroundColor(Color.parseColor("#f8f8f8"));
            colMagasin.setText(String.valueOf(stkInventoryData.getMagasin()));
            colMagasin.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        // data columns Quantity
        final TextView colQuantity = new TextView(this.getContext());
        colQuantity.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        colQuantity.setGravity(Gravity.LEFT);
        colQuantity.setPadding(5, 15, 0, 15);
        if (stkInventoryData.getId() == -1) {
            colQuantity.setText("Inv.#");
            colQuantity.setBackgroundColor(Color.parseColor("#f0f0f0"));
            colQuantity.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        }else{
            colQuantity.setBackgroundColor(Color.parseColor("#f8f8f8"));
            colQuantity.setText(String.valueOf(stkInventoryData.getQuantity()));
            colQuantity.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        // data columns button minus
        final Button colBtnMinus = new Button(this.getContext());
        colBtnAdd.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        colBtnAdd.setGravity(Gravity.LEFT);
        colBtnAdd.setPadding(5, 15, 0, 15);
        if (stkInventoryData.getId() == -1) {
            colBtnAdd.setText("-");
            colBtnAdd.setBackgroundColor(Color.parseColor("#f0f0f0"));
            colBtnAdd.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        }else{
            colBtnAdd.setBackgroundColor(Color.parseColor("#f8f8f8"));
            colBtnAdd.setText("-");
            colBtnAdd.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        //===============================
        // add table row
        //===============================
        final TableRow tr = new TableRow(this.getContext());
        tr.setId(stkInventoryData.getId());
        TableLayout.LayoutParams trParams = new
                TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin,
                bottomRowMargin);
        tr.setPadding(0,0,0,0);
        tr.setLayoutParams(trParams);

        tr.addView(colBtnAdd);
        tr.addView(colId);
        tr.addView(colArticle);
        tr.addView(colMagasin);
        tr.addView(colQuantity);
        tr.addView(colBtnMinus);


        /*
        if (stkInventoryData.getId() > -1) {
            tr.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    TableRow tr = (TableRow) v;
                }
            });
        }
        */

        return tr;
    }


    public  void add(TableRow tableRow){
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;

        TableLayout.LayoutParams trParams = new
                TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin,
                bottomRowMargin);

        tbInventory.addView(tableRow, trParams);
    }


    /**
     *
     * @param value
     */
    public void setScannedValue(String value){
        stkInventoryViewModel.setText(value);
        if(stkInventoryViewModel.isAllowedScan(value)) {
            StkInventoryData inventory = new StkInventoryData(value);
            stkInventoryViewModel.add(inventory);
            updateTable();
        }else{
            stkInventoryViewModel.setText(value + "/n is wrong");
        }
    }

}