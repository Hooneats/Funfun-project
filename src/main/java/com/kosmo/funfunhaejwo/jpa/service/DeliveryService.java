package com.kosmo.funfunhaejwo.jpa.service;

import org.springframework.http.ResponseEntity;

public interface DeliveryService {

    ResponseEntity<?> insertDelivery(long funding_id, String delivery_num);

}
