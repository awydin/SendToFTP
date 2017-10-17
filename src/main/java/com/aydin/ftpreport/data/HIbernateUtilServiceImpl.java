package com.aydin.ftpreport.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.aydin.ftpreport.entities.AllCouponsReportEntity;
import com.google.inject.Singleton;



/*
 * 
 * @author : anasrollahpour
 */

@Singleton
public class HIbernateUtilServiceImpl implements HibernateUtilService
{

    private  final SessionFactory sessionFactory = buildNewSesstionFactory();

    private  SessionFactory buildNewSesstionFactory()
    {

        try
        {
            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(AllCouponsReportEntity.class);

            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("There was an error building factory");
        }

    }

    /* (non-Javadoc)
     * @see com.aydin.ftpreport.data.HibernateUtilService#getSessionfactory()
     */
    public  SessionFactory getSessionfactory()
    {
        return sessionFactory;
    }

}
