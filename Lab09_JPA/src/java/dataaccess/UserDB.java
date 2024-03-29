package dataaccess;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.*;


/**
 *
 * @author Alex Tompkins - 821984
 */
public class UserDB {

    public List<User> getAll() throws Exception {
EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try{
            List<User> userList = em.createNamedQuery("User.findAll",User.class).getResultList();
            return userList;
            
        }finally{
            em.close();
        }
    }

    public User get(String email) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            User user =em.find(User.class, email);
            return user;
        }finally{
            em.close();
        }
    }

    public void insert(User user) throws Exception {
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans = em.getTransaction();
    
    try{
        //Role role = user.getRole();
        //role.getUserList().add(user);
        trans.begin();
        em.persist(user);   
        em.merge(user);
        //em.merge(role);
        trans.commit();
    }catch(Exception e){
        trans.rollback();
    }finally{
        em.close();
    }
    
    
    }

    public void update(User user) throws Exception {
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans = em.getTransaction();
    
        try{
    trans.begin();
    em.merge(user);
    trans.commit();
    }catch(Exception e){
        trans.rollback();
    }finally{
        em.close();
    }
    }

    public void delete(User user) throws Exception {
            EntityManager em = DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans = em.getTransaction();
    
        try{
//            Role role = user.getRole();
//            role.getUserList().remove(user);
            trans.begin();
            em.remove(em.merge(user));
//            em.merge(user);
            trans.commit();
            
    }catch(Exception e){
        trans.rollback();
    }finally{
        em.close();
    }
    }
}
