package com.pdpsoft.security;

import com.pdpsoft.business.PersistenceManager;
import com.pdpsoft.business.PersistenceManagerFactory;
import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;

import javax.crypto.SealedObject;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 30, 2009
 * Time: 3:57:53 PM
 */
public class ParadiseAuthenticationAuthorizationService implements G16CommonBusinessCommand {
    final static Log LOG = LogFactory.getLog(ParadiseAuthenticationAuthorizationService.class);
    PersistenceManager persistenceManager = PersistenceManagerFactory.getDefault();

    G16CommonBusinessCommand authentication;
    G16CommonBusinessCommand authorization;

    /**
     * @param o is an instance of Map<Integer, SystemUserEntity>, which by SystemUserEntity.id we are
     *          going to fetch all his related actions and his information as SystemUserEntity. The
     *          argument should have the following attributes: username and password
     * @return List of AuthorizationCustomEntity
     * @throws G16CommonBusinessException
     * @throws HibernateEXC
     */
    @SuppressWarnings({"unchecked"})
    public Object execute(Object o) throws G16CommonBusinessException, HibernateEXC {
        Map<Integer, Object> arg = (Map<Integer, Object>) o;
        Map<Integer, SystemUserEntity> param = new HashMap<Integer, SystemUserEntity>();
        param.put(0, (SystemUserEntity) SecurityAlgorithm.unseal((SealedObject) arg.get(0)));

        SystemUserEntity systemUserEntity = authenticate(param);
        param.put(0, systemUserEntity);
        return new AuthorizationCustomEntity(systemUserEntity, (List<SystemActionEntity>) authorization.execute(param));
    }

    /**
     * @param o is an instance of Map<Integer, SystemUserEntity>, which by SystemUserEntity.id we are
     *          going to fetch all his related actions and his information as SystemUserEntity. The
     *          argument should have the following attributes: username and password
     * @return Instance of SystemUserEntity which is meant that the authenticates has been
     *         done and the specified user has been loaded
     * @throws SecurityException when authentication fails
     */
    @SuppressWarnings({"unchecked"})
    private SystemUserEntity authenticate(Map<Integer, SystemUserEntity> map) throws G16CommonBusinessException, HibernateEXC {
        List<SystemUserEntity> userEntityList = persistenceManager.findByCondition(SystemUserEntity.class, (Criterion) authentication.execute(map));
        if (userEntityList.size() != 1)
            throw new G16CommonBusinessException("Authentication failed on username: ".concat(map.get(0).getUserName()), -1);
        return userEntityList.get(0);
    }

    // Setters and Getters

    public G16CommonBusinessCommand getAuthentication() {
        return authentication;
    }

    public void setAuthentication(G16CommonBusinessCommand authentication) {
        this.authentication = authentication;
    }

    public G16CommonBusinessCommand getAuthorization() {
        return authorization;
    }

    public void setAuthorization(G16CommonBusinessCommand authorization) {
        this.authorization = authorization;
    }
}
