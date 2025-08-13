package org.practice.bootproj2dependencyinjection.beans;

import org.springframework.stereotype.Component;

@Component("java")
public final  class JavaCourseMaterial implements CourseMaterialInterface{

    public JavaCourseMaterial(){
        System.out.println("JavaCourseMaterial.0paramConstructor");
    }
    @Override
    public String courseContent() {
        System.out.println("JavaCourseMaterial.courseContent");
        return "1.Oops, 2.Exception Handling, 3.Collections and etc...";
    }

    @Override
    public double price() {
        return 400;
    }
}
