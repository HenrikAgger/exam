/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.BikeDTO;
import dto.BikesDTO;
import entities.Bike;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class BikeFacade {

    private static BikeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private BikeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BikeFacade getBikeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BikeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Create a Bike
    public BikeDTO addBike(BikeDTO b) {
        EntityManager em = getEntityManager();
        Bike bike = new Bike(b.getMake(), b.getSize(), b.getGender(), b.getGears(), b.getDayPrice());
        try {
            em.getTransaction().begin();
            em.persist(bike);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BikeDTO(bike);
    }

    // Edit Bike
    public BikeDTO editBike(BikeDTO b) {
        EntityManager em = getEntityManager();
        Bike bike = new Bike(b.getMake(), b.getSize(), b.getGender(), b.getGears(), b.getDayPrice());
        bike.setId(b.getId());
        try {
            em.getTransaction().begin();
            em.merge(bike);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BikeDTO(bike);
    }
    
    // Delete a Bike
    public BikeDTO deleteBike(Long bike_id) {
        EntityManager em = getEntityManager();
        Bike bike = em.find(Bike.class, bike_id);
        try {
            em.getTransaction().begin();
            em.remove(bike);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BikeDTO(bike);
    }
    

    // Find a Bike
    public BikeDTO getBike(Long bike_id) {
        EntityManager em = getEntityManager();
        Bike bikeDTO = em.find(Bike.class, bike_id);
        return new BikeDTO(bikeDTO);
    }

    // Get all Bikes
    public BikesDTO getAllBikes() {
        EntityManager em = getEntityManager();
        try {
            List<Bike> list = em.createQuery("SELECT b FROM Bike b", Bike.class).getResultList();
            return new BikesDTO(list);
        } finally {
            em.close();
        }
    }

}
