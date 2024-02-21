package net.javaguides.springboot.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.module.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long>{

}
