/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.RentalDTO;
import dto.RentalsDTO;
import entities.Rental;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class RentalFacade {

    private static RentalFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private RentalFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static RentalFacade getRentalFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RentalFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }    
    
    // Create a RentalDate
    public RentalDTO addRental(RentalDTO r) {
        EntityManager em = getEntityManager();
        Rental rental = new Rental(r.getDate());
        try {
            em.getTransaction().begin();
            em.persist(rental);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RentalDTO(rental);
    }
    
    // Edit Rental
    public RentalDTO editRental(RentalDTO r) {
        EntityManager em = getEntityManager();
        Rental rental = new Rental(r.getDate());
        rental.setId(r.getId());
        try {
            em.getTransaction().begin();
            em.merge(rental);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RentalDTO(rental);
    }
    
    // Delete a Rental
    public RentalDTO deleteRental(Long rental_id) {
        EntityManager em = getEntityManager();
        Rental rental = em.find(Rental.class, rental_id);
        try {
            em.getTransaction().begin();
            em.remove(rental);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RentalDTO(rental);
    }
    
    
    
    
    // Find a RentalDate
    public RentalDTO getRental(Long rental_id) {
        EntityManager em = getEntityManager();
        Rental rentalDTO = em.find(Rental.class, rental_id);
        return new RentalDTO(rentalDTO);
    }
    
    // Get all RentalDates
    public RentalsDTO getAllRentals() {
        EntityManager em = getEntityManager();
        try {
            List<Rental> list = em.createQuery("SELECT r FROM Rental r", Rental.class).getResultList();
            return new RentalsDTO(list);
        } finally {
            em.close();
        }
    }
    
    
    
    
    
    
}
