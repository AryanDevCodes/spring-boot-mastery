package org.practice.bootproject6.beans;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Component("teamDetails")
@ConfigurationProperties("team.info")
public class PropertiesTeams {

    private String name;
    private long id;
    private String[] memberName;
    private List<String> teamMembers;
    private Set<Long> phoneNumbers;
    private Map<String,String> idDetails;
    private Company compDetails;


}
