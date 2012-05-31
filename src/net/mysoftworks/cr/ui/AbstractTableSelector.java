package net.mysoftworks.cr.ui;

import java.util.Collection;
import java.util.List;

import net.mysoftworks.cr.support.AbstractPropertyData;
import net.mysoftworks.cr.support.CollectionPropertyData;
import net.mysoftworks.cr.utils.EntityManagerUtils;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public abstract class AbstractTableSelector extends HorizontalLayout {

	protected AbstractPropertyData propertyData;
	protected Table selectionTable;

	public Table getSelectionTable() {return selectionTable;}
	
	protected void initSelectionTable(Collection data) {
		this.selectionTable = new Table();
		BeanItemContainer cnt = new BeanItemContainer(propertyData.getType(),data);
		this.selectionTable.setContainerDataSource(cnt);
		this.selectionTable.setImmediate(false);
		this.selectionTable.setSelectable(true);
		this.selectionTable.setCaption("Selezionare valori");
	}

	protected <T> List<T> availableValues(Collection except) {
		List<T> l = EntityManagerUtils.selectAll(propertyData.getType(),except);
		return l;
//		return new BeanItemContainer(propertyData.getType(),l);
	}

	protected abstract void initLayout();
	
	public AbstractTableSelector( AbstractPropertyData propertyData ) {
		this.propertyData = propertyData;
		initLayout();
	}


	protected void addSaveButton(final Table current) {
		
		Button saveButton = new Button("Salva");
		saveButton.addListener(new ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				try {
					current.commit();
System.out.println("on savettttttttt:" + current.getValue());					
System.out.println("on save2:" + current.getContainerDataSource().getItemIds());
System.out.println("on save3:" + propertyData.getValue());
					if (propertyData instanceof CollectionPropertyData) {
						Collection<?> ids = current.getContainerDataSource().getItemIds();
						((CollectionPropertyData) propertyData).clear();
						for (Object object : ids) {
							propertyData.addOrSetValue(object);
						}
					} else {
System.out.println("========propertyData:" + propertyData.getClass());						
//						propertyData.addOrSetValue(current.getValue());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				((Window) getWindow().getParent()).removeWindow(getWindow());
			}
		});
		
		addComponent(saveButton);		
	}
	
}
