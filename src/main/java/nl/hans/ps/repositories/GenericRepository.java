package nl.hans.ps.repositories;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericRepository<T> {

    private static final int DEFAULT_MAX_RESULTS = 20;

    protected abstract Class<T> getPersistentClass();

    protected abstract EntityManager getEntityManager();

    public T add(T entity) {

        getEntityManager().persist(entity);
        return entity;
    }

    public T saveOrUpdate(T entity) {

        T newEntity = getEntityManager().merge(entity);
        return newEntity;
    }

    public T update(T entity) {

        return saveOrUpdate(entity);
    }

    public void delete(T entity) {

        getEntityManager().remove(entity);
    }

    public T findById(Long id) {

        T entity = getEntityManager().find(getPersistentClass(), id);
        return entity;
    }

    public T findById(String id) {

        T entity = getEntityManager().find(getPersistentClass(), id);
        return entity;
    }

    public List<T> findAll(String orderField, Integer maxResults) {

        return findByParameters(new HashMap<String, Object>(), orderField, maxResults);
    }

    public List<T> findByParameters(Map<String, Object> queryParameters, String orderField) {

        return findByParameters(queryParameters, orderField, DEFAULT_MAX_RESULTS);
    }

    public List<T> findByParameters(Map<String, Object> queryParameters, String orderField, int maxResults) {

        String select = "select e from " + getPersistentClass().getSimpleName() + " e  ";
        select += queryParameters.size() > 0 ? " where " : "";

        String whereClause = "";
        for (String name : queryParameters.keySet()) {
            whereClause += whereClause.length() > 1 ? " and " : "";
            whereClause += " e." + name + " = :" + name;
        }

        select += whereClause;
        if (orderField != null) {
            select += " order by " + orderField;
        }

        Query query = getEntityManager().createQuery(select);
        for (String name : queryParameters.keySet()) {
            query.setParameter(name, queryParameters.get(name));
        }

        return query.setMaxResults(maxResults).getResultList();
    }

    public boolean exists(Map<String, Object> queryParameters) {

        List entities = findByParameters(queryParameters, null, 1);
        return entities.size() > 0 ? true : false;
    }
}
