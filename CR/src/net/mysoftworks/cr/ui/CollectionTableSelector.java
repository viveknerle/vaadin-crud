package net.mysoftworks.cr.ui;

import java.util.Collection;

import net.mysoftworks.cr.support.CollectionPropertyData;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.SourceIs;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.TableDragMode;

@SuppressWarnings("serial")
public class CollectionTableSelector extends AbstractTableSelector implements DropHandler  {
	private Table currentItems;
	public Table getCurrentItems() {return currentItems;}

	@Override
	protected void initLayout() {
		Collection curr = currentValues();
		Collection avail = availableValues(curr);
		initSelectionTable(avail);
		initCurrentItems(curr);
		addComponent(getSelectionTable());
		addComponent(getCurrentItems());
		addSaveButton(getCurrentItems());
		
	}

	
	private void initCurrentItems(Collection elements) {
		this.currentItems = new Table();
		BeanItemContainer cnt = new BeanItemContainer(propertyData.getType(),elements);
		this.currentItems.setContainerDataSource(cnt);
		this.currentItems.setImmediate(false);
		this.currentItems.setSelectable(true);
		this.currentItems.setCaption("Second table");
		
		this.selectionTable.setDragMode(TableDragMode.ROW);
		this.currentItems.setDragMode(TableDragMode.ROW);
		this.selectionTable.setDropHandler(this);
		this.currentItems.setDropHandler(this);
	}
	
	@Override
	public void drop(DragAndDropEvent event) {
		DataBoundTransferable t = (DataBoundTransferable) event.getTransferable();
		Container sourceContainer = t.getSourceContainer();
System.out.println("==============drop-->ItemId:"+t.getItemId() + ";ItemId.class:"+t.getItemId().getClass() + "\n==============on " + ((sourceContainer == currentItems.getContainerDataSource()) ? "firstTable" : "secondTable"));
		sourceContainer.removeItem(t.getItemId());
		if (sourceContainer == selectionTable.getContainerDataSource()) {
			currentItems.addItem(t.getItemId());
		} else {
			selectionTable.addItem(t.getItemId());
		}
		
//		propertyData.removeItem(t.getItemId());
	}

	@Override
	public AcceptCriterion getAcceptCriterion() {
		return new SourceIs(selectionTable,currentItems);
	}

	private Collection currentValues() {
		return (Collection) propertyData.getValue();
	}
	

	public CollectionTableSelector(CollectionPropertyData propertyData) {
		super(propertyData);
	}


	
	
}
