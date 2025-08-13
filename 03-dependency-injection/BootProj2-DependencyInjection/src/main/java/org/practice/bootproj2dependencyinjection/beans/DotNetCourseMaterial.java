package org.practice.bootproj2dependencyinjection.beans;

import org.springframework.stereotype.Component;

@Component("dotNet")
public final class DotNetCourseMaterial implements CourseMaterialInterface{
    public DotNetCourseMaterial() {
        System.out.println("DotNetCourseMaterial.0paramConstructor");
    }

    @Override
    public String courseContent() {
        System.out.println("DotNetCourseMaterial.courseContent");
        return "1.Oops, 2.Exception Handling, 3.Collections and etc...";
    }

    @Override
    public double price() {
        return 300;
    }
}
