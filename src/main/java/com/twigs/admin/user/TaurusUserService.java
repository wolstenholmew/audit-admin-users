package com.twigs.admin.user;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaurusUserService {

    @Autowired
    private TaurusUserDao userDao;

    @Autowired
    private TaurusUserPermissionDao userPermissionDao;

    @Autowired
    private TaurusPermissionDao permissionDao;

    public List<TaurusUser> findAllUsers() {
        return userDao.findAll();
    }

    private List<TaurusUserPermission> findUsersPermissions(int userId) {
        return userPermissionDao.findByTaurusUserId(userId);
    }

    private TaurusPermission findPermissionByTaurusPermissionId(int taurusPermissionId) {
        return permissionDao.findByTaurusId(taurusPermissionId);
    }

    public DualListModel<TaurusPermission> createPickListOfPermissionsForUser(int userId) {
        List<TaurusPermission> source = new ArrayList<>();
        List<TaurusPermission> target = new ArrayList<>();
        DualListModel<TaurusPermission> pickList = new DualListModel<>(source, target);

        for(TaurusUserPermission userPermission : findUsersPermissions(userId)) {
            TaurusPermission permission = findPermissionByTaurusPermissionId(userPermission.getTaurusPermissionId());
            if(userPermission.isAuthorised()) {
                target.add(permission);
            } else {
                source.add(permission);
            }
        }
        return pickList;
    }

    public boolean updateAndSaveUserPermissionList(List<TaurusPermission> permissions, TaurusUser user, boolean keep) {
        for(TaurusPermission p : permissions) {
            TaurusUserPermission userPermission = userPermissionDao.findByTaurusUserIdAndTaurusPermissionId(user.getTaurusId(), p.getTaurusId());
            userPermission.setAuthorised(keep);
            userPermissionDao.save(userPermission);
        }
        return true;
    }

    public TaurusUser markUserAuditComplete(TaurusUser taurusUser, boolean auditComplete) {
        taurusUser.setAuditComplete(auditComplete);
        return userDao.save(taurusUser);
    }

}
