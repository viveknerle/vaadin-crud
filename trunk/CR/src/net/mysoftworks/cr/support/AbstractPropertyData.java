package net.mysoftworks.cr.support;

import java.io.Serializable;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;

public abstract class AbstractPropertyData implements Serializable {

	private static final long serialVersionUID = 1L;
	protected BeanItem beanItem;
	protected Object propertyId;
	protected Property property;
	protected Class<?> type;

	AbstractPropertyData(BeanItem beanItem, Object propertyId) {
		this.beanItem = beanItem;
		this.propertyId = propertyId;
		this.property = beanItem.getItemProperty(propertyId);
		this.type = property.getType(); 
	}

	public BeanItem getBeanItem() {
		return this.beanItem;
	}
	
	public Object getValue() {
		return property.getValue();
	}

	public Object getPropertyId() {
		return propertyId;
	}
	
	public void setValue(Object value){
		property.setValue(value);
	}
	
	public Class getType() {
		return type;
	}
	
	public String getSimpleName() {
		return type.getSimpleName();
	}
	
	public abstract void createNewValue() throws Exception;
	
	public Class getModelType() {
		return type;
	}
	
	public abstract void addOrSetValue(Object value) throws Exception ;
	
	public abstract com.vaadin.ui.Field getField(Component uiContext);
	
	@Override
	public String toString() {
		return "propertyData [beanItem=" + beanItem + ", propertyId="
				+ propertyId + "]";
	}
	
	
}