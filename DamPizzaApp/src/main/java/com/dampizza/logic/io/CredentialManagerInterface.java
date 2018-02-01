/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.io;

import com.dampizza.model.entity.CredentialEntity;

/**
 *
 * @author Carlos
 */
public interface CredentialManagerInterface {
    
    public CredentialEntity getCredential(long id);
    public Integer createCredential();
    public Integer updateCredential();
    public Integer deleteCredential();
}
