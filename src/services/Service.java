package services;

import java.util.List;

public interface Service<T> {
    public Boolean insert(T value);
    public List<T> getData();
}
