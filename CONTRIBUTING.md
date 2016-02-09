CONTRIBUTING
============

Set up env
----------------

Setup env should pass

Run

```bash
cd ops/application-host
vagrant up
```

Build project
----------------

Build should pass

Run bash in basedir after setup env
 
```bash
gradle clean build
```

Code Styles check
----------------

Java code styles check should pass

Run bash in basedir after build run
 
```bash
gradle checkstyle
```

Report should be without errors.
