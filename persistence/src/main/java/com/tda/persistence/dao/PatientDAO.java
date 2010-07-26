package com.tda.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tda.model.patient.Patient;

public class PatientDAO extends GenericDAOImpl<Patient> {

	@Override
	protected Class<Patient> getDomainClass() {
		return Patient.class;
	}

	@SuppressWarnings("unchecked")
	public List<Patient> findByNameContaining(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Patient.class);
		criteria.add(Restrictions.like("name", "%" + name + "%"));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<Patient> findByMotherNameContaining(String motherName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Patient.class);
		criteria.add(Restrictions.like("motherName", "%" + motherName + "%"));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<Patient> findByFatherNameContaining(String fatherName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Patient.class);
		criteria.add(Restrictions.like("fatherName", "%" + fatherName + "%"));
		return getHibernateTemplate().findByCriteria(criteria);
	}
}
