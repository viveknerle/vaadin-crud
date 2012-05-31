package net.mysoftworks.cr.support;

import org.apache.commons.lang.StringUtils;
import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public abstract class AbstractPropertyField extends CustomField {

	private ValueChangeListener vcl = new ValueChangeListener() {
		public void valueChange(Property.ValueChangeEvent event) {
System.out.println("===========Event property:" + event.getProperty());				
System.out.println("===========Label getValue class:" + label.getValue().getClass());				
System.out.println("===========Event getValue class:" + event.getProperty().getValue().getClass());				
			setComponentError(new UserError("Modifiche non salvate"));
			label.setValue(event.getProperty().getValue());
		}
	};
	
	protected abstract Component getEditor(); 
	
	protected AbstractPropertyData propertyData;
	protected Label label;

	private void addVChangeListener() {
System.out.println("addVChangeListener");		
		addListener(vcl);		
	}
	
	protected void createSelector(Layout layout) {
		Button selOpener = new Button("Seleziona");
		selOpener.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					addVChangeListener();
					Window child = new Window(propertyData.getSimpleName());
					child.setModal(true);
					child.setWidth("80%");
					Component editor = getEditor();
System.out.println("editor is " + editor);					
					child.addComponent(editor);
//					child.addComponent(new CollectionSelectionEditor(propertyData));
					getApplication().getMainWindow().addWindow(child);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		selOpener.setWidth("10%");
		layout.addComponent(selOpener);
	}

	protected void createLabel(GridLayout layout) {
		label = new Label();
//		PopupView pv = new PopupView(this.propertyData.getValue().toString(),getParent());
		label.setWidth("80%");
		Object val = getValue().toString();
//		Object val = this.propertyData.getValue();
		if (val!=null) {
			val = StringUtils.abbreviate(val.toString(), 20);
		}
		label.setValue(val);
		layout.addComponent(label);
	}
	
	protected Layout commonInit() {
		setCaption(DefaultFieldFactory.createCaptionByPropertyId(propertyData.getPropertyId()));
//		HorizontalLayout layout = new HorizontalLayout();
//		layout.setSizeFull();
//		layout.setMargin(false);
//		Panel p = new Panel(layout);

		GridLayout layout = new GridLayout(3,1);
		layout.setMargin(false);
		layout.setSpacing(true);		
//System.out.println("getContent:" + ((VerticalLayout)p.getContent()).setMargin(false));
//((VerticalLayout)p.getContent()).setSpacing(false);
//((VerticalLayout)p.getContent()).setMargin(false);
		setCompositionRoot(layout);
		createLabel(layout);
		createSelector(layout);
		return layout;
	}

	public AbstractPropertyField(AbstractPropertyData propertyData, Component uiContext) {
		this.propertyData = propertyData;
		setPropertyDataSource(propertyData.property);
	}


	@Override
	public Class<?> getType() {
		if (propertyData instanceof CollectionPropertyData) {
			return ((CollectionPropertyData)propertyData).getGenericType();
		}
		return propertyData.getType();
	}

	
}
