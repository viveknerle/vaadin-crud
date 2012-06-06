package net.mysoftworks.crud.interfaces;

import java.io.Serializable;
import java.util.Collection;

import com.vaadin.data.Container;
import com.vaadin.data.Item;

public interface ContainerProvider<ET> {

	public Container loadAll(Class<ET> clazz,Collection<ET> exclude);
	public Item findByKey(Class<ET> clazz,Serializable pk);
	public Item refresh(ET instance );
}
