package org.jason.mapmaker.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * @author Jason
 */
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class AbstractHibernateTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    protected void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    protected Long getCount(Class<?> clazz) {

        Criteria countCriteria = sessionFactory.getCurrentSession().createCriteria(clazz);

        countCriteria.setProjection(Projections.rowCount());

        Long result = (Long) countCriteria.uniqueResult();

        return result;
    }

}
