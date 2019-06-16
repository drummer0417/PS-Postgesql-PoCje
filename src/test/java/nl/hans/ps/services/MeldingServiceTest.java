package nl.hans.ps.services;

import nl.hans.ps.models.Melding;
import nl.hans.ps.repositories.MeldingRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class MeldingServiceTest {

    public static final Melding TEST_MELDING = new Melding("login", "Goodmorning Odijk!!");

    @Mock
    MeldingRepository meldingRepository;

    @InjectMocks
    MeldingService meldingService;

    @Before
    public void initTestCase() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void add() {

        // a melding to add
        Melding melding = new Melding();
        melding.setMeldingId("login");
        melding.setMeldingTekst("Goodmorning Odijk!!");

        // melding is added
        when(meldingRepository.add(melding)).thenReturn(TEST_MELDING);
        Melding addedMelding = meldingService.add(melding);

        // then added melding should be equal to TEST_MELDING
        assertEquals(TEST_MELDING, addedMelding);
        assertEquals("login", addedMelding.getMeldingId());
    }

    @Test
    public void findById() {

        // given a meldingService

        // when find melding met id "login"
        when(meldingRepository.findById("login")).thenReturn(TEST_MELDING);
        Melding foundMelding = meldingService.findById("login");

        // then found melding tekst should be equal to TEST_MELDING.getMeldingTekst
        assertEquals(TEST_MELDING.getMeldingTekst(), foundMelding.getMeldingTekst());
    }
}