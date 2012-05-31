package net.mysoftworks.cr;

import net.mysoftworks.cr.support.BeanPropertyData;
import net.mysoftworks.cr.utils.EntityManagerUtils;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class FormBean extends Form {

//	private Object key;
//	private Object instance;
	private BeanItem beanItem;
	
//	private BeanPropertyData parentData;

	private void init(final Object instance,String title){
		if (instance==null) throw new NullPointerException("Create a bean form with null instance");
		
		if (title == null) {
			title = instance.getClass().getSimpleName();
		}
		
		// Create the Form
//		final Form pForm = new Form();
		setCaption(title);
		setFormFieldFactory(new CustomFieldFactory());
		this.beanItem = new BeanItem(instance);
		setItemDataSource(beanItem);
		setImmediate(false);
//		setLayout(new GridLayout(2, 8));
				
		
		setWidth("80%");
		setHeight("60%");
		
		
		Button saveButton = new Button("Salva");
		saveButton.addListener(new ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				commit();
				EntityManagerUtils.saveBean(beanItem.getBean());
//				if (parentData != null) {
//					parentData.setValue(instance);
//				}
				((Window) getWindow().getParent()).removeWindow(getWindow());
			}
		});
		
		getFooter().addComponent(saveButton);
		
	}
	
	public FormBean(Object instance,Class classIfNull) throws Exception {
		if (instance == null && classIfNull != null ) {
			instance = classIfNull.newInstance();
		}
		init(instance,null);
	}

	public FormBean(BeanPropertyData parentData) throws Exception {
//		this.parentData = parentData;
//		String title = DefaultFieldFactory.createCaptionByPropertyId(parentData.getPropertyId());
		String title = parentData.getModelType().getSimpleName();
		if (parentData.getValue()==null) {
			parentData.createNewValue();
		}
		init(parentData.getValue(),title);
	}

	
	@Override
	public String toString() {
		return "FormBean [beanClass=" + beanItem.getClass() + "]";
	}

}
