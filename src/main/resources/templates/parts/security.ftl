<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isActive = user.isActive()
    currentUserId = user.getId()
    >
<#else>
    <#assign
    name = "unknown"
    isActive = false
    currentUserId = -1
    >
</#if>