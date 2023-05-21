package com.volkonovskij.domain.hibernate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuthenticationInfo.class)
public abstract class AuthenticationInfo_ {

	public static volatile SingularAttribute<AuthenticationInfo, String> userPassword;
	public static volatile SingularAttribute<AuthenticationInfo, String> email;

	public static final String USER_PASSWORD = "userPassword";
	public static final String EMAIL = "email";

}

