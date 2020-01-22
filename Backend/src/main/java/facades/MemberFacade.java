/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.MemberDTO;
import dto.MembersDTO;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henrik
 */
public class MemberFacade {
    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // Create a Member
    public MemberDTO addMember(MemberDTO m) {
        EntityManager em = getEntityManager();
        Member member = new Member(m.getName(), m.getSd(), m.getAcc());
        try {
            em.getTransaction().begin();
            em.persist(member);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MemberDTO(member);
    }
       
    // Edit Member
    public MemberDTO editMember(MemberDTO m) {
        EntityManager em = getEntityManager();
        Member member = new Member(m.getName(), m.getSd(), m.getAcc());
        member.setId(m.getId());
        try {
            em.getTransaction().begin();
            em.merge(member);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MemberDTO(member);
    }
    
    // Delete a Member
    public MemberDTO deleteMember(Long member_id) {
        EntityManager em = getEntityManager();
        Member member = em.find(Member.class, member_id);
        try {
            em.getTransaction().begin();
            em.remove(member);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MemberDTO(member);
    }
    
    // Find a Member
    public MemberDTO getMember(Long member_id) {
        EntityManager em = getEntityManager();
        Member memberDTO = em.find(Member.class, member_id);
        return new MemberDTO(memberDTO);
    }
    
    // Get all Members
    public MembersDTO getAllMembers() {
        EntityManager em = getEntityManager();
        try {
            List<Member> list = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
            return new MembersDTO(list);
        } finally {
            em.close();
        }
    }

    // No of Members
    public long getMemberCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long memberCount = (long) em.createQuery("SELECT COUNT(m) FROM Member m").getSingleResult();
            return memberCount;
        } finally {
            em.close();;
        }
    }
    
  
}
