package by.sivko.pinserver.models.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T,PK extends Serializable> {
    T findOne(PK id);
    T save(T t);
}
