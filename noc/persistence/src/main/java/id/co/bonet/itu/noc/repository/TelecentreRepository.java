package id.co.bonet.itu.noc.repository;

import javax.persistence.NoResultException;

import id.co.bonet.itu.noc.Telecentre;

import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TelecentreRepository extends PersistenceRepository<Telecentre> {
	public Telecentre checkIfExist(int telecentreId) {
		try {
			Telecentre telecentre = (Telecentre) entityManager
					.createQuery("FROM Telecentre t WHERE t.telecentre_id = ?")
					.setParameter(1, telecentreId).getSingleResult();
			return telecentre;
		} catch (NoResultException e) {
			System.err.println(e.getMessage());
			return null;
		}

	}
}