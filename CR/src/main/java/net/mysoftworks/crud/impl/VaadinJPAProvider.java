package net.mysoftworks.crud.impl;

import java.io.Serializable;
import java.util.Collection;

import net.mysoftworks.crud.interfaces.ContainerProvider;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;
import com.vaadin.data.Container;
import com.vaadin.data.Item;

public class VaadinJPAProvider<ET> implements ContainerProvider<ET> {
	
	private JPAContainer<ET> container; 
	
	
	public JPAContainer<ET> getJPAContainer(Class<ET> cl) {
		if (container == null) {
			container = JPAContainerFactory.makeBatchable(cl,"antonio");
			container.getEntityProvider().setLazyLoadingDelegate(new HibernateLazyLoadingDelegate());
		}	
		return container;
	}
	
	public Item getEntity(Class<ET> cl,Object value) {
System.out.println("\n\n==========getEntity:" + value);		
		JPAContainer<ET> container = getJPAContainer(cl);
		JPAContainerItem<ET> e = (JPAContainerItem<ET>)container.getItem(value);
		return e;
	}

	@Override
	public Container loadAll(Class<ET> clazz, Collection<ET> exclude) {
System.out.println("\n\n==========loadAll:" + clazz);		
		return getJPAContainer(clazz);
	}

	@Override
	public Item findByKey(Class<ET> clazz, Serializable pk) {
System.out.println("\n\n==========findByKey:class:" + clazz + " pk:" + pk);
		return getEntity(clazz,pk);
	}

	@Override
	public Item refresh(ET instance) {
System.out.println("\n\n==========refresh:" + instance);		
		return getEntity((Class<ET>) instance.getClass(),instance);
	}
}
