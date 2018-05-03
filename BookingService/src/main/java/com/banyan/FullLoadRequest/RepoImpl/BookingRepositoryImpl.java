package com.banyan.FullLoadRequest.RepoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Repos.BookingRepositoryCustom;

@Service
public class BookingRepositoryImpl implements BookingRepositoryCustom{
	
	   @PersistenceContext
	    private EntityManager em;
	

	@Transactional
	public void refresh(Booking book) {
		em.refresh(em.merge(book));
		/*em.getTransaction().commit();*/
		/*em.clear();*/
	}

}
