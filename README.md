[![Build Status](https://travis-ci.org/ZhangColin/modern.svg?branch=master)](https://travis-ci.org/ZhangColin/modern) [![Quality Gate](https://sonarqube.com/api/badges/gate?key=Cartisan:modern)](https://sonarqube.com/dashboard?id=Cartisan%3Amodern) [![Issues](https://img.shields.io/sonar/http/sonarqube.com/Cartisan:modern/violations.svg)](https://sonarqube.com/component_issues/index?id=Cartisan%3Amodern#resolved=false)

[![Overall Coverage](https://img.shields.io/sonar/http/sonarqube.com/Cartisan:modern/overall_coverage.svg)](https://sonarqube.com/component_measures/domain/Coverage?id=Cartisan%3Amodern) [![UT Coverage](https://img.shields.io/sonar/http/sonarqube.com/Cartisan:modern/coverage.svg)](https://sonarqube.com/component_measures/domain/Coverage?id=Cartisan%3Amodern) [![Function Complexity](https://img.shields.io/sonar/http/sonarqube.com/Cartisan:modern/function_complexity.svg)](https://sonarqube.com/component_measures/domain/Complexity?id=Cartisan%3Amodern) [![Class Complexity](https://img.shields.io/sonar/http/sonarqube.com/Cartisan:modern/class_complexity.svg)](https://sonarqube.com/component_measures/domain/Complexity?id=Cartisan%3Amodern) [![Duplications](https://img.shields.io/sonar/http/sonarqube.com/Cartisan:modern/duplicated_blocks.svg)](https://sonarqube.com/component_measures/domain/Duplications?id=Cartisan%3Amodern) [![Code Smells](https://img.shields.io/sonar/http/sonarqube.com/Cartisan:modern/code_smells.svg)](https://sonarqube.com/component_measures/domain/Maintainability?id=Cartisan%3Amodern)


# 缘起
16年9月底，参加了姚若舟老师的《现代网页开发》课程，受益良多，把过去我自己学习的东西都串联了起来。

这个项目是个人的练习项目，主要目的如下：
* 1、跟着《现代网页开发课程》的示例项目 [bbuddy](https://github.com/nerds-odd-e/bbuddy) 仔细地做一遍，再深理解，并且bbuddy本身也一直在不断更新，可以从中学习到更多有用好玩的东西。
* 2、平时自己学习内容的尝试

# Installation
Please install the following tools for this project. The latest version should be fine unless specific version is listed.
* git
* jdk 1.8
* gradle 2.13 (don't use gradle 2.14, 2.14.1 or 3.X)
* mysql
* intellij idea community edition
    * lombok plug-in
* Firefox 46.0 (don't use higher version)
* gulp 3.9.1

# Setup Development Environment
Use git to clone this project into a folder. Then in this folder, run the command below in order.

* Create a default mysql dba user 'mysql -u -root -p < src/main/sql/create_default_dba.sql'
* Start Web Application `gradle run` (on port 8090)
* Run All Tests `gradle ci` (on port 8080)
* if you want to start it on a port rather than 8090 (e.g. 8070), please do 'gradle run -Pport=8070'

# Setup Intellij Development Environment
* Start Web Application. Run com.cartisan.modern.Application as a Spring Boot application by using "dev" as the active profile
* Run Unit Tests. Run those unit tests as normal. The only limitation is that you can't run those "Nested" tests together with other non "Nested" tests
* Run Acceptance Test (cucumber). In feature file, you can select the feature or one scenario and then run it. In the configuration, you need to set the active profile as "test" by adding `SPRING_PROFILES_ACTIVE=test` to the environment variables.
* [Spring Boot Developer Tools](http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html) is used so that you can hot load any modified code, template file and resource file without restart the application. Please follow the steps below to enable this hot load feature.
    * Start the application in Intellij as described in the first item. Don't start it with gradle in command line.
    * Edit any code or file, and make the project. Then, the change will be reloaded automatically.
    * You can install a Chrome extension called [Live Reload](https://chrome.goole.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei?hl=en) so that the tab (in which this application is opened) will be refreshed automatically.

# Setup Development Environment using VM
Install the following tools first.

* [VirtualBox](https://www.virtualbox.org/)
* [Vagrant](https://www.vagrantup.com/)
* [Ansible](https://www.ansible.com/)

Then run the command: 'vagrant up'

# If you are using MacOS, and have [Homebrew](http://brew.sh) installed
Run the below commands:

    brew cask install virtualbox vagrant
    brew install ansible
    vagrant up