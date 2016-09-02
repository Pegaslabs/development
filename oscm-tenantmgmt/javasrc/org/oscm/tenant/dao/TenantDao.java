/*******************************************************************************
 *
 *  Copyright FUJITSU LIMITED 2016
 *
 *  Creation Date: 30.08.2016
 *
 *******************************************************************************/
package org.oscm.tenant.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.oscm.converter.ParameterizedTypes;
import org.oscm.dataservice.local.DataService;
import org.oscm.domobjects.Tenant;
import org.oscm.internal.types.exception.ObjectNotFoundException;

@Stateless
@LocalBean
public class TenantDao {

    @EJB
    DataService dataManager;

    public List<Tenant> getAllTenants() {
        Query query = dataManager.createNamedQuery("Tenant.getAll");
        return ParameterizedTypes.list(query.getResultList(), Tenant.class);
    }

    public Tenant getTenantByTenantId(String tenantId) throws ObjectNotFoundException {
        Tenant tenant = new Tenant();
        tenant.getDataContainer().setTenantId(tenantId);
        return (Tenant) dataManager.getReferenceByBusinessKey(tenant);
    }
    
    public List<Tenant> getTenantsByIdPattern(String tenantIdPattern) {
        Query query = dataManager.createNamedQuery("Tenant.getTenantsByIdPattern");
        query.setParameter("tenantIdPattern", tenantIdPattern);
        return ParameterizedTypes.list(query.getResultList(), Tenant.class);
    }
}
