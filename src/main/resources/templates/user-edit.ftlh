<!DOCTYPE html>
<html>
<head>
    <title>Market</title>
</head>
<body>
<h1>Market</h1><hr>
<h3>Редактирование пользователя ${userDTO.name}</h3>
<form action="/admin/user/edit" method="post">
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}" ${userDTO.roles?seq_contains(role)?string("checked", "")}>${role}</label>
        </div>
    </#list>
    <input type="hidden" value="${userDTO.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Сохранить</button>
</form>
</body>
</html>