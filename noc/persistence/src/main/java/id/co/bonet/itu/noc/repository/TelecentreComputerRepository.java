package id.co.bonet.itu.noc.repository;

import javax.persistence.NoResultException;

import id.co.bonet.itu.noc.TelecentreComputer;

import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TelecentreComputerRepository extends
		PersistenceRepository<TelecentreComputer> {
	public TelecentreComputer checkIfExist(String telecentreId, String hostId) {
		try {
			TelecentreComputer telecentreComputer = (TelecentreComputer) entityManager
					.createQuery(
							"FROM TelecentreComputer tc WHERE tc.telecentre.id = ? AND tc.hostid = ?")
					.setParameter(1, telecentreId).setParameter(2, hostId)
					.getSingleResult();
			return telecentreComputer;
		} catch (NoResultException e) {
			System.err.println(e.getMessage());
			return null;
		}

	}
}
