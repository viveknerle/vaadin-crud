package net.mysoftworks.crud.impl;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.jsp.JspWriter;

import net.mysoftworks.crud.interfaces.ContainerProvider;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class EntityManagerProvider<ET> implements ContainerProvider<ET> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory emf;
	private static Logger log = LoggerFactory.getLogger(EntityManagerProvider.class); 
	

	public static void main(String[] args) {
		System.out.println(StringUtils.leftPad("", 20, "123"));
	}
	
	private EntityManager getEm(boolean create) {
		if (emf == null || create) {
			Map<String,String> map = new HashMap<String,String>();
			if (create) {
				map.put("hibernate.hbm2ddl.auto", "create");
			}
			
			emf = Persistence.createEntityManagerFactory("localJndiMysql",map);
//			emf = Persistence.createEntityManagerFactory("remoteJdbcOracleVMXP",map);
		}
		return emf.createEntityManager();
	}	
	
	private Object getPrimaryKey(Object entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Unable to read primary key for null instance");
		}
		Method m = null;
		try {
System.out.println("\n\nentity.getClass():" + entity.getClass().getName());			
			m = entity.getClass().getMethod("getId", null);
System.out.println("\n\nmethod:" + m);			
			return m.invoke(entity, (Object[])null);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("Please provide <getId> method for retrieving primary key of " + entity.getClass());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static <ET> Serializable generateKey(Class<ET> clazz,String keyValue) {
		if (keyValue == null || "".equals(keyValue)) {
			throw new IllegalArgumentException("Unable to generate primary key for class " + clazz + " using null value");
		}
		Method m = null;
		try {
			m = clazz.getMethod("getId", (Class<?>)null);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("Please provide <getId> method for retrieving primary key of class " + clazz);
		}
		try {
System.out.println("\n\n============keyValue:" + keyValue);			
			Class<ET> cl = (Class<ET>) m.getReturnType();
			Constructor<ET> stringConstructor = (Constructor<ET>) cl.getConstructor(String.class);
System.out.println("\n\n============stringConstructor:" + stringConstructor);			
			return (Serializable) stringConstructor.newInstance(keyValue);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("Please provide a " + String.class + " constructor for primary key of class " + clazz);
		} catch (Exception e) {
			
			throw new IllegalArgumentException("Please provide a " + String.class + " constructor for primary key of class " + clazz);
		}
		
	}
	
	public ET loadBean(EntityManager em,Class<ET> beanClass,String key) {
		if (em==null) em = getEm(false);
		Serializable keyVal = generateKey(beanClass,key);
		ET obj = em.find(beanClass, keyVal);
		return obj;
	}
	
	public void saveBean(EntityManager em,Object bean) {
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
	
	public <T> List<T> selectAll(EntityManager em,Class<T> cl,Collection<T> except) {
		if (em==null) em = getEm(false);
		StringBuilder query = new StringBuilder("from ");
		char alias = cl.getSimpleName().charAt(0);
		query.append(cl.getName());
		if (except!=null && !except.isEmpty()) {
			query.append(" ").append(alias);
			query.append(" where ");
			query.append(alias);
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

	@Override
	public Container loadAll(Class<ET> clazz, Collection<ET> exclude) {
		List<ET> coll = selectAll(null, clazz, exclude);
		BeanItemContainer<ET> result = new BeanItemContainer<ET>(clazz);
		result.addAll(coll);
		for (ET et : coll) {
			Object pk = getPrimaryKey(et);
System.out.println("ET:" + et + " has pk:" + pk);
//			result.addBean(result.addItem(pk));
//			result.addItem(pk,et);
		}
		return result;
	}

	@Override
	public Item findByKey(Class<ET> clazz, Serializable pk) {
		ET bean = loadBean(null,clazz,pk.toString());
		return new BeanItem<ET>(bean);
	}

	@Override
	public Item refresh(ET instance) {
System.out.println("instance class:" + instance.getClass());
		EntityManager em = getEm(false);
		if (em.contains(instance)) {
			em.refresh(instance);
			return new BeanItem<ET>(instance);
		} else {
			return new BeanItem<ET>(em.merge(instance));
		}
	}
	
	
}
