package net.mysoftworks.cr.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;

public class CollectionPropertyData extends AbstractPropertyData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CollectionPropertyData(BeanItem beanItem, Object propertyId) {
		super(beanItem,propertyId);
	}

	
	public void createNewValue() throws Exception {
		if (PropertyDataFactory.isSet(type)) {
			property.setValue(new HashSet());
		} else {
			if (PropertyDataFactory.isList(type)) {
				property.setValue(new ArrayList());
			}
		} 
	}
	
	private Class _collectionClass = null;
	
	public Class getGenericType() {
		if (_collectionClass==null) {
			_collectionClass = PropertyDataFactory.getCollectionClass(beanItem,propertyId);
		}
		return _collectionClass;
	}

	private Collection _getValue() {
		return (Collection)getValue();
	}
	
	public void addItem(Object item) {
		_getValue().add(item);
	}

	public void removeItem(Object item) {
		_getValue().remove(item);
	}
	
	public boolean isEmpty() {
		return _getValue().isEmpty();
	}

	public void clear() {
		_getValue().clear();
	}
	
	public com.vaadin.ui.Field getField(Component uiContext) {
		return new CollectionField(this, uiContext);
	}

	@Override
	public String getSimpleName() {
		return getGenericType().getSimpleName();
	}
	
	public Class getModelType() {
		return getGenericType();
	}
	
	@Override
	public void addOrSetValue(Object value) throws Exception {
		if (_getValue()==null) {
			createNewValue();
		}
		addItem(value);
	}
	
}