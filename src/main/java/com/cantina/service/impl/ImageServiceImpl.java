package com.cantina.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantina.model.ImageModel;
import com.cantina.repository.ImageRepository;
import com.cantina.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public ImageModel findById(Long id) {
		return imageRepository.findById(id).get();
	}
}
