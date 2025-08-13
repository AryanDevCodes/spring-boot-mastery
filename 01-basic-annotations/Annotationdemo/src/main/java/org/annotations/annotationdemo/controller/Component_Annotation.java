package org.annotations.annotationdemo.controller;
import org.springframework.stereotype.Component;

@Component
public class Component_Annotation {
    public String showAnnotation(){
        return "Component Annotation";
    }
}
