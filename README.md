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

* [Supervisor](localhost:9001)
* [Access control reporting system](localhost:9999)
* [Reporting api instanse](localhost:9999/api)
 * [Reporting api instanse #1](localhost:9901/manage/health)
 * [Reporting api instanse #2](localhost:9902/manage/health)