16年10月份，参加了姚若舟老师的《现代网页开发》课程，受益良多，把过去我自己学习的东西都串联了起来。

这个项目是个人的练习项目，主要目的如下：
1、跟着《现代网页开发课程》的示例项目bbuddy（https://github.com/nerds-odd-e/bbuddy）仔细地做一遍，再深理解，并且bbuddy本身也一直在不断更新，可以从中学习到更多有用好玩的东西。
2、平时自己学习内容的尝试

[![Build Status](https://travis-ci.org/ZhangColin/modern.svg?branch=master)](https://travis-ci.org/ZhangColin/modern)

# Installation
Please install the following tools for this project. The latest version should be fine unless specific version is listed.
>* git
>* jdk 1.8
>* gradle
>* mysql
>* intellij idea community edition
>>* lombok plug-in
>* Firefox

# Setup Development Environment
Use git to clone this project into a folder. Then in this folder, run the command below in order.
>1. Setup Database
`mysql -u root -p < db_migrations/initialize_users_and_roles.sql`
>2. Run Tests
`gradle check cucumber`
>3. Start Web Application
`gradle bootRunByPort -Pport=8090`