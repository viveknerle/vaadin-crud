package net.mysoftworks.crud;

import net.mysoftworks.crud.impl.DeafultEntityEditor;
import net.mysoftworks.crud.impl.EntityManagerProvider;
import net.mysoftworks.crud.interfaces.ContainerProvider;
import net.mysoftworks.crud.interfaces.EntityEditor;

import com.vaadin.data.Item;
import com.vaadin.ui.HorizontalLayout;

public class JPAEntityEditor<ET> extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected Class<ET> cl;
//	private Container container;
	protected EntityEditor<ET> entityEditor;
	private ContainerProvider<ET> cp; 

	protected ContainerProvider<ET> getContainerProvider() {
		return cp;
	}
	
	public void render(Item instance) {
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
		this.cp = new EntityManagerProvider<ET>();
//		this.cp = new VaadinJPAProvider<ET>();
	}	
		
}
