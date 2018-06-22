package com.banyan.FullLoadRequest.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.banyan.FullLoadRequest.Entities.StopOff;

public interface StopOffRepository extends CrudRepository<StopOff,Integer>{
	List<StopOff> findAllByRtQteAddId(Integer id);
}
