package net.mysoftworks.cr.support;

import net.mysoftworks.cr.FormBean;
import net.mysoftworks.cr.ui.BeanTableSelector;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseListener;

@SuppressWarnings("serial")
public class BeanField extends AbstractPropertyField {


	private void createUpdater(Layout l) {
		Button activator = null;
		if (propertyData.getValue()==null) {
			activator = new Button("Crea");
//			ThemeResource creaIcon = new ThemeResource("");
//			activator.setIcon(creaIcon);
		} else {
			activator = new Button("Modifica");
		}
		activator.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					Window child = new Window();
					child.setModal(true);
//					if (propertyData instanceof CollectionPropertyData) {
//						CollectionPropertyData cpdata = (CollectionPropertyData) propertyData;
//						child.setCaption(cpdata.getGenericType().getSimpleName());
//						child.addComponent(new TableBean(propertyData));
//					} else {
						String prop = DefaultFieldFactory.createCaptionByPropertyId(propertyData.getPropertyId());
						child.setCaption(prop);
						child.addComponent(new FormBean((BeanPropertyData) propertyData));
//					}
					
					getApplication().getMainWindow().addWindow(child);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});		
		activator.setWidth("10%");
		
		l.addComponent(activator);
	}
	
	public BeanField(BeanPropertyData propertyData, Component uiContext) {
		super(propertyData,uiContext);
		Layout l = commonInit();
		createUpdater(l);

		
	}

	@Override
	protected Component getEditor() {
		return new BeanTableSelector((BeanPropertyData) propertyData);
	}


}
