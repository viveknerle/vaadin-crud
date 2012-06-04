package net.mysoftworks.crud.interfaces;

import com.vaadin.data.Item;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;

public interface EntityEditor<T> {

	public Component createEditor(Class<T> clazz);
	public void setEditorInstance(Item instance);
	public ClickListener getClickListener(AbstractField master);
	
}
