package net.bogor.itu.repository.pos;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import net.bogor.itu.entity.pos.Conversion;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.springframework.stereotype.Repository;

@Repository
public class ConversionRepository extends PersistenceRepository<Conversion> {

	public Conversion findConversion(String uom1, String uom2) {
		String ql = "SELECT c FROM Conversion c WHERE (c.uomFrom.id = ? AND c.uomTo.id = ?) OR (c.uomFrom.id = ? AND c.uomTo.id = ?)";
		Query query = entityManager.createQuery(ql)
				.setParameter(1, uom1)
				.setParameter(2, uom2)
				.setParameter(3, uom2)
				.setParameter(4, uom1);
		try {
			return (Conversion) query.getSingleResult();			
		} catch (Exception e) {
			return null;
		}
	}
	
	public EntityListWrapper<Conversion> findTargetConversion(String from){
		TypedQuery<Conversion> query = createQuery(entityClass, "d", "d",
				"( d.uomTo.id = ? ) AND d.logInformation.statusFlag = ?",
				from, StatusFlag.ACTIVE);
		EntityListWrapper<Conversion> paging = new EntityListWrapper<Conversion>();
		paging.setEntityList(query.getResultList());
		return paging;
	}
	
	public EntityListWrapper<Conversion> findAll(String keyword, int limit,
			int page) {
		TypedQuery<Conversion> query = createQuery(entityClass, "d", "d",
				"( d.uomFrom.name LIKE ? OR d.uomTo.name LIKE ? ) AND d.logInformation.statusFlag = ?", "%"+keyword+"%", "%"+keyword+"%", StatusFlag.ACTIVE);
		if (limit > 0) {
			query.setMaxResults(limit);
		}
		query.setFirstResult(page);
		EntityListWrapper<Conversion> paging = new EntityListWrapper<Conversion>();
		paging.setCurrentPage(page);
		paging.setLimit(limit);
		paging.setEntityList(query.getResultList());

		return paging;
	}

}
