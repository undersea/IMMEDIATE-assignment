package immediate.learning.support.service;


public class ServiceImpl<T> implements Service<T> {
    public void update(T entity){ System.out.println("update: " + entity); }
    public void save(T entity){ System.out.println("update: " + entity); }
}