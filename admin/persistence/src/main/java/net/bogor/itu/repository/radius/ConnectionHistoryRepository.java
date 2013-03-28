package net.bogor.itu.repository.radius;

import org.springframework.stereotype.Repository;

import net.bogor.itu.entity.radius.ConnectionHistory;
import net.bogor.itu.persistence.PersistenceRepository;

@Repository
public class ConnectionHistoryRepository extends PersistenceRepository<ConnectionHistory> {

}
