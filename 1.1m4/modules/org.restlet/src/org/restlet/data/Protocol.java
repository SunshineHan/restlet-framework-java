/*
 * Copyright 2005-2008 Noelios Consulting.
 * 
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the "License"). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * http://www.opensource.org/licenses/cddl1.txt See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL HEADER in each file and
 * include the License file at http://www.opensource.org/licenses/cddl1.txt If
 * applicable, add the following below this CDDL HEADER, with the fields
 * enclosed by brackets "[]" replaced with your own identifying information:
 * Portions Copyright [yyyy] [name of copyright owner]
 */

package org.restlet.data;

/**
 * Protocol used by client and server connectors. Connectors enable the
 * communication between components by implementing standard protocols.
 * 
 * @author Jerome Louvel (contact@noelios.com)
 */
public final class Protocol extends Metadata {

    /** Indicates that the port number is undefined. */
    public static final int UNKNOWN_PORT = -1;

    /**
     * AJP 1.3 protocol to communicate with Apache HTTP server or Microsoft IIS.
     */
    public static final Protocol AJP = new Protocol("ajp", "AJP",
            "Apache Jakarta Protocol", 8009);

    /** All protocols wildcard. */
    public static final Protocol ALL = new Protocol("all", "ALL",
            "Wildcard for all protocols", UNKNOWN_PORT);

    /**
     * CLAP (ClassLoader Access Protocol) is a custom scheme to access to
     * representations via classloaders. Example URI:
     * "clap://thread/org/restlet/Restlet.class".<br>
     * <br>
     * In order to work, CLAP requires a client connector provided by the core
     * Restlet engine.
     * 
     * @see org.restlet.data.LocalReference
     */
    public static final Protocol CLAP = new Protocol("clap", "CLAP",
            "Class Loader Access Protocol", UNKNOWN_PORT);

    /**
     * FILE is a standard scheme to access to representations stored in the file
     * system (locally most of the time). Example URI:
     * "file:///D/root/index.html".<br>
     * <br>
     * In order to work, FILE requires a client connector provided by the core
     * Restlet engine.
     * 
     * @see org.restlet.data.LocalReference
     */
    public static final Protocol FILE = new Protocol("file", "FILE",
            "Local File System Protocol", UNKNOWN_PORT);

    /** FTP protocol. */
    public static final Protocol FTP = new Protocol("ftp", "FTP",
            "File Transfer Protocol", 21);

    /** HTTP protocol. */
    public static final Protocol HTTP = new Protocol("http", "HTTP",
            "HyperText Transport Protocol", 80);

    /** HTTPS protocol (via SSL socket). */
    public static final Protocol HTTPS = new Protocol("https", "HTTPS",
            "HyperText Transport Protocol (Secure)", 443);

    /**
     * JAR (Java ARchive) is a common scheme to access to representations inside
     * archive files. Example URI:
     * "jar:http://www.foo.com/bar/baz.jar!/COM/foo/Quux.class".
     * 
     * @see org.restlet.data.LocalReference
     */
    public static final Protocol JAR = new Protocol("jar", "JAR",
            "Java ARchive", UNKNOWN_PORT);

    /** JDBC protocol. */
    public static final Protocol JDBC = new Protocol("jdbc", "JDBC",
            "Java DataBase Connectivity", UNKNOWN_PORT);

    /** POP protocol. */
    public static final Protocol POP = new Protocol("pop", "POP",
            "Post Office Protocol", 110);

    /** POPS protocol (via SSL/TLS socket).. */
    public static final Protocol POPS = new Protocol("pops", "POPS",
            "Post Office Protocol (Secure)", 995);

    /**
     * RIAP (Restlet Internal Access Protocol) is a custom scheme to access
     * representations via internal calls to virtual hosts/components. Example
     * URIs: "riap://component/myAppPath/myResource" and
     * "riap://application/myResource".<br>
     * <br>
     * In order to work, RIAP doesn't requires any client connector and is
     * automatically supported by the Restlet engine.
     * 
     * @see org.restlet.data.LocalReference
     */
    public static final Protocol RIAP = new Protocol("riap", "RIAP",
            "Restlet Internal Access Protocol", UNKNOWN_PORT);

    /** SMTP protocol. */
    public static final Protocol SMTP = new Protocol("smtp", "SMTP",
            "Simple Mail Transfer Protocol", 25);

    /**
     * SMTP with STARTTLS protocol (started with a plain socket).
     * 
     * @deprecated Use the "startTls" parameter on the JavaMail connector
     *             instead.
     */
    @Deprecated
    public static final Protocol SMTP_STARTTLS = new Protocol("smtp",
            "SMTP_STARTTLS",
            "Simple Mail Transfer Protocol (starting a TLS encryption)", 25);

    /** SMTPS protocol (via SSL/TLS socket). */
    public static final Protocol SMTPS = new Protocol("smtps", "SMTPS",
            "Simple Mail Transfer Protocol (Secure)", 465);

    /** Local Web Archive access protocol. */
    public static final Protocol WAR = new Protocol("war", "WAR",
            "Web Archive Access Protocol", UNKNOWN_PORT);

    /**
     * Creates the protocol associated to a URI scheme name. If an existing
     * constant exists then it is returned, otherwise a new instance is created.
     * 
     * @param name
     *                The scheme name.
     * @return The associated protocol.
     */
    public static Protocol valueOf(final String name) {
        Protocol result = null;

        if ((name != null) && !name.equals("")) {
            if (name.equalsIgnoreCase(AJP.getSchemeName()))
                result = AJP;
            else if (name.equalsIgnoreCase(CLAP.getSchemeName()))
                result = CLAP;
            else if (name.equalsIgnoreCase(FILE.getSchemeName()))
                result = FILE;
            else if (name.equalsIgnoreCase(FTP.getSchemeName()))
                result = FTP;
            else if (name.equalsIgnoreCase(HTTP.getSchemeName()))
                result = HTTP;
            else if (name.equalsIgnoreCase(HTTPS.getSchemeName()))
                result = HTTPS;
            else if (name.equalsIgnoreCase(JAR.getSchemeName()))
                result = JAR;
            else if (name.equalsIgnoreCase(JDBC.getSchemeName()))
                result = JDBC;
            else if (name.equalsIgnoreCase(POP.getSchemeName()))
                result = POP;
            else if (name.equalsIgnoreCase(POPS.getSchemeName()))
                result = POPS;
            else if (name.equalsIgnoreCase(RIAP.getSchemeName()))
                result = RIAP;
            else if (name.equalsIgnoreCase(SMTP.getSchemeName()))
                result = SMTP;
            else if (name.equalsIgnoreCase(SMTP_STARTTLS.getSchemeName()))
                result = SMTP_STARTTLS;
            else if (name.equalsIgnoreCase(SMTPS.getSchemeName()))
                result = SMTPS;
            else if (name.equalsIgnoreCase(WAR.getSchemeName()))
                result = WAR;
            else
                result = new Protocol(name);
        }

        return result;
    }

    /** The default port if known or -1. */
    private volatile int defaultPort;

    /** The scheme name. */
    private volatile String schemeName;

    /**
     * Constructor.
     * 
     * @param schemeName
     *                The scheme name.
     */
    public Protocol(final String schemeName) {
        this(schemeName, schemeName.toUpperCase(), schemeName.toUpperCase()
                + " Protocol", UNKNOWN_PORT);
    }

    /**
     * Constructor.
     * 
     * @param schemeName
     *                The scheme name.
     * @param name
     *                The unique name.
     * @param description
     *                The description.
     * @param defaultPort
     *                The default port.
     */
    public Protocol(final String schemeName, final String name,
            final String description, int defaultPort) {
        super(name, description);
        this.schemeName = schemeName;
        this.defaultPort = defaultPort;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object object) {
        return (object instanceof Protocol)
                && getName().equalsIgnoreCase(((Protocol) object).getName());
    }

    /**
     * Returns the default port number.
     * 
     * @return The default port number.
     */
    public int getDefaultPort() {
        return this.defaultPort;
    }

    /**
     * Returns the URI scheme name.
     * 
     * @return The URI scheme name.
     */
    public String getSchemeName() {
        return this.schemeName;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return (getName() == null) ? 0 : getName().toLowerCase().hashCode();
    }
}