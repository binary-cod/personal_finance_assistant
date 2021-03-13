package services;

import java.util.ArrayList;

public interface Service<T> {
    public Boolean insert(T value);
    public ArrayList<T> getData();
}
