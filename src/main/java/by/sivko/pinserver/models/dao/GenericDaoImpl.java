package by.sivko.pinserver.models.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;


public class GenericDaoImpl<T,PK extends Serializable> implements GenericDao<T,PK> {

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public T findOne(PK id) {
        return this.em.find(entityClass, id);
    }


    @Override
    @Transactional
    public T save(T t) {
        this.em.persist(t);
        return t;
    }

}
