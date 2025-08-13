package org.practice.bootproj2dependencyinjection.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@ImportResource(value = "classpath:properties.xml")
@Component("student")
public final class Student {

    //importing beanId from external files we may use alias tag

    private final CourseMaterialInterface material;
    @Autowired
    public Student(@Qualifier(value = "beanID")CourseMaterialInterface material) {
        /* this is error due to aliasTag because beanID from .props file
         is accessed only at runtime not at compileTime */
        this.material = material;
    }
    @Value("${examName}")
    String examName;
    public void Preparation(){
        System.out.println("Student Preparation Started "+examName);
        String courseContent = material.courseContent();
        double price = material.price();
        System.out.println("Preparation is going on"+ courseContent+" with Price ==> "+price);
        System.out.println("Preparation Completed for "+examName);
    }

}
