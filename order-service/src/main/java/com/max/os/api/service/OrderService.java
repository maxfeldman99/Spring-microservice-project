package com.max.os.api.service;

import com.max.os.api.common.Payment;
import com.max.os.api.common.TransactionRequest;
import com.max.os.api.common.TransactionResponse;
import com.max.os.api.entity.Order;
import com.max.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService   {

    @Autowired
    private OrderRepository repository;

    @Autowired
    @Lazy
    private RestTemplate template;

    // accessing  the yml file inside our spring server config at GIT
    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response="";
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        //rest call
        // PAYMENT-SERVICE is declared in the pom.xml
        Payment paymentResponse = template.postForObject(ENDPOINT_URL,payment,Payment.class);

        response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successfully and order placed":"there is a failure in payment api , order added to card";

        repository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}
