/**
 * Copyright 2013 Meruvian
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bogor.itu.persistence;

import javax.annotation.PostConstruct;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.meruvian.yama.persistence.DefaultPersistence;

/**
 * @author Dian Aditya
 * 
 */
public class FullSearchService extends
		PersistenceRepository<DefaultPersistence> {

	@PostConstruct
	private void indexDatabase() throws InterruptedException {
		FullTextEntityManager em = Search
				.getFullTextEntityManager(entityManager);
		em.createIndexer().startAndWait();
	}
}
