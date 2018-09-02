package com.twigs.admin.user;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.List;

@FacesConverter(value = "permissionPickListConverter")
public class permissionPickListConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        DualListModel<TaurusPermission> pList = (DualListModel)((PickList) component).getValue();
        String[] args = value.split("-");
        TaurusPermission permission = findPermissionInDualList(pList, Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        return permission;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        String s = "";
        if (value instanceof TaurusPermission) {
            TaurusPermission p = (TaurusPermission) value;
            s = String.format("%s-%s", p.getId(), p.getTaurusId());
        }
        return s;
    }

    private TaurusPermission findPermissionInDualList(DualListModel<TaurusPermission> dualList, int objId, int taurusId) {
        TaurusPermission permission = null;
        permission = findPermissionInList(dualList.getSource(), objId, taurusId);
        if(null == permission) {
            permission = findPermissionInList(dualList.getTarget(), objId, taurusId);
        }
        return permission;
    }

    private TaurusPermission findPermissionInList(List<TaurusPermission> list, int objId, int taurusId) {
        for (TaurusPermission p : list) {
            if (p.getId() == objId && p.getTaurusId() == taurusId) {
                return p;
            }
        }
        return null;
    }

}
