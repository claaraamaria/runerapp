

package org.runnerup.export.oauth2client;

public interface OAuth2Server {
    /**
     * Used as title when opening authorization dialog
     * 
     * @return
     */
    String getName();

    String getClientId();

    String getRedirectUri();

    String getClientSecret();

    String getAuthUrl();

    String getAuthExtra();

    String getTokenUrl();

    @SuppressWarnings("SameReturnValue")
    String getRevokeUrl();
}
