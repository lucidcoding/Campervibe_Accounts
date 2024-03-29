package uk.co.luciditysoftware.campervibe.data.repositories;

import java.util.UUID;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import uk.co.luciditysoftware.campervibe.domain.entities.User;
import uk.co.luciditysoftware.campervibe.domain.repositorycontracts.UserRepository;

@Repository
@Scope("prototype")
public class UserRepositoryImpl implements UserRepository{

	private SessionFactory sessionFactory;

	@Inject 
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public User getById(UUID id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User)session.get(User.class, id);
		return user;
	}

	@Override
	public User getByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.createCriteria(User.class)
			.add( Restrictions.eq("username", username) )
			.uniqueResult();
		
		return user;
	}
	
	@Override
	public void save(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
}
