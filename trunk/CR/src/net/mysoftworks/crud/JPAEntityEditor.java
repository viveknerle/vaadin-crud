package net.mysoftworks.crud;

import net.mysoftworks.crud.impl.DeafultEntityEditor;
import net.mysoftworks.crud.interfaces.EntityEditor;
import net.mysoftworks.crud.utils.VaadinJPAUtils;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.ui.HorizontalLayout;

public class JPAEntityEditor<ET> extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected Class<ET> cl;
	protected Container container;
	protected EntityEditor<ET> entityEditor;

	
	private void initJPAContainer() {
		this.container = VaadinJPAUtils.getJPAContainer(cl);
	}
	
	public void renderEditor(Item instance) {
		if (entityEditor == null) {
			entityEditor = new DeafultEntityEditor<ET>();
		}
		addComponent(entityEditor.createEditor(cl));
		entityEditor.setEditorInstance(instance);

	}

	public void setEntityEditor(EntityEditor<ET> ee) {
		this.entityEditor = ee;
	}
	
	public JPAEntityEditor(Class<ET> cl)  throws Exception {
		this.cl = cl;
		initJPAContainer();
	}	
		
}
