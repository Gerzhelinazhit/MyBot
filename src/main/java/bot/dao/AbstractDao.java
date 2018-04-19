package bot.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractDao <PK extends Serializable, T> {
    private final Class<T> persistentClass;
    private SessionFactory sessionFactory;
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }


//    private EntityManager em = ConnectionDBEntity.getEntityManagerFactory().createEntityManager();

//   protected EntityManager getEntityManager() {
//        return em;
//    }
    @Transactional
    public T getByKey(PK key) {
        return getSession().find(persistentClass, key);
    }

@Transactional
    public void persist(T entity) {
getSession().getTransaction().begin();
        getSession().persist(entity);
getSession().getTransaction().commit();
    }
    @Transactional
    public void update(T entity) {

        getSession().merge(entity);

    }

//    public List<MainTableEntity> getAll(){
//        List list ;
//        getEntityManager().getTransaction().begin();
//        list =getEntityManager().createQuery("from NationalityEntity laba3").getResultList();
//        getEntityManager().getTransaction().commit();
//        return list;
//    }
    @Transactional
    public void delete(T entity) {
        getSession().remove(getSession().contains(entity) ? entity : getSession().merge(entity));

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