<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
        <form method="post">
            <input type="text" name="title" placeholder="Введите тему сообщение" />
            <input type="text" name="text" placeholder="Введите сообщение" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <#list articles as article>
        <div>
            <b>${article.id}</b>
            <span>${article.title}</span>
            <i>${article.text}</i>
            <strong>${article.authorName}</strong>
        </div>
    <#else>
        No articles
    </#list>
</@c.page>