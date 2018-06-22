package com.banyan.FullLoadRequest.Repos;

import org.springframework.data.repository.CrudRepository;

import com.banyan.FullLoadRequest.Entities.RateQt;

public interface RateQtRepository extends CrudRepository<RateQt, Integer>, RateQtRepositoryCustom {
}
