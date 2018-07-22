package co.edu.uniquindio.chord.node;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StableRingTest {
    @Mock
    private ChordNode node;
    @InjectMocks
    @Spy
    private StableRing stableRing;

    @Test
    public void run_notify(){
        doNothing().when(stableRing).notifyObservers(node);

        stableRing.run();

        verify(stableRing).notifyObservers(node);
    }

}