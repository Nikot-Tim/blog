<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isActive = user.isActive()
    >
<#else>
    <#assign
    name = "unknown"
    isActive = false
    >
</#if>