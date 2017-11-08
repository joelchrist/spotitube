# Spotitube

### Running instructions
You can run this implementation of the Spotitube backend locally by running `mvn package` followed by a `docker-compose up`

This wil start up two docker containers, a TomEE and a MySQL container.
The TomEE container will be accessible over HTTP on port 8080. There is also a debug port open on port 9000.
The spotitube WAR file will be mounted in the container for easy development. After starting the containers a simple `mvn package` will rebuild your application and TomEE will detect the changes.

### Database
The scripts under /init-scripts/ will create the Spotitube database and insert a small amount of test data.
If you make changes to these scripts and want them to be executed, you should do the following things:
- Stop the running containers by running `docker-compose down` of quitting the running `docker-compose up` process.
- Remove the /mysql_data/ directory by running `rm -rf mysql_data`
- Restart the containers by running `docker-compose up`

# Documentation
Documentation can be found under the /doc/ directory. This directory contains the finished documentation document, as well as all diagrams in image format.
There is also a .vpp file, which can be opened in Visual Paradigm. Lastly there is a database model available in .mwb format. This can be opened in MySQLWorkbench.