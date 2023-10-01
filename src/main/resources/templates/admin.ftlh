<!DOCTYPE html>
<html>
<head>
    <title>Market</title>
</head>
<body>
<h1>Market</h1>
<hr>
<h4>Панель администратора</h4>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Номер телефона</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Бан</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list users as userDTO>
        <tr>
            <th>${userDTO.email}</th>
            <th>${userDTO.phone}</th>
            <th><#if userDTO.active>true<#else>false</#if></th>
            <th><#list userDTO.roles as role>${role}</#list></th>
            <th>
                <form action="/admin/user/ban/${userDTO.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Бан">
                </form>
            </th>
            <th><a href="/admin/user/edit/${userDTO.id}">Редактировние</a></th>
            <th><a href="/user/${userDTO.id}">Подробная информация</a></th>
        </tr>
    <#else>
        <h3>Пользователей нет</h3>
    </#list>
</table>
</body>
</html>