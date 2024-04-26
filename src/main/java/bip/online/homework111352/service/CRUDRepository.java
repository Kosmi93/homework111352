package bip.online.homework111352.service;

public interface CRUDRepository<T,K> {
        T save(T data);
        void delete(K data);
        T update (T data);
        T findById(K id);

}
