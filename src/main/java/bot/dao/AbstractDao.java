package bot.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractDao <PK extends Serializable, T> {
    private final Class<T> persistentClass;

    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    private SessionFactory sessionFactory;
//    private EntityManager em = ConnectionDBEntity.getEntityManagerFactory().createEntityManager();

//   protected EntityManager getEntityManager() {
//        return em;
//    }

    public T getByKey(PK key) {
        return getSession().find(persistentClass, key);
    }

    public void persist(T entity) {
        getSession().getTransaction().begin();
        getSession().persist(entity);
        getSession().getTransaction().commit();
    }

    public void update(T entity) {
        getSession().getTransaction().begin();
        getSession().merge(entity);
        getSession().getTransaction().commit();
    }

//    public List<MainTableEntity> getAll(){
//        List list ;
//        getEntityManager().getTransaction().begin();
//        list =getEntityManager().createQuery("from NationalityEntity laba3").getResultList();
//        getEntityManager().getTransaction().commit();
//        return list;
//    }

    public void delete(T entity) {
        getSession().getTransaction().begin();
        getSession().remove(getSession().contains(entity) ? entity : getSession().merge(entity));
        getSession().getTransaction().commit();
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return getSession().getCriteriaBuilder();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}