package com.example.restaurant;

public class Order {
    public String foodName;
    public String customerName;

    Order(String foodName,String customerName)
    {
        this.customerName = customerName;
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getCustomerName() {
        return customerName;
    }
}
