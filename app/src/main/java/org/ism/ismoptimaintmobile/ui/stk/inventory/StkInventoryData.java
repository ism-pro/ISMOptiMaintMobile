package org.ism.ismoptimaintmobile.ui.stk.inventory;

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
