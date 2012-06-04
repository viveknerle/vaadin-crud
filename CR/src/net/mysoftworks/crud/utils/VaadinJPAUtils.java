package net.mysoftworks.crud.utils;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;
import com.vaadin.data.Item;

public class VaadinJPAUtils<ET> {

	public static <ET> JPAContainer<ET> getJPAContainer(Class<ET> cl) {
		JPAContainer<ET> container = JPAContainerFactory.makeBatchable(cl,"antonio");
		container.getEntityProvider().setLazyLoadingDelegate(new HibernateLazyLoadingDelegate());
		return container;
	}
	
	public static <ET> Item getEntity(Class<ET> cl,Object value) {
		JPAContainer<ET> container = getJPAContainer(cl);
		JPAContainerItem<ET> e = (JPAContainerItem<ET>)container.getItem(value);
		return e;
	}
}
