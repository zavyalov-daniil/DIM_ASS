<!DOCTYPE html>
<html>
<head>
    <title>Market</title>
</head>
<body>
<h1>Market</h1><hr>
<h4>Подробная информация о товаре</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>Название товара: </b>${productDTO.title}<br>
<b>Описание: </b>${productDTO.description}<br>
<b>Цена: </b>${productDTO.price}<br>
<b>Город: </b>${productDTO.location}<br>
<b>Продавец: </b>${productDTO.user.name}<br>
<b>Номер телефона: </b>${productDTO.user.phone}<br>
</body>
</html>
