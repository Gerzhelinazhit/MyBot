package bot.dao;

import Hibernate.ConnectionDBEntity;
import entity.MainTableEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao <PK extends Serializable, T>{
    private final Class<T> persistentClass;

    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

//    private EntityManager em = ConnectionDBEntity.getEntityManagerFactory().createEntityManager();

    protected EntityManager getEntityManager() {
        return em;
    }

    public T getByKey(PK key) { return getEntityManager().find(persistentClass, key); }

    public void persist(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
    }

    public void update(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();
    }

//    public List<MainTableEntity> getAll(){
//        List list ;
//        getEntityManager().getTransaction().begin();
//        list =getEntityManager().createQuery("from NationalityEntity laba3").getResultList();
//        getEntityManager().getTransaction().commit();
//        return list;
//    }

    public void delete(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));
        getEntityManager().getTransaction().commit();
    }

    public CriteriaBuilder getCriteriaBuilder(){
        return getEntityManager().getCriteriaBuilder();
    }
}