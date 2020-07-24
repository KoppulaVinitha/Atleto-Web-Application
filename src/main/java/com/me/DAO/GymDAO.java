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

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.GymException;
import com.me.exception.UserException;
import com.me.pojo.Gym;
import com.me.pojo.User;

public class GymDAO extends DAO{

	public GymDAO(){
		
	}
	
	public List<Gym> list() throws GymException {
        try {
            begin();
            Query q = getSession().createQuery("from Gym");
            List<Gym> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new GymException("Could not list the gyms", e);
        }
    }
	
	
	public Gym register(Gym c) throws GymException{
			
		try {
            begin();
            Gym comp = new Gym(c.getGymName(),c.getGymAddress(),c.getGymDescription());
            getSession().save(comp);
            commit();
            return comp;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new GymException("Exception while creating gym: " + e.getMessage());
        }
	}

	
	public Gym get(String name) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Gym where gymName= :gymName");
			q.setString("gymName", name);		
			Gym comp = (Gym) q.uniqueResult();
			commit();
			return comp;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get gym " + name, e);
		}
	}

	
	public Gym getById(int id) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Gym where gymId= :compId");
			q.setInteger("compId", id);		
			Gym comp = (Gym) q.uniqueResult();
			commit();
			return comp;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get gym " + id, e);
		}
	}
	
	public void delete(Gym comp) throws GymException{
		try {
			begin();
			getSession().delete(comp);
//			return comp;
		} catch (HibernateException e) {
			rollback();
			throw new GymException("Could not delete gym " + comp.getGymId(), e);
		}
	}
	}

