package com.volkonovskij.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateRole.class)
public abstract class HibernateRole_ {

	public static volatile SingularAttribute<HibernateRole, Timestamp> created;
	public static volatile SingularAttribute<HibernateRole, String> roleName;
	public static volatile SingularAttribute<HibernateRole, Long> id;
	public static volatile SingularAttribute<HibernateRole, HibernateUser> user;
	public static volatile SingularAttribute<HibernateRole, Timestamp> changed;

	public static final String CREATED = "created";
	public static final String ROLE_NAME = "roleName";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String CHANGED = "changed";

}

