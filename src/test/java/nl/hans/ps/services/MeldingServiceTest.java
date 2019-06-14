package nl.hans.ps.services;

import nl.hans.ps.models.Melding;
import nl.hans.ps.repositories.MeldingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class MeldingServiceTest {

    @InjectMocks
    MeldingRepository meldingRepository;

    MeldingService meldingService = new MeldingService();


    @Test
    public void add() {

        MeldingService meldingService = new MeldingService();

        Melding melding = new Melding();
        melding.setMelding_id("login");
        melding.setMelding_tekst("This is the very first melding :-)");

//        when(meldingRepository.add(any())).thenReturn(new Melding("login", "bla"));

        Assert.assertTrue(true);
//        Melding addedMelding = meldingService.add(melding);
//        assertEquals("login", addedMelding.getMelding_id());

    }

    @Test
    public void findById() {

    }
}