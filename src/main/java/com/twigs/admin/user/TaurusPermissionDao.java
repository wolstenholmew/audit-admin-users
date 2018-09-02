package com.twigs.admin.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaurusPermissionDao extends JpaRepository<TaurusPermission, Integer> {

    TaurusPermission findByTaurusId(int taurusPermissionId);

}
