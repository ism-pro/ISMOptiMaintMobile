package org.ism.ismoptimaintmobile.ui.stk.inventory;

import androidx.annotation.NonNull;

import net.sourceforge.jtds.jdbc.DateTime;

import java.util.Date;

public class StkInventoryData {
    private int id;
    private String article;
    private String magasin;
    private double quantity;
    private Date dateScanned;

    public StkInventoryData(String article, String magasin) {
        this.id = 0;
        this.article = article;
        this.magasin = magasin;
        this.quantity = 1;
        this.dateScanned = new Date();
    }

    /**
     * This constructor is base on setup article stock like ###-#####/M## with first part article
     * and second part magasin
     * @param stock like ###-#####/M## with first part article and second part magasin
     */
    public StkInventoryData(@NonNull String stock){
        if(stock!=null){
            String data[] = stock.replace("§", "/").replace("$", "/").split("/");
            this.id = 0;
            this.article = data[0];
            this.magasin = data[1];
            this.quantity = 1;
            this.dateScanned = new Date();
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getDateScanned() {
        return dateScanned;
    }

    public void setDateScanned(Date dateScanned) {
        this.dateScanned = dateScanned;
    }
}
