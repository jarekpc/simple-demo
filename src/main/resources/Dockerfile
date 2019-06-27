#FROM jboss/keycloak
#ADD path/to/cli/files /opt/jboss/keycloak/path/to/cli/files
#RUN cd /opt/jboss/keycloak && bin/jboss-cli.sh --file=standalone-ha-configuration.cli && rm -rf /opt/jboss/keycloak/standalone/configuration/standalone_xml_history
#CMD ["-b", "0.0.0.0", "--server-config", "standalone-ha.xml"]
FROM jboss/keycloak:latest
ADD TCPPING.cli /opt/jboss/tools/cli/jgroups/discovery/
ADD JDBC_PING.cli /opt/jboss/tools/cli/jgroups/discovery/
CMD ["-b", "0.0.0.0"]
