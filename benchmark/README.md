# dm benchmarks
The projects in this folder provide a simple registration, wiring and service invocation benchmark for different dependency managers on different OSGi frameworks. The dependency managers in the benchmark are

* Felix SCR (declarative services)
* Eclipse Gemini (Blueprint)
* Apache Felix Dependency Manager
* Apache Felix iPojo

The tested OSGi frameworks are: 

* Apache Felix
* Eclipse Equinox
* Knoplerfish

## The case
The case as well as the results are on the [Ultimate Dependency Manager Shootout](http://www.slideshare.net/mfrancis/the-ultimate-dependency-manager-shoot-out-x-uiterlinden-s-mak) slides on slideshare.


## Getting started
The benchmark projects have been created using Eclipse Kelper sr2 with the bndtools plugin, version 2.3.0.RC2. This plugin can be installed from the Eclipse marketplace.

After the IDE has been setup, clone this git project and import the projects in the 'benchmark' folder into a new  Eclipse workspace.

### Producers
Producer projects have been created for automatically registering a huge amount of services using:

* plain OSGi service registration (producer.osgi)
* Apache Felix DependencyManager (producer.dm)
* Apache Felix iPojo (producer.ipojo)

These tests can be run using the *.bndrun files in these projects. A .bndrun file exists for each of the OSGi frameworks mentioned.

The results are printed to the console. The output looks like following:

	Starting sensors bundle.
	Read 12 provinces with 463860 postal codes.
	Event: Start registering Sensor services []
	100000;1608;1608;Registered services.
	200000;666;2274;Registered services.
	300000;694;2968;Registered services.
	400000;624;3592;Registered services.
	392;3984;Finished registering 463860 Sensor services

The format is as follows: `<amount>;<split time>;<total time>;<message>`
In this case registering 463860 services took 3984 milliseconds.

### Consumers
Consumer projects have been created for publishing a service for which the component has a dependency to 19021 sensor services.

The consumer projects also contain *.bndrun files to execute the tests.

### Iterating tests

For both Apache Felix Dependency Manager and iPojo iterating tests are available, these test run in a loop, starting with publishing 50 services and incrementing with 50 on each iteration. During each of these iterations a client is published which has 1 dependency to a sensor service. Goal of these tests is to check whether the amount of services in the service registry impacts time needed to inject this single dependency. Obviously the tests can easily be modified to inject multiple services instead of just one.
