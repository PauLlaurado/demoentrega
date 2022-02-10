package com.example.demo.repository;

import com.example.demo.domain.model.Misatges;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MisatgesRepository extends JpaRepository<Misatges, UUID> {
    <T> List<T> findBy(Class<T> type);
    <T> List<T> findByIduserenviat(UUID Iduserenviat,Class<T> type);
    <T> List<T> findByIduserremitent(UUID Iduserremitent ,Class<T> type);

    <T> List<T> findByIduserenviatOrIduserremitent( UUID Iduserenviat, UUID Iduserremitent, Class<T> clazz);

}
