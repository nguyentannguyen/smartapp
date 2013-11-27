package com.smartapp.data.repository;

import com.smartapp.data.domain.T_Account;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Repository of T_Account
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Repository
public class AccountRepository extends GenericRepositoryImpl<T_Account,Long>{

	public T_Account loadByIdentifier(String identifier) {
        try {
            Query query = entityManager.createQuery("from T_Account a where a.identifier=:identifier").setParameter("identifier", identifier);
            return (T_Account) query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
	}

    public T_Account loadByUuid(String uuid) {
        try {
            Query query = entityManager.createQuery("from T_Account a where a.uuid=:uuid").setParameter("uuid", uuid);
            return (T_Account) query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }

    public boolean checkIfAccountExisted(String identifier) {
        try{
            Query query = entityManager.createQuery("from T_Account a where a.identifier=:identifier").setParameter("identifier", identifier);
            return null != query.getSingleResult();
        }
        catch(NoResultException e){
            return false;
        }
    }
}
