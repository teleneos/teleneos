package id.co.bonet.itu.noc.service;

import org.meruvian.yama.persistence.EntityListWrapper;

import id.co.bonet.itu.noc.Telecentre;

public interface TelecentreService {

	Telecentre save(Telecentre telecentre);
	EntityListWrapper<Telecentre> all(int limit, int page);
	Telecentre findById(String id);
}
