package com.twigs.admin.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaurusUserPermissionDao extends JpaRepository<TaurusUserPermission, Integer> {

    List<TaurusUserPermission> findByTaurusUserId(int taurusUserId);
    TaurusUserPermission findByTaurusUserIdAndTaurusPermissionId(int userId, int permissionId);

}
