package com.volkonovskij.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Doctor.class)
public abstract class Doctor_ {

	public static volatile SetAttribute<Doctor, Visit> visits;
	public static volatile SingularAttribute<Doctor, Boolean> isDeleted;
	public static volatile SingularAttribute<Doctor, String> surname;
	public static volatile SingularAttribute<Doctor, Timestamp> created;
	public static volatile SetAttribute<Doctor, Schedule> schedules;
	public static volatile SingularAttribute<Doctor, String> name;
	public static volatile SingularAttribute<Doctor, Specialization> specialization;
	public static volatile SingularAttribute<Doctor, Long> id;
	public static volatile SingularAttribute<Doctor, String> office;
	public static volatile SingularAttribute<Doctor, HibernateRegion> regionNumber;
	public static volatile SingularAttribute<Doctor, HibernateUser> user;
	public static volatile SingularAttribute<Doctor, Timestamp> changed;

	public static final String VISITS = "visits";
	public static final String IS_DELETED = "isDeleted";
	public static final String SURNAME = "surname";
	public static final String CREATED = "created";
	public static final String SCHEDULES = "schedules";
	public static final String NAME = "name";
	public static final String SPECIALIZATION = "specialization";
	public static final String ID = "id";
	public static final String OFFICE = "office";
	public static final String REGION_NUMBER = "regionNumber";
	public static final String USER = "user";
	public static final String CHANGED = "changed";

}

