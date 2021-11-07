package dao.impl;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import dao.StaffDao;
import model.Staff;
import util.HibernateUtil;

public class StaffImpl implements StaffDao {
	
	private OgmSessionFactory sessionFactory;

	public StaffImpl() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	public boolean addStaff(Staff staff) {
		OgmSession session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.save(staff);
			transaction.commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		
		session.close();
		return false;
	}

}
