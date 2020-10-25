# webdriver

testng-pasterbin-test-task1.xml
task I Can Win
ри выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка и концепцию Page Object. Автоматизировать следующий сценарий:

Открыть https://pastebin.com или аналогичный сервис в любом браузере
Создать New Paste со следующими деталями:
* Код: "Hello from WebDriver"

* Paste Expiration: "10 Minutes"

* Paste Name / Title: "helloweb"


testng-pasterbin-test-task2.xml
task Bring It On
При выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка и концепцию Page Object. Автоматизировать следующий сценарий:

Открыть https://pastebin.com  или аналогичный сервис в любом браузере
Создать New Paste со следующими деталями:
* Код:

git config --global user.name  "New Sheriff in Town"
git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
git push origin master --force
* Syntax Highlighting: "Bash"

* Paste Expiration: "10 Minutes"

* Paste Name / Title: "how to gain dominance among developers"

3. Сохранить paste и проверить следующее:

* Заголовок страницы браузера соответствует Paste Name / Title

* Синтаксис подвечен для bash

* Проверить что код соответствует введенному в пункте 2


testng-all-google-calculator.xml
Содержит тест:
1. Открыть https://cloud.google.com/ 
2. Нажав кнопку поиска по порталу вверху страницы, ввести в поле поиска"Google Cloud Platform Pricing Calculator"
3. Запустить поиск, нажав кнопку поиска.
4. В результатах поиска кликнуть "Google Cloud Platform Pricing Calculator" и перейти на страницу калькулятора.



testng-all-google-calculator.xml
Hardcore  + задание с курса FrameWork 
При выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка и концепцию Page Object. Автоматизировать следующий сценарий:

1. Открыть https://cloud.google.com/ 
2. Нажав кнопку поиска по порталу вверху страницы, ввести в поле поиска"Google Cloud Platform Pricing Calculator"
3. Запустить поиск, нажав кнопку поиска.
4. В результатах поиска кликнуть "Google Cloud Platform Pricing Calculator" и перейти на страницу калькулятора.
5. Активировать раздел COMPUTE ENGINE вверху страницы
6. Заполнить форму следующими данными:
    * Number of instances: 4
    * What are these instances for?: оставить пустым
    * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS
    * VM Class: Regular
    * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB)
    * Выбрать Add GPUs
        * Number of GPUs: 1
        * GPU type: NVIDIA Tesla V100
    * Local SSD: 2x375 Gb
    * Datacenter location: Frankfurt (europe-west3)
    * Commited usage: 1 Year
7. Нажать Add to Estimate
8. Выбрать пункт EMAIL ESTIMATE
9. В новой вкладке открыть https://10minutemail.com или аналогичный сервис для генерации временных email'ов
10. Скопировать почтовый адрес сгенерированный в 10minutemail
11. Вернуться в калькулятор, в поле Email ввести адрес из предыдущего пункта
12. Нажать SEND EMAIL
13. Дождаться письма с рассчетом стоимости и проверить что Total Estimated Monthly Cost в письме совпадает с тем, что отображается в калькуляторе

FrameWork
Практическое задание 
Задача - построить фреймворк для автоматизации Hardcore  задания из курса WebDriver.

Что должно быть в итоговом фреймворке:

webdrivermanager для управления коннекторам к браузерам
Page Object / Page Factory для абстракций страниц
Модель для бизнес-объектов необходимых сущностей
properties файлы с тестовыми данным для разных окружений (как минимум 2)
xml suites для smoke тестов и всех тестов
При падении теста должен быть сделан скриншот с датой и временем
Фреймворк должен иметь возможность запуска с Jenkins и параметризацией браузера, тест suite, environment. Результаты тестов должны быть на графике джобы, скриншоты должны быть заархивированны как артефакты

