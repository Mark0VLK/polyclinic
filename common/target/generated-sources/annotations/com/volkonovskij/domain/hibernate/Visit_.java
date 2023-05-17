package com.volkonovskij.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Visit.class)
public abstract class Visit_ {

	public static volatile SingularAttribute<Visit, Doctor> doctor;
	public static volatile SingularAttribute<Visit, String> note;
	public static volatile SingularAttribute<Visit, Boolean> isDeleted;
	public static volatile SingularAttribute<Visit, Float> price;
	public static volatile SingularAttribute<Visit, Timestamp> created;
	public static volatile SingularAttribute<Visit, HibernatePatient> patient;
	public static volatile SingularAttribute<Visit, Long> id;
	public static volatile SingularAttribute<Visit, Boolean> status;
	public static volatile SingularAttribute<Visit, Timestamp> changed;

	public static final String DOCTOR = "doctor";
	public static final String NOTE = "note";
	public static final String IS_DELETED = "isDeleted";
	public static final String PRICE = "price";
	public static final String CREATED = "created";
	public static final String PATIENT = "patient";
	public static final String ID = "id";
	public static final String STATUS = "status";
	public static final String CHANGED = "changed";

}

