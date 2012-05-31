package net.mysoftworks.cr.support;

import java.io.Serializable;
import java.util.Arrays;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Select;

public class EnumPropertyData extends AbstractPropertyData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EnumPropertyData(BeanItem beanItem, Object propertyId) {
		super(beanItem,propertyId);
	}

	@Override
	public void createNewValue() throws Exception {
		throw new Exception("Unable to create a new instance from Enum");
//		Object newV = this.type.newInstance();
//		property.setValue(newV);
	}
	
	@Override
	public com.vaadin.ui.Field getField(Component uiContext) {
		Select select  = new Select(propertyId.toString());
		select.setContainerDataSource(new IndexedContainer(Arrays.asList( type.getEnumConstants()) ) ); 
		select.setImmediate(false);
		return select;
	}

	@Override
	public void addOrSetValue(Object value) throws Exception {
		super.setValue(value);
	}


}
