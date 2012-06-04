package net.mysoftworks.crud;

import net.mysoftworks.crud.utils.VaadinJPAUtils;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;

public class JPAMasterDetailEntityEditor<ET> extends JPAEntityEditor<ET> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum MASTER_SELECT_TYPE {SELECT,TABLE,TEXT_SEARCH}
	private AbstractField masterElement;
	private MASTER_SELECT_TYPE masterType;
	
	private AbstractField createSelect() {
		Select itemSelector = new Select("Select a " + cl.getSimpleName(),container);
		itemSelector.setItemCaptionMode(Select.ITEM_CAPTION_MODE_ITEM);
		itemSelector.setImmediate(true);
		itemSelector.setNullSelectionAllowed(false);
		return itemSelector;
		
	}
	private AbstractField createTable() {
		Table table = new Table("Select a " + cl.getSimpleName(),container);
		table.setSelectable(true);
		table.setMultiSelect(false);
		table.setImmediate(true);
		table.setNullSelectionAllowed(false);
		return table;
	}
	
	private AbstractField createTextSearch() {
		return null;
//		Table table = new Table("Select a " + cl.getSimpleName(),container);
//		table.setSelectable(true);
//		table.setMultiSelect(false);
//		table.setImmediate(true);
//		table.setNullSelectionAllowed(false);
//		return table;
	}
	
	private AbstractField createMaster(MASTER_SELECT_TYPE master) {
		AbstractField masterElement = null;
		switch (master) {
		case SELECT:masterElement = createSelect();	break;
		case TABLE:	masterElement = createTable(); break;
		case TEXT_SEARCH: masterElement = createTextSearch(); break;
		default:
			break;
		}
		if (masterElement != null) {
			addComponent(masterElement);
		}	
		return masterElement;
	}

	private ValueChangeListener getMasterValueChangeListener(Component masterElement) {
		return new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Item item = VaadinJPAUtils.getEntity(cl, event.getProperty().getValue());
				renderEditorInstance(item);
			}

		};
	}

	private void renderEditorInstance(Item instance) {
		super.renderEditor(instance);
	}
	
	public void renderEditor() {
		this.masterElement = createMaster(masterType);
		if (masterElement != null) {
			masterElement.addListener(getMasterValueChangeListener(masterElement));
		}
	}
	
	
	public JPAMasterDetailEntityEditor(Class<ET> cl,MASTER_SELECT_TYPE master)  throws Exception {
		super(cl);
		this.masterType = master;
		
	}	
		
}
