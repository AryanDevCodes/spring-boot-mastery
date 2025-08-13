package org.practice.bootproj2dependencyinjection.beans;

import org.springframework.stereotype.Component;

@Component("UI")
public final class UIcourseMaterial implements CourseMaterialInterface {
    public UIcourseMaterial() {
        System.out.println("UIcourseMaterial.0paramConstructor");
    }

    @Override
    public String courseContent() {
        System.out.println("UIcourseMaterial.courseContent");
        return "1.HTML, 2.CSS, 3.JAVASCRIPT, and etc...";
    }

    @Override
    public double price() {
        return 500;
    }
}
