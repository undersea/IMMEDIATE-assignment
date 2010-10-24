package immediate.learning.support.gui;

import immediate.learning.support.dao.Dao;

public interface View<T> {
    public void setDao(Dao<T> dao);
}