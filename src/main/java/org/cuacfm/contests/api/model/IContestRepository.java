package org.cuacfm.contests.api.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IContestRepository extends MongoRepository<Contest, String> {

}
