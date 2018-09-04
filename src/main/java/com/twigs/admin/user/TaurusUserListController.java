package com.twigs.admin.user;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/*
select tu.username, tp.name from taurus_user_permission tup
        left join taurus_user tu on tu.taurus_id = tup.taurus_user_id
        join taurus_permission tp on tp.taurus_id = tup.taurus_permission_id and tup.authorised = true
*/

@Scope(value = "session")
@Component(value = "taurusUserListController")
@ELBeanName(value = "taurusUserListController")
@Join(path = "/", to = "/user/taurus-user-list.jsf")
public class TaurusUserListController {

    @Autowired
    private TaurusUserService userService;

    private List<TaurusUserListItem> taurusUserListItems;
    private AccordionPanel activeAccordionPanel;

    @PostConstruct
    public void init() {
        taurusUserListItems = userService.findAllUsers().stream()
                .map( u -> new TaurusUserListItem( u, userService.createPickListOfPermissionsForUser( u.getTaurusId() ) ) )
                .collect( Collectors.toList() );
    }

    public List<TaurusUser> getTaurusUsers() {
        return taurusUserListItems.stream()
                .map( p -> p.getUser() )
                .collect( Collectors.toList() );
    }

    public void onTabChange(TabChangeEvent event) {
        activeAccordionPanel = (AccordionPanel)event.getTab().getParent();
    }

    public DualListModel<TaurusPermission> getSelectedUserPermissions() {
        return taurusUserListItems.get(getActiveTabIndex()).getPermissions();
    }

    public void setSelectedUserPermissions(DualListModel<TaurusPermission> selectedUserPermissions) {
        this.taurusUserListItems.get(getActiveTabIndex()).setPermissions(selectedUserPermissions);
    }

    public void onPickListTransfer(TransferEvent event) {
        userService.updateAndSaveUserPermissionList((List<TaurusPermission>)event.getItems(), taurusUserListItems.get(getActiveTabIndex()).getUser(), event.isAdd());
    }

    public void markUserAuditComplete(boolean auditComplete) {
        TaurusUserListItem user = taurusUserListItems.get( getActiveTabIndex() );
        user.setUser( userService.markUserAuditComplete( user.getUser(), auditComplete ) );
    }

    public int getActiveTabIndex() {
        return null != activeAccordionPanel ? Integer.valueOf(activeAccordionPanel.getActiveIndex()) : 0;
    }

    public String getActiveTabIndexString() {
        return null != activeAccordionPanel ? activeAccordionPanel.getActiveIndex() : "0";
    }
}
