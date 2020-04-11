package com.max.os.api.common;

import com.max.os.api.entity.Order;


public class TransactionRequest {

    private Order order;
    private Payment payment;

    public TransactionRequest(Order order, Payment payment) {
        this.order = order;
        this.payment = payment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }


}
