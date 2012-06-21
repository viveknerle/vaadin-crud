package net.mysoftworks.crud;

import java.util.Collection;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
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
		Container cont = getContainerProvider().loadAll(cl, null);
System.out.println("\n\nContainer:" + cont.getClass() );		
		if (cont instanceof BeanItemContainer) {
			BeanItemContainer bic = (BeanItemContainer) cont;
			Collection ids = bic.getItemIds();
			
			for (Object id: ids) {
				System.out.println("\n\nitem:" + ((BeanItem)bic.getItem(id)).getBean());			
			}
			
		}
		Select itemSelector = new Select("Select a " + cl.getSimpleName(),cont);
		itemSelector.setItemCaptionMode(Select.ITEM_CAPTION_MODE_ITEM);
		itemSelector.setImmediate(true);
		itemSelector.setNullSelectionAllowed(false);
		return itemSelector;
		
	}
	private AbstractField createTable() {
		Table table = new Table("Select a " + cl.getSimpleName(),getContainerProvider().loadAll(cl, null));
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
System.out.println("event.getProperty():" + event.getProperty());				
				Item item = getContainerProvider().refresh((ET) event.getProperty().getValue());
				renderDetail(item);
			}

		};
	}

	private void renderDetail(Item instance) {
		super.render(instance);
	}
	
	public void render() {
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
