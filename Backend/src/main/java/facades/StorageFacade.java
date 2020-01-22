/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.StorageDTO;
import dto.StoragesDTO;
import entities.Storage;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class StorageFacade {

    private static StorageFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private StorageFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static StorageFacade getStorageFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StorageFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // Create a Storage
    public StorageDTO addStorage(StorageDTO s) {
        EntityManager em = getEntityManager();
        Storage storage = new Storage(s.getAddress(), s.getCapacity());
        try {
            em.getTransaction().begin();
            em.persist(storage);
            em.getTransaction();
        } finally {
            em.close();
        }
        return new StorageDTO(storage);
    }
    
    // Get all Storages
    public StoragesDTO getAllStorages() {
        EntityManager em = getEntityManager();
        try {
            List<Storage> list = em.createQuery("SELECT s FROM Storage s", Storage.class).getResultList();
            return new StoragesDTO(list);
        } finally {
            em.close();
        }
    }
    
    // No of Storages
    public long getStorageCount() {
        EntityManager em = getEntityManager();
        try {
            long storageCount = (long) em.createQuery("SELECT COUNT(s) FROM Storage s").getSingleResult();
            return storageCount;
        } finally {
            em.close();
        }
    }
    
    // Find a Bike on Storage
    public StorageDTO getStorage(Long storage_id) {
        EntityManager em = getEntityManager();
        Storage storageDTO = em.find(Storage.class, storage_id);
        return new StorageDTO(storageDTO);
    }    
    
}
