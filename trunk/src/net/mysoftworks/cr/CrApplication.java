package net.mysoftworks.cr;

import net.mysoftworks.cr.model.Utente;
import net.mysoftworks.cr.utils.EntityManagerUtils;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class CrApplication extends Application {

	@Override
	public void init() {

		final Window mainWindow = new Window("Cr Application");
		final TextField className = new TextField();
		final TextField id = new TextField();
		id.setWidth("10%");
		final Button button = new Button("Edit");
		final Button crea = new Button("Crea database");
		className.setWidth("50%");
		mainWindow.addComponent(className);
		mainWindow.addComponent(id);
		mainWindow.addComponent(button);
//		mainWindow.addComponent(crea);

		className.setValue(Utente.class.getName());
		id.setValue("1");
		button.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {

				try {
					String clazz = className.getValue().toString();
					Class<?> cl = Class.forName(clazz);
					//					BeanUtils.setProperty(o, "codice", "Il codice");
					//					BeanUtils.setProperty(o, "descrizione", "La descrizione");
					//					BeanUtils.setProperty(o, "descrizioneBreve", "La descrizioneBreve");
					Window child = new Window(cl.getSimpleName());
					child.setModal(true);
					child.setWidth("80%");
					Object obj = EntityManagerUtils.loadBean(cl, id.toString());
					child.addComponent(new FormBean(obj,cl));
					mainWindow.addWindow(child);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		crea.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				EntityManagerUtils.getEntityManager();
			}
		});


		setMainWindow(mainWindow);
	}

}

