package net.mysoftworks.crud;

import java.lang.reflect.Field;

import net.mysoftworks.crud.model.CollectionMetadata;

import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.EmbeddedForm;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;

public class CustomFF extends FieldFactory {

	public static Class getCollectionClass(Class c,Object propertyId) {
		try {
			Field field = c.getDeclaredField(propertyId.toString());
			if (field == null) {
				field = c.getField(propertyId.toString());
			}
			CollectionMetadata ann = field.getAnnotation(CollectionMetadata.class);
			return ann.collectionClass();
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}

	public CustomFF() {
	}
	
	
	public CustomFF(Item entity) {
		System.out.println("item:" + entity.toString());
	}
	
	@Override
	protected com.vaadin.ui.Field createEmbeddedField(EntityContainer jpacontainer, Object itemId, Object propertyId, Component uiContext)
    {
		
		
		
System.out.println("createEmbeddedField for " + propertyId);		
		com.vaadin.ui.Field form = super.createEmbeddedField(jpacontainer,itemId,propertyId,uiContext);
		((EmbeddedForm)form).setFormFieldFactory(this);
		return form;
    }
	
	
	@Override
	public com.vaadin.ui.Field createField(Item item, Object propertyId, Component uiContext) {
		
		if ("handler".equals(propertyId) || "hibernateLazyInitializer".equals(propertyId)) {
System.out.println("-----------------NULL field!!!!!!!!!!!!!");			
			return null;
		}
		
		
		Property prop = item.getItemProperty(propertyId);
		if (prop.getValue()==null) {
			System.out.println("-----------------NULL value for " + propertyId + "!!!!!!!!!!!!!");			
			return null;
			
		}
		
//		AbstractPropertyData pData = PropertyDataFactory.getPropertyData(item, propertyId);
//		if (pData!=null) {
//			return pData.getField(uiContext);
//		}

//		JPAContainerItem i = ((JPAContainerItem)item);
//System.out.println("\titem: " + i.getEntity().getClass() + "\n\tproperty: " + propertyId + "\n\tgetCollectionClass:" +  getCollectionClass(i.getContainer().getEntityClass(), propertyId));		
		return super.createField(item, propertyId, uiContext);
	}

}
