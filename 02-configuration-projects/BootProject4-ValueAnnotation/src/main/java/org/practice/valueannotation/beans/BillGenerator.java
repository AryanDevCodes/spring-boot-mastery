package org.practice.valueannotation.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BillGenerator {
    private final itemsInfo info;
    public BillGenerator(itemsInfo info) {
        System.out.println("BillGenerator.BillGeneratorConstructor");
        this.info = info;
    }

    @Value("#{info.dosaPrice + info.idlyPrice+info.pohaPrice }")
    private  float  total;


    @Value("paradise")
    private String HotelName;


    @Override
    public String toString() {
        return "BillGenerator{" +
                "total=" + total +
                ", HotelName='" + HotelName + '\'' +
                ", items =" + info +
                '}';
    }
}
