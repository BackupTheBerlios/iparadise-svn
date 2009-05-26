package com.pdpsoft.web.common;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Sep 4, 2006
 * Time: 2:34:16 PM
 */
public class ErrorCatalogTransferObject {
    private String errorKey;
    private String errorId;
    private String errorDescription;
    private String customerKey;
    private String technicalKey;
    private String destination;

    public ErrorCatalogTransferObject() {
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public String getTechnicalKey() {
        return technicalKey;
    }

    public void setTechnicalKey(String technicalKey) {
        this.technicalKey = technicalKey;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
