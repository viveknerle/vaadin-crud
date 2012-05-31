package net.mysoftworks.cr.support;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.mysoftworks.cr.model.CollectionMetadata;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Select;

public class PropertyDataFactory {

	public static Class getCollectionClass(BeanItem beanItem,Object propertyId) {
		try {
			Field field = beanItem.getBean().getClass().getDeclaredField(propertyId.toString());
			if (field == null) {
				field = beanItem.getBean().getClass().getField(propertyId.toString());
			}
			CollectionMetadata ann = field.getAnnotation(CollectionMetadata.class);
			return ann.collectionClass();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean canManage(Class pType) {
		if (pType.getName().startsWith("net.mysoftworks.cr.model")) {
			return true;
		}
		return false;
	}

	public static boolean canManage(Class[] parsed) {
		if (parsed[1]!=null) {
			return canManage(parsed[1]);
		}
		return canManage(parsed[0]);
	}
	
	public static boolean isSet(Class type) {
		return Set.class.isAssignableFrom(type);
	}

	public static boolean isList(Class type) {
		return List.class.isAssignableFrom(type);
	}

	public static boolean isCollection(Class type) {
		return Collection.class.isAssignableFrom(type);
	}

	public static boolean isCollection(BeanItem beanItem, Object propertyId) {
		Property prop = beanItem.getItemProperty(propertyId);
		Class type = prop.getType();
		return isCollection(type);
	}

	public static Class[] getGenericTypes(BeanItem beanItem, Object propertyId) {
		Class[] result= new Class[2];
		Property prop = beanItem.getItemProperty(propertyId);
		result[0] = prop.getType();
		if (isCollection(result[0])) {
			result[1] = getCollectionClass(beanItem,propertyId);
		}
		return result;
	}
	
	public static AbstractPropertyData getPropertyData(BeanItem beanItem, Object propertyId) {
		Class[] parsed = getGenericTypes(beanItem,propertyId);
		boolean canManage = canManage(parsed);
		if (canManage) {
			if (parsed[1]!=null) {
				return new CollectionPropertyData(beanItem, propertyId);
			} else {
				if (parsed[0].isEnum()) {
					return new EnumPropertyData(beanItem, propertyId);
				}
				return new BeanPropertyData(beanItem, propertyId);
			}
		}
		
		return null;
	}
	
}
