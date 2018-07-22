package co.edu.uniquindio.dhash.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "p2p.dhash")
@Data
public class DHashProperties {
    private int replicationAmount = 1;
    private String resourceDirectory = "dhash/";
}
