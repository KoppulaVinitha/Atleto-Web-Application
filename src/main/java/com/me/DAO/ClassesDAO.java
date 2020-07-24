/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.me.DAO;

/**
 *
 * @author koppu
 */
import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.ClassesException;
import com.me.exception.UserException;
import com.me.pojo.Gym;
import com.me.pojo.ClasList;
import com.me.pojo.User;

public class ClassesDAO extends DAO{

public ClassesDAO(){
	
}
	
	
public ClasList register(ClasList j) throws ClassesException{
		
		try {
            begin();
            ClasList clas = new ClasList(j.getClasName(),j.getClasDesc());
            clas.setGym(j.getGym());
            clas.setPostedName(j.getPostedName());
            clas.setPostedID(j.getPostedID());
            getSession().save(clas);
            commit();
            return clas;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new ClassesException("Exception while creating gym: " + e.getMessage());
        }
	}

public List<ClasList> list() throws ClassesException {
    try {
        begin();
        Query q = getSession().createQuery("from ClasList");
        
        List<ClasList> list = q.list();
        commit();
        return list;
    } catch (HibernateException e) {
        rollback();
        throw new ClassesException("Could not list the Classes", e);
    }
}

public List<ClasList> listGym(Gym gym) throws ClassesException {
    try {
        begin();
        Query q = getSession().createQuery("from ClasList where gym= :comp");
        q.setInteger("comp", gym.getGymId());
        List<ClasList> list = q.list();
        commit();
        return list;
    } catch (HibernateException e) {
        rollback();
        throw new ClassesException("Could not list the Classes", e);
    }
}


public List<ClasList> listClasname(String name) throws ClassesException {
    try {
        begin();
        Query q = getSession().createQuery("from ClasList where lower(clasName) LIKE lower(:clasname)");
        q.setString("clasname", "%"+name+"%");
        List<ClasList> list = q.list();
        commit();
        return list;
    } catch (HibernateException e) {
        rollback();
        throw new ClassesException("Could not list the Classes with clas name", e);
    }
}
//entityManager.createQuery("select at from AttendeesVO at where lower(at.user.firstName) LIKE lower(:searchKeyword)",AttendeesVO.class);
public ClasList getClas(int id) throws ClassesException{
	try{
		begin();
		Query q = getSession().createQuery("from ClasList where clasID= :id");
		q.setInteger("id", id);
		ClasList clas = (ClasList)q.uniqueResult();
		commit();
		return clas;
	} catch(HibernateException e) {
		rollback();
		throw new ClassesException("could not find the clas",e);
	}
}


public void deleteClas(ClasList clas) throws ClassesException {
	try {
//		Serializable id = new Long(clas.getClasID());
//		Object persistentInstance = getSession().load(ClasList.class, id);
		begin();
//		if (persistentInstance != null) {
//		    getSession().delete(persistentInstance);
//		}
//		etSession().delete(clas);
//		commit();
		Query q = getSession().createQuery("delete from ClasList where clasID= :id");
		q.setInteger("id", clas.getClasID());
		ClasList clas1 = (ClasList)q.uniqueResult();
		commit();
	} catch (HibernateException e) {
		rollback();
		throw new ClassesException("cannot delete clas", e.getCause());
	}
 }

public void update(ClasList clas) throws ClassesException {
    try {
        begin();
        getSession().update(clas);
        commit();
        System.out.println("Updated the clas");
    } catch (HibernateException e) {
        rollback();
        throw new ClassesException("Could not update the clas", e);
    }
}

//public ClasList findClas(int id, int) throws ClassesException{
//	try{
//		begin();
//		Query q = getSession().createQuery("from ClasList where clasID= :id");
//		q.setInteger("id", id);
//		ClasList clas = (ClasList)q.uniqueResult();
//		commit();
//		return clas;
//	} catch(HibernateException e) {
//		rollback();
//		throw new ClassesException("could not find the clas",e);
//	}
//}


}
