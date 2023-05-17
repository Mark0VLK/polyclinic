package com.volkonovskij.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateUser.class)
public abstract class HibernateUser_ {

	public static volatile SingularAttribute<HibernateUser, Doctor> doctor;
	public static volatile SingularAttribute<HibernateUser, AuthenticationInfo> authenticationInfo;
	public static volatile SingularAttribute<HibernateUser, String> phoneNumber;
	public static volatile SingularAttribute<HibernateUser, Boolean> isDeleted;
	public static volatile SingularAttribute<HibernateUser, Timestamp> created;
	public static volatile SingularAttribute<HibernateUser, HibernatePatient> patient;
	public static volatile SetAttribute<HibernateUser, HibernateRole> roles;
	public static volatile SingularAttribute<HibernateUser, HibernateDocument> document;
	public static volatile SingularAttribute<HibernateUser, Long> id;
	public static volatile SingularAttribute<HibernateUser, String> login;
	public static volatile SingularAttribute<HibernateUser, Timestamp> changed;

	public static final String DOCTOR = "doctor";
	public static final String AUTHENTICATION_INFO = "authenticationInfo";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String IS_DELETED = "isDeleted";
	public static final String CREATED = "created";
	public static final String PATIENT = "patient";
	public static final String ROLES = "roles";
	public static final String DOCUMENT = "document";
	public static final String ID = "id";
	public static final String LOGIN = "login";
	public static final String CHANGED = "changed";

}

