<!DOCTYPE html>
<html>
<head>
    <title>Future market</title>
</head>
<body>
<h1>Market</h1>
<#if userDTO.email??>
    <h3>Имя пользователя: <i>${userDTO.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
    <#if userDTO.isAdmin()>
        <a href="/admin">Панель администратора</a>
    </#if>
<#else>
    <a href="/login">Войти</a></h1>
</#if>
<hr>
<h4>Товары по всей России</h4>
<form action="/" method="get">
    Поиск по названию объявления: <input type="text" name="title"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list products as productDTO>
    <div>
        <p><b>${productDTO.title}</b> ${productDTO.price} руб. | <a href="/productDTO/${productDTO.id}">Подробнее...</a></p>
    </div>
    <#else>
    <h3>Товаров нет</h3>
</#list>
<hr>
<#if userDTO.email??>
    <hr>
    <h3>Создать новый товар</h3>
    <form action="/productDTO/create" method="post" enctype="multipart/form-data">
        Название объявления: <input type="text" name="title"/><br><br>
        Описание объявления: <input type="text" name="description"/><br><br>
        Цена: <input type="number" name="price"/><br><br>
        Город: <input type="text" name="location"/><br><br>
        1 фотография: <input type="file" name="file1"/><br><br>
        2 фотография: <input type="file" name="file2"/><br><br>
        3 фотография: <input type="file" name="file3"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить товар"/>
    </form>
</#if>
</body>
</html>