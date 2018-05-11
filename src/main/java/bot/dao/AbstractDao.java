package bot.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Repository
@Transactional
public abstract class AbstractDao<PK extends Serializable, T> {
    private final Class<T> persistentClass;


    @SuppressWarnings("uncheked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @PersistenceContext
    private EntityManager em;


    protected EntityManager getEntityManager() {
        return em;
    }

    public T getByKey(PK key) {

        return getEntityManager().find(persistentClass, key);
    }


    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    public void update(T entity) {
        getEntityManager().merge(entity);
    }
    //    public List<MainTableEntity> getAll(){
//        List list ;
//        getEntityManager().getTransaction().begin();
//        list =getEntityManager().createQuery("from NationalityEntity laba3").getResultList();
//        getEntityManager().getTransaction().commit();
//        return list;
//    }
    public void flush() {
        getEntityManager().flush();
    }


    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));

    }

    public CriteriaBuilder getCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

}