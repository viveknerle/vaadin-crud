package net.mysoftworks.cr.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionMetadata {

	public Class collectionClass();
	
	public boolean unmodifiable() default false;
}
