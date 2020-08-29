package org.ism.ismoptimaintmobile.ui.stk.inventory;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.ism.ismoptimaintmobile.MainActivity;
import org.ism.ismoptimaintmobile.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StkInventory {

    List<StkInventoryData> inventories = new ArrayList<>();



    /**
     * Add = append scanned inventory to list
     * @param inventoryData
     */
    public void add(StkInventoryData inventoryData){
        inventories.add(inventoryData);
    }




    public List<StkInventoryData> getInventories() {
        return inventories;
    }

    public void setInventories(List<StkInventoryData> inventories) {
        this.inventories = inventories;
    }

    public TableRow getAsTableRow(int index, Context context){
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize = 0, smallTextSize =0, mediumTextSize = 0;
        textSize = 16;
        smallTextSize = 8;
        mediumTextSize = 12;

        StkInventoryData inventory = inventories.get(index);

        // data columns Id
        final TextView colId = new TextView(context);
        colId.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        colId.setGravity(Gravity.LEFT);
        colId.setPadding(5, 15, 0, 15);
        if (index == -1) {
            colId.setText("Inv.#");
            colId.setBackgroundColor(Color.parseColor("#f0f0f0"));
            colId.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        }
        else {
            colId.setBackgroundColor(Color.parseColor("#f8f8f8"));
            colId.setText(String.valueOf(inventory.getId()));
            colId.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }





        // add table row
        final TableRow tr = new TableRow(context);
        tr.setId(index + 1);
        TableLayout.LayoutParams trParams = new
                TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin,
                bottomRowMargin);
        tr.setPadding(0,0,0,0);
        tr.setLayoutParams(trParams);
        tr.addView(colId);
       // tr.addView(tv2);
       // tr.addView(layCustomer);
       // tr.addView(layAmounts);
        if (index > -1) {
            tr.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    TableRow tr = (TableRow) v;
                }
            });
        }

        return tr;
    }

    /**
     * Trouve le contenu dans la base de donnée et l'ajoute à la liste
     * @param stkInventory must be in optimaint code ref like ###-#####/### where it will be
     *                     separated by "/" with first part as code reference of article and second
     *                     part as magasin reference
     */
    public void append(String stkInventory) {
        stkInventory.replace("§","/").replace("$", "/");
        if(!stkInventory.contains("/"))
            return;

        String data[] = stkInventory.split("/");
        StkInventoryData inventory = new StkInventoryData(data[0], data[1]);
        int isInvExist = findByArticle(data[0], data[1]);
        if(isInvExist!=-1){

        }else{
            // Inventory does not exist
            inventory.setId(inventories.size()+1);
            inventory.setDateScanned(new Date());
            inventories.add(inventory);
        }



    }

    /**
     * Check if article is already defined in the list
     * @param article
     * @param magasin
     * @return index or -1 if error
     */
    public int findByArticle(String article, String magasin){
        if(inventories.isEmpty()){
            return -1;
        }

        for (StkInventoryData inventoryData : inventories) {
            if(article.matches(inventoryData.getArticle()) & magasin.matches(magasin)){
                return inventoryData.getId()-1;
            }
        }
        return -1;
    }
}
