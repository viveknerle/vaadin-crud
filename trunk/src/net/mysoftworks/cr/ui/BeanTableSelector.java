package net.mysoftworks.cr.ui;

import net.mysoftworks.cr.support.BeanPropertyData;

@SuppressWarnings("serial")
public class BeanTableSelector extends AbstractTableSelector {

	protected void initLayout() {
		initSelectionTable(availableValues(null));
		addComponent(getSelectionTable());
		addSaveButton(getSelectionTable());
	}
	
	public BeanTableSelector(BeanPropertyData propertyData ) {
		super(propertyData);
	}

}
