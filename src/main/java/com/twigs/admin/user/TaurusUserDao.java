package com.twigs.admin.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaurusUserDao extends JpaRepository<TaurusUser, Integer> {

    TaurusUser findByTaurusId(int taurusId);

}
