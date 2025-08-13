package org.practice.valueannotation.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("info")
public class itemsInfo {
    @Value("${idli.price}")
    public   float  idlyPrice;


    @Value("${dosa.price}")
    public float  dosaPrice;


    @Value("${poha.price}")
    public float  pohaPrice;



    @Override
    public String toString() {
        return "itemsInfo{" +
                "idlyPrice=" + idlyPrice +
                ", dosaPrice=" + dosaPrice +
                ", pohaPrice=" + pohaPrice +
                '}';
    }
}
