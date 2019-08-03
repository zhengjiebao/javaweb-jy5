package com.itdr.pojo;

public class Products {
    private Integer id;
    private Integer categoryld;
    private String pname;
    private String subtitle;
    private Integer status;
    private double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryld() {
        return categoryld;
    }

    public void setCategoryld(Integer categoryld) {
        this.categoryld = categoryld;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", categoryld=" + categoryld +
                ", pname='" + pname + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", status=" + status +
                ", price=" + price +
                '}';
    }
}
