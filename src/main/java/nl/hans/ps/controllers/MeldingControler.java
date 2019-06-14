package nl.hans.ps.controllers;

import nl.hans.ps.models.Melding;
import nl.hans.ps.services.MeldingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/melding")
public class MeldingControler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Context
    UriInfo uriInfo;

    @EJB
    MeldingService meldingService;

    public MeldingControler() {

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMelding(Melding melding) {

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();

        try {
            melding = meldingService.add(melding);
            URI uri = uriBuilder.path(String.valueOf(melding.getMelding_id())).build();
            return Response.created(uri).build();
        } catch (RuntimeException e) {
            String message = e.getMessage();
            logger.debug(message);
            return null;
        }
    }

    /**
     * Rest service voor ophalen van een melding met gegeven id
     *
     * @param id
     * @return een melding in JSON formaat
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Melding findByID(@PathParam("id") String id) {

        logger.debug("from rest controller.findByID... id: " + id);
        Melding melding = meldingService.findById(id);
        logger.debug("from rest controller.findByID... melding: " + melding);

        return melding;
    }

    /**
     * Rest service voor ophalen alle meldingen
     *
     * @return lijst van meldingen in JSON formaat
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Melding> findAllMeldingen() {

        List<Melding> meldingen = meldingService.findAll(100);
        logger.debug("# meldingen: " + meldingen.size());
        StringBuilder sb = new StringBuilder();

        for (Melding melding : meldingen) {
            logger.debug("from rest controller: " + melding);
            sb.append("\n");
        }
        return meldingen;
    }

    @DELETE
    @Path("{id}")
    public Response deleteMelding(@PathParam("id") String id) {

        try {
            meldingService.delete(id);
            return Response.ok().build();
        } catch (EJBException e) {
            String message = e.getCausedByException() != null ? e.getCausedByException().getMessage() : e.getMessage();
            logger.debug(message);
            return Response.status(404).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateMelding(@PathParam("id") String id, Melding newMelding) {

        logger.debug("id: " + id.toString());
        logger.debug("update: " + newMelding.toString());

        newMelding.setMelding_id(id);
        try {
            Melding updatedMelding = meldingService.update(id, newMelding);
            return Response.ok().entity(updatedMelding).build();
        } catch (EJBException e) {
            String message = e.getCausedByException() != null ? e.getCausedByException().getMessage() : e.getMessage();
            logger.debug(message);
            return Response.status(404).build();
        }
    }
}
