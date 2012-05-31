package net.mysoftworks.cr;

import net.mysoftworks.cr.support.AbstractPropertyData;
import net.mysoftworks.cr.support.PropertyDataFactory;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;

public class CustomFieldFactory extends DefaultFieldFactory {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		if (propertyId.equals("id") || propertyId.toString().startsWith("hibernate")) {
			return null;
		}
		AbstractPropertyData pData = PropertyDataFactory.getPropertyData((BeanItem)item, propertyId);
		if (pData!=null) {
			return pData.getField(uiContext);
		}
		return super.createField(item, propertyId, uiContext);
	}
}
