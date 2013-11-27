package com.smartapp.data.repository;

import com.smartapp.data.domain.T_User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Repository of T_User
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Repository
public class UserRepository extends GenericRepositoryImpl<T_User,Long>{

	public T_User loadByEmail(String email,String identifier) {
        try{
            Query query = entityManager
                            .createQuery("from T_User u where u.email=:email and u.account.identifier=:identifier")
                            .setParameter("email", email)
                            .setParameter("identifier", identifier);
            return (T_User) query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
	}

    public boolean checkIfUserExisted(String email) {
        try{
            Query query = entityManager
                            .createQuery("from T_User u where u.email=:email")
                            .setParameter("email", email);
            return null != query.getSingleResult();
        }
        catch(NoResultException e){
            return false;
        }
    }
}
