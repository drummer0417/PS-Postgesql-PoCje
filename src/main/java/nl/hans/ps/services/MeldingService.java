package nl.hans.ps.services;

import nl.hans.ps.models.Melding;
import nl.hans.ps.repositories.MeldingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class MeldingService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    MeldingRepository meldingenRepository;

    public Melding add(Melding melding){

        if (!meldingenRepository.alreadyExists(melding)) {
            return meldingenRepository.add(melding);
        } else {
            // Todo: Replace with line below with something which makes more sence
            throw new RuntimeException("Melding "+ melding.getMelding_id() + " already exists");
        }
    }

    public Melding findById(String meldingId){

        // stukje logic

        Melding melding = meldingenRepository.findById(meldingId);

        // nog meer stukjes logic

        return melding;
    }

    public List<Melding> findAll(Integer maxResults) {

        return meldingenRepository.findAll("melding_id", maxResults);
    }

    public Melding update(String id, Melding newMelding) {

        Melding melding = findById(newMelding.getMelding_id());
        if (melding == null) {
            throw new RuntimeException("Melding bestaat niet: " + id);
        }
        logger.info("before update: " + melding.toString());

        if (newMelding.getMelding_tekst() != null) {
            melding.setMelding_tekst(newMelding.getMelding_tekst());
        }
        logger.info("after update: " + melding.toString());

        return melding;
    }

    public void delete(String id) {

        Melding melding = findById(id);

        meldingenRepository.delete(melding);
        logger.debug("melding verwijderd: " + melding);
    }
}
