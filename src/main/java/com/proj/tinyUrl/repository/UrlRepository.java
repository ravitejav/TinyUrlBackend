package com.proj.tinyUrl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.tinyUrl.entites.Url;

public interface UrlRepository extends JpaRepository<Url, Integer> {

}
