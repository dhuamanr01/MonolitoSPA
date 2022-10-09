package com.monolito365.utilities;

public class Customer {

    private int order;
    private String name;
    public Customer(int order, String name) {
        setOrder(order);
        setName(name);
    }
    public int getOrder() {return order;}
    public void setOrder(int order) {this.order = order;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String toString(){return "order=" + order + ",name=" + name;}

}
