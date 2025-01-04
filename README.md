obd-java-api
============

OBD-II Java API

## NOTICE

**This project is no longer fully maintained.**

*I have forked the project and will work on it as time permits, but it is not fully supported. I am using it for my own private applications.*
Roque

[![Build status](https://circleci.com/gh/pires/obd-java-api.svg?style=svg)](https://circleci.com/gh/pires/obd-java-api)

## Important resources

Before opening an issue or using this library, please take a look at the following resources:

* [Understanding OBD](https://www.elmelectronics.com/help/obd/tips/#UnderstandingOBD)
* [The ELM327](https://www.elmelectronics.com/help/obd/tips/#327_Commands)

## Build ##

### Requisites ###

* JDK 1.8 or newer
* Maven 3.3.9 or newer

### Compile, package and install locally ###

```
mvn clean install
```

## Usage ##

Run git clone in root folder of your application to get the project locally

```
cd /path/to/your/project
git clone git@github.com:roq3/obd-java-api.git
```

### Gradle ###
```
dependencies {
    implementation project(':obd-java-api')
}
```

### Example ###

After pairing and establishing Bluetooth connection to your ELM327 device..
```
...
// retrieve Bluetooth socket
socket = ...; // specific to the VM you're using (Java, Android, etc.)

// execute commands
try {
  new EchoOffCommand().run(socket.getInputStream(), socket.getOutputStream());
  new LineFeedOffCommand().run(socket.getInputStream(), socket.getOutputStream());
  new TimeoutCommand(125).run(socket.getInputStream(), socket.getOutputStream());
  new SelectProtocolCommand(ObdProtocols.AUTO).run(socket.getInputStream(), socket.getOutputStream());
  new AmbientAirTemperatureCommand().run(socket.getInputStream(), socket.getOutputStream());
} catch (Exception e) {
  // handle errors
}
```

## Troubleshooting ##

As *@dembol* noted:

Have you checked your ELM327 adapter with Torque or Scanmaster to see if it works with your car? Maybe the problem is with your device?

Popular OBD diagnostic tools reset state and disable echo, spaces etc before protocol selection. Download some ELM327 terminal for android and try following commands in order:
```
ATD
ATZ
AT E0
AT L0
AT S0
AT H0
AT SP 0
```
