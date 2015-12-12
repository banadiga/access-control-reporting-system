Access control reporting system
====

# Set up dev env

* To start application host:

```bash
cd ops/application-host
vagrant up
```

**known problems**
* Re-provision will be failed

# Setup dev evn

On UNIX-like operating systems, the following Bash shell command will enable the Daemon for the current user:

```bash
touch ~/.gradle/gradle.properties && echo "org.gradle.daemon=true" >> ~/.gradle/gradle.properties
```

# Build

```bash
gradle clean build
```

# Available links

To get status of application go to 
* [Supervisor](http://localhost:9001/)

To run data import
* [Run import thru Supervisor](http://localhost:9001/index.html?processname=data%3Aimport&action=start)

To run data analytic
* [Run analytic thru Supervisor](http://localhost:9001/index.html?processname=data%3Aanalytic&action=start)

To show report
* [Go to access control Dashboard](http://localhost:9999/)

Redis run at localhost:6379
 * [Redis commander](http://localhost:8081/)

API links:
* [Reporting api instance](http://localhost:9999/api/)
 * [Reporting api instance #1](http://localhost:9901/manage/health)
 * [Reporting api instance #2](http://localhost:9902/manage/health)
