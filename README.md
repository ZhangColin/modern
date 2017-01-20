16年10月份，参加了姚若舟老师的《现代网页开发》课程，受益良多，把过去我自己学习的东西都串联了起来。

这个项目是个人的练习项目，主要目的如下：
>1、跟着《现代网页开发课程》的示例项目 [bbuddy](https://github.com/nerds-odd-e/bbuddy) 仔细地做一遍，再深理解，并且bbuddy本身也一直在不断更新，可以从中学习到更多有用好玩的东西。
>2、平时自己学习内容的尝试

[![Build Status](https://travis-ci.org/ZhangColin/modern.svg?branch=master)](https://travis-ci.org/ZhangColin/modern)

# Installation
Please install the following tools for this project. The latest version should be fine unless specific version is listed.
* git
* jdk 1.8
* gradle
* mysql
* intellij idea community edition
    * lombok plug-in
* Firefox 46.0 (don't use higher version)

# Setup Development Environment
Use git to clone this project into a folder. Then in this folder, run the command below in order.

* Run Tests `gradle clean check cucumber`
* Start Web Application `gradle clean bootRunByPort -Pport=8090`

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