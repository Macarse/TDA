package com.tda.persistence.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.tda.model.applicationuser.OnlineUser;

public class OnlineUserDAO extends GenericDAOImpl<OnlineUser> {

	@Override
	protected Class<OnlineUser> getDomainClass() {
		return OnlineUser.class;
	}

	// @SuppressWarnings("unchecked")
	// public void deleteTimeoutUsers() {
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// String now = sdf.format(new Date());
	// List<OnlineUser> list = getHibernateTemplate().find(
	// "from OnlineUser ou WHERE timeout <= '" + now + "'");
	// for (OnlineUser onlineUser : list) {
	// this.delete(onlineUser);
	// }
	// }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteTimeoutUsers() {
		getHibernateTemplate().execute(new HibernateCallback() {

			@SuppressWarnings("deprecation")
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String now = sdf.format(new Date());

				String query = "DELETE FROM tda.OnlineUser WHERE timeout <= '"
						+ now + "'";

				Statement st = session.connection().createStatement();
				st.executeUpdate(query);

				return null;
			}
		});
	}
}
