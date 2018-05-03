package com.banyan.FullLoadRequest.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.banyan.FullLoadRequest.Entities.RateQtDetail;

public interface RateQtDetailRepository extends CrudRepository<RateQtDetail, Integer>, RateQtDetailRepositoryCustom {
	
	List<RateQtDetail> findByRtQteAddId(Integer rtQTeAddId);
}
