package nl.hans.ps.repositories;

import nl.hans.ps.models.Melding;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@Stateless
@LocalBean
public class MeldingRepository extends GenericRepository<Melding> {

    @PersistenceContext
    EntityManager em;

    @Override
    protected Class<Melding> getPersistentClass() {

        return Melding.class;
    }

    @Override
    protected EntityManager getEntityManager() {

        return em;
    }

    public boolean exits(Melding melding) {

        Map<String, Object> parameters = new HashMap();
        parameters.put("meldingId", melding.getMeldingId());
        return exists(parameters);
    }
}
