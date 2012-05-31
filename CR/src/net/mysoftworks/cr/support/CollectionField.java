package net.mysoftworks.cr.support;

import net.mysoftworks.cr.ui.CollectionTableSelector;

import com.vaadin.data.Property;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

@SuppressWarnings("serial")
public class CollectionField extends AbstractPropertyField {
	

	public CollectionField(CollectionPropertyData propertyData, Component uiContext) {
		super(propertyData,uiContext);
		Layout layout  = commonInit();
	
	}

	@Override
	protected Component getEditor() {
		return new CollectionTableSelector((CollectionPropertyData) propertyData);
	}
	
}
