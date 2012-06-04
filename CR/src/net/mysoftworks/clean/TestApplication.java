package net.mysoftworks.clean;

import net.mysoftworks.crud.JPAMasterDetailEntityEditor;
import net.mysoftworks.crud.JPAMasterDetailEntityEditor.MASTER_SELECT_TYPE;
import net.mysoftworks.crud.model.Comune;
import net.mysoftworks.crud.model.Indirizzo;
import net.mysoftworks.crud.model.Regione;
import net.mysoftworks.crud.model.Utente;

import com.vaadin.Application;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Select;
import com.vaadin.ui.Window;

public class TestApplication extends Application {

	@Override
	public void init()  {

		final Window mainWindow = new Window("Test Application" + Math.random());
		final Select sel = new Select("Tipi");
		sel.addItem(Utente.class.getName());
		sel.addItem(Indirizzo.class.getName());
		sel.addItem(Comune.class.getName());
		sel.addItem(Regione.class.getName());
		sel.setImmediate(true);
		
		sel.addListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				String clazz = (String) event.getProperty().getValue();
				try {
					mainWindow.removeAllComponents();
					mainWindow.addComponent(sel);
					JPAMasterDetailEntityEditor ee = new JPAMasterDetailEntityEditor(Class.forName(clazz),MASTER_SELECT_TYPE.SELECT);
					ee.renderEditor();
					mainWindow.addComponent(ee);
					
				}	
				catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
			}
			
		});
		mainWindow.setSizeFull();
		mainWindow.addComponent(sel);
		setMainWindow(mainWindow);
		
	}

}

