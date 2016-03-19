package com.fpl.hibernate;

import org.hibernate.Session;

public class TestHibernate {

	public static void main(final String[] args) {
		final HibernateTemplate template = HibernateTemplateFactory.getInstance().getHibernateTemplate();
		template.execute(new HibernateCallback("test") {
			@Override
			public Object doInHibernate(final Session session) {
				System.out.println(session);
				return null;
			}
		});
	}
}
