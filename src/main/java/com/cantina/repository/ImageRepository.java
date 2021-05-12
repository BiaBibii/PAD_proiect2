package com.cantina.repository;

import org.springframework.data.repository.CrudRepository;

import com.cantina.model.ImageModel;

public interface ImageRepository extends CrudRepository<ImageModel, Long>{

}
