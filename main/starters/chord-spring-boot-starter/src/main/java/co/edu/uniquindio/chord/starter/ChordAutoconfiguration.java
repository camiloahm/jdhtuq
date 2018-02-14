package co.edu.uniquindio.chord.starter;

import co.edu.uniquindio.chord.node.ChordNodeFactory;
import co.edu.uniquindio.overlay.OverlayNodeFactory;
import co.edu.uniquindio.utils.communication.transfer.CommunicationManager;
import co.edu.uniquindio.utils.communication.transfer.network.CommunicationManagerUDP;
import co.edu.uniquindio.utils.communication.transfer.structure.CommunicationManagerStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Configuration
@ConditionalOnClass({OverlayNodeFactory.class, ChordNodeFactory.class})
@EnableConfigurationProperties(ChordProperties.class)
public class ChordAutoconfiguration {
    @Autowired
    private ChordProperties chordProperties;

    @Bean
    @ConditionalOnMissingBean
    public OverlayNodeFactory overlayNodeFactory(@Qualifier("communicationManagerChord") CommunicationManager communicationManager) {
        return new ChordNodeFactory(communicationManager, new HashSet<>());
    }

    @Bean("communicationManagerChord")
    @ConditionalOnMissingBean(name = "communicationManagerChord")
    @ConditionalOnProperty(prefix = "p2p.chord",
            name = "communication_type",
            havingValue = "DATA_STRUCTURE")
    public CommunicationManager communicationManagerStructure() {
        CommunicationManagerStructure communication = new CommunicationManagerStructure();

        Map<String, String> params = new HashMap<>();
        params.put("RESPONSE_TIME", "2000");

        communication
                .setCommunicationProperties(params);

        communication.init();

        return communication;
    }

    @Bean("communicationManagerChord")
    @ConditionalOnMissingBean(name = "communicationManagerChord")
    @ConditionalOnProperty(prefix = "p2p.chord",
            name = "communication_type",
            havingValue = "NETWORK")
    public CommunicationManager communicationManagerUDP() {
        return new CommunicationManagerUDP();
    }

}
