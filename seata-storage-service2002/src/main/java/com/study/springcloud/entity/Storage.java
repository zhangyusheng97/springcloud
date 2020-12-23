package com.study.springcloud.entity;

public class Storage {
    private long id;
    private long productId;
    private int total;
    private int used;
    private int residue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getResidue() {
        return residue;
    }

    public void setResidue(int residue) {
        this.residue = residue;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", productId=" + productId +
                ", total=" + total +
                ", used=" + used +
                ", residue=" + residue +
                '}';
    }
}
