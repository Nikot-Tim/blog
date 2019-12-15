<#import "login.ftl" as l>
<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/articles">Blog</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if user??>
                <li>
                    <a class="nav-link" href="/my/${currentUserId}">My articles</a>
                </li>
                <#if isAdmin>
                    <li>
                        <a class="nav-link" href="/user">User list</a>
                    </li>
                </#if>
                    <li>
                        <a class="nav-link" href="/user/profile">Profile</a>
                    </li>
            </#if>
        </ul>
        <div class="navbar-text mr-3"><#if user??>${name}</#if></div>
        <@l.logout />
    </div>

</nav>
