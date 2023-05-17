package com.volkonovskij.domain.hibernate;

import com.volkonovskij.domain.Gender;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernatePatient.class)
public abstract class HibernatePatient_ {

	public static volatile SetAttribute<HibernatePatient, Visit> visits;
	public static volatile SingularAttribute<HibernatePatient, String> address;
	public static volatile SingularAttribute<HibernatePatient, Gender> gender;
	public static volatile SingularAttribute<HibernatePatient, Boolean> isDeleted;
	public static volatile SingularAttribute<HibernatePatient, String> surname;
	public static volatile SingularAttribute<HibernatePatient, Timestamp> created;
	public static volatile SingularAttribute<HibernatePatient, String> name;
	public static volatile SingularAttribute<HibernatePatient, HibernateRegion> regionNumber;
	public static volatile SingularAttribute<HibernatePatient, Timestamp> birthDate;
	public static volatile SingularAttribute<HibernatePatient, HibernateUser> user;
	public static volatile SingularAttribute<HibernatePatient, Long> cardNumber;
	public static volatile SingularAttribute<HibernatePatient, Timestamp> changed;

	public static final String VISITS = "visits";
	public static final String ADDRESS = "address";
	public static final String GENDER = "gender";
	public static final String IS_DELETED = "isDeleted";
	public static final String SURNAME = "surname";
	public static final String CREATED = "created";
	public static final String NAME = "name";
	public static final String REGION_NUMBER = "regionNumber";
	public static final String BIRTH_DATE = "birthDate";
	public static final String USER = "user";
	public static final String CARD_NUMBER = "cardNumber";
	public static final String CHANGED = "changed";

}

