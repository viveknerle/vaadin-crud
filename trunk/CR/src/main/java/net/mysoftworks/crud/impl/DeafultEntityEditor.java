package net.mysoftworks.crud.impl;

import net.mysoftworks.crud.CustomFF;
import net.mysoftworks.crud.interfaces.EntityEditor;

import com.vaadin.data.Item;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Form;

public class DeafultEntityEditor<T> implements EntityEditor<T> {

	private Form entityForm = new Form();
	
	@Override
	public Component createEditor(Class<T> clazz) {
		entityForm.setCaption( clazz.getSimpleName() + " Editor");
		entityForm.addStyleName("bordered"); // Custom style
		entityForm.setWidth("420px");
		entityForm.setWriteThrough(false); // Enable buffering
		entityForm.setEnabled(false);
        // Handle saves on the form
        Button save = new Button("Save");
        entityForm.getFooter().removeAllComponents();
        entityForm.getFooter().addComponent(save);
		return entityForm;
	}

	@Override
	public void setEditorInstance(Item instance) {
		entityForm.setFormFieldFactory(new CustomFF());
		entityForm.setItemDataSource(instance);
		entityForm.setEnabled(true);
	}

	@Override
	public ClickListener getClickListener(AbstractField master) {
		final Form form = (Form)master;
		return new ClickListener() {
			@Override
			public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
                form.commit();
                form.setEnabled(false);
			}
        };
	}

}
