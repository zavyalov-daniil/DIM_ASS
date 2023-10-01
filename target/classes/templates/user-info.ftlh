<!DOCTYPE html>
<html>
<head>
    <title>Market</title>
</head>
<body>
<h1>Market</h1><hr>
<h3>Подробная информация о пользователе ${userDTO.name}</h3>
<b>Номер телефона: </b> ${userDTO.phone}<br>
<b>Email: </b> ${userDTO.email}<br>
<h4>Товары пользователя ${userDTO.name}</h4>
<#list products as productDTO>
    <div>
        <p><b>${productDTO.title}</b> ${productDTO.price} руб. | <a href="/productDTO/${productDTO.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>