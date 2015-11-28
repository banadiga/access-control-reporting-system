Access control reporting system
====

# Setup dev evn

On UNIX-like operating systems, the following Bash shell command will enable the Daemon for the current user:

```bash
touch ~/.gradle/gradle.properties && echo "org.gradle.daemon=true" >> ~/.gradle/gradle.properties
```

# Build

```bash
gradle clean build
```

# Set up env

* To start application host:

```bash
cd ops/application-host
vagrant up
```

# Available links

To start application go to 
* [Supervisor](http://localhost:9001/)

Other links:
* [Access control reporting system](http://localhost:9999/)
* [Reporting api instanse](http://localhost:9999/api/)
 * [Reporting api instanse #1](http://localhost:9901/manage/health)
 * [Reporting api instanse #2](http://localhost:9902/manage/health)