package org.ism.ismoptimaintmobile.ui.stk.inventory;

import android.os.Bundle;
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

public class StkInventoryFragment extends Fragment implements View.OnClickListener{

    private View root;

    public static StkInventoryViewModel stkInventoryViewModel;
    public static StkInventory inventory;

    private TextView scanned;
    private Button scanBtn;
    public static TableLayout tbInventory;


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

        inventory = new StkInventory();

        return root;
    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode() {
        MainActivity main = (MainActivity) getParentFragment().getActivity();
        IntentIntegrator integrator = new IntentIntegrator(main);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning code");
        integrator.initiateScan();
    }


    public static void add(TableRow tableRow){
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


}