package org.ism.ismoptimaintmobile.ui.stk.inventory;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StkInventoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<StkInventoryData>> inventories;

    public StkInventoryViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("...scan...");


        inventories = new MutableLiveData<>();
        inventories.setValue(new ArrayList<StkInventoryData>());
    }




    /**
     * Add = append scanned inventory to list
     * @param inventoryData
     */
    public void add(StkInventoryData inventoryData){
        if(inventories==null){
            inventories = new MutableLiveData<>();
            inventories.setValue(new ArrayList<StkInventoryData>());
        }
        inventoryData.setId(inventories.getValue().size()+1);

        // Check if inventory does not already exist


        // If inventory exist increment value


        // If inventory not exist add to inventories list
        inventories.getValue().add(inventoryData);

    }












    /**
     * Check if article is already defined in the list
     * @param article
     * @param magasin
     * @return index or -1 if error
     */
    public int findByArticle(String article, String magasin){
        if(inventories.getValue().isEmpty()){
            return -1;
        }

        for (StkInventoryData inventoryData : inventories.getValue()) {
            if(article.matches(inventoryData.getArticle()) & magasin.matches(magasin)){
                return inventoryData.getId()-1;
            }
        }
        return -1;
    }


    /**
     * Check if article is already defined in the list
     * @param stkInventoryData current inventory data defined by article and magasin
     * @return index or -1 if error
     */
    public int findByArticle(StkInventoryData stkInventoryData){
        String article = stkInventoryData.getArticle();
        String magasin = stkInventoryData.getMagasin();

        return findByArticle(article, magasin);
    }






    public void newScanned(String contents) {
        setText(contents);
    }



    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String contents) {
        mText.setValue(contents);
    }

    public LiveData<List<StkInventoryData>> getInventories() {
        return inventories;
    }

    public void setInventories(List<StkInventoryData> inventories) {
        this.inventories.setValue(inventories);
    }

    public static boolean isAllowedScan(String stock) {
        if(stock==null)
            return false;

        String stockPrepared = stock.replace("§", "/").replace("$", "/");

        if(!stockPrepared.contains("/"))
            return false;

        String data[] = stockPrepared.split("/");

        String art = data[0];
        String mag = data[1];

        // Control article
        if(art.isEmpty())
            return  false;

        if(!art.contains("-"))
            return false;

        data = art.split("-");

        if(data[0].length()!=3)
            return false;

        if(data[1].length()!=5)
            return false;


        // Control magasin
        if(mag.isEmpty())
            return false;

        if(!mag.contains("M"))
            return false;

        // if all control passed scan bare code is good
        return true;
    }
}