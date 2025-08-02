package web.web.hooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.web.hooks.modal.SchoolData;

@Repository
public interface SchoolDataRepository extends JpaRepository < SchoolData, Long>{
    
}
