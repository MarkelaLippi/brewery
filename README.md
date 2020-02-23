# Brewery

Приложение для взаимодействия покупателей пива с сотрудниками отдела продаж пивоварни, а также взаимодействия 
сотрудников отдела продаж с сотрудниками цеха по производству пива.

##Customer (Покупатель/заказчик)
Пользователь, желающий ознакомиться с предлагаемым ассортиментом пива и, в дальнейшем, сделать заказ на производство 
партии пива.

Поля:
* Email
* ФИО
* Телефон

Связи:
* Заказ (“Order”).
  Оформляется покупателем.
* Отзыв (“Review”).
  Формируется покупателем в качестве оценки работы пивоварни. 

##Salesman (Работник отдела продаж)
Пользователь, который ответственен за взаимодействие с:
* заказчиком - по вопросам оформления заказа;
* работником пивоваренного цеха – по вопросам оформления требования о производстве партии пива. 

Поля:
* Email
* ФИО
* Должность
* Отдел

Связи:
* Заказ (“Order”). 
  Обрабатывается работником отдела продаж.
* Отзыв (“Review”).
  Анализируется работником отдела продаж.
* Требование (“Produce request”).
  Оформляется работником отдела продаж.
* Пиво(“Product”). 
  Анализируется на предмет наличия в достаточном количестве для отгрузки покупателю.  

##Brewer (Работник цеха пивоварения/пивовар)
Пользователь, ответственный за взаимодействие с:
* отделом продаж по вопросам выполнения требования о производстве партии пива;

Поля:
* Email
* ФИО
* Должность
* Отдел

Связи:
* Требование (“Produce request”). 
  Анализируется пивоваром и принимается к исполнению.
* Рецепт (“Recipe”). 
  Анализируется пивоваром для определения необходимых компонентов для производства партии пива.
* Пиво/Ингредиент (“Product”). 
  Анализируется пивоваром на предмет наличия ингредиентов в достаточном количестве.
  Запасы готового пива пополняются пивоваром по завершении производства очередной партии.

##Order (Заказ)
Заказ на поставку партии пива.

Поля:
* Дата
* ID заказчика
* Список заказываемого пива
* Список отзывов покупателя

Связи:
* Покупатель (“Customer”). 
  Оформляет заказ.
* Работник отдела продаж ("Salesman"). 
  Обрабатывает заказ. Если остатков пива достаточно, то отгружает партию пива. 
  Иначе, оформляет требование к цеху пивоварения на производство необходимого количества пива. 
  
##Review (Отзыв)
Отзыв о продукции, выпускаемой пивоварней, компетентности сотрудника отдела продаж и работе пивоварни в целом.

Поля:
* Дата
* Содержание
* ID заказчика 
* ID заказа

Связи:
* Покупатель (“Customer”). 
  Оставляет отзыв.
* Работник отдела продаж ("Salesman"). 
  Просматривает отзыв клиента и реагирует на него. 
 
##Produce request (Требование)
Требование, направляемое в цех пивоварения, о производстве необходимой партии пива.

Поля:
* Дата
* Наименование/ID пива
* Количество
* Срок производства
* Статус

Связи:
* Работник отдела продаж ("Salesman"). 
  Оформляет требование. 
* Пивовар ("Brewer"). 
  Обрабатывает требование, проверяет наличие ингредиентов и запускает производственный процесс. 

##Product (Пиво/Ингредиент)
Выпускаемая продукция/ингредиент для производства продукции.

Поля:
* Наименование
* Описание
* Цена
* Количество
* Рецепт на изготовление

Связи:
* Работник отдела продаж ("Salesman"). 
  Просматривает наличие и забирает часть для формирования партии для покупателя. 
* Пивовар ("Brewer"). 
  Проверяет наличие необходимых компонентов для производства партии пива.
  По завершени производства вносит произведенную партию пива в базу. 

##Recipe (Рецепт на изготовление пива)
Наименование и количество ингредиентов, необходимые для производства условной единицы пива.

Поля:
* Наименование/ID пива
* Компоненты

Связи:
* Пивовар ("Brewer"). 
  На основании рецепта проверяет какие ингредиенты и в каком количестве ему нужны для производства партии пива, 
  указанной в требовании отдела продаж. 
  
# User Stories

##Так как основной целью предполагаемого к разработке приложения является удовлетворение потребностей клиентов, начнем с функционала покупателя (“Customer”).

##CUST-STEP-1 Как покупатель я хочу зарегистрироваться в системе. Для этого я ввожу логин и пароль. Если такого пользователя не найдено, меня регистрируют в системе

Request:

```
POST /brewery/sign-up
```
```json
{
        "fullName" : "Ivanov Ivan",
        "email" : "Ivanov@gmail.com",
        "phone" : "+375297772255",
        "password" : "12345678"
}
```
Response: ```201 Created```

```
{
        "id" : 1
}
```

##CUST-STEP-2 Как зарегистрированный покупатель я хочу войти в систему. Я ввожу логин и пароль. Если такой пользователь существует, и пароль совпадает я вхожу систему.

Request:

```
POST /brewery/sign-in
```
```json
{
       "email" : " Ivanov@gmail.com ",
       "password" : "12345678"
}
```
Response: ```200 OK```

```
{
        "id" : 1
}
```

##CUST-STEP-3 Как покупатель я хочу ознакомиться с предлагаемым ассортиментом. В результате, получаю список реализуемой продукции

Request:

```
POST /brewery/customer/pricelist
```
```json
[
  {
        "beerId" : 1, 
        "beerName" : "CoolBeer",
        "beerDescription" : "Light, 4.8% alcohol...",
        "price" : 2.5
  }
]
```
Response: ```200 OK```

##CUST-STEP-4 Как покупатель, который изучил ассортимент, я хочу заказать партию пива. Для этого я оформляю заказ.

Request:

```
POST /brewery/customer/orders
```
```json
{
        "date" : "2020-02-05",
        "customerId" : 1,
        "orderItemDtos" : [
                             {"productId" : 1,
                              "amount" : 250.0 }
                          ]
}
```
Response: ```201 Created```

```
{
        "id" : 1
}
```

##CUST-STEP-5 Как покупатель, получивший свой заказ и (не)удовлетворенный работой пивоварни, я хочу оставить отзыв об этом. Для этого я оформляю отзыв.

Request:

```
POST /brewery/customer/reviews
```
```json
{
       "date" : "2020-02-06",
       "content" : "I want to thank...",
       "customerId" : 1,
       "orderId" : 1
}
```
Response: ```201 Created```

```
{
        "id" : 1
}
```

##Далее, переходим к функционалу работника отдела продаж (“Salesman”).

##SALES-STEP-1 Как зарегистрированный пользователь я хочу войти в систему. Я ввожу логин и пароль. Если такой пользователь существует и пароль совпадает, я вхожу систему

Request:

```
POST /brewery/sign-in
```
```json
{
       "email" : "Petrov@gmail.com ",
       "password" : "87654321"
}
```
Response: ```200 OK```

```
{
        "id" : 1
}
```

##SALES-STEP-2 Как работник отдела продаж я хочу ознакомиться со списком, поступивших заказов от покупателей. В результате, получаю список заказов.

Request:

```
GET brewery/sales/orders
```
Response: ```200 OK```
```json
[
  {
       "id" : 1, 
       "date" : "05.02.2020",
       "customerId" : 1,
       "orderItemDtos" : [
                           {"productId" : 1,
                            "amount" : 250.0 }
                         ]
  }
]
```

##SALES-STEP-3 Как работник отдела продаж я хочу ознакомиться с имеющимися в наличии и готовыми к отгрузке покупателям остатками пива. В результате, получаю список готового к отгрузке покупателям пива.

Request:

```
GET /brewery/sales/products?type=beer
```
Response: ```200 OK```
```json
[
  {
       "id" : 1,
       "name" : "CoolBeer",
       "description" : "Light, 4.8% alcohol...",
       "price" : 2.5,
       "amount" : 500.0,
       "unit" : "LITRE"
  }
]
```

##SALES-STEP-4 Как работник отдела продаж я сопоставляю заказы покупателей с готовыми к отгрузке остатками пива. Если пива в наличии недостаточно, я оформляю требование на производство пива к цеху пивоварения. 

Request:

```
POST /brewery/sales/requests
```
```json
{
       "date" : "2020-02-05",
       "term" : "2020-02-10",
       "status" : "NEW",
       "produceRequestItemDtos" : [
                                    {"productId" : 1,
                                     "amount" : 150 }
                                  ]
}
```
Response: ```201 Created```

```
{
        "id" : 1
}
```

##Далее, переходим к функционалу работника цеха пивоварения (“Brewer”).

##BREWER-STEP-1 Как зарегистрированный пользователь я хочу войти в систему. Я ввожу логин и пароль. Если такой пользователь существует и пароль совпадает, я вхожу систему

Request:

```
POST /brewery/sign-in
```
```json
{
       "email" : "Sidorov@gmail.com",
       "password" : "11223344"
}
```
Response: ```200 OK```

```
{
        "id" : 1
}
```

##BREWER -STEP-2 Как работник цеха пивоварения я хочу ознакомиться со списком новых требований, поступивших от отдела продаж. В результате, получаю список новых требований о производстве пива.

Request:

```
GET /brewery/brewer/requests/?status=new"
```
Response: ```200 OK```
```json
[
  {
       "date" : "2020-02-05",
       "term" : "2020-02-10",
       "status" : "NEW",
       "produceRequestItemDtos" : [
                                    {"productId" : 1,
                                      "amount" : 150 }
                                  ]
  }
]
```

##BREWER -STEP-3 Как работник цеха пивоварения, получив список новых требований от отдела продаж, я выбираю одно для дальнейшей обработки. Для этого я получаю его.

Request:

```
GET /brewery/brewer/requests/1"
```
Response: ```200 OK```
```json
{
       "date" : "2020-02-05",
       "term" : "2020-02-10",
       "status" : "NEW",
       "produceRequestItemDtos" : [
                                    {"productId" : 1,
                                      "amount" : 350.0 }
                                  ]
}
```
##BREWER -STEP-4 Как работник цеха пивоварения, изучив новое требование от отдела продаж, я принимаю его в производство. Для этого я меняю его статус на "In process".

Request:

```
PATCH /brewery/brewer/requests/1
```
```json
{
       "status" : "In_progress"
}
```
Response: ```200 OK```

```
{
        "id" : 1
}
```

##BREWER -STEP-5 Как работник цеха пивоварения я хочу ознакомиться с рецептом приготовления пива из поступившего требования. Для этого я получаю рецепт приготовления данного вида пива.

Request:

```
GET /brewery/brewer/recipes/1
```
Response: ```200 OK```
```json
{
       "id" : 1,
       "productId" : 1,
       "recipeItemDtos" : [
                            {"productId" : 2,
                             "amount" : 3.0 }
                          ]
}
```

##BREWER -STEP-6 Как работник цеха пивоварения, ознакомившись с рецептом, я хочу проверить, есть ли в наличии необходимые ингредиенты для производства пива данного вида. Для этого я получаю количество необходимого ингредиента, имеющееся сейчас в наличии.

Request:

```
GET /brewery/brewer/ingredients/1
```
Response: ```200 OK```
```json
{
       "id" : 1,
	   "name" : "Water",
	   "amount" : 100.0,
       "unit" : "Litre"
}
```

##BREWER -STEP-7 Как работник цеха пивоварения, удостоверившись в наличии необходимых ингредиентов, я запускаю производственный процесс. После того, как партия пива будет сварена, я отражаю поступление готового пива, изменяя его остатки в базе.

Request:

```
PATCH /brewery/brewer/beers/1
```
```json
{
       "amount" : 2740
}
```
Response: ```200 OK```

```
{
        "id" : 1
}
```

##BREWER -STEP-8 Как работник цеха пивоварения, я уведомляю отдел продаж о том, что их требование выполнено. Для этого я меняю его статус на «Completed».

Request:

```
PATCH /brewery/brewer/requests/1
```
```json
{
       "status" : "Completed"
}
```
Response: ```200 OK```

```
{
        "id" : 1
}
```

























