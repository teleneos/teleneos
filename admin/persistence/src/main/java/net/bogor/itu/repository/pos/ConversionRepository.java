package net.bogor.itu.repository.pos;

import javax.persistence.Query;

import net.bogor.itu.entity.pos.Conversion;
import net.bogor.itu.persistence.PersistenceRepository;

import org.springframework.stereotype.Repository;

@Repository
public class ConversionRepository extends PersistenceRepository<Conversion> {

	public Conversion findConversion(String uom1, String uom2) {
		System.err.println(uom1+"xxx"+uom2);
		String ql = "SELECT c FROM Conversion c WHERE (c.uomFrom.id = ? AND c.uomTo.id = ?) OR (c.uomFrom.id = ? AND c.uomTo.id = ?)";
		Query query = entityManager.createQuery(ql)
				.setParameter(1, uom1)
				.setParameter(2, uom2)
				.setParameter(3, uom2)
				.setParameter(4, uom1);
		try {
			return (Conversion) query.getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
