package com.volkonovskij.domain.hibernate;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Schedule.class)
public abstract class Schedule_ {

	public static volatile SingularAttribute<Schedule, Timestamp> date;
	public static volatile SingularAttribute<Schedule, Timestamp> timeStart;
	public static volatile SingularAttribute<Schedule, Boolean> isDeleted;
	public static volatile SingularAttribute<Schedule, Timestamp> created;
	public static volatile SetAttribute<Schedule, Doctor> doctors;
	public static volatile SingularAttribute<Schedule, Long> id;
	public static volatile SingularAttribute<Schedule, Timestamp> timeFinish;
	public static volatile SingularAttribute<Schedule, Timestamp> changed;

	public static final String DATE = "date";
	public static final String TIME_START = "timeStart";
	public static final String IS_DELETED = "isDeleted";
	public static final String CREATED = "created";
	public static final String DOCTORS = "doctors";
	public static final String ID = "id";
	public static final String TIME_FINISH = "timeFinish";
	public static final String CHANGED = "changed";

}

