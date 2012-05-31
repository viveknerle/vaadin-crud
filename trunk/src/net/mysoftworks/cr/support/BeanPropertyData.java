package net.mysoftworks.cr.support;

import java.io.Serializable;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;

public class BeanPropertyData extends AbstractPropertyData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	BeanPropertyData(BeanItem beanItem, Object propertyId) {
		super(beanItem,propertyId);
	}

	@Override
	public void createNewValue() throws Exception {
		Object newV = this.type.newInstance();
		property.setValue(newV);
	}
	
	@Override
	public com.vaadin.ui.Field getField(Component uiContext) {
		return new BeanField(this, uiContext);
	}

	@Override
	public void addOrSetValue(Object value) throws Exception {
		super.setValue(value);
	}


}
