package com.twigs.admin.user;

import org.primefaces.model.DualListModel;

public class TaurusUserListItem {

    private TaurusUser user;
    private DualListModel<TaurusPermission> permissions;

    public TaurusUserListItem( TaurusUser user, DualListModel<TaurusPermission> permissions ) {
        this.user = user;
        this.permissions = permissions;
    }

    public TaurusUser getUser() {
        return user;
    }

    public void setUser( TaurusUser user ) {
        this.user = user;
    }

    public DualListModel<TaurusPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions( DualListModel<TaurusPermission> permissions ) {
        this.permissions = permissions;
    }
}
