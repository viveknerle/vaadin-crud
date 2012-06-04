package net.mysoftworks.crud.utils;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityManagerUtils {

	private static EntityManagerFactory emf;
	private static Logger log = LoggerFactory.getLogger(EntityManagerUtils.class); 
	
	
//	public static EntityManager getEntityManager() {
//		return getEm(false);
//	}
	
	private static EntityManager getEm(boolean create) {
		if (emf == null || create) {
			Map map = new HashMap();
			if (create) {
				map.put("hibernate.hbm2ddl.auto", "create");
			}
			emf = Persistence.createEntityManagerFactory("antonio",map);
		}
		return emf.createEntityManager();
	}	
	
	private static Serializable generateKey(Class clazz,String keyValue) {
		if (keyValue == null || "".equals(keyValue)) {
			throw new IllegalArgumentException("Unable to generate primary key for class " + clazz + " using null value");
		}
		Method m = null;
		try {
			m = clazz.getMethod("getId", null);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("Please provide <getId> method for retrieving primary key of class " + clazz);
		}
		try {
			Constructor stringConstructor = m.getReturnType().getConstructor(String.class);
			return (Serializable) stringConstructor.newInstance(keyValue);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("Please provide a " + String.class + " constructor for primary key of class " + clazz);
		} catch (Exception e) {
			throw new IllegalArgumentException("Please provide a " + String.class + " constructor for primary key of class " + clazz);
		}
		
	}
	
	public static Object loadBean(EntityManager em,Class beanClass,String key) {
		if (em==null) em = getEm(false);
		Serializable keyVal = generateKey(beanClass,key);
		Object obj = em.find(beanClass, keyVal);
		return obj;
	}
	
	public static void saveBean(EntityManager em,Object bean) {
		if (em==null) em = getEm(false);
System.out.println("saveBean " + bean);		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		try {
			bean = em.merge(bean);
			tr.commit();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			tr.rollback();
		}
	}	
	
	public static <T> List<T> selectAll(EntityManager em,Class<T> cl,Collection except) {
		if (em==null) em = getEm(false);
		StringBuilder query = new StringBuilder("from ");
		query.append(cl.getName());
		if (except!=null && !except.isEmpty()) {
			query.append(" ").append(cl.getSimpleName());
			query.append(" where ");
			query.append(cl.getSimpleName());
			query.append(" not in (:except)");
		}
System.out.println("Query " + query.toString());		
		Query qr = em.createQuery(query.toString());
		if (except!=null && !except.isEmpty()) {
			qr.setParameter("except", except );
		}
			
		List<T> l = qr.getResultList();
		return l;
	}
	
	
}
