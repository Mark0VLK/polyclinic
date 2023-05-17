package com.volkonovskij.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateRegion.class)
public abstract class HibernateRegion_ {

	public static volatile SingularAttribute<HibernateRegion, Long> number;
	public static volatile SingularAttribute<HibernateRegion, Boolean> isDeleted;
	public static volatile SingularAttribute<HibernateRegion, Timestamp> created;
	public static volatile SetAttribute<HibernateRegion, Doctor> doctors;
	public static volatile SetAttribute<HibernateRegion, HibernatePatient> patients;
	public static volatile SingularAttribute<HibernateRegion, String> addressRange;
	public static volatile SingularAttribute<HibernateRegion, Long> id;

	public static final String NUMBER = "number";
	public static final String IS_DELETED = "isDeleted";
	public static final String CREATED = "created";
	public static final String DOCTORS = "doctors";
	public static final String PATIENTS = "patients";
	public static final String ADDRESS_RANGE = "addressRange";
	public static final String ID = "id";

}

