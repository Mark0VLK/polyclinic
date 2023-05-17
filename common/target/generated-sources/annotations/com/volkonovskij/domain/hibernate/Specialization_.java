package com.volkonovskij.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Specialization.class)
public abstract class Specialization_ {

	public static volatile SingularAttribute<Specialization, Boolean> isDeleted;
	public static volatile SingularAttribute<Specialization, Timestamp> created;
	public static volatile SetAttribute<Specialization, Doctor> doctors;
	public static volatile SingularAttribute<Specialization, String> name;
	public static volatile SingularAttribute<Specialization, Long> id;
	public static volatile SingularAttribute<Specialization, Timestamp> changed;

	public static final String IS_DELETED = "isDeleted";
	public static final String CREATED = "created";
	public static final String DOCTORS = "doctors";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String CHANGED = "changed";

}

