package org.practice.beans;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

// Target Class

@Component("wmg")
@Resource(mappedName = "application.properties")
public class WishMessageGenerator {
    @Value(value =" ${app.user}")
    private String user;

    private final LocalDateTime date;
    @Autowired
    public WishMessageGenerator(LocalDateTime date) {
        System.out.println("WishMessageGenerator::0)paramConstructor");
        this.date = date;
    }
    public String generateWishMessage() {
        int hour = date.getHour();
        if (hour<12) return "GoodMorning"+user;
        else if (hour<16) {
            return "GoodAfternoon"+user;
        } else if (hour<18) {
            return "GoodEvening"+user;
        } else return "GoodNight"+user;
    }
}
