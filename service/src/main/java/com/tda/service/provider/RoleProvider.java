package com.tda.service.provider;

import java.util.Enumeration;
import java.util.Properties;

import com.tda.model.applicationuser.Authority;
import com.tda.service.api.AuthorityService;
import com.tda.service.exception.NoDataFoundException;
import com.tda.service.exception.SingleResultExpectedException;

public class RoleProvider {
	private Properties propertiesHolder;

	private AuthorityService authorityService;

	public void init() {
		loadDefaultRoles(propertiesHolder);

	}

	public void setPropertiesHolder(final Properties propertiesHolder) {
		this.propertiesHolder = propertiesHolder;
	}

	private void loadDefaultRoles(final Properties properties) {
		for (Enumeration<Object> en = properties.keys(); en.hasMoreElements();) {
			String key = (String) en.nextElement();
			String role = properties.getProperty(key);

			Authority authority = null;
			try {
				authority = authorityService.findByAuthority(role);
			} catch (NoDataFoundException e) {
				authority = new Authority();
				authority.setAuthority(role);
				authorityService.save(authority);

			} catch (SingleResultExpectedException e) {
				// TODO we should log this as it is an anomaly in the data
				// representation
			}

		}
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public AuthorityService getAuthorityService() {
		return authorityService;
	}
}
