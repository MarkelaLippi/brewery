## CUST-STEP-1 Как покупатель я хочу зарегистрироваться в системе. Для этого я ввожу логин и пароль. Если такой покупатель не был ранее зарегистрирован, меня регистрируют в системе

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
        "token" : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJdmFub3ZAZ21haWwuY29tIiwiZXhwIjoxNTgyOTUyMzEwLCJpYXQiOjE1ODI5MTYzMTB9.4L-u5G5Iwwr8SJkH7y8iPO83gJ7o_KtGd3hAa-4jvp4
}
```

## CUST-STEP-2 Как зарегистрированный покупатель я хочу войти в систему. Я ввожу логин и пароль. Если такой пользователь существует, и пароль совпадает я вхожу систему.

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
        "token" : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJdmFub3ZAZ21haWwuY29tIiwiZXhwIjoxNTgyOTUyNzE4LCJpYXQiOjE1ODI5MTY3MTh9.lJUbTDzyA_MbqvZacT5ajT1W3u82O4nMrNBv-M-B1pA
}
```

## CUST-STEP-3 Как покупатель я хочу ознакомиться с предлагаемым ассортиментом. В результате, получаю список реализуемой продукции

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

## CUST-STEP-4 Как покупатель, который изучил ассортимент, я хочу заказать партию пива. Для этого я оформляю заказ.

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

## CUST-STEP-5 Как покупатель, получивший свой заказ и (не)удовлетворенный работой пивоварни, я хочу оставить отзыв об этом. Для этого я оформляю отзыв.

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
## CUST-STEP-6 Как покупатель, написавший отзыв о качестве выполнения заказа, я хочу его подкорректировать. Для этого я изменяю содержание отзыва. 

Request:

```
PATCH /brewery/customer/reviews/1
```
```json
{
       "content" : "I would like to notice..."
}
```
Response: ```200 OK```

```
{
        "id" : 1
}
```

## CUST-STEP-7 Как покупатель, написавший отзыв о качестве выполнения заказа, я хочу его удалить. Для этого я удаляю отзыв. 

Request:

```
DELETE /brewery/customer/reviews/1
```

Response: ```200 OK```

## SALES-STEP-1 Как зарегистрированный пользователь я хочу войти в систему. Я ввожу логин и пароль. Если такой пользователь существует и пароль совпадает, я вхожу систему

Request:

```
POST /brewery/sign-in
```
```json
{
       "email" : "Petrov@gmail.com ",
       "password" : "12345678"
}
```
Response: ```200 OK```

```
{
        "token" : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJdmFub3ZAZ21haWwuY29tIiwiZXhwIjoxNTgyOTUyNzE4LCJpYXQiOjE1ODI5MTY3MTh9.lJUbTDzyA_MbqvZacT5ajT1W3u82O4nMrNBv-M-B1pA
}
```

## SALES-STEP-2 Как работник отдела продаж я хочу ознакомиться со списком, поступивших заказов от покупателей. В результате, получаю список заказов.

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
  },
  {
       "id" : 1, 
       "date" : "05.02.2020",
       "customerId" : 1,
       "orderItemDtos" : [
                           {"productId" : 1,
                            "amount" : 10.0 }
                         ]
  }
]
```

## SALES-STEP-3 Как работник отдела продаж я хочу ознакомиться с имеющимися в наличии и готовыми к отгрузке покупателям остатками пива. В результате, получаю список готового к отгрузке покупателям пива.

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

## SALES-STEP-4 Как работник отдела продаж я сопоставляю заказы покупателей с готовыми к отгрузке остатками пива. Если пива в наличии недостаточно, я оформляю требование на производство пива к цеху пивоварения. 

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

## BREWER-STEP-1 Как зарегистрированный пользователь я хочу войти в систему. Я ввожу логин и пароль. Если такой пользователь существует и пароль совпадает, я вхожу систему

Request:

```
POST /brewery/sign-in
```
```json
{
       "email" : "Korzun@gmail.com",
       "password" : "12345678"
}
```
Response: ```200 OK```

```
{
        "token" : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJdmFub3ZAZ21haWwuY29tIiwiZXhwIjoxNTgyOTUyNzE4LCJpYXQiOjE1ODI5MTY3MTh9.lJUbTDzyA_MbqvZacT5ajT1W3u82O4nMrNBv-M-B1pA
}
```

## BREWER-STEP-2 Как работник цеха пивоварения я хочу ознакомиться со списком новых требований, поступивших от отдела продаж. В результате, получаю список новых требований о производстве пива.

Request:

```
GET /brewery/brewer/requests/?status=new"
```
Response: ```200 OK```
```json
[
  {
       "date" : "02.02.2020",
       "term" : "04.02.2020",
       "status" : "NEW",
       "produceRequestItemDtos" : [
                                    {"productId" : 1,
                                     "amount" : 350.0 }
                                  ]
  },
  {
       "date" : "05.02.2020",
       "term" : "10.02.2020",
       "status" : "NEW",
       "produceRequestItemDtos" : [
                                    {"productId" : 1,
                                     "amount" : 150.0 }
                                  ]
  }
]
```

## BREWER-STEP-3 Как работник цеха пивоварения, получив список новых требований от отдела продаж, я выбираю одно для дальнейшей обработки. Для этого я получаю его.

Request:

```
GET /brewery/brewer/requests/1"
```
Response: ```200 OK```
```json
{
       "date" : "2020-02-02",
       "term" : "2020-02-04",
       "status" : "NEW",
       "produceRequestItemDtos" : [
                                    {"productId" : 1,
                                      "amount" : 350.0 }
                                  ]
}
```
## BREWER-STEP-4 Как работник цеха пивоварения, изучив новое требование от отдела продаж, я принимаю его в производство. Для этого я меняю его статус на "In_progress".

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

## BREWER-STEP-5 Как работник цеха пивоварения я хочу ознакомиться с рецептом приготовления пива из поступившего требования. Для этого я получаю рецепт приготовления данного вида пива.

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

## BREWER-STEP-6A Как работник цеха пивоварения, ознакомившись с рецептом, я хочу проверить, есть ли в наличии необходимые ингредиенты для производства пива данного вида. Для этого я получаю количество необходимого ингредиента (один), имеющееся сейчас в наличии.

Request:

```
GET /brewery/brewer/products/2
```
Response: ```200 OK```
```json
{
       "id" : 2, 
       "name" : "Water",
       "description" : "Artesian, ...",
       "amount" : 800.0,
       "unit" : "LITRE"
}
```
## BREWER-STEP-6B Как работник цеха пивоварения, ознакомившись с рецептом, я хочу проверить, есть ли в наличии необходимые ингредиенты для производства пива данного вида. Для этого я получаю количество необходимых ингредиентов (несколько), имеющееся сейчас в наличии.

Request:

```
GET /brewery/brewer/products/2
```
Response: ```200 OK```
```json
[
  {
       "id" : 2, 
       "name" : "Water",
       "description" : "Artesian, ...",
       "amount" : 800.0,
       "unit" : "LITRE"
  },
  {
       "id" : 3, 
       "name" : "Alcohol",
       "description" : "Concentration 90%, ...",
       "amount" : 100.0,
       "unit" : "LITRE"
  }
]
```

## BREWER-STEP-7 Как работник цеха пивоварения, удостоверившись в наличии необходимых ингредиентов, я запускаю производственный процесс. После того, как партия пива будет сварена, я отражаю поступление готового пива, изменяя его остатки в базе.

Request:

```
PATCH /brewery/brewer/products/1
```
```json
{
       "amount" : 250
}
```
Response: ```200 OK```

```
{
        "id" : 1
}
```

## BREWER-STEP-8 Как работник цеха пивоварения, я уведомляю отдел продаж о том, что их требование выполнено. Для этого я меняю его статус на «Completed».

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


























